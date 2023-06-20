using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    interface ParticipantRepository:Repository<int,Participant>
    {
        List<Participant> findByActivityOrderedDescByPoints(string Activity);
    }
}