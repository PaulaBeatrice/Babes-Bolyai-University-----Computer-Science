using System;
using System.Collections.Generic;
using System.Globalization;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_1.domain
{
    public class Result :Entity<int>
    {
        public Participant Participant { get; set; }
        public Referee Referee { get; set; }
        public int Points { get; set; }
        public string Activity { get; set; }

        public Result(Participant participant, Referee referee, int points, string activity)
        {
            Participant = participant;
            Referee = referee;
            Points = points;
            Activity = activity;
        }
    }
}
