using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class Student : Entity<int>
    {
        public string Name { get; set; }
        public string School { get; set; }
        public Student(int id, string nume, string school)
        {
            this.Id = id;
            this.Name = nume;
            this.School = school;
        }
        public Student()
        {

        }
        public override string ToString()
        {
            return this.Id + " | " + this.Name + " | " + this.School;
        }
    }
}
