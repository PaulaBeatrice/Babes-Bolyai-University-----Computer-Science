using System;
using System.Net.Sockets;
using System.Threading;
using model;
using networking;
using persistence;
using protobuf;
using services;

namespace server
{
    using server;
    class StartServer
    {
        static void Main(string[] args)
        {
            RefereeDBRepository refRepo = new RefereeDBRepository();
            ParticipantDBRepository partRepo = new ParticipantDBRepository();
            ResultDBRepository resRepo = new ResultDBRepository(refRepo, partRepo);
            IService serviceImpl = new ServiceImpl(refRepo, partRepo, resRepo);

            ProtoTriathlonServer scs = new ProtoTriathlonServer("127.0.0.1", 55556, serviceImpl);
            scs.Start();
            Console.WriteLine("Server started...");
            Console.ReadLine();

            // SerialServer server = new SerialServer("127.0.0.1", 55555, serviceImpl);
            // server.Start();
            // Console.WriteLine("Server started ...");
            // Console.ReadLine();



        }
    }

    public class SerialServer: ConcurrentServer 
    {
        private IService server;
        private ClientObjectWorker worker;
        public SerialServer(string host, int port, IService server) : base(host, port)
        {
            this.server = server;
            Console.WriteLine("SerialServer...");
            /*
            foreach (Participant participant in server.getAllParticipants())
            {
                Console.WriteLine(participant);
            }
            */
            
        }
        protected override Thread createWorker(TcpClient client)
        {
            worker = new ClientObjectWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }

    public class ProtoTriathlonServer : ConcurrentServer
    {
        private IService server;
        private ProtoTriathlonWorker worker;
        public ProtoTriathlonServer(string host, int port, IService server)
            : base(host, port)
        {
            this.server = server;
            Console.WriteLine("ProtoChatServer...");
        }

        protected override Thread createWorker(TcpClient client)
        {
            worker = new ProtoTriathlonWorker(server, client);
            return new Thread(new ThreadStart(worker.run));
        }
    }
}