namespace Laborator_1
{
    partial class CabinetOftalmologic
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(CabinetOftalmologic));
            dataGridViewParent = new DataGridView();
            dataGridViewChild = new DataGridView();
            label1 = new Label();
            label2 = new Label();
            txtNume = new TextBox();
            txtPrenume = new TextBox();
            btnAdaugaAngajat = new Button();
            btnStergeAngajat = new Button();
            btnActualizeazaAngajat = new Button();
            ((System.ComponentModel.ISupportInitialize)dataGridViewParent).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewChild).BeginInit();
            SuspendLayout();
            // 
            // dataGridViewParent
            // 
            dataGridViewParent.BackgroundColor = Color.LightBlue;
            dataGridViewParent.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewParent.GridColor = Color.LightBlue;
            dataGridViewParent.Location = new Point(58, 22);
            dataGridViewParent.Name = "dataGridViewParent";
            dataGridViewParent.RowHeadersWidth = 51;
            dataGridViewParent.RowTemplate.Height = 29;
            dataGridViewParent.Size = new Size(554, 200);
            dataGridViewParent.TabIndex = 0;
            dataGridViewParent.CellClick += dataGridViewParent_CellClick;
            dataGridViewParent.CellContentClick += dataGridViewParent_CellContentClick;
            // 
            // dataGridViewChild
            // 
            dataGridViewChild.BackgroundColor = Color.LightBlue;
            dataGridViewChild.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGridViewChild.GridColor = Color.LightBlue;
            dataGridViewChild.Location = new Point(58, 247);
            dataGridViewChild.Name = "dataGridViewChild";
            dataGridViewChild.RowHeadersWidth = 51;
            dataGridViewChild.RowTemplate.Height = 29;
            dataGridViewChild.Size = new Size(554, 200);
            dataGridViewChild.TabIndex = 1;
            dataGridViewChild.CellClick += dataGridViewChild_CellClick;
            dataGridViewChild.CellContentClick += dataGridViewChild_CellContentClick;
            // 
            // label1
            // 
            label1.AutoSize = true;
            label1.BackColor = Color.LightBlue;
            label1.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            label1.Location = new Point(116, 480);
            label1.Name = "label1";
            label1.Size = new Size(55, 22);
            label1.TabIndex = 1;
            label1.Text = "Nume";
            // 
            // label2
            // 
            label2.AutoSize = true;
            label2.BackColor = Color.LightBlue;
            label2.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            label2.Location = new Point(116, 517);
            label2.Name = "label2";
            label2.Size = new Size(78, 22);
            label2.TabIndex = 2;
            label2.Text = "Prenume";
            // 
            // txtNume
            // 
            txtNume.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            txtNume.Location = new Point(218, 473);
            txtNume.Name = "txtNume";
            txtNume.Size = new Size(329, 30);
            txtNume.TabIndex = 2;
            // 
            // txtPrenume
            // 
            txtPrenume.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            txtPrenume.Location = new Point(218, 514);
            txtPrenume.Name = "txtPrenume";
            txtPrenume.Size = new Size(329, 30);
            txtPrenume.TabIndex = 3;
            // 
            // btnAdaugaAngajat
            // 
            btnAdaugaAngajat.BackColor = Color.SkyBlue;
            btnAdaugaAngajat.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            btnAdaugaAngajat.Location = new Point(117, 556);
            btnAdaugaAngajat.Name = "btnAdaugaAngajat";
            btnAdaugaAngajat.Size = new Size(430, 40);
            btnAdaugaAngajat.TabIndex = 4;
            btnAdaugaAngajat.Text = "Adaugă angajatul";
            btnAdaugaAngajat.UseVisualStyleBackColor = false;
            btnAdaugaAngajat.Click += btnAdaugaAngajat_Click;
            // 
            // btnStergeAngajat
            // 
            btnStergeAngajat.BackColor = Color.SkyBlue;
            btnStergeAngajat.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            btnStergeAngajat.Location = new Point(117, 602);
            btnStergeAngajat.Name = "btnStergeAngajat";
            btnStergeAngajat.Size = new Size(430, 40);
            btnStergeAngajat.TabIndex = 5;
            btnStergeAngajat.Text = "Șterge angajatul";
            btnStergeAngajat.UseVisualStyleBackColor = false;
            btnStergeAngajat.Click += btnStergeAngajat_Click;
            // 
            // btnActualizeazaAngajat
            // 
            btnActualizeazaAngajat.BackColor = Color.SkyBlue;
            btnActualizeazaAngajat.Font = new Font("Sylfaen", 10.2F, FontStyle.Regular, GraphicsUnit.Point);
            btnActualizeazaAngajat.Location = new Point(116, 648);
            btnActualizeazaAngajat.Name = "btnActualizeazaAngajat";
            btnActualizeazaAngajat.Size = new Size(430, 40);
            btnActualizeazaAngajat.TabIndex = 6;
            btnActualizeazaAngajat.Text = "Actualizează angajatul";
            btnActualizeazaAngajat.UseVisualStyleBackColor = false;
            btnActualizeazaAngajat.Click += btnActualizeazaAngajat_Click;
            // 
            // CabinetOftalmologic
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackgroundImage = (Image)resources.GetObject("$this.BackgroundImage");
            ClientSize = new Size(657, 721);
            Controls.Add(btnActualizeazaAngajat);
            Controls.Add(btnStergeAngajat);
            Controls.Add(btnAdaugaAngajat);
            Controls.Add(txtPrenume);
            Controls.Add(txtNume);
            Controls.Add(label2);
            Controls.Add(label1);
            Controls.Add(dataGridViewChild);
            Controls.Add(dataGridViewParent);
            Icon = (Icon)resources.GetObject("$this.Icon");
            Name = "CabinetOftalmologic";
            Text = "Cabinet Oftalmologic";
            Load += CabinetOftalmologic_Load;
            ((System.ComponentModel.ISupportInitialize)dataGridViewParent).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGridViewChild).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private DataGridView dataGridViewParent;
        private DataGridView dataGridViewChild;
        private Label label1;
        private Label label2;
        private TextBox txtNume;
        private TextBox txtPrenume;
        private Button btnAdaugaAngajat;
        private Button btnStergeAngajat;
        private Button btnActualizeazaAngajat;
    }
}