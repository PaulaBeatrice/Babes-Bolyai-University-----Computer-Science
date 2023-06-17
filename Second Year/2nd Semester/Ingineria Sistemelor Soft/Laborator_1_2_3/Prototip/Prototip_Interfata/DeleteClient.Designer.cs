namespace Prototip_Interfata
{
    partial class DeleteClient
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
            textBox7 = new TextBox();
            label7 = new Label();
            button1 = new Button();
            SuspendLayout();
            // 
            // textBox7
            // 
            textBox7.BackColor = Color.MistyRose;
            textBox7.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            textBox7.Location = new Point(81, 19);
            textBox7.Name = "textBox7";
            textBox7.Size = new Size(235, 27);
            textBox7.TabIndex = 29;
            // 
            // label7
            // 
            label7.AutoSize = true;
            label7.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            label7.Location = new Point(26, 26);
            label7.Name = "label7";
            label7.Size = new Size(26, 20);
            label7.TabIndex = 28;
            label7.Text = "Id";
            // 
            // button1
            // 
            button1.BackColor = Color.MistyRose;
            button1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button1.Location = new Point(26, 67);
            button1.Name = "button1";
            button1.Size = new Size(290, 29);
            button1.TabIndex = 30;
            button1.Text = "Sterge clientul";
            button1.UseVisualStyleBackColor = false;
            // 
            // DeleteClient
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.PeachPuff;
            ClientSize = new Size(349, 140);
            Controls.Add(button1);
            Controls.Add(textBox7);
            Controls.Add(label7);
            Name = "DeleteClient";
            Text = "DeleteClient";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private TextBox textBox7;
        private Label label7;
        private Button button1;
    }
}