namespace Prototip_Interfata
{
    partial class MainClient
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
            label1 = new Label();
            button1 = new Button();
            button2 = new Button();
            button3 = new Button();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Georgia", 12F, FontStyle.Regular, GraphicsUnit.Point);
            label1.Location = new Point(224, 42);
            label1.Name = "label1";
            label1.Size = new Size(94, 24);
            label1.TabIndex = 0;
            label1.Text = "Welcome";
            // 
            // button1
            // 
            button1.BackColor = Color.MistyRose;
            button1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button1.Location = new Point(31, 119);
            button1.Name = "button1";
            button1.Size = new Size(143, 29);
            button1.TabIndex = 1;
            button1.Text = "Rent a book";
            button1.UseVisualStyleBackColor = false;
            button1.Click += button1_Click;
            // 
            // button2
            // 
            button2.BackColor = Color.MistyRose;
            button2.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button2.Location = new Point(207, 119);
            button2.Name = "button2";
            button2.Size = new Size(154, 29);
            button2.TabIndex = 2;
            button2.Text = "Return a book";
            button2.UseVisualStyleBackColor = false;
            button2.Click += button2_Click;
            // 
            // button3
            // 
            button3.BackColor = Color.MistyRose;
            button3.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button3.Location = new Point(384, 119);
            button3.Name = "button3";
            button3.Size = new Size(174, 29);
            button3.TabIndex = 3;
            button3.Text = "View rental history";
            button3.UseVisualStyleBackColor = false;
            button3.Click += button3_Click;
            // 
            // MainClient
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.PeachPuff;
            ClientSize = new Size(580, 211);
            Controls.Add(button3);
            Controls.Add(button2);
            Controls.Add(button1);
            Controls.Add(label1);
            Name = "MainClient";
            Text = "MainClient";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Button button1;
        private Button button2;
        private Button button3;
    }
}