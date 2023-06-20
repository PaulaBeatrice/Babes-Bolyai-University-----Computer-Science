using System;
using System.Windows.Forms;
using networking;
using services;

namespace client
{
    static class StartClient
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        [STAThread]
        static void Main()
        {
            Application.EnableVisualStyles();
            Application.SetCompatibleTextRenderingDefault(false);
            
            IService server = new ServerObjectProxy("127.0.0.1", 55555);
            ClientCtrl ctrl=new ClientCtrl(server);
            LoginWindow win=new LoginWindow(ctrl);
            Application.Run(win);
        }
    }
}