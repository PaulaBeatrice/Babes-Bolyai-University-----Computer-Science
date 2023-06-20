using System;
using System.Collections.Generic;
using model;

namespace persistence
{
    public class RepositoryException : ApplicationException
    {
        public RepositoryException() { }
        public RepositoryException(String mess) : base(mess){}
        public RepositoryException(String mess, Exception e) : base(mess, e) { }
    }
    public interface Repository<ID,E> where E: Entity<ID>
    {
        E FindOne(ID id);
        IEnumerable<E> FindAll();
        void Save(E entity);
        void Delete(ID id);    
        void Update(E entity);
    }
}