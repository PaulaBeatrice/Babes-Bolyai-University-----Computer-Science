using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    interface ResultRepository:Repository<int,Result>
    {
        List<Participant> getEvaluatedParticipants(Referee referee);
    }
}