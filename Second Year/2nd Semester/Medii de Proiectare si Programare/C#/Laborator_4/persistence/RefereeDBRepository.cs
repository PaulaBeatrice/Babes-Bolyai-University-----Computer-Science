using System;
using System.Collections.Generic;
using System.Data;
using log4net;
using model;

namespace persistence
{
    public class RefereeDBRepository : RefereeRepository
    {
        private static readonly ILog log = LogManager.GetLogger("RefereeDBRepository");

        public RefereeDBRepository()
        {
            log.Info("Creating RefereeDBRepository");
        }

        public Referee FindOne(int id)
        {
            log.InfoFormat("Entering findOne with value {0}", id);
            IDbConnection conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "select id,username,password,first_name,last_name,activity  from Referees where id=@id";
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
            IDbConnection conn = DBUtils.getConnection();
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
            var conn = DBUtils.getConnection();
            using (var command = conn.CreateCommand())
            {
                command.CommandText =
                    "insert into Referees(username,password,first_name,last_name, activity) values(@username, @password, @first_name, @last_name, @activity)";

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
            IDbConnection conn = DBUtils.getConnection();
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

        public Referee FindBy(string username, string pass)
        {
            log.InfoFormat("Entering FindBy with value {0}", username);
            IDbConnection con = DBUtils.getConnection();

            using (var comm = con.CreateCommand())
            {
                comm.CommandText = "select id from Referees where username=@id and password=@pass";
                var paramId = comm.CreateParameter();
                paramId.ParameterName = "@id";
                paramId.Value = username;
                comm.Parameters.Add(paramId);
                var paramPass = comm.CreateParameter();
                paramPass.ParameterName = "@pass";
                paramPass.Value = pass;
                comm.Parameters.Add(paramPass);
                using (var dataR = comm.ExecuteReader())
                {
                    if (dataR.Read())
                    {
                        Referee referee = this.FindOne(dataR.GetInt32(0));
                        return referee;
                    }
                }
            }
            return null;
        }
    }
}