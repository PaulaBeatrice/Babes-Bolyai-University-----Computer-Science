using System;
using System.Collections.Generic;
using System.Threading.Tasks;
using model;
using persistence;
using services;

namespace server
{
    public class ServiceImpl: IService
    {
        private RefereeRepository refereeRepository;
        private ParticipantRepository participantRepository;
        private ResultRepository resultRepository;
        private readonly IDictionary <int, IObserver> loggedClients;

        public ServiceImpl(RefereeRepository refRepo, ParticipantRepository partRepo, ResultRepository resRepo)
        {
            refereeRepository = refRepo;
            participantRepository = partRepo;
            resultRepository = resRepo;
            loggedClients = new Dictionary<int, IObserver>();
        }
        
        
        public Referee login(Referee referee, IObserver client)
        {
            Referee refOk = refereeRepository.FindBy(referee.Username, referee.Password);
            if (refOk != null)
            {
                if (loggedClients.ContainsKey(refOk.Id))
                    throw new Exception("Referee already logged in");
                loggedClients[refOk.Id] = client;
            }
            else
            {
                throw new Exception("Authentification failed");
            }

            return refOk;
        }

        public IEnumerable<Participant> getAllParticipants()
        {
            return participantRepository.FindAll();
        }

        public IEnumerable<Result> getAllResults()
        {
            return resultRepository.FindAll();
        }

        public List<Participant> getNotedParticipants(Referee referee)
        {
            return resultRepository.GetNotedParticipantsRepo(referee);
        }

        public List<Participant> getNotNoted(Referee referee)
        {
            return resultRepository.GetNotNotedParticipantsRepo(referee);
        }

        public Participant getPartById(int id)
        {
            return participantRepository.FindOne(id);
        }

        public void addScore(Result result)
        {
            Console.WriteLine("Adaugam scorul");
            resultRepository.Save(result);
            Participant participant = participantRepository.FindOne(result.Participant.Id);
            foreach (var referee in loggedClients.Keys)
            {
                Console.WriteLine("Referee with ID " + referee + " a primit notificare de scor nou");
                IObserver refClient = loggedClients[referee];
                Task.Run(() => refClient.notifyAddedPoints(participant));
            }
        }

        public void logout(int id, IObserver client)
        {
            IObserver localClient = loggedClients[id];
            if (localClient == null)
                throw new Exception("Referee not logged in");
            loggedClients.Remove(id);
        }
    }
}