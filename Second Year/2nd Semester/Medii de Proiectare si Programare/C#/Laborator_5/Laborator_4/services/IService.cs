using System.Collections.Generic;
using model;

namespace services
{
    public interface IService
    {
        Referee login(Referee referee, IObserver client);
        List<Participant> getAllParticipants();
        IEnumerable<Result> getAllResults(); 
        List<Participant> getNotedParticipants(Referee referee); 
        List<Participant> getNotNoted(Referee referee) ; 
        Participant getPartById(int id)  ; 
        void addScore(Result result) ; 
        void logout(Referee referee, IObserver client)  ;
    }
}