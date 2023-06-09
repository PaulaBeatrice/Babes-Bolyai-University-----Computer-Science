using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;

namespace Laborator_C_sharp.Repository
{
     class TeamRepository:InFileRepository<int,Team>
    {
        public TeamRepository(string filename) : base(filename, EntityToFileMapping.CreateTeam) { }
    }
}
