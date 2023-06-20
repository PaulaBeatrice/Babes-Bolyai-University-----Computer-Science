using System;
using System.Collections.Generic;
using System.Linq;
using System.Windows.Forms;
using model;

namespace client
{
    public partial class TriathlonWindow : Form
    {
        private readonly ClientCtrl ctrl;
        private readonly IList<Participant> partData;
        private IList<Participant> notedPart;
        private IList<Participant> notNotedPart;
        public TriathlonWindow(ClientCtrl ctrl)
        {
            InitializeComponent();
            this.ctrl = ctrl;
            partData = ctrl.getParts();
            partData = partData.OrderByDescending(p => p.Points).ToList();
            
            notedPart = ctrl.getNoted();
            notedPart = notedPart.OrderBy(p => p.First_Name).ToList();
            
            
            notNotedPart = ctrl.getNotNoted();

            NotedParticipants.DataSource = notedPart.Select(p => p.First_Name + " " + p.Last_Name + " " + p.Points).ToList();

            NotNotedParticipants.DataSource = notNotedPart.Select(p => p.First_Name + " " + p.Last_Name).ToList();
            TopParticipants.DataSource = partData.Select(p => p.First_Name + " " + p.Last_Name+ " " + p.Points).ToList();

            ctrl.updateEvent += userUpdate;
        }

        private void logoutBtn_Click(object sender, EventArgs e)
        {
            Console.WriteLine("Closing");
            ctrl.logout();
            ctrl.updateEvent -= userUpdate;
            Application.Exit();
        }

        public void userUpdate(object sender, UserEventArgs e)
        {
            if (e.UserEventType == UserEvent.NewScore)
            {
                Participant participant = (Participant)e.Data;
                
                foreach(Participant part in partData)
                    if (part.Id == participant.Id)
                        part.Points = participant.Points;
                
                
                List<Participant> partDataList = partData.ToList(); 
                partDataList = partDataList.OrderByDescending(p => p.Points).ToList();
                IEnumerable<Participant> partDataSorted = partDataList.AsReadOnly();

                
                
                TopParticipants.BeginInvoke(new UpdateListBoxCallback(this.updateListBox),
                    new Object[] { TopParticipants, partDataSorted });
            }
        }
        
        private void updateListBox(ListBox listBox, IList<Participant> newData)
        {
            listBox.DataSource = null;
            listBox.DataSource = newData;
        }
        public delegate void UpdateListBoxCallback(ListBox list, IList<Participant> data);

        private void addScoreBtn_Click(object sender, EventArgs e)
        {
            int points = Convert.ToInt32(scoreText.Text);
            int indexPart = NotNotedParticipants.SelectedIndex;
            Participant participant = notNotedPart[indexPart];
            ctrl.addScore(participant,points);
            scoreText.Clear();
            initTabele();
        }

        private void initTabele()
        {
            notedPart.Clear();
            notNotedPart.Clear();
            
            notNotedPart = ctrl.getNotNoted();
            notedPart = ctrl.getNoted();
            notedPart = notedPart.OrderBy(p => p.First_Name).ToList();

            NotedParticipants.DataSource = notedPart;
            NotNotedParticipants.DataSource = notNotedPart;
        }
    }
}