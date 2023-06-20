using System;
using System.Collections.Generic;
using System.Net.Sockets;
using System.Threading;
using Google.Protobuf;
using services;
using Triathlon.Protocol;
using Participant = model.Participant;

namespace protobuf
{
    public class ProtoTriathlonWorker:IObserver
    {
        private IService server;
        private TcpClient connection;

        private NetworkStream stream;
        private volatile bool connected;

        public ProtoTriathlonWorker(IService server, TcpClient connection)
        {
            this.server = server;
            this.connection = connection;
            try
            {
                stream = connection.GetStream();
                connected = true;
            }
            catch (Exception e)
            {
                Console.WriteLine(e.StackTrace);
            }
        }
        
        public virtual void run()
        {
            while(connected)
            {
                try
                {
					
                    TriathlonRequest request = TriathlonRequest.Parser.ParseDelimitedFrom(stream);
                    TriathlonResponse response =handleRequest(request);
                    if (response!=null)
                    {
                        sendResponse(response);
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
                Console.WriteLine("Error "+e);
            }
        }
        public void notifyAddedPoints(model.Participant participant)
        {
            Console.WriteLine("Score added -> " + participant);
            try
            {
                sendResponse(ProtoUtils.createAddScoreResponse(participant));
            }
            catch (Exception e)
            {
                throw new Exception("Adding error");
            }
        }

        private TriathlonResponse handleRequest(TriathlonRequest request)
        {
            TriathlonResponse response = null;
            TriathlonRequest.Types.Type reqType = request.Type;
            switch (reqType)
            {
                case TriathlonRequest.Types.Type.Login:
                {
                    Console.WriteLine("Login Request...");
                    model.Referee referee = ProtoUtils.getReferee(request);
                    try
                    {
                        model.Referee theReferee;
                        lock (server)
                        {
                            theReferee = server.login(referee, this);
                            Console.WriteLine("REFEREE GASIT " + theReferee.Id + " " + theReferee.First_Name);
                        }

                        return ProtoUtils.createOkResponse(theReferee);
                    }
                    catch (Exception e)
                    {
                        connected = false;
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.Logout:
                {
                    Console.WriteLine("Logout Request");
                    model.Referee referee = ProtoUtils.getReferee(request);
                    try
                    {
                        lock (server)
                        {
                            server.logout(referee, this);
                        }

                        connected = false;
                        return ProtoUtils.createOkResponse();
                    }
                    catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.AddScore:
                {
                    Console.WriteLine("Adding Score Request + " + request);
                    model.Result result = ProtoUtils.getResult(request);
                    Console.WriteLine("Preiau result ul ->" + result.Participant.Id);
                    try
                    {
                        lock (server)
                        {
                            server.addScore(result);
                        }

                        return ProtoUtils.createOkResponse();
                    }
                    catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.GetTop:
                {
                    Console.WriteLine("Get Top Participants");
                    try
                    {
                        List<model.Participant> participants;
                        lock (server)
                        {
                            participants = server.getAllParticipants();
                        }

                        return ProtoUtils.createGetTopResponse(participants);
                    }
                    catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.GetNoted:
                {
                    Console.WriteLine("Get Noted Participants");
                    model.Referee referee = ProtoUtils.getReferee(request);
                    try
                    {
                        List<model.Participant> participants;
                        lock(server)
                        {
                            participants = server.getNotedParticipants(referee);
                        }

                        return ProtoUtils.createGetNotedPartResponse(participants);
                    }catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.GetNotNoted:
                {
                    Console.WriteLine("Get Not Noted Participants");
                    model.Referee referee = ProtoUtils.getReferee(request);
                    Console.WriteLine("Referee + " + referee.Id + " " + referee.First_Name);
                    try
                    {
                        List<model.Participant> participants;
                        lock(server)
                        {
                            Console.WriteLine("Creez lista");
                            participants = server.getNotNoted(referee);
                        }

                        return ProtoUtils.createGetNotedPartResponse(participants);
                    }catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
                case TriathlonRequest.Types.Type.GetPart:
                {
                    Console.WriteLine("Get Participant");
                    int id = ProtoUtils.getId(request);
                    Console.WriteLine("CU ID UL -> "+ id);
                    try
                    {
                        model.Participant participant;
                        lock (server)
                        {
                            participant = server.getPartById(id);
                        }
                        Console.WriteLine("Am gasit -> " + participant);
                        return ProtoUtils.createGetPart(participant);
                    }catch (Exception e)
                    {
                        return ProtoUtils.createErrorResponse(e.Message);
                    }
                }
            }

            return response;
        }

        private void sendResponse(TriathlonResponse response)
        {
            Console.WriteLine("sending response "+response);
            lock (stream)
            {
                response.WriteDelimitedTo(stream);
                stream.Flush();
            }
        }
    }
}