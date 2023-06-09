using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Repository;
using Laborator_C_sharp.Exceptions;
using Laborator_C_sharp.Validator;

namespace Laborator_C_sharp.Service
{
    class GameService
    {
        private Repository<int, Game> gameRepo;
        private Validator<Game> gameValidator;
        public GameService(Repository<int,Game>gameRepo,Validator<Game>gameValidator)
        {
            this.gameRepo = gameRepo;
            this.gameValidator = gameValidator;
        }
        public Game GetGameByTeams(Team firstteam, Team secondteam)
        {
            List<Game> games = this.GetAll().ToList();
            var result = games.Where(g => g.FirstTeam.Name.Equals(firstteam.Name) &&
            g.SecondTeam.Name.Equals(secondteam.Name) ||
            g.FirstTeam.Name.Equals(secondteam.Name) &&
            g.SecondTeam.Name.Equals(firstteam.Name));
            Game resultGame = result.FirstOrDefault();
            if (resultGame == null)
                throw new ServiceException("Can't find this game!");
            return resultGame;
        }
        public IEnumerable<Game>GetAllGamesFromPeriod(DateTime d1, DateTime d2)
        {
            List<Game> games = this.GetAll().ToList();
            var result = games.Where(g => g.Date >= d1 && g.Date <= d2);
            return result.ToList();
        }
        public Tuple<int, int> ComputeScore(Game game, List<ActivePlayer> activePlayers1, List<ActivePlayer> activePlayers2)
        {
            int firstTeamScore = (from a in activePlayers1 select a.ScoredPoints).Sum();
            int secontTeamScore = (from a in activePlayers2 select a.ScoredPoints).Sum();

            return new Tuple<int, int>(firstTeamScore, secontTeamScore);
        }
        public IEnumerable<Game>GetAll()
        {
            return this.gameRepo.FindAll().ToList();
        }

    }
}
