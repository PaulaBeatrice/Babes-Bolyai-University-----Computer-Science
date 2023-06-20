using System;
using model;

namespace networking
{
    public interface Request
    {
    }

    [Serializable]
    public class LoginRequest : Request
    {
        private Referee referee;
        public LoginRequest(Referee referee)
        {
            this.referee = referee;
        }
        public virtual Referee Referee
        {
            get { return referee; }
        }
    }


    [Serializable]
    public class LogoutRequest : Request
    {
        private Referee referee;
        public LogoutRequest(Referee referee)
        {
            this.referee = referee;
        }
        public virtual Referee Referee
        {
            get { return referee; }
        }
    }
    
    [Serializable]
    public class GetTopParticipantRequest: Request {}

    [Serializable]
    public class GetNotedRequest : Request
    {
        private Referee referee;
        public GetNotedRequest(Referee referee)
        {
            this.referee = referee;
        }

        public virtual Referee Referee
        {
            get
            {
                return referee;
            }
        }
    }
    
    [Serializable]
    public class GetNotNotedRequest : Request
    {
        private Referee referee;
        public GetNotNotedRequest(Referee referee)
        {
            this.referee = referee;
        }

        public virtual Referee Referee
        {
            get
            {
                return referee;
            }
        }
    }

    [Serializable]
    public class AddScoreRequest : Request
    {
        private Result result;
        public AddScoreRequest(Result result)
        {
            this.result = result;
        }

        public virtual Result Result
        {
            get { return result; }
        }
    }

    [Serializable]
    public class GetPartRequest : Request
    {
        private int idPart;

        public GetPartRequest(int id)
        {
            this.idPart = id;
        }

        public virtual int IdPart
        {
            get { return idPart; }
        }
    }

}