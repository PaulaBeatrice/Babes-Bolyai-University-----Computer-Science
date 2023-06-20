using System.Collections.Generic;
using model;

namespace persistence
{
    public interface ParticipantRepository:Repository<int,Participant>
    {
        List<Participant> getAllParticipants();
    }
}