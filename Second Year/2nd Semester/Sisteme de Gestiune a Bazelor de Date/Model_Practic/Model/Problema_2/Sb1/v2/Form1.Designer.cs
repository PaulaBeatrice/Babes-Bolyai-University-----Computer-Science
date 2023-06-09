namespace v2
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
            txtArtist = new TextBox();
            lblArtist = new Label();
            txtDurata = new TextBox();
            lblDurata = new Label();
            txtAn = new TextBox();
            lblAn = new Label();
            txtTitlu = new TextBox();
            lblTitlu = new Label();
            tabelMelodii = new DataGridView();
            tabelArtisti = new DataGridView();
            ((System.ComponentModel.ISupportInitialize)tabelMelodii).BeginInit();
            ((System.ComponentModel.ISupportInitialize)tabelArtisti).BeginInit();
            SuspendLayout();
            // 
            // btnDelete
            // 
            btnDelete.Location = new Point(700, 438);
            btnDelete.Name = "btnDelete";
            btnDelete.Size = new Size(187, 40);
            btnDelete.TabIndex = 25;
            btnDelete.Text = "Delete";
            btnDelete.UseVisualStyleBackColor = true;
            btnDelete.Click += btnDelete_Click;
            // 
            // btnUpdate
            // 
            btnUpdate.Location = new Point(700, 379);
            btnUpdate.Name = "btnUpdate";
            btnUpdate.Size = new Size(187, 40);
            btnUpdate.TabIndex = 24;
            btnUpdate.Text = "Update";
            btnUpdate.UseVisualStyleBackColor = true;
            btnUpdate.Click += btnUpdate_Click;
            // 
            // btnAdd
            // 
            btnAdd.Location = new Point(700, 312);
            btnAdd.Name = "btnAdd";
            btnAdd.Size = new Size(187, 40);
            btnAdd.TabIndex = 23;
            btnAdd.Text = "Add";
            btnAdd.UseVisualStyleBackColor = true;
            btnAdd.Click += btnAdd_Click;
            // 
            // txtArtist
            // 
            txtArtist.Location = new Point(346, 473);
            txtArtist.Name = "txtArtist";
            txtArtist.ReadOnly = true;
            txtArtist.Size = new Size(312, 27);
            txtArtist.TabIndex = 22;
            // 
            // lblArtist
            // 
            lblArtist.AutoSize = true;
            lblArtist.Location = new Point(254, 477);
            lblArtist.Name = "lblArtist";
            lblArtist.Size = new Size(42, 20);
            lblArtist.TabIndex = 21;
            lblArtist.Text = "artist";
            // 
            // txtDurata
            // 
            txtDurata.Location = new Point(346, 422);
            txtDurata.Name = "txtDurata";
            txtDurata.Size = new Size(312, 27);
            txtDurata.TabIndex = 20;
            // 
            // lblDurata
            // 
            lblDurata.AutoSize = true;
            lblDurata.Location = new Point(254, 426);
            lblDurata.Name = "lblDurata";
            lblDurata.Size = new Size(52, 20);
            lblDurata.TabIndex = 19;
            lblDurata.Text = "durata";
            // 
            // txtAn
            // 
            txtAn.Location = new Point(346, 370);
            txtAn.Name = "txtAn";
            txtAn.Size = new Size(312, 27);
            txtAn.TabIndex = 18;
            // 
            // lblAn
            // 
            lblAn.AutoSize = true;
            lblAn.Location = new Point(254, 374);
            lblAn.Name = "lblAn";
            lblAn.Size = new Size(25, 20);
            lblAn.TabIndex = 17;
            lblAn.Text = "an";
            // 
            // txtTitlu
            // 
            txtTitlu.Location = new Point(346, 318);
            txtTitlu.Name = "txtTitlu";
            txtTitlu.Size = new Size(312, 27);
            txtTitlu.TabIndex = 16;
            // 
            // lblTitlu
            // 
            lblTitlu.AutoSize = true;
            lblTitlu.Location = new Point(254, 322);
            lblTitlu.Name = "lblTitlu";
            lblTitlu.Size = new Size(35, 20);
            lblTitlu.TabIndex = 15;
            lblTitlu.Text = "titlu";
            // 
            // tabelMelodii
            // 
            tabelMelodii.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelMelodii.Location = new Point(700, 19);
            tabelMelodii.Name = "tabelMelodii";
            tabelMelodii.RowHeadersWidth = 51;
            tabelMelodii.RowTemplate.Height = 29;
            tabelMelodii.Size = new Size(614, 277);
            tabelMelodii.TabIndex = 14;
            // 
            // tabelArtisti
            // 
            tabelArtisti.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            tabelArtisti.Location = new Point(5, 19);
            tabelArtisti.Name = "tabelArtisti";
            tabelArtisti.RowHeadersWidth = 51;
            tabelArtisti.RowTemplate.Height = 29;
            tabelArtisti.Size = new Size(669, 277);
            tabelArtisti.TabIndex = 13;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            ClientSize = new Size(1347, 512);
            Controls.Add(btnDelete);
            Controls.Add(btnUpdate);
            Controls.Add(btnAdd);
            Controls.Add(txtArtist);
            Controls.Add(lblArtist);
            Controls.Add(txtDurata);
            Controls.Add(lblDurata);
            Controls.Add(txtAn);
            Controls.Add(lblAn);
            Controls.Add(txtTitlu);
            Controls.Add(lblTitlu);
            Controls.Add(tabelMelodii);
            Controls.Add(tabelArtisti);
            Name = "Form1";
            Text = "Form1";
            Load += Form1_Load;
            ((System.ComponentModel.ISupportInitialize)tabelMelodii).EndInit();
            ((System.ComponentModel.ISupportInitialize)tabelArtisti).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private Button btnDelete;
        private Button btnUpdate;
        private Button btnAdd;
        private TextBox txtArtist;
        private Label lblArtist;
        private TextBox txtDurata;
        private Label lblDurata;
        private TextBox txtAn;
        private Label lblAn;
        private TextBox txtTitlu;
        private Label lblTitlu;
        private DataGridView tabelMelodii;
        private DataGridView tabelArtisti;
    }
}