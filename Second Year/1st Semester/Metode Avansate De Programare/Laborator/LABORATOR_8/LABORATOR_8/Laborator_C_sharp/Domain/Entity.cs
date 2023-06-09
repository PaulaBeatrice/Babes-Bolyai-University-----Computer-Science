using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class Entity<TID>
    {
        public TID Id { get; set; }
    }
}
