using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using log4net;
using model;

namespace persistence
{
     public class ResultDBRepository:ResultRepository
    {
        private static readonly ILog log = LogManager.GetLogger("ResultDBRepository");
        private RefereeRepository refereeRepository;
        private ParticipantRepository participantRepository;
        
        public ResultDBRepository(RefereeDBRepository refereeDbRepository, ParticipantDBRepository participantDbRepository)
        {
            log.Info("Creating ResultDBRepository");
            refereeRepository = refereeDbRepository;
            participantRepository = participantDbRepository;
        }
        
        public Result FindOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,id_referee,id_participant,points,activity from Results where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);

                using (var dataR = command.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        int idR = dataR.GetInt32(1);
                        Referee referee = refereeRepository.FindOne(idR);
                        int idP = dataR.GetInt32(2);
                        Participant participant = participantRepository.FindOne(idP);
                        int points = dataR.GetInt32(3);
                        string activity = dataR.GetString(4);
                        Result result = new Result(idV, participant, referee, points, activity);
                        log.InfoFormat("Existing findOne with value {0}", result);
                        return result;
                    }
                }
            }
            log.InfoFormat("Existing findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Result> FindAll()
        {
            log.Info("Find all Results");
            IDbConnection conn = DBUtils.getConnection();
            IList<Result> results = new List<Result>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,id_referee,id_participant,points,activity from Results";

                using (var dataR = command.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        int idR = dataR.GetInt32(1);
                        Referee referee = refereeRepository.FindOne(idR);
                        int idP = dataR.GetInt32(2);
                        Participant participant = participantRepository.FindOne(idP);
                        int points = dataR.GetInt32(3);
                        string activity = dataR.GetString(4);
                        Result result = new Result(idV, participant, referee, points, activity);
                        results.Add(result);
                    }
                }

                return results;
            }
        }

        public void Save(Result entity)
        {
            var conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "insert into Results(id_referee, id_participant,points,activity) values(@id_ref, @id_part, @points, @activity)";
                //var paramId = command.CreateParameter();
                //paramId.ParameterName = "@id";
                //paramId.Value = entity.Id;
                //command.Parameters.Add(paramId);
                
                var paramIdR = command.CreateParameter();
                paramIdR.ParameterName = "@id_ref";
                paramIdR.Value = entity.Referee.Id;
                command.Parameters.Add(paramIdR);
                
                var paramIdP = command.CreateParameter();
                paramIdP.ParameterName = "@id_part";
                paramIdP.Value = entity.Participant.Id;
                command.Parameters.Add(paramIdP);

                var paramActivity = command.CreateParameter();
                paramActivity.ParameterName = "@activity";
                paramActivity.Value = entity.Activity;
                command.Parameters.Add(paramActivity);

                var paramPoints = command.CreateParameter();
                paramPoints.ParameterName = "@points";
                paramPoints.Value = entity.Points;
                command.Parameters.Add(paramPoints);

                var result = command.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No result added!");
                
                // Actualizarea punctajului participantului
                int new_points = entity.Participant.Points + entity.Points;
                Participant new_participant = new Participant(entity.Participant.Id,entity.Participant.First_Name,entity.Participant.Last_Name,new_points);
                participantRepository.Update(new_participant);
            }
        }

        public void Delete(int id)
        {
            IDbConnection conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "delete from Results where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);
                var dataR = command.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No referee deleted!");
                Result entity = FindOne(id);
                int new_points = entity.Participant.Points -  entity.Points;
                Participant new_participant = new Participant(entity.Participant.Id,entity.Participant.First_Name,entity.Participant.Last_Name,new_points);
                this.participantRepository.Update(new_participant);
            }
        }

        public void Update(Result entity)
        {
            var conn = DBUtils.getConnection();
            int old_points = this.participantRepository.FindOne(entity.Participant.Id).Points; // punctajul vechi
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "update Results set id_referee = @id_ref, id_participant = @id_part, points = @points, activity = @activity where id = @id)";
                var paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = entity.Id;
                command.Parameters.Add(paramId);

                var paramR = command.CreateParameter();
                paramR.ParameterName = "@id_ref";
                paramR.Value = entity.Referee.Id;
                command.Parameters.Add(paramR);

                var paramP = command.CreateParameter();
                paramP.ParameterName = "@id_part";
                paramP.Value = entity.Participant.Id;
                command.Parameters.Add(paramP);
                
                var paramPoints = command.CreateParameter();
                paramPoints.ParameterName = "@points";
                paramPoints.Value = entity.Points;
                command.Parameters.Add(paramPoints);

                var paramActivity = command.CreateParameter();
                paramActivity.ParameterName = "@activity";
                paramActivity.Value = entity.Activity;
                command.Parameters.Add(paramActivity);

                var result = command.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No result updated!");
                
                //actualizam punctajul participantului
                int new_points = entity.Participant.Points - old_points + entity.Points;
                Participant new_participant = new Participant(entity.Participant.Id,entity.Participant.First_Name,entity.Participant.Last_Name,new_points);
                this.participantRepository.Update(new_participant);
            }
        }


        public List<Participant> GetNotedParticipantsRepo(Referee referee) 
        {
            IDbConnection con = DBUtils.getConnection();
            List<Participant> participantList = new List<Participant>();
            try 
            {
                using (var preSmt = con.CreateCommand()) 
                {
                    preSmt.CommandText = "select * from Results where id_referee=@id";
                    var paramId = preSmt.CreateParameter();
                    paramId.ParameterName = "@id";
                    paramId.Value = referee.Id;
                    preSmt.Parameters.Add(paramId);
                    
                    using (var resultSet = preSmt.ExecuteReader()) 
                    {
                        while (resultSet.Read()) 
                        {
                            int id_part = resultSet.GetInt32(2);
                            Participant participant = this.participantRepository.FindOne(id_part);
                            Participant new_part = new Participant(participant.Id, participant.First_Name, participant.Last_Name, resultSet.GetInt32(3));
                            participantList.Add(new_part);
                        }
                    }
                }
            } 
            catch (SqlException e) 
            {
                Console.Error.WriteLine("Error DB " + e);
            }
            return participantList;
        }


        public List<Participant> GetNotNotedParticipantsRepo(Referee referee) 
        {
            List<Participant> participantList = new List<Participant>();
            foreach (Participant p in participantRepository.FindAll()) 
            {
                // for each participant, check if there is a result with participant p and referee r
                // if not, add it to the list
                bool found = false;
                foreach (Result r in FindAll()) 
                {
                    if (r.Participant.Id == p.Id && r.Referee.Id == referee.Id) 
                    {
                        found = true;
                        break;
                    }
                }
                if (!found) 
                {
                    participantList.Add(p);
                }
            }
            return participantList;
        }
    }
}