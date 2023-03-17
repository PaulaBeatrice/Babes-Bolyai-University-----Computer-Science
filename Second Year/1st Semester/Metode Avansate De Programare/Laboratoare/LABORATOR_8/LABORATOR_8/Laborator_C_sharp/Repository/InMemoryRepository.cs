using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;

namespace Laborator_C_sharp.Repository
{
    class InMemoryRepository<ID,E> : Repository<ID,E> where E : Entity<ID>
    {
        protected IDictionary<ID, E> entities = new Dictionary<ID, E>();
        public E Delete(ID id)
        {
            if (!this.entities.ContainsKey(id))
                return null;
            E entity = this.entities[id];
            this.entities.Remove(id);
            return entity;
        }
        public IEnumerable<E> FindAll()
        {
            return this.entities.Values.ToList<E>();
        }
        public E FindOne(ID id)
        {
            if(this.entities.ContainsKey(id))
                return this.entities[id];
            return null;
        }
        public E Save(E entity)
        {
            if (entity == null)
                throw new ArgumentNullException("Entity must not be null");
            if (this.entities.ContainsKey(entity.Id))
                return entity;
            this.entities[entity.Id] = entity;
            return default;
        }
        public E Update(E entity)
        {
            if(this.entities.ContainsKey(entity.Id))
            {
                this.entities[entity.Id] = entity;
                return entity; 
            }
            return null;
        }
    }
}
