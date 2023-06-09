using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

using Laborator_C_sharp.Domain;
namespace Laborator_C_sharp.Repository
{
    class PlayerRepository : InFileRepository<int, Player>
    {
        public PlayerRepository(string filename) : base(filename, EntityToFileMapping.CreatePlayer) { }
    }
}
