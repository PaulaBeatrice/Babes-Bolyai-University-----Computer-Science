using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Exceptions
{
    class ServiceException : Exception
    {
        private string mess;
        public ServiceException(string mess)
        {
            this.mess = mess;
        }
        public string GetMessage()
        {
            return this.mess;
        }
    }
}
