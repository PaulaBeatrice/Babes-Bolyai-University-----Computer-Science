using System;
using System.Collections.Generic;
using System.Configuration;
using Laborator_1.domain;
using Laborator_1.repository;
using log4net.Config;

namespace Laborator2
{
    internal class Program
    {
        
        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        public static void Main(string[] args)
        {
            //configurare jurnalizare folosind log4net
            XmlConfigurator.Configure(new System.IO.FileInfo("apptest.config"));
           
           log.Info("Hello logging world!");
           Console.WriteLine("ABC");
           
            Console.WriteLine("Configuration Settings for triatlonDB {0}",GetConnectionStringByName("triatlonDB"));
            IDictionary<String, string> props = new Dictionary<string, string>();
            props.Add("ConnectionString", GetConnectionStringByName("triatlonDB"));
            
            Console.WriteLine("Referees");
            RefereeDBRepository repo = new RefereeDBRepository(props);
           // repo.Save(new Referee(5,"Anghel","aaaaaaa","Anghel","Ioan","running"));

            foreach (Referee referee in repo.FindAll())
            {
                Console.WriteLine(referee);
            }
        }
        
        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings =ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }
    }
}