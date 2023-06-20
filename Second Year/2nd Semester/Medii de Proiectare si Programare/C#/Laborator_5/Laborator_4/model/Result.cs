using System;

namespace model
{
    [Serializable]
    public class Result :Entity<int>
    {
        public Participant Participant { get; set; }
        public Referee Referee { get; set; }
        public int Points { get; set; }
        public string Activity { get; set; }

        public Result(int id,Participant participant, Referee referee, int points, string activity)
        {
            Id = id;
            Participant = participant;
            Referee = referee;
            Points = points;
            Activity = activity;
        }

        public int Id { get; set; }
    }
}