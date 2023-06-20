using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Formatters.Binary;
using System.Threading;
using model;
using services;

namespace networking
{
    public class ClientObjectWorker:IObserver
    {
        private IService server;
        private TcpClient connection;

        private NetworkStream stream;
        private IFormatter formatter;
        private volatile bool connected;

        public ClientObjectWorker(IService server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = this.connection.GetStream();
                formatter = new BinaryFormatter();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void run()
        {
            while (connected)
            {
                try
                {
                    object request = formatter.Deserialize(stream);
                    object response = handleRequest((Request)request);
                    if (response != null)
                    {
                        sendResponse((Response)response);
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }

                try
                {
                    Thread.Sleep(1000);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }

            try
            {
                stream.Close();
                connection.Close();
            }
            catch (Exception e)
            {
                Console.WriteLine("Error " + e);
            }
        }

        public void notifyAddedPoints(Participant participant)
        {
            Console.WriteLine("Score added -> " + participant);
            try
            {
                sendResponse(new NewScoreResponse(participant));
            }
            catch (Exception e)
            {
                throw new Exception("Adding error");
            }
        }

        private Response handleRequest(Request request)
        {
            Response response = null;
            if (request is LoginRequest)
            {
                Console.WriteLine("LOGIN REQUEST");
                LoginRequest logReq = (LoginRequest)request;
                Referee referee = logReq.Referee;
                try
                {
                    Referee findReferee;
                    lock (server)
                    { 
                        findReferee = server.login(referee, this);
                        Console.WriteLine("REFEREE GASIT " + findReferee.Id + findReferee.First_Name);
                    }

                    return new LoginResponse(findReferee);
                    //return new OkResponse();
                }
                catch (Exception e)
                {
                    connected = false;
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is LogoutRequest)
            {
                Console.WriteLine("LOGOUT");
                LogoutRequest logReq = (LogoutRequest)request;
                int referee = logReq.Referee;
                try
                {
                    lock (server)
                    {
                        server.logout(referee, this);
                    }

                    connected = false;
                    return new OkResponse();
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is AddScoreRequest)
            {
                Console.WriteLine("Adding Score");
                AddScoreRequest addReq = (AddScoreRequest)request;
                Result result = addReq.Result;
                try
                {
                    lock (server)
                    {
                        server.addScore(result);
                    }

                    return new OkResponse();
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetTopParticipantRequest)
            {
                Console.WriteLine("Get Participants");
                GetTopParticipantRequest getReq = (GetTopParticipantRequest)request;
                try
                {
                    IEnumerable<Participant> participants;
                    lock (server)
                    {
                        participants = server.getAllParticipants();
                    }

                    return new GetParticipantsResponse(participants);
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetNotedRequest)
            {
                Console.WriteLine("Get Noted Participants");
                GetNotedRequest getReq = (GetNotedRequest)request;
                Referee referee = getReq.Referee;
                try
                {
                    List<Participant> participants;
                    lock (server)
                    {
                        participants = server.getNotedParticipants(referee);
                    }

                    return new GetNotedParticipantsResponse(participants);
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }
            
            if (request is GetNotNotedRequest)
            {
                Console.WriteLine("Get Not Noted Participants");
                GetNotNotedRequest getReq = (GetNotNotedRequest)request;
                Referee referee = getReq.Referee;
                try
                {
                    List<Participant> participants;
                    lock (server)
                    {
                        participants = server.getNotNoted(referee);
                    }

                    return new GetNotNotedParticipantsResponse(participants);
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }

            if (request is GetPartRequest)
            {
                Console.WriteLine("Get participant by id");
                GetPartRequest getReq = (GetPartRequest)request;
                int id = getReq.IdPart;
                try
                {
                    Participant participant;
                    lock (server)
                    {
                        participant = server.getPartById(id);
                    }

                    return new GetPartIdResponse(participant);
                }
                catch (Exception e)
                {
                    return new ErrorResponse(e.Message);
                }
            }
            
            return response;

        }

        private void sendResponse(Response response)
        {
            Console.WriteLine("Sending response..." + response);
            lock (stream)
            {
                formatter.Serialize(stream, response);
                stream.Flush();
            }
        }
        
        
    }
}