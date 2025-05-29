using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Sem11_12.Model
{
    enum KnowledgeLevel
    {
        Junior=1, Medium, Senior
    }
    class Angajat : Entity<string> , ICloneable
    {
        public String Nume { get; set; }
        public double VenitPeOra { get; set; }
        public KnowledgeLevel Nivel { get; set; }

        public object Clone()
        {
            throw new NotImplementedException();
        }

        public override string ToString()
        {
            return ID+" "+Nume+" "+VenitPeOra+" "+Nivel;
        }

        public override bool Equals(object obj)
        {
            if (obj == null) return false;
            if(obj is Angajat)
            {
                return (obj as Angajat).ID == ID;
            }
            return base.Equals(obj);
        }

        public static bool operator == (Angajat a1, Angajat a2) 
        {
            if (a1 is null) return a2 is null;
            return a1.Equals(a2); 
        }
        public static bool operator != (Angajat a1, Angajat a2) 
        {
            if (a1 is null) return a2 is not null;
            return !a1.Equals(a2); 
        }
    }
}
