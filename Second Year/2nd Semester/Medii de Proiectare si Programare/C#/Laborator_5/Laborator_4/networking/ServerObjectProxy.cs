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
    public class ServerObjectProxy:IService
    {
        private string host;
        private int port;

        private IObserver client;

        private NetworkStream stream;

        private IFormatter formatter;
        private TcpClient connection;

        private Queue<Response> responses;
        private volatile bool finished;
        private EventWaitHandle waitHandle;

        public ServerObjectProxy(string host, int port)
        {
            this.host = host;
            this.port = port;
            responses = new Queue<Response>();
        }



        public virtual Referee login(Referee referee, IObserver client)
        {
            Console.WriteLine("SE INITIALIZEAZA CONEXIUNEA LA SERVER");
            initializeConnection();
           // Console.WriteLine("SE TRIMITE CERERE DE LOGIN");
            sendRequest(new LoginRequest(referee));
           // Console.WriteLine("SE CITESTE RASPUNSUL DE LA SV");
            Response response = readResponse();
            // Console.WriteLine("VERIFICAM RASPUNSUL");
            if (response is LoginResponse)
            {
                this.client = client;
                LoginResponse resp = (LoginResponse)response;
                Referee returnedReferee = resp.Referee;
                Console.WriteLine("LOGIN RETURNED " + returnedReferee.First_Name + " " + returnedReferee.Id);
                return returnedReferee;
            }

            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                closeConnection();
                throw new Exception(err.Message);
            }

            return referee;
        }

        public List<Participant> getAllParticipants()
        {
            sendRequest(new GetTopParticipantRequest());
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err =(ErrorResponse)response;
                throw new Exception(err.Message);
            }

            GetParticipantsResponse resp = (GetParticipantsResponse)response;
            List<Participant> participants = resp.Participants;
            return participants;
        }

        public IEnumerable<Result> getAllResults()
        {
            return null;
        }

        public List<Participant> getNotedParticipants(Referee referee)
        {
            sendRequest(new GetNotedRequest(referee));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err =(ErrorResponse)response;
                throw new Exception(err.Message);
            }

            GetNotedParticipantsResponse resp = (GetNotedParticipantsResponse)response;
            List<Participant> participants = resp.Participants;
            return participants;
        }

        public List<Participant> getNotNoted(Referee referee)
        {
            sendRequest(new GetNotNotedRequest(referee));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err =(ErrorResponse)response;
                throw new Exception(err.Message);
            }

            GetNotNotedParticipantsResponse resp = (GetNotNotedParticipantsResponse)response;
            List<Participant> participants = resp.Participants;
            return participants;
        }

        public Participant getPartById(int id)
        {
            sendRequest(new GetPartRequest(id));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err =(ErrorResponse)response;
                throw new Exception(err.Message);
            }

            GetPartIdResponse resp = (GetPartIdResponse)response;
            Participant participant = resp.Participant;
            return participant;
        }

        public void addScore(Result result)
        {
            sendRequest(new AddScoreRequest(result));
            Response response = readResponse();
            if (response is ErrorResponse)
            {
                ErrorResponse err =(ErrorResponse)response;
                throw new Exception(err.Message);
            }
        }

        public void logout(Referee referee, IObserver client)
        {
            sendRequest(new LogoutRequest(referee));
            Response response = readResponse();
            closeConnection();
            if (response is ErrorResponse)
            {
                ErrorResponse err = (ErrorResponse)response;
                throw new Exception(err.Message);
            }
        }



        private void initializeConnection()
        {
            try
            {
                connection = new TcpClient(host, port);
                stream = connection.GetStream();
                formatter = new BinaryFormatter();
                finished = false;
                waitHandle = new AutoResetEvent(false);
                startReader();
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        private void startReader()
        {
            Thread tw = new Thread(run);
            tw.Start();
        }

        private void sendRequest(Request request)
        {
            try
            {
                formatter.Serialize(stream, request);
                stream.Flush();
            }
            catch (Exception e)
            {
                throw new Exception("Error sending object " + request);
            }
        }

        private Response readResponse()
        {
            Response response = null;
            try
            {
                waitHandle.WaitOne();
                lock (responses)
                {
                    response = responses.Dequeue();
                }
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }

            return response;
        }

        private void handleUpdate(UpdateResponse update)
        {
            if (update is NewScoreResponse)
            {
                NewScoreResponse scoreUpd = (NewScoreResponse)update;
                Participant participant = scoreUpd.Participant;
                Console.WriteLine("Score added to " + participant);
                try
                {
                    client.notifyAddedPoints(participant);
                }
                catch (Exception e)
                {
                    Console.WriteLine(e.StackTrace);
                }
            }
        }

        private void closeConnection()
        {
            finished = true;
            try
            {
                stream.Close();

                connection.Close();
                waitHandle.Close();
                client = null;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }

        public virtual void run()
        {
            while (!finished)
            {
                try
                {
                    object response = formatter.Deserialize(stream);
                    Console.WriteLine("Response received " + response);
                    if (response is UpdateResponse)
                    {
                        handleUpdate((UpdateResponse)response);
                    }
                    else
                    {
                        lock (responses)
                        {
                            responses.Enqueue((Response)response);
                        }

                        waitHandle.Set();
                    }
                }
                catch (Exception e)
                {
                    Console.WriteLine("Reading error "+ e);
                }
            }
        }
    }
}