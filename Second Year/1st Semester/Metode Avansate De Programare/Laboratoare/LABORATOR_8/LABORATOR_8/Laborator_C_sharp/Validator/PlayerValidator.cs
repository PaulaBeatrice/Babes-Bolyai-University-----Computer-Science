using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Exceptions;

namespace Laborator_C_sharp.Validator
{
    class PlayerValidator:Validator<Player>
    {
        public void validate(Player entity)
        {
            string errormess = "";
            if (entity.Name.Length == 0)
                errormess.Concat("Student must have a name!\n");
            if (errormess.Length > 0)
                throw new ValidatorException(errormess);

        }
    }
}
