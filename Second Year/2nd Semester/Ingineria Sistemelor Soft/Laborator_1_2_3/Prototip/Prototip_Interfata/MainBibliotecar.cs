using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Prototip_Interfata
{
    public partial class MainBibliotecar : Form
    {
        public MainBibliotecar()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            AddBook addBook = new AddBook();
            addBook.Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            DeleteBook delBook = new DeleteBook();
            delBook.Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            ViewBooks viewBooks = new ViewBooks();
            viewBooks.Show();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            AddClient addClient = new AddClient();
            addClient.Show();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            DeleteClient delClient = new DeleteClient();
            delClient.Show();
        }

        private void button6_Click(object sender, EventArgs e)
        {
            UpdateClient updateClient = new UpdateClient();
            updateClient.Show();
        }

        private void button7_Click(object sender, EventArgs e)
        {
            ViewClients viewClients = new ViewClients();
            viewClients.Show();
        }
    }
}
