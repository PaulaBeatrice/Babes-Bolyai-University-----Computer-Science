using System;
using System.Collections.Generic;
using System.Data;
using System.Runtime.InteropServices;
using Laborator_1.domain;
using log4net;

namespace Laborator_1.repository
{
    public class RefereeDBRepository:RefereeRepository
    {
        private static readonly ILog log = LogManager.GetLogger("RefereeDBRepository");
        private IDictionary<string, string> props;
        
        public RefereeDBRepository(IDictionary<string, string> props)
        {
            log.Info("Creating RefereeDBRepository"); 
            this.props = props;
        }
        
        public Referee FindOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection conn = DBUtils.getConnection(props);
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,username,password,first_name,last_name,activity  from Referees where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);

                using (var dataR = command.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        int idV = dataR.GetInt32(0);
                        String username = dataR.GetString(1);
                        String password = dataR.GetString(2);
                        String first_name = dataR.GetString(3);
                        String last_name = dataR.GetString(4);
                        String activity = dataR.GetString(5);
                        Referee referee = new Referee(id, username, password, first_name, last_name, activity);
                        log.InfoFormat("Existing findOne with value {0}", referee);
                        return referee;
                    }
                }
            }
            log.InfoFormat("Existing findOne with value {0}", null);
            return null;
        }

        public IEnumerable<Referee> FindAll()
        {
            log.Info("Find ALL Referees");
            IDbConnection conn = DBUtils.getConnection(props);
            IList<Referee> referees = new List<Referee>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select id,username,password,first_name,last_name,activity  from Referees";
                using (var dataR = command.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String username = dataR.GetString(1);
                        String password = dataR.GetString(2);
                        String first_name = dataR.GetString(3);
                        String last_name = dataR.GetString(4);
                        String activity = dataR.GetString(5);
                        Referee referee = new Referee(id, username, password, first_name, last_name, activity);
                        log.InfoFormat("Existing find Referee with value {0}", id);
                        referees.Add(referee);
                    }
                }
            }

            return referees;
        }

        public void Save(Referee entity)
        {
            var conn = DBUtils.getConnection(props);
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "insert into Referees values(@id, @username, @password, @first_name, @last_name, @activity)";
                var paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = entity.Id;
                command.Parameters.Add(paramId);
                
                var paramUsername = command.CreateParameter();
                paramUsername.ParameterName = "@username";
                paramUsername.Value = entity.Username;
                command.Parameters.Add(paramUsername);
                
                var paramPassword = command.CreateParameter();
                paramPassword.ParameterName = "@password";
                paramPassword.Value = entity.Password;
                command.Parameters.Add(paramPassword);
                
                var paramFirstName = command.CreateParameter();
                paramFirstName.ParameterName = "@first_name";
                paramFirstName.Value = entity.First_Name;
                command.Parameters.Add(paramFirstName);
                
                var paramLastName = command.CreateParameter();
                paramLastName.ParameterName = "@last_name";
                paramLastName.Value = entity.Last_Name;
                command.Parameters.Add(paramLastName);
                
                var paramActivity = command.CreateParameter();
                paramActivity.ParameterName = "@activity";
                paramActivity.Value = entity.Activity;
                command.Parameters.Add(paramActivity);

                var result = command.ExecuteNonQuery();
                if (result == 0)
                    throw new RepositoryException("No referee added!");
            }
        }

        public void Delete(int id)
        {
            IDbConnection conn = DBUtils.getConnection(props);
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "delete from Referees where id=@id";
                IDbDataParameter paramId = command.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = id;
                command.Parameters.Add(paramId);
                var dataR = command.ExecuteNonQuery();
                if (dataR == 0)
                    throw new RepositoryException("No referee deleted!");
            }
        }

        public void Update(Referee entity)
        {
            throw new System.NotImplementedException();
        }

        public List<Referee> findByActivity(string Activity)
        {
            log.InfoFormat("Entering findByActivity with value {0}", Activity);
            IDbConnection conn = DBUtils.getConnection(props);
            List<Referee> referees = new List<Referee>();
            using (var command = conn.CreateCommand())
            {
                command.CommandText = "select * from Referees where activity=@activity";
                IDbDataParameter paramActivity = command.CreateParameter();
                paramActivity.ParameterName = "@activity";
                paramActivity.Value = Activity;
                command.Parameters.Add(paramActivity);

                using (var dataR = command.ExecuteReader())
                {
                    while (dataR.Read())
                    {
                        int id = dataR.GetInt32(0);
                        String username = dataR.GetString(1);
                        String password = dataR.GetString(2);
                        String first_name = dataR.GetString(3);
                        String last_name = dataR.GetString(4);
                        String activity = dataR.GetString(5);
                        Referee referee = new Referee(id, username, password, first_name, last_name, activity);
                        log.InfoFormat("Existing findByActivity with balue {0}",Activity);
                        referees.Add(referee);
                    }
                }
            }
            log.InfoFormat("Existing findbyActivity with value {0}",Activity);
            return referees;
        }
    }
}