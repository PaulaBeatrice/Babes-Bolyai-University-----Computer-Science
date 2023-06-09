using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    enum Type
    {
        Substitute, Participant
    }
    class ActivePlayer : Entity<Tuple<int,int>>
    {
        public int PlayerID { get; set; }
        public int GameID { get; set; }
        public int ScoredPoints { get; set; }
        public Type Type { get; set; }
        public ActivePlayer(int playerId, int gameId, int p, Type type)
        {
            this.PlayerID = playerId;
            this.GameID = gameId;
            this.ScoredPoints = p;
            this.Type = type;
        }
        public ActivePlayer() { }
        public override string ToString()
        {
            return "Player ID: " + this.PlayerID + " | Game ID: " + this.GameID + " | Points: " + this.ScoredPoints + " | Type: " + this.Type;
        }
    }
}
