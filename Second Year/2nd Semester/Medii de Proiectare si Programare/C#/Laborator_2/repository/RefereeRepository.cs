using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    interface RefereeRepository : Repository<int, Referee>
    {
        List<Referee> findByActivity(string Activity);
    }
}