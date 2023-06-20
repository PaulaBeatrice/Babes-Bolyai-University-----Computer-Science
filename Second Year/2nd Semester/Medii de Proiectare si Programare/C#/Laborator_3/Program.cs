using log4net.Config;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Linq;
using System.Threading.Tasks;
using System.Windows.Forms;
using Laborator_1.domain;
using Laborator_1.repository;
using Laborator3.service;

namespace Laborator3
{
    /*
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            Application.Run(new Form1());
        }
    }*/
    internal class Program
    {

        private static readonly log4net.ILog log = log4net.LogManager.GetLogger(System.Reflection.MethodBase.GetCurrentMethod().DeclaringType);
        public static void Main(string[] args)
        {
            //configurare jurnalizare folosind log4net
            XmlConfigurator.Configure(new System.IO.FileInfo("apptest.config"));

            log.Info("Hello logging world!");

            //  Console.WriteLine("Configuration Settings for triatlonDB {0}", GetConnectionStringByName("triatlonDB"));

            Application.SetCompatibleTextRenderingDefault(false);

            IDictionary<String, string> props = new Dictionary<string, string>();
            props.Add("ConnectionString", GetConnectionStringByName("triatlonDB"));
            RefereeDBRepository repo = new RefereeDBRepository(props);
            ParticipantDBRepository repoP = new ParticipantDBRepository(props);
            ResultDBRepository repoR = new ResultDBRepository(props, repo, repoP);
            Service srv = new Service(repoP, repo, repoR);
            Form1 main = new Form1();
            main.service = srv;

            foreach (Referee r in srv.GetReferees())
                Console.WriteLine(r.Username + ' ' + r.Password);

            Application.EnableVisualStyles();

            Application.Run(main);
        }

        static string GetConnectionStringByName(string name)
        {
            // Assume failure.
            string returnValue = null;

            // Look for the name in the connectionStrings section.
            ConnectionStringSettings settings = ConfigurationManager.ConnectionStrings[name];

            // If found, return the connection string.
            if (settings != null)
                returnValue = settings.ConnectionString;

            return returnValue;
        }

    }
}