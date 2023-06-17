namespace Prototip_Interfata
{
    partial class AddBook
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
            label2 = new Label();
            label3 = new Label();
            label4 = new Label();
            textBox1 = new TextBox();
            textBox2 = new TextBox();
            textBox3 = new TextBox();
            textBox4 = new TextBox();
            button1 = new Button();
            textBox5 = new TextBox();
            label5 = new Label();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            label1.Location = new Point(75, 46);
            label1.Name = "label1";
            label1.Size = new Size(39, 18);
            label1.TabIndex = 0;
            label1.Text = "Titlu";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            label2.Location = new Point(75, 94);
            label2.Name = "label2";
            label2.Size = new Size(46, 18);
            label2.TabIndex = 1;
            label2.Text = "Autor";
            // 
            // label3
            // 
            label3.AutoSize = true;
            label3.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            label3.Location = new Point(75, 138);
            label3.Name = "label3";
            label3.Size = new Size(58, 18);
            label3.TabIndex = 2;
            label3.Text = "Editura";
            // 
            // label4
            // 
            label4.AutoSize = true;
            label4.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            label4.Location = new Point(75, 182);
            label4.Name = "label4";
            label4.Size = new Size(112, 18);
            label4.TabIndex = 3;
            label4.Text = "Anul publicatiei";
            // 
            // textBox1
            // 
            textBox1.BackColor = Color.MistyRose;
            textBox1.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            textBox1.Location = new Point(207, 39);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(208, 25);
            textBox1.TabIndex = 4;
            // 
            // textBox2
            // 
            textBox2.BackColor = Color.MistyRose;
            textBox2.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            textBox2.Location = new Point(207, 131);
            textBox2.Name = "textBox2";
            textBox2.Size = new Size(208, 25);
            textBox2.TabIndex = 5;
            // 
            // textBox3
            // 
            textBox3.BackColor = Color.MistyRose;
            textBox3.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            textBox3.Location = new Point(207, 87);
            textBox3.Name = "textBox3";
            textBox3.Size = new Size(208, 25);
            textBox3.TabIndex = 6;
            // 
            // textBox4
            // 
            textBox4.BackColor = Color.MistyRose;
            textBox4.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            textBox4.Location = new Point(207, 179);
            textBox4.Name = "textBox4";
            textBox4.Size = new Size(208, 25);
            textBox4.TabIndex = 7;
            // 
            // button1
            // 
            button1.BackColor = Color.MistyRose;
            button1.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            button1.Location = new Point(75, 276);
            button1.Name = "button1";
            button1.Size = new Size(340, 41);
            button1.TabIndex = 8;
            button1.Text = "Adauga cartea";
            button1.UseVisualStyleBackColor = false;
            // 
            // textBox5
            // 
            textBox5.BackColor = Color.MistyRose;
            textBox5.Location = new Point(207, 224);
            textBox5.Name = "textBox5";
            textBox5.Size = new Size(208, 27);
            textBox5.TabIndex = 9;
            // 
            // label5
            // 
            label5.AutoSize = true;
            label5.Font = new Font("Georgia", 9F, FontStyle.Regular, GraphicsUnit.Point);
            label5.Location = new Point(75, 233);
            label5.Name = "label5";
            label5.Size = new Size(35, 18);
            label5.TabIndex = 10;
            label5.Text = "Pret";
            // 
            // AddBook
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.PeachPuff;
            ClientSize = new Size(500, 359);
            Controls.Add(label5);
            Controls.Add(textBox5);
            Controls.Add(button1);
            Controls.Add(textBox4);
            Controls.Add(textBox3);
            Controls.Add(textBox2);
            Controls.Add(textBox1);
            Controls.Add(label4);
            Controls.Add(label3);
            Controls.Add(label2);
            Controls.Add(label1);
            Name = "AddBook";
            Text = "AddBook";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private Label label2;
        private Label label3;
        private Label label4;
        private TextBox textBox1;
        private TextBox textBox2;
        private TextBox textBox3;
        private TextBox textBox4;
        private Button button1;
        private TextBox textBox5;
        private Label label5;
    }
}