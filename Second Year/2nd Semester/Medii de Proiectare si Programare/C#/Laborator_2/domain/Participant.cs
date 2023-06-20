using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_1.domain
{
    public class Participant : Entity<int>
    {
        public string First_Name { get; set; }
        public string Last_Name { get; set; }
        public int Points { get; set; }

        public Participant(int id,string first_Name, string last_Name, int points)
        {
            this.Id = id;
            First_Name = first_Name;
            Last_Name = last_Name;
            Points = points;
        }

        public override string ToString()
        {
            return this.First_Name + ";" + this.Last_Name + ";" + this.Points.ToString();
        }

        public int Id { get; set; }
    }
}
