using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class Team : Entity<int>
    {
        public string Name { get; set; }
        public Team(int id, string nume)
        {
            this.Id = id;
            this.Name = nume;
        }
        public Team() { }
        public override string ToString()
        {
            return this.Id + " | " + this.Name;
        }
    }
}
