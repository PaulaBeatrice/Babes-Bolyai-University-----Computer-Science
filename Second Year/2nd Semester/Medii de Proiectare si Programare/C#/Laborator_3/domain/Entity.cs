using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_1.domain
{
    public interface Entity<ID>
    {
        ID Id { get; set; }
    }
}
