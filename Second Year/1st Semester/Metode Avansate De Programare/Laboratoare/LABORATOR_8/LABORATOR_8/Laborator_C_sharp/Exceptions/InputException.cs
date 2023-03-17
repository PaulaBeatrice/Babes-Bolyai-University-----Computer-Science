using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Exceptions
{
    class InputException : Exception
    {
        private string mess;
        public InputException(string mess)
        {
            this.mess = mess;
        }
        public string GetMessage()
        {
            return this.mess;
        }
    }
}
