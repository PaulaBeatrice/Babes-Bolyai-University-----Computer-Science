using System;
using model;

namespace persistence
{
    internal class Program
    {
        public static void Main(string[] args)
        {
            /*
            RefereeDBRepository refereeRepository = new RefereeDBRepository();
            foreach (var r in refereeRepository.FindAll())
            {
                Console.WriteLine(r.Username + " " + r.First_Name);
            }
            
            ParticipantDBRepository participantRepository = new ParticipantDBRepository();
            foreach (var part in participantRepository.FindAll())
            {
                Console.WriteLine(part.First_Name + " " + part.Last_Name);
            }
            
            Console.WriteLine(refereeRepository.FindBy("aa","ss"));
            Console.WriteLine(refereeRepository.FindBy("anonim123","abcd"));

            ResultRepository resultRepository = new ResultDBRepository(refereeRepository, participantRepository);
            foreach (var res in resultRepository.FindAll())
            {
                Console.WriteLine(res.Referee.First_Name + " " + res.Participant);
            }
            
            Console.WriteLine("NOTED: ");
            foreach(var participant in resultRepository.GetNotedParticipantsRepo(refereeRepository.FindOne(2)))
            {
                Console.WriteLine(participant.First_Name);
            }
            Console.WriteLine("NOT NOTED: " );
            foreach(var participant in resultRepository.GetNotNotedParticipantsRepo(refereeRepository.FindOne(2)))
            {
                Console.WriteLine(participant.First_Name);
            }
            */
        }
    }
}