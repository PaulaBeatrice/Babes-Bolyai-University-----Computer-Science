using System.Collections.Generic;
using model;

namespace persistence
{
    public interface RefereeRepository : Repository<int, Referee>
    {
        Referee FindBy(string username, string password);
    }
}