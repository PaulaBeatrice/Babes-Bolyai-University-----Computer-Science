using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Laborator_C_sharp.Domain;
namespace Laborator_C_sharp.Repository
{
    class GameRepository:InFileRepository<int,Game>
    {
        public GameRepository(string filename) : base(filename, EntityToFileMapping.CreateGame) { }
    }
}
