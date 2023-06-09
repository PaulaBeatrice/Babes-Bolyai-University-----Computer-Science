namespace WinFormsApp1
{
    partial class Form1
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
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            dataGrid_Parent = new DataGridView();
            tableName_Parent = new Label();
            dataGrid_Child = new DataGridView();
            tableName_Child = new Label();
            panel_Parent = new Panel();
            panel_Child = new Panel();
            addButton = new Button();
            updateButton = new Button();
            deleteButton = new Button();
            messageToUser = new Label();
            ((System.ComponentModel.ISupportInitialize)dataGrid_Parent).BeginInit();
            ((System.ComponentModel.ISupportInitialize)dataGrid_Child).BeginInit();
            SuspendLayout();
            // 
            // dataGrid_Parent
            // 
            dataGrid_Parent.BackgroundColor = Color.WhiteSmoke;
            dataGrid_Parent.BorderStyle = BorderStyle.Fixed3D;
            dataGrid_Parent.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGrid_Parent.Location = new Point(41, 74);
            dataGrid_Parent.Name = "dataGrid_Parent";
            dataGrid_Parent.RowHeadersWidth = 51;
            dataGrid_Parent.RowTemplate.Height = 24;
            dataGrid_Parent.Size = new Size(361, 197);
            dataGrid_Parent.TabIndex = 0;
            dataGrid_Parent.CellClick += dataGrid_Parent_CellClick;
            // 
            // tableName_Parent
            // 
            tableName_Parent.AutoSize = true;
            tableName_Parent.Font = new Font("Microsoft Sans Serif", 12F, FontStyle.Bold, GraphicsUnit.Point);
            tableName_Parent.Location = new Point(41, 25);
            tableName_Parent.Name = "tableName_Parent";
            tableName_Parent.Size = new Size(75, 25);
            tableName_Parent.TabIndex = 1;
            tableName_Parent.Text = "Parent";
            // 
            // dataGrid_Child
            // 
            dataGrid_Child.BackgroundColor = Color.WhiteSmoke;
            dataGrid_Child.BorderStyle = BorderStyle.Fixed3D;
            dataGrid_Child.ColumnHeadersHeightSizeMode = DataGridViewColumnHeadersHeightSizeMode.AutoSize;
            dataGrid_Child.Location = new Point(464, 65);
            dataGrid_Child.Name = "dataGrid_Child";
            dataGrid_Child.RowHeadersWidth = 51;
            dataGrid_Child.RowTemplate.Height = 24;
            dataGrid_Child.Size = new Size(383, 206);
            dataGrid_Child.TabIndex = 2;
            dataGrid_Child.CellClick += dataGrid_Child_CellClick;
            // 
            // tableName_Child
            // 
            tableName_Child.AutoSize = true;
            tableName_Child.Font = new Font("Microsoft Sans Serif", 12F, FontStyle.Bold, GraphicsUnit.Point);
            tableName_Child.Location = new Point(459, 25);
            tableName_Child.Name = "tableName_Child";
            tableName_Child.Size = new Size(62, 25);
            tableName_Child.TabIndex = 3;
            tableName_Child.Text = "Child";
            // 
            // panel_Parent
            // 
            panel_Parent.BackColor = Color.WhiteSmoke;
            panel_Parent.Location = new Point(46, 392);
            panel_Parent.Name = "panel_Parent";
            panel_Parent.Size = new Size(356, 216);
            panel_Parent.TabIndex = 4;
            // 
            // panel_Child
            // 
            panel_Child.BackColor = Color.WhiteSmoke;
            panel_Child.Location = new Point(464, 392);
            panel_Child.Name = "panel_Child";
            panel_Child.Size = new Size(383, 216);
            panel_Child.TabIndex = 0;
            // 
            // addButton
            // 
            addButton.FlatStyle = FlatStyle.Popup;
            addButton.Location = new Point(464, 316);
            addButton.Name = "addButton";
            addButton.Size = new Size(113, 40);
            addButton.TabIndex = 5;
            addButton.Text = "Adaugă";
            addButton.UseVisualStyleBackColor = true;
            addButton.Click += addButton_Click;
            // 
            // updateButton
            // 
            updateButton.FlatStyle = FlatStyle.Popup;
            updateButton.Location = new Point(598, 316);
            updateButton.Name = "updateButton";
            updateButton.Size = new Size(113, 40);
            updateButton.TabIndex = 6;
            updateButton.Text = "Actualizează";
            updateButton.UseVisualStyleBackColor = true;
            updateButton.Click += updateButton_Click;
            // 
            // deleteButton
            // 
            deleteButton.FlatStyle = FlatStyle.Popup;
            deleteButton.Location = new Point(734, 316);
            deleteButton.Name = "deleteButton";
            deleteButton.Size = new Size(113, 40);
            deleteButton.TabIndex = 7;
            deleteButton.Text = "Șterge";
            deleteButton.UseVisualStyleBackColor = true;
            deleteButton.Click += deleteButton_Click;
            // 
            // messageToUser
            // 
            messageToUser.AutoSize = true;
            messageToUser.Location = new Point(12, 503);
            messageToUser.Name = "messageToUser";
            messageToUser.Size = new Size(0, 20);
            messageToUser.TabIndex = 8;
            // 
            // Form1
            // 
            AutoScaleDimensions = new SizeF(120F, 120F);
            AutoScaleMode = AutoScaleMode.Dpi;
            BackgroundImage = (Image)resources.GetObject("$this.BackgroundImage");
            ClientSize = new Size(902, 643);
            Controls.Add(messageToUser);
            Controls.Add(deleteButton);
            Controls.Add(updateButton);
            Controls.Add(addButton);
            Controls.Add(panel_Child);
            Controls.Add(panel_Parent);
            Controls.Add(tableName_Child);
            Controls.Add(dataGrid_Child);
            Controls.Add(tableName_Parent);
            Controls.Add(dataGrid_Parent);
            Icon = (Icon)resources.GetObject("$this.Icon");
            Name = "Form1";
            Text = "Cabinet Oftalmologic";
            Load += Form1_Load;
            ((System.ComponentModel.ISupportInitialize)dataGrid_Parent).EndInit();
            ((System.ComponentModel.ISupportInitialize)dataGrid_Child).EndInit();
            ResumeLayout(false);
            PerformLayout();
        }

        #endregion

        private System.Windows.Forms.DataGridView dataGrid_Parent;
        private System.Windows.Forms.Label tableName_Parent;
        private System.Windows.Forms.DataGridView dataGrid_Child;
        private System.Windows.Forms.Label tableName_Child;
        private System.Windows.Forms.Panel panel_Parent;
        private System.Windows.Forms.Panel panel_Child;
        private System.Windows.Forms.Button addButton;
        private System.Windows.Forms.Button updateButton;
        private System.Windows.Forms.Button deleteButton;
        private System.Windows.Forms.Label messageToUser;
    }
}