using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using Laborator_C_sharp.Domain;
using Laborator_C_sharp.Repository;
using Laborator_C_sharp.Service;
using Laborator_C_sharp.Exceptions;
using Laborator_C_sharp.Validator;

namespace Laborator_C_sharp.UI
{
    internal class UI
    {
        private TeamService teamService;
        private PlayerService playerService;
        private GameService gameService;
        private ActivePlayerService activePlayerService;

        public UI()
        {
            string teamsFileName = "Teams.txt";
            Repository<int, Team> teamRepository = new TeamRepository(teamsFileName);
            Validator<Team> teamValidator = new TeamValidator();
            this.teamService = new TeamService(teamRepository, teamValidator);

            string playersFileName = "Players.txt";
            Repository<int, Player> playerRepository = new PlayerRepository(playersFileName);
            Validator<Player> playerValidator = new PlayerValidator();
            this.playerService = new PlayerService(playerRepository, playerValidator);

            string gamesFileName = "Games.txt";
            Repository<int, Game> gameRepository = new GameRepository(gamesFileName);
            Validator<Game> gameValidator = new GameValidator();
            this.gameService = new GameService(gameRepository, gameValidator);

            string activePlayersFileName = "ActivePlayers.txt";
            Repository<Tuple<int, int>, ActivePlayer> activePlayerRepository = new ActivePlayerRepository(activePlayersFileName);
            Validator<ActivePlayer> activePlayerValidator = new ActivePlayerValidator();
            this.activePlayerService = new ActivePlayerService(activePlayerRepository, playerRepository, activePlayerValidator);

        }
        private void DisplayMenu()
        {
            Console.WriteLine("***************************************************");
            Console.WriteLine("1. Display players from a team.");
            Console.WriteLine("2. Display active players from team from a game.");
            Console.WriteLine("3. Display all games form certain time period.");
            Console.WriteLine("4. Display the score from a game.");
            Console.WriteLine("0. Exit program.");
            Console.WriteLine("***************************************************"); ;
        }
        public void Strart()
        {
            while(true)
            {
                this.DisplayMenu();
                Console.Write(">>>");
                string input = Console.ReadLine();
                switch (input)
                {
                    case "0": { return; }
                    case "1": { this.GetPlayersFromTeam(); break; }
                    case "2": { this.GetActivePlayersFromGame(); break; }
                    case "3": { this.GetGamesFromTimePeriod(); break; }
                    case "4": { this.GetScoreOfGame(); break; }
                    default: { Console.WriteLine("Invalid command!"); break; }
                }
            }
        }
        private void GetPlayersFromTeam()
        {
            Console.WriteLine("Give a team: ");
            string input = Console.ReadLine();
            try
            {
                Team team = this.teamService.GetTeamByName(input);
                List<Player> players = (List<Player>)this.playerService.GetAllPlayersByTeam(team);
                players.ForEach(x => Console.WriteLine("ID = " + x.Id + " | Name = " + x.Name));
            }
            catch (ServiceException ex)
            {
                Console.WriteLine(ex.GetMessage());
            }
        }
        private void GetActivePlayersFromGame()
        {
            Console.WriteLine("Give a game: ");
            string inputgame = Console.ReadLine();
            Console.WriteLine("Give a team: ");
            string inputteam = Console.ReadLine();
            try
            {
                Team team = this.teamService.GetTeamByName(inputteam);
                string[] gameNames = inputgame.Split(" vs. ");
                Team firstteam = this.teamService.GetTeamByName(gameNames[0]);
                Team secondteam = this.teamService.GetTeamByName(gameNames[1]);
                Game game = this.gameService.GetGameByTeams(firstteam, secondteam);
                List<ActivePlayer> activeplayers = this.activePlayerService.GetAllActivePlayersFromGameAndTeam(game, team).ToList();
                activeplayers.ForEach(x => Console.WriteLine(this.playerService.GetById(x.PlayerID).Name + " | " + x.ScoredPoints + " scored points | " + x.Type.ToString()));
            }
            catch(ServiceException ex)
            {
                Console.WriteLine(ex.GetMessage());
            }
            catch (InputException ex)
            { 
                Console.WriteLine(ex.GetMessage());
            }

        }
        private void GetGamesFromTimePeriod()
        {
            Console.Write("First date: ");
            string firstDateInput = Console.ReadLine();
            Console.Write("Second date: ");
            string secondDateInput = Console.ReadLine();


            bool parse1 = DateTime.TryParse(firstDateInput, out DateTime firstDate);
            bool parse2 = DateTime.TryParse(secondDateInput, out DateTime secondDate);

            if (!parse1 || !parse2)
            {
                Console.WriteLine("Invalid date format!(mm/dd/yyyy).");
                return;
            }
            if (firstDate.CompareTo(secondDate) > 0)
            {
                Console.WriteLine("First date can't be after the second date!");
                return;
            }

            List<Game> games = this.gameService.GetAllGamesFromPeriod(firstDate, secondDate).ToList();
            games.ForEach(Console.WriteLine);
        }
        private void GetScoreOfGame()
        {
            Console.Write("Give a game: ");
            string inputGame = Console.ReadLine();

            try
            {
                string[] gameNames = inputGame.Split(" vs. ");

                Team firstTeam = this.teamService.GetTeamByName(gameNames[0]);
                Team secondTeam = this.teamService.GetTeamByName(gameNames[1]);

                Game game = this.gameService.GetGameByTeams(firstTeam, secondTeam);

                List<ActivePlayer> activePlayers1 = this.activePlayerService.GetAllActivePlayersFromGameAndTeam(game, firstTeam).ToList();
                List<ActivePlayer> activePlayers2 = this.activePlayerService.GetAllActivePlayersFromGameAndTeam(game, secondTeam).ToList();

                Tuple<int, int> score = this.gameService.ComputeScore(game, activePlayers1, activePlayers2);

                Console.WriteLine("[" + firstTeam.Name + "] " + score.Item1 + " - " + score.Item2 + " [" + secondTeam.Name + "]");
            }
            catch (ServiceException e)
            {
                Console.WriteLine(e.GetMessage());
            }
            catch (InputException e)
            {
                Console.WriteLine(e.GetMessage());
            }
        }
        private void validateGameInput(string[] input)
        {
            if (input.Length != 3)
                throw new InputException("The game must be of form Team 1 vs. Team 2");

            if (input[1] != "vs.")
                throw new InputException("The game must be of form Team 1 vs. Team 2");
        }
    }

}

