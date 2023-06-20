using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    public interface ParticipantRepository:Repository<int,Participant>
    {
        List<Participant> FindByActivityOrderedDescByPoints(string Activity);
    }
}