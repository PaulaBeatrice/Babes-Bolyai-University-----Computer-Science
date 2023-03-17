using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;

namespace Laborator_C_sharp.Repository
{
    class DataReader
    {
        public static List<T>ReadData<T>(string filename, CreateEntity<T> createEntity)
        {
            List<T> list = new List<T>();
            using(StreamReader reader = new StreamReader(filename))
            {
                string s;
                while((s = reader.ReadLine()) != null)
                {
                    T entity = createEntity(s);
                    list.Add(entity);
                }
            }
            return list;
        }
    }
}
