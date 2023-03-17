using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class Game : Entity<int>
    {
        public Team FirstTeam { get; set; }
        public Team SecondTeam { get; set; }
        public DateTime Date { get; set; }
        public Game(int id, Team firstTeam, Team secondTeam)
        {
            this.Id = id;
            this.FirstTeam = firstTeam;
            this.SecondTeam = secondTeam;
        }
        public Game() { }
        public override string ToString()
        {
            return this.FirstTeam.Name + " vs. " + this.SecondTeam.Name + " | " + this.Date.ToString();
        }
    }
}
