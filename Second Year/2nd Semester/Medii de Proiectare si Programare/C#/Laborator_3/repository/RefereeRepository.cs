using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    public interface RefereeRepository : Repository<int, Referee>
    {
        List<Referee> FindByActivity(string Activity);
    }
}