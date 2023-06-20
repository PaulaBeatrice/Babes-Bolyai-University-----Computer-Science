using System.Collections.Generic;
using Laborator_1.domain;

namespace Laborator_1.repository
{
    public interface ResultRepository:Repository<int,Result>
    {
        List<Participant> GetEvaluatedParticipants(Referee referee);

        int isEvaluated(Participant participant, Referee referee);

        IEnumerable<Participant> GetPartsByActivity(string activity);
    }
}