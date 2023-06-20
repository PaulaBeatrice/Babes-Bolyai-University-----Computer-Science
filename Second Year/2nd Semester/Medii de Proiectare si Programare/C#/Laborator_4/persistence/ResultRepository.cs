using System.Collections.Generic;
using model;

namespace persistence
{
    public interface ResultRepository:Repository<int,Result>
    {
        List<Participant> GetNotedParticipantsRepo(Referee referee);
        List<Participant> GetNotNotedParticipantsRepo(Referee referee);
    }
}