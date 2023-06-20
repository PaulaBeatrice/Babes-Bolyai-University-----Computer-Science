using System.ComponentModel;

namespace client
{
    partial class TriathlonWindow
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }

            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.NotNotedParticipants = new System.Windows.Forms.ListBox();
            this.NotedParticipants = new System.Windows.Forms.ListBox();
            this.TopParticipants = new System.Windows.Forms.ListBox();
            this.scoreText = new System.Windows.Forms.TextBox();
            this.addScoreBtn = new System.Windows.Forms.Button();
            this.logoutBtn = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // NotNotedParticipants
            // 
            this.NotNotedParticipants.FormattingEnabled = true;
            this.NotNotedParticipants.ItemHeight = 16;
            this.NotNotedParticipants.Location = new System.Drawing.Point(24, 77);
            this.NotNotedParticipants.Name = "NotNotedParticipants";
            this.NotNotedParticipants.Size = new System.Drawing.Size(205, 276);
            this.NotNotedParticipants.TabIndex = 0;
            // 
            // NotedParticipants
            // 
            this.NotedParticipants.FormattingEnabled = true;
            this.NotedParticipants.ItemHeight = 16;
            this.NotedParticipants.Location = new System.Drawing.Point(259, 77);
            this.NotedParticipants.Name = "NotedParticipants";
            this.NotedParticipants.Size = new System.Drawing.Size(176, 276);
            this.NotedParticipants.TabIndex = 1;
            // 
            // TopParticipants
            // 
            this.TopParticipants.FormattingEnabled = true;
            this.TopParticipants.ItemHeight = 16;
            this.TopParticipants.Location = new System.Drawing.Point(463, 77);
            this.TopParticipants.Name = "TopParticipants";
            this.TopParticipants.Size = new System.Drawing.Size(179, 276);
            this.TopParticipants.TabIndex = 2;
            // 
            // scoreText
            // 
            this.scoreText.Location = new System.Drawing.Point(24, 402);
            this.scoreText.Name = "scoreText";
            this.scoreText.Size = new System.Drawing.Size(127, 22);
            this.scoreText.TabIndex = 3;
            // 
            // addScoreBtn
            // 
            this.addScoreBtn.Location = new System.Drawing.Point(186, 398);
            this.addScoreBtn.Name = "addScoreBtn";
            this.addScoreBtn.Size = new System.Drawing.Size(209, 25);
            this.addScoreBtn.TabIndex = 4;
            this.addScoreBtn.Text = "Add Score";
            this.addScoreBtn.UseVisualStyleBackColor = true;
            this.addScoreBtn.Click += new System.EventHandler(this.addScoreBtn_Click);
            // 
            // logoutBtn
            // 
            this.logoutBtn.Location = new System.Drawing.Point(586, 12);
            this.logoutBtn.Name = "logoutBtn";
            this.logoutBtn.Size = new System.Drawing.Size(94, 25);
            this.logoutBtn.TabIndex = 5;
            this.logoutBtn.Text = "Logout";
            this.logoutBtn.UseVisualStyleBackColor = true;
            this.logoutBtn.Click += new System.EventHandler(this.logoutBtn_Click);
            // 
            // label1
            // 
            this.label1.Location = new System.Drawing.Point(24, 46);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(162, 28);
            this.label1.TabIndex = 6;
            this.label1.Text = "NotNotedParticipants";
            // 
            // label2
            // 
            this.label2.Location = new System.Drawing.Point(255, 46);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(140, 21);
            this.label2.TabIndex = 7;
            this.label2.Text = "NotedParticipants\r\n";
            // 
            // label3
            // 
            this.label3.Location = new System.Drawing.Point(463, 46);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(150, 17);
            this.label3.TabIndex = 8;
            this.label3.Text = "Top Participants";
            // 
            // TriathlonWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(8F, 16F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(692, 450);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.logoutBtn);
            this.Controls.Add(this.addScoreBtn);
            this.Controls.Add(this.scoreText);
            this.Controls.Add(this.TopParticipants);
            this.Controls.Add(this.NotedParticipants);
            this.Controls.Add(this.NotNotedParticipants);
            this.Name = "TriathlonWindow";
            this.Text = "TriathlonWindow";
            this.ResumeLayout(false);
            this.PerformLayout();
        }

        private System.Windows.Forms.Label label3;

        private System.Windows.Forms.Label label2;

        private System.Windows.Forms.Label label1;

        private System.Windows.Forms.Button logoutBtn;

        private System.Windows.Forms.ListBox NotNotedParticipants;
        private System.Windows.Forms.ListBox NotedParticipants;
        private System.Windows.Forms.TextBox scoreText;
        private System.Windows.Forms.Button addScoreBtn;

        private System.Windows.Forms.ListBox TopParticipants;

        #endregion
    }
}