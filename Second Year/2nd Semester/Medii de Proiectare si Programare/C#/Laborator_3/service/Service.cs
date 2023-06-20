using Laborator_1.domain;
using Laborator_1.repository;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator3.service
{
    public class Service
    {
        private ParticipantRepository participantRepository;
        private RefereeRepository refereeRepository;
        private ResultRepository resultRepository;

        public Service(ParticipantRepository participantRepository, RefereeRepository refereeRepository, ResultRepository resultRepository)
        {
            this.participantRepository = participantRepository;
            this.refereeRepository = refereeRepository;
            this.resultRepository = resultRepository;
        }

        public IEnumerable<Participant> GetParticipants() { return this.participantRepository.FindAll(); }
        public IEnumerable<Referee> GetReferees() { return this.refereeRepository.FindAll(); }
        public IEnumerable<Result> GetResults() { return this.resultRepository.FindAll(); }
        public List<Participant> getEvaluatedParticipants(Referee referee) { return resultRepository.GetEvaluatedParticipants(referee); }
        public void addScore(Result result) { resultRepository.Save(result); }
        public Participant getParticipantById(int id) { return participantRepository.FindOne(id); }

        public int isEvaluated(Participant p, Referee referee) { return resultRepository.isEvaluated(p, referee); }

        public IEnumerable<Participant> GetPartsByActivity(string activity) { return resultRepository.GetPartsByActivity(activity); }

        public void addScor(Result r) { resultRepository.Save(r); }
    }
}
