namespace Sb3
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
            btnDelete = new Button();
            btnUpdate = new Button();
            btnAdd = new Button();
            txtProducator = new TextBox();
            lblProducator = new Label();
            txtPret = new TextBox();
            lblPret = new Label();
            txtCalorii = new TextBox();
            lblCalorii = new Label();
            txtNume = new TextBox();
            lblNume = new Label();
            tabelBiscuiti = new DataGridView();
            tabelProducatori = new DataGridView();
            ((System.ComponentModel.ISupportInitialize)tabelBiscuiti).BeginInit();
            ((System.ComponentModel.ISupportInitialize)tabelProducatori).BeginInit();
            SuspendLayout();
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(712, 455);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(187, 40);
            btnDelete.TabIndex = 38;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            btnDelete.Click += btnDelete_Click;
            // 
            // btnUpdate
            // 
            btnUpdate.Location = new Point(712, 396);
            btnUpdate.Name = "btnUpdate";
            btnUpdate.Size = new Size(187, 40);
            btnUpdate.TabIndex = 37;
            btnUpdate.Text = "Update";
            btnUpdate.UseVisualStyleBackColor = true;
            btnUpdate.Click += btnUpdate_Click;
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(712, 329);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(187, 40);
            btnAdd.TabIndex = 36;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            btnAdd.Click += btnAdd_Click;
            // 
            // txtProducator
            // 
            txtProducator.Location = new Point(358, 490);
            txtProducator.Name = "txtProducator";
            txtProducator.ReadOnly = true;
            txtProducator.Size = new Size(312, 27);
            txtProducator.TabIndex = 35;
            // 
            // lblProducator
            // 
            lblProducator.AutoSize = true;
            lblProducator.Location = new Point(266, 494);
            lblProducator.Name = "lblProducator";
            lblProducator.Size = new Size(83, 20);
            lblProducator.TabIndex = 34;
            lblProducator.Text = "producator";
            // 
            // txtPret
            // 
            txtPret.Location = new Point(358, 439);
            txtPret.Name = "txtPret";
            txtPret.Size = new Size(312, 27);
            txtPret.TabIndex = 33;
            // 
            // lblPret
            // 
            lblPret.AutoSize = true;
            lblPret.Location = new Point(266, 443);
            lblPret.Name = "lblPret";
            lblPret.Size = new Size(36, 20);
            lblPret.TabIndex = 32;
            lblPret.Text = "pret";
            // 
            // txtCalorii
            // 
            txtCalorii.Location = new Point(358, 387);
            txtCalorii.Name = "txtCalorii";
            txtCalorii.Size = new Size(312, 27);
            txtCalorii.TabIndex = 31;
            // 
            // lblCalorii
            // 
            lblCalorii.AutoSize = true;
            lblCalorii.Location = new Point(266, 391);
            lblCalorii.Name = "lblCalorii";
            lblCalorii.Size = new Size(50, 20);
            lblCalorii.TabIndex = 30;
            lblCalorii.Text = "calorii";
            // 
            // txtNume
            // 
            txtNume.Location = new Point(358, 335);
            txtNume.Name = "txtNume";
            txtNume.Size = new Size(312, 27);
            txtNume.TabIndex = 29;
            // 
            // lblNume
            // 
            lblNume.AutoSize = true;
            lblNume.Location = new Point(266, 339);
            lblNume.Name = "lblNume";
            lblNume.Size = new Size(46, 20);
            lblNume.TabIndex = 28;
            lblNume.Text = "nume";
            // 
            // tabelBiscuiti
            // 
            tabelBiscuiti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelBiscuiti.Location = new Point(712, 36);
            tabelBiscuiti.Name = "tabelBiscuiti";
            tabelBiscuiti.RowHeadersWidth = 51;
            tabelBiscuiti.RowTemplate.Height = 29;
            tabelBiscuiti.Size = new Size(614, 277);
            tabelBiscuiti.TabIndex = 27;
            // 
            // tabelProducatori
            // 
            tabelProducatori.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelProducatori.Location = new Point(17, 36);
            tabelProducatori.Name = "tabelProducatori";
            tabelProducatori.RowHeadersWidth = 51;
            tabelProducatori.RowTemplate.Height = 29;
            tabelProducatori.Size = new Size(669, 277);
            tabelProducatori.TabIndex = 26;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1343, 552);
            Controls.Add(btnDelete);
            Controls.Add(btnUpdate);
            Controls.Add(btnAdd);
            Controls.Add(txtProducator);
            Controls.Add(lblProducator);
            Controls.Add(txtPret);
            Controls.Add(lblPret);
            Controls.Add(txtCalorii);
            Controls.Add(lblCalorii);
            Controls.Add(txtNume);
            Controls.Add(lblNume);
            Controls.Add(tabelBiscuiti);
            Controls.Add(tabelProducatori);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ((System.ComponentModel.ISupportInitialize)tabelBiscuiti).EndInit();
            ((System.ComponentModel.ISupportInitialize)tabelProducatori).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnDelete;
        private Button btnUpdate;
        private Button btnAdd;
        private TextBox txtProducator;
        private Label lblProducator;
        private TextBox txtPret;
        private Label lblPret;
        private TextBox txtCalorii;
        private Label lblCalorii;
        private TextBox txtNume;
        private Label lblNume;
        private DataGridView tabelBiscuiti;
        private DataGridView tabelProducatori;
    }
}