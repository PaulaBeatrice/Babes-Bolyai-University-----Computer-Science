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
    public partial class MainClient : Form
    {
        public MainClient()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            RentBook book = new RentBook();
            book.Show();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            ReturnBook book = new ReturnBook();
            book.Show();
        }

        private void button3_Click(object sender, EventArgs e)
        {
            RentalHistory book = new RentalHistory();   
            book.Show();
        }
    }
}
