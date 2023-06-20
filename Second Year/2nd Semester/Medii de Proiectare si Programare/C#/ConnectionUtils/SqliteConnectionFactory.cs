using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SQLite;

namespace ConnectionUtils
{
    public class SqliteConnectionFactory:ConnectionFactory
    {
        public override IDbConnection createConnection(IDictionary<string, string> props)
        {
            //Windows Sqlite Connection, fisierul .db ar trebuie sa fie in directorul debug/bin
            String connectionString = "Data Source=C:\\FACULTATE\\Anul 2\\Semestrul 2\\Medii de Proiectare si Programare\\Laboratoare\\mpp-proiect-java-PaulaBeatrice\\triatlon.db;Version=3";
            return new SQLiteConnection(connectionString);
        }
    }
}