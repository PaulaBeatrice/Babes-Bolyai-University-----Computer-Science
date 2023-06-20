using System;
using System.Collections.Generic;
using model;

namespace networking
{
    public interface Response{}
    
    [Serializable]
    public class OkResponse: Response{}

    [Serializable]
    public class ErrorResponse : Response
    {
        private string message;

        public ErrorResponse(string message)
        {
            this.message = message;
        }

        public virtual string Message
        {
            get
            {
                return message;
            }
        }
    }

    [Serializable]
    public class GetParticipantsResponse : Response
    {
        private List<Participant> participants;

        public GetParticipantsResponse(List<Participant> participants)
        {
            this.participants = participants;
        }

        public virtual List<Participant> Participants
        {
            get
            {
                return participants;
            }
        }
    }

    [Serializable]
    public class GetNotedParticipantsResponse : Response
    {
        private List<Participant> participants;

        public GetNotedParticipantsResponse(List<Participant> participants)
        {
            this.participants = participants;
        }

        public virtual List<Participant> Participants
        {
            get
            {
                return participants;
            }
        }
    }
    
    [Serializable]
    public class GetNotNotedParticipantsResponse : Response
    {
        private List<Participant> participants;

        public GetNotNotedParticipantsResponse(List<Participant> participants)
        {
            this.participants = participants;
        }

        public virtual List<Participant> Participants
        {
            get
            {
                return participants;
            }
        }
    }

    [Serializable]
    public class GetPartIdResponse : Response
    {
        private Participant participant;

        public GetPartIdResponse(Participant participant)
        {
            this.participant = participant;
        }

        public virtual Participant Participant
        {
            get { return participant; }
        }
    }

    [Serializable]
    public class LoginResponse : Response
    {
        private Referee referee;

        public LoginResponse(Referee referee)
        {
            this.referee = referee;
        }

        public virtual Referee Referee
        {
            get { return referee; }
        }
    }
    
    public interface UpdateResponse: Response{}

    [Serializable]
    public class NewScoreResponse : UpdateResponse
    {
        private Participant participant;

        public NewScoreResponse(Participant participant)
        {
            this.participant = participant;
        }
        public virtual Participant Participant
        {
            get
            {
                return participant;
            }
        }
    }
}