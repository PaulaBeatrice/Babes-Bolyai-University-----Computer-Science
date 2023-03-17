using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Validator
{
    interface Validator<E>
    {
        public void validate(E entity);
    }
}
