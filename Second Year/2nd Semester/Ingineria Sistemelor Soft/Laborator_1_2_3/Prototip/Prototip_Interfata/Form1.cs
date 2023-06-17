namespace Prototip_Interfata
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {

        }

        private void button1_Click(object sender, EventArgs e)
        {
            if(textBox1.Text == "b" && textBox2.Text == "b")
            {
                MainBibliotecar mainBibliotecar = new MainBibliotecar();
                mainBibliotecar.Show();
            }
            if(textBox1.Text == "c" &&  textBox2.Text == "c") { 
                MainClient mainClient = new MainClient();
                mainClient.Show();
            }
        }
    }
}