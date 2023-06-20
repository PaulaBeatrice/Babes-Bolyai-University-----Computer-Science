using System;
using System.Windows.Forms;

namespace client
{
    public partial class LoginWindow : Form
    {
        private ClientCtrl ctrl;
        public LoginWindow(ClientCtrl ctrl)
        {
            InitializeComponent();
            this.ctrl = ctrl;
        }

        private void loginBtn_Click(object sender, EventArgs e)
        {
            Console.Write("S-a apasat LogIn...");
            Console.Write("Username {0} - Password - {1} ",userText.Text,passwordText.Text);
            String user = userText.Text;
            String password = passwordText.Text;
            try
            {
                ctrl.login(user, password);
                TriathlonWindow thrWin = new TriathlonWindow(ctrl);
                thrWin.Text = "Triathlon window for " + user;
                thrWin.Show();
                this.Hide();
                
                
            }
            catch (Exception ex)
            {
                MessageBox.Show(this, "Login error" + ex.Message, "Error", MessageBoxButtons.OK, MessageBoxIcon.Error);
                return;
            }
        }
    }
}