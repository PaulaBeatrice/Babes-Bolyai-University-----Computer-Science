using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Exceptions;

namespace Laborator_C_sharp.Validator
{
    class TeamValidator:Validator<Team>
    {
        public void validate(Team entity)
        {
            if (entity.Name.Length == 0)
                throw new ValidatorException("Team must have a name!\n");
        }
    }
}
