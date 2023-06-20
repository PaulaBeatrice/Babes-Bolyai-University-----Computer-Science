using Laborator_1.domain;
using Laborator_1.repository;
using Laborator3.service;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Configuration;
using System.Data;
using System.Data.Entity.Core.Common.CommandTrees.ExpressionBuilder;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace Laborator3
{
    public partial class Home : Form
    {
        public Home()
        {
            InitializeComponent();
        }

        public Service srv { get; set; }
        public Referee referee;
        private int idParticipant;


        public void initTabele()
        {
            tabelParticipantiNep.Rows.Clear();
            tabelTopPart.Rows.Clear();
            topPropriu.Rows.Clear();

            IEnumerable<Participant> participants = srv.GetParticipants();
            foreach (Participant p in participants)
            {
                if (srv.isEvaluated(p, this.referee) == 0)
                {
                    string[] row = new string[] { p.Id.ToString(), p.First_Name, p.Last_Name };
                    tabelParticipantiNep.Rows.Add(row);
                }
            }

            IEnumerable<Result> results = srv.GetResults();
            List<Participant> all = new List<Participant>();
            foreach (Result r in results)
            {
                if (r.Referee.Id == this.referee.Id)
                {
                    Participant p = new Participant(r.Participant.Id, r.Participant.First_Name, r.Participant.Last_Name, r.Points);
                    all.Add(p);
                }
            }
            List<Participant> sortedParticipants = all.OrderBy(p => p.First_Name).ToList();


            foreach (Participant p in sortedParticipants)
            {
                string[] row = new string[] { p.Id.ToString(), p.First_Name, p.Last_Name, p.Points.ToString() };
                topPropriu.Rows.Add(row);
            }

            IDictionary<Participant, int> topPart = new Dictionary<Participant, int>();
            foreach (Participant p in srv.GetParticipants())
            { // fiecare participant
                int scor = 0;
                foreach (Result r in srv.GetResults())
                {// calculam punctajul la proba respectiva
                    if (r.Participant.Id == p.Id && r.Activity == this.referee.Activity)
                        scor += r.Points;                
                }
                topPart.Add(p, scor);
            }

            var topPartSorted = topPart.OrderByDescending(x => x.Value);
            foreach (var p in topPartSorted)
            {
                string[] row = new string[] { p.Key.Id.ToString(), p.Key.First_Name, p.Key.Last_Name, p.Value.ToString() };
                tabelTopPart.Rows.Add(row);
            }
        }

        private void Home_Load(object sender, EventArgs e)
        {

            tabelParticipantiNep.Rows.Clear();
            tabelParticipantiNep.Columns.Add("Id", "Id");
            tabelParticipantiNep.Columns.Add("First Name", "First Name");
            tabelParticipantiNep.Columns.Add("Last Name", "Last Name");

            tabelTopPart.Rows.Clear();
            tabelTopPart.Columns.Add("Id", "Id");
            tabelTopPart.Columns.Add("First Name", "First Name");
            tabelTopPart.Columns.Add("Last Name", "Last Name");
            tabelTopPart.Columns.Add("Points", "Points");

            topPropriu.Rows.Clear();
            topPropriu.Columns.Add("Id", "Id");
            topPropriu.Columns.Add("First Name", "First Name");
            topPropriu.Columns.Add("Last Name", "Last Name");
            topPropriu.Columns.Add("Points", "Points");

            initTabele();
            label2.Text += this.referee.Activity;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            if (txtScor.Text == "")
                MessageBox.Show("Introduceti scorul obtinut!");
            else if (idParticipant == 0)
                MessageBox.Show("Alegeti un participant!");
            else
            {
                Participant p = this.srv.getParticipantById(idParticipant);
                int points;
                int.TryParse(txtScor.Text, out points);
                int idR = 0;
                foreach (Result result in srv.GetResults())
                    if (result.Id > idR)
                        idR = result.Id;

                Result r = new Result(idR + 1, p, this.referee, points, this.referee.Activity);
                this.srv.addScor(r);
                initTabele();
                txtScor.Text = "";
                MessageBox.Show("Punctajul a fost inregistrat!");
            }
           
        }

        private void tabelParticipantiNep_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && e.ColumnIndex >= 0)
            {
                DataGridViewRow row = tabelParticipantiNep.Rows[e.RowIndex];
                int.TryParse(row.Cells[0].Value.ToString(), out this.idParticipant);
            }
        }

        private void topPropriu_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}
