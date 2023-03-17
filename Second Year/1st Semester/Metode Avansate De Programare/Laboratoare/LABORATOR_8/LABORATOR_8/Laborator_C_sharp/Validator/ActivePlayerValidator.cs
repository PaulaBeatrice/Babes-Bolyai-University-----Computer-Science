using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Exceptions;

namespace Laborator_C_sharp.Validator
{
    class ActivePlayerValidator:Validator<ActivePlayer>
    {
        public void validate(ActivePlayer entity)
        {
            if (entity.ScoredPoints < 0)
                throw new ValidatorException("Invalid numer of points!");
        }
    }
}
