using System;
using System.Collections.Generic;
using System.Windows.Forms;
using model;
using services;

namespace client
{
    public class ClientCtrl:IObserver
    {
        public event EventHandler<UserEventArgs> updateEvent;
        private readonly IService server;
        private Referee currentReferee;

        public ClientCtrl(IService server)
        {
            this.server = server;
            currentReferee = null;
        }

        public void login(String user, String password)
        {
            Referee referee = new Referee(0, user, password, "a", "a", "a");
            Referee currentRef = server.login(referee, this);
            Console.WriteLine("LOGIN SUCCEDED ...");
            currentReferee = currentRef;
            Console.WriteLine("Current User {0}", currentRef);
            
        }

        public void notifyAddedPoints(Participant participant)
        {
            Console.WriteLine("Adding score to: " + participant.First_Name);
            UserEventArgs userEventArgs = new UserEventArgs(UserEvent.NewScore, participant);
            Console.WriteLine("Score added");
            OnUserEvent(userEventArgs);
        }

        protected virtual void OnUserEvent(UserEventArgs e)
        {
            if (updateEvent == null) return;
            updateEvent(this, e);
            Console.WriteLine("Update Event Called");
        }

        public void logout()
        {
            Console.WriteLine("Ctrl logout");
            server.logout(currentReferee.Id,this);
            currentReferee = null;
        }

        public IEnumerable<Participant> getParts()
        {
            IEnumerable<Participant> participants = server.getAllParticipants();
            return participants;
        }

        public List<Participant> getNoted()
        {
            return server.getNotedParticipants(currentReferee);
        }
        
        public List<Participant> getNotNoted()
        {
            return server.getNotNoted(currentReferee);
        }

        public void addScore(Participant participant, int points)
        {
            Result result = new Result(0, participant, currentReferee, points, currentReferee.Activity);
            Console.WriteLine(result.Referee.First_Name + "-->" + result.Participant.First_Name + " ==" + result.Points);
            UserEventArgs userEventArgs = new UserEventArgs(UserEvent.NewScore, participant);
            OnUserEvent(userEventArgs);
            server.addScore(result);
        }
    }
}