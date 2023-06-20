using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;

namespace persistence
{
    public class ParticipantDBRepository:ParticipantRepository
    {
        private static readonly ILog log = LogManager.GetLogger("ParticipantDBRepository");

        public ParticipantDBRepository()
        {
            log.Info("Creating ParticipantDBRepository");
        }
        
        public Participant FindOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,first_name,last_name,points  from Participants where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);

                using (var dataR = command.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String first_name = dataR.GetString(1);
                        String last_name = dataR.GetString(2);
                        int points = dataR.GetInt32(3);
                        Participant participant = new Participant(idV, first_name, last_name, points);
                        log.InfoFormat("Existing findOne with value {0}", participant);
                        return participant;
                    }
                }
            }
            log.InfoFormat("Existing findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Participant> FindAll()
        {
            IDbConnection conn = DBUtils.getConnection();
            IList<Participant> participants = new List<Participant>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,first_name,last_name,points  from Participants";
                using (var dataR = command.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String first_name = dataR.GetString(1);
                        String last_name = dataR.GetString(2);
                        int points = dataR.GetInt32(3);
                        Participant participant = new Participant(id, first_name, last_name, points);
                        participants.Add(participant);
                    }
                }
            }
            return participants;
        }

        public void Save(Participant entity)
        {
            var conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "insert into Participants(first_name,last_name,points) values(@first_name, @last_name, @points)";

                var paramFirstName = command.CreateParameter();
                paramFirstName.ParameterName = "@first_name";
                paramFirstName.Value = entity.First_Name;
                command.Parameters.Add(paramFirstName);
                
                var paramLastName = command.CreateParameter();
                paramLastName.ParameterName = "@last_name";
                paramLastName.Value = entity.Last_Name;
                command.Parameters.Add(paramLastName);
                
                var paramPoints = command.CreateParameter();
                paramPoints.ParameterName = "@points";
                paramPoints.Value = entity.Points;
                command.Parameters.Add(paramPoints);

                var result = command.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No participant added!");
            }
        }

        public void Delete(int id)
        {
            IDbConnection conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "delete from Participants where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);
                var dataR = command.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No participant deleted!");
            }
        }

        public void Update(Participant entity)
        {
            var conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "update Participants set points = @points where id = @id";
                var paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = entity.Id;
                command.Parameters.Add(paramId);

                // var paramFirstName = command.CreateParameter();
                // paramFirstName.ParameterName = "@first_name";
                // paramFirstName.Value = entity.First_Name;
                // command.Parameters.Add(paramFirstName);
                //
                // var paramLastName = command.CreateParameter();
                // paramLastName.ParameterName = "@last_name";
                // paramLastName.Value = entity.Last_Name;
                // command.Parameters.Add(paramLastName);
                //
                var paramPoints = command.CreateParameter();
                paramPoints.ParameterName = "@points";
                paramPoints.Value = entity.Points;
                command.Parameters.Add(paramPoints);

                var result = command.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No participant updated!");
            }
        }

        public List<Participant> getAllParticipants()
        {
             List<Participant> participantList = new List<Participant>();
             foreach (Participant p in this.FindAll())
             {
                 participantList.Add((p));
             }
             return participantList;
        }
    }
}