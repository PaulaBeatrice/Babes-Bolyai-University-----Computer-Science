namespace Prototip_Interfata
{
    partial class DeleteBook
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
            textBox1 = new TextBox();
            button1 = new Button();
            SuspendLayout();
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            label1.Location = new Point(28, 43);
            label1.Name = "label1";
            label1.Size = new Size(26, 20);
            label1.TabIndex = 0;
            label1.Text = "Id";
            // 
            // textBox1
            // 
            textBox1.BackColor = Color.MistyRose;
            textBox1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            textBox1.Location = new Point(86, 40);
            textBox1.Name = "textBox1";
            textBox1.Size = new Size(182, 27);
            textBox1.TabIndex = 1;
            // 
            // button1
            // 
            button1.BackColor = Color.MistyRose;
            button1.Font = new Font("Georgia", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            button1.Location = new Point(28, 100);
            button1.Name = "button1";
            button1.Size = new Size(240, 29);
            button1.TabIndex = 2;
            button1.Text = "Sterge cartea";
            button1.UseVisualStyleBackColor = false;
            // 
            // DeleteBook
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.PeachPuff;
            ClientSize = new Size(297, 173);
            Controls.Add(button1);
            Controls.Add(textBox1);
            Controls.Add(label1);
            Name = "DeleteBook";
            Text = "DeleteBook";
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Label label1;
        private TextBox textBox1;
        private Button button1;
    }
}