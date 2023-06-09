using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Laborator_C_sharp.Domain
{
    class EntityToFileMapping
    {
        public static Team CreateTeam(string line)
        {
            string[] filds = line.Split(";");
            Team team = new Team()
            {
                Id = int.Parse(filds[0]),
                Name = filds[1]
            };
            return team;
        }
        public static Player CreatePlayer(string line)
        {
            string[] filds = line.Split(";");
            Player player = new Player()
            {
                Id = int.Parse(filds[0]),
                Name = filds[1],
                School = filds[2],
                Team = new Team(int.Parse(filds[3]),filds[4])
            };
            return player;
        }
        public static ActivePlayer CreateActivePlayer(string line)
        {
            string[] fields = line.Split(";");
            Type type;
            if (fields[3] == "Substitute") type = Type.Substitute;
            else type = Type.Participant;
            ActivePlayer activePlayer = new ActivePlayer()
            {
                Id = new Tuple<int, int>(int.Parse(fields[0]), int.Parse(fields[1])),
                PlayerID = int.Parse(fields[0]),
                GameID = int.Parse(fields[1]),
                ScoredPoints = int.Parse(fields[2]),
                Type = type
            };
            return activePlayer;
        }
        public static Game CreateGame(string line)
        {
            string[] fields = line.Split(";");
            Team firstTeam = new Team()
            {
                Id = int.Parse(fields[1]),
                Name = fields[2]
            };
            Team secondTeam = new Team()
            {
                Id = int.Parse(fields[3]),
                Name = fields[4]
            };
            Game game = new Game()
            {
                Id = int.Parse(fields[0]),
                FirstTeam = firstTeam,
                SecondTeam = secondTeam,
                Date = DateTime.ParseExact(fields[5], @"d/M/yyyy", null)
            };
            return game;
        }
    }
}
