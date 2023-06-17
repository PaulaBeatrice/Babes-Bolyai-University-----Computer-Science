namespace Prototip_Interfata
{
    partial class MainBibliotecar
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

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
            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            button4 = new Button();
            button5 = new Button();
            button6 = new Button();
            button7 = new Button();
            SuspendLayout();
            // 
            // button1
            // 
            button1.BackColor = Color.MistyRose;
            button1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button1.Location = new Point(49, 63);
            button1.Name = "button1";
            button1.Size = new Size(189, 29);
            button1.TabIndex = 0;
            button1.Text = "Add a book";
            button1.UseVisualStyleBackColor = false;
            button1.Click += button1_Click;
            // 
            // button2
            // 
            button2.BackColor = Color.MistyRose;
            button2.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button2.Location = new Point(49, 126);
            button2.Name = "button2";
            button2.Size = new Size(189, 29);
            button2.TabIndex = 1;
            button2.Text = "Delete a book";
            button2.UseVisualStyleBackColor = false;
            button2.Click += button2_Click;
            // 
            // button3
            // 
            button3.BackColor = Color.MistyRose;
            button3.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button3.Location = new Point(49, 193);
            button3.Name = "button3";
            button3.Size = new Size(189, 29);
            button3.TabIndex = 2;
            button3.Text = "View books";
            button3.UseVisualStyleBackColor = false;
            button3.Click += button3_Click;
            // 
            // button4
            // 
            button4.BackColor = Color.MistyRose;
            button4.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button4.Location = new Point(310, 36);
            button4.Name = "button4";
            button4.Size = new Size(183, 29);
            button4.TabIndex = 3;
            button4.Text = "Add a client";
            button4.UseVisualStyleBackColor = false;
            button4.Click += button4_Click;
            // 
            // button5
            // 
            button5.BackColor = Color.MistyRose;
            button5.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button5.Location = new Point(310, 96);
            button5.Name = "button5";
            button5.Size = new Size(183, 29);
            button5.TabIndex = 4;
            button5.Text = "Delete a client";
            button5.UseVisualStyleBackColor = false;
            button5.Click += button5_Click;
            // 
            // button6
            // 
            button6.BackColor = Color.MistyRose;
            button6.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button6.Location = new Point(310, 162);
            button6.Name = "button6";
            button6.Size = new Size(183, 29);
            button6.TabIndex = 5;
            button6.Text = "Update a client";
            button6.UseVisualStyleBackColor = false;
            button6.Click += button6_Click;
            // 
            // button7
            // 
            button7.BackColor = Color.MistyRose;
            button7.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button7.Location = new Point(310, 224);
            button7.Name = "button7";
            button7.Size = new Size(183, 29);
            button7.TabIndex = 6;
            button7.Text = "View clients";
            button7.UseVisualStyleBackColor = false;
            button7.Click += button7_Click;
            // 
            // MainBibliotecar
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.PeachPuff;
            ClientSize = new Size(553, 289);
            Controls.Add(button7);
            Controls.Add(button6);
            Controls.Add(button5);
            Controls.Add(button4);
            Controls.Add(button3);
            Controls.Add(button2);
            Controls.Add(button1);
            Name = "MainBibliotecar";
            Text = "MainBibliotecar";
            ResumeLayout(false);
        }

        #endregion

        private Button button1;
        private Button button2;
        private Button button3;
        private Button button4;
        private Button button5;
        private Button button6;
        private Button button7;
    }
}