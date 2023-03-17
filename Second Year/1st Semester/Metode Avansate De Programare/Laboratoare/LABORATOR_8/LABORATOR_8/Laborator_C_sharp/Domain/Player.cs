using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class Player : Student
    {
        public Team Team { get; set; }
        public Player(int id, string nume, string school, Team team)
        {
            this.Id = id;
            this.Name = nume;
            this.School = school;
            this.Team = team;
        }
        public Player() { }
        public override string ToString()
        {
            return base.ToString() + " | " + this.Team.Name;
        }
    }
}
