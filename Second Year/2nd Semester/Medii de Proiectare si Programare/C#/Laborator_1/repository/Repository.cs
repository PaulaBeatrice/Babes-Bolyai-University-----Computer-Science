using Laborator_1.domain;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_1.repository
{
    public interface Repository<ID,E> where E: Entity<ID>
    {
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        void Save(E entity);
        void Delete(ID id);    
        void Update(E entity);
    }
}
