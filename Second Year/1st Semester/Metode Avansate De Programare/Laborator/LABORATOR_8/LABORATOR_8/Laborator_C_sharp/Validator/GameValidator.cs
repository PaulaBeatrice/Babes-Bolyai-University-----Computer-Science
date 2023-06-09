using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Exceptions;

namespace Laborator_C_sharp.Validator
{
    class GameValidator:Validator<Game>
    {
        public void validate(Game entity)
        {
            string errrorMess = "";
            if (entity.Date.CompareTo(DateTime.Today) > 0)
                errrorMess += "Invalid date!";
            if (errrorMess.Length > 0)
                throw new ValidatorException(errrorMess);
        }
    }
}
