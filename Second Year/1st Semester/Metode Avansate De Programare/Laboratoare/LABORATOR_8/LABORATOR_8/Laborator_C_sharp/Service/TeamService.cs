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
    class TeamService
    {
        private Repository<int,Team> teamRepository;
        private Validator<Team> teamValidator;
        public TeamService(Repository<int,Team>teamRepo,Validator<Team>teamVal)
        {
            this.teamRepository = teamRepo;
            this.teamValidator = teamVal;
        }
        public Team Save(Team entity)
        {
            this.teamValidator.validate(entity);
            return this.teamRepository.Save(entity);
        }
        public Team GetTeamByName(string nume)
        {
            List<Team> teams = this.teamRepository.FindAll().ToList();
            var result = teams.Where(t => t.Name.Equals(nume));
            Team resultTeam = result.FirstOrDefault();
            if (resultTeam == null)
                throw new ServiceException("There is no team named " + nume);
            return resultTeam;
        }
        public IEnumerable<Team>GetAll()
        {
            return this.teamRepository.FindAll().ToList();
        }
    }
}
