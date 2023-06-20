﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_1.domain
{
    public class Referee : Entity<int>
    {
        public string Username { get; set; }
        public string Password { get; set; }
        public string First_Name { get; set; }
        public string Last_Name { get; set; }
        public string Activity { get; set; }
        public Referee(string username, string password, string first_name, string last_name, string activity)
        {
            this.Username = username;
            this.Password = password;
            this.First_Name = first_name;
            this.Last_Name = last_name;
            this.Activity = activity;
        }
        public override string ToString()
        {
            return this.Username + ";" + this.Password + ";" + this.First_Name + ";" + this.Last_Name + ";" + this.Activity.ToString();
        }
    }
}
