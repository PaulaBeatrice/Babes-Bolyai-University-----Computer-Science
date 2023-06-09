namespace Sb1
{
    partial class Form1
    {
        /// <summary>
        ///  Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        ///  Clean up any resources being used.
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
        ///  Required method for Designer support - do not modify
        ///  the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            tabelCofetarii = new DataGridView();
            tabelBriose = new DataGridView();
            lblNume = new Label();
            lblDescr = new Label();
            lblPret = new Label();
            lblCof = new Label();
            txtNume = new TextBox();
            txtDescr = new TextBox();
            txtPret = new TextBox();
            txtCof = new TextBox();
            btnAdauga = new Button();
            btnUpdate = new Button();
            btnDelete = new Button();
            ((System.ComponentModel.ISupportInitialize)tabelCofetarii).BeginInit();
            ((System.ComponentModel.ISupportInitialize)tabelBriose).BeginInit();
            SuspendLayout();
            // 
            // tabelCofetarii
            // 
            tabelCofetarii.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelCofetarii.Location = new Point(12, 23);
            tabelCofetarii.Name = "tabelCofetarii";
            tabelCofetarii.RowHeadersWidth = 51;
            tabelCofetarii.RowTemplate.Height = 29;
            tabelCofetarii.Size = new Size(515, 289);
            tabelCofetarii.TabIndex = 0;
            // 
            // tabelBriose
            // 
            tabelBriose.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelBriose.Location = new Point(546, 22);
            tabelBriose.Name = "tabelBriose";
            tabelBriose.RowHeadersWidth = 51;
            tabelBriose.RowTemplate.Height = 29;
            tabelBriose.Size = new Size(539, 290);
            tabelBriose.TabIndex = 1;
            // 
            // lblNume
            // 
            lblNume.AutoSize = true;
            lblNume.Location = new Point(552, 333);
            lblNume.Name = "lblNume";
            lblNume.Size = new Size(49, 20);
            lblNume.TabIndex = 2;
            lblNume.Text = "Nume";
            // 
            // lblDescr
            // 
            lblDescr.AutoSize = true;
            lblDescr.Location = new Point(552, 383);
            lblDescr.Name = "lblDescr";
            lblDescr.Size = new Size(71, 20);
            lblDescr.TabIndex = 3;
            lblDescr.Text = "Descriere";
            // 
            // lblPret
            // 
            lblPret.AutoSize = true;
            lblPret.Location = new Point(552, 432);
            lblPret.Name = "lblPret";
            lblPret.Size = new Size(35, 20);
            lblPret.TabIndex = 4;
            lblPret.Text = "Pret";
            // 
            // lblCof
            // 
            lblCof.AutoSize = true;
            lblCof.Location = new Point(552, 482);
            lblCof.Name = "lblCof";
            lblCof.Size = new Size(70, 20);
            lblCof.TabIndex = 5;
            lblCof.Text = "Cofetarie";
            // 
            // txtNume
            // 
            txtNume.Location = new Point(649, 333);
            txtNume.Name = "txtNume";
            txtNume.Size = new Size(202, 27);
            txtNume.TabIndex = 6;
            // 
            // txtDescr
            // 
            txtDescr.Location = new Point(649, 383);
            txtDescr.Name = "txtDescr";
            txtDescr.Size = new Size(202, 27);
            txtDescr.TabIndex = 7;
            // 
            // txtPret
            // 
            txtPret.Location = new Point(649, 429);
            txtPret.Name = "txtPret";
            txtPret.Size = new Size(202, 27);
            txtPret.TabIndex = 8;
            // 
            // txtCof
            // 
            txtCof.Location = new Point(649, 475);
            txtCof.Name = "txtCof";
            txtCof.ReadOnly = true;
            txtCof.Size = new Size(202, 27);
            txtCof.TabIndex = 9;
            // 
            // btnAdauga
            // 
            btnAdauga.Location = new Point(925, 329);
            btnAdauga.Name = "btnAdauga";
            btnAdauga.Size = new Size(108, 29);
            btnAdauga.TabIndex = 10;
            btnAdauga.Text = "Adauga";
            btnAdauga.UseVisualStyleBackColor = true;
            btnAdauga.Click += btnAdauga_Click;
            // 
            // btnUpdate
            // 
            btnUpdate.Location = new Point(925, 382);
            btnUpdate.Name = "btnUpdate";
            btnUpdate.Size = new Size(108, 29);
            btnUpdate.TabIndex = 11;
            btnUpdate.Text = "Update";
            btnUpdate.UseVisualStyleBackColor = true;
            btnUpdate.Click += btnUpdate_Click;
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(925, 429);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(108, 29);
            btnDelete.TabIndex = 12;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            btnDelete.Click += btnDelete_Click;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1128, 555);
            Controls.Add(btnDelete);
            Controls.Add(btnUpdate);
            Controls.Add(btnAdauga);
            Controls.Add(txtCof);
            Controls.Add(txtPret);
            Controls.Add(txtDescr);
            Controls.Add(txtNume);
            Controls.Add(lblCof);
            Controls.Add(lblPret);
            Controls.Add(lblDescr);
            Controls.Add(lblNume);
            Controls.Add(tabelBriose);
            Controls.Add(tabelCofetarii);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ((System.ComponentModel.ISupportInitialize)tabelCofetarii).EndInit();
            ((System.ComponentModel.ISupportInitialize)tabelBriose).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView tabelCofetarii;
        private DataGridView tabelBriose;
        private Label lblNume;
        private Label lblDescr;
        private Label lblPret;
        private Label lblCof;
        private TextBox txtNume;
        private TextBox txtDescr;
        private TextBox txtPret;
        private TextBox txtCof;
        private Button btnAdauga;
        private Button btnUpdate;
        private Button btnDelete;
    }
}