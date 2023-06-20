using System;
using System.Collections.Generic;
using System.Data;
using System.Runtime.CompilerServices;
using Laborator_1.domain;
using log4net;

namespace Laborator_1.repository
{
    public class ResultDBRepository:ResultRepository
    {
        private static readonly ILog log = LogManager.GetLogger("ResultDBRepository");
        private IDictionary<string, string> props;
        private RefereeRepository refereeRepository;
        private ParticipantRepository participantRepository;
        
        public ResultDBRepository(IDictionary<string, string> props, RefereeDBRepository refereeDbRepository, ParticipantDBRepository participantDbRepository)
        {
            log.Info("Creating ResultDBRepository");
            this.props = props;
            refereeRepository = refereeDbRepository;
            participantRepository = participantDbRepository;
        }
        
        public Result FindOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection conn = DBUtils.getConnection(props);
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
            IDbConnection conn = DBUtils.getConnection(props);
            IList<Result> results = new List<Result>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,id_referee,id_participant,points,activity from Results";

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
                        results.Add(result);
                    }
                }
            }

            return results;
        }

        public void Save(Result entity)
        {
            var conn = DBUtils.getConnection(props);
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "insert into Results values(@id, @id_ref, @id_part, @points, @activity)";
                var paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = entity.Id;
                command.Parameters.Add(paramId);
                
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
            IDbConnection conn = DBUtils.getConnection(props);
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
            var conn = DBUtils.getConnection(props);
            int old_points = this.participantRepository.FindOne(entity.Participant.Id).Points; // puntajul vechi
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

        public List<Participant> getEvaluatedParticipants(Referee referee)
        {
            log.InfoFormat("Entering getEvaluatedParticipants with value {0}", referee);
            IDbConnection conn = DBUtils.getConnection(props);
            List<Participant> participants = new List<Participant>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id_partipant from Results where id_referee=@ref";
                IDbDataParameter paramRef = command.CreateParameter();
                paramRef.ParameterName = "@ref";
                paramRef.Value = referee.Id;
                command.Parameters.Add(paramRef);

                using (var dataR = command.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int idP = dataR.GetInt32(0);
                        Participant participant = participantRepository.FindOne(idP);
                        log.InfoFormat("Existing getEvaluatedParticipants with value {0}",referee);
                        participants.Add(participant);
                    }
                }
            }
            log.InfoFormat("Existing getEvaluatedParticipants with value {0}",referee);
            return participants;
        }
    }
}