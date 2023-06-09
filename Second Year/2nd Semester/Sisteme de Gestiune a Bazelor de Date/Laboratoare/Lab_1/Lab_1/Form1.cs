using System.Data;
using System.Windows.Forms;
using Microsoft.Data.SqlClient;

namespace Laborator_1
{
    public partial class CabinetOftalmologic : Form
    {
        string connectionString = @"Server=DESKTOP-QMOV01R\SQLEXPRESS;Database=CabinetOftalmologic;
        Integrated Security=true;TrustServerCertificate=true;";
        DataSet ds = new DataSet();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        BindingSource parentBS = new BindingSource();
        BindingSource childBS = new BindingSource();

        private int rolSelectat = 0;
        private int angajatSelectat = 0;
        private string numeAngajat;
        private string prenumeAngajat;
        public CabinetOftalmologic()
        {
            InitializeComponent();
        }

        public void refreshDataGriedView()
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Roluri;",
                        connection);
                    childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Angajati;",
                        connection);
                    ds.Clear();
                    parentAdapter.Fill(ds, "Roluri");
                    childAdapter.Fill(ds, "Angajati");
                    parentBS.DataSource = ds.Tables["Roluri"];
                    dataGridViewParent.DataSource = parentBS;
                    dataGridViewChild.DataSource = childBS;

                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewParent_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && e.ColumnIndex >= 0)
            {
                DataGridViewRow row = dataGridViewParent.Rows[e.RowIndex];
                int.TryParse(row.Cells[0].Value.ToString(), out rolSelectat);
            }
            txtNume.Text = "";
            txtPrenume.Text = "";
        }

        private void dataGridViewChild_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            if (e.RowIndex >= 0 && e.ColumnIndex >= 0)
            {
                DataGridViewRow row = dataGridViewChild.Rows[e.RowIndex];
                int.TryParse(row.Cells[0].Value.ToString(), out angajatSelectat);
                numeAngajat = row.Cells[1].Value.ToString();
                prenumeAngajat = row.Cells[2].Value.ToString();
            }
            txtNume.Text = numeAngajat;
            txtPrenume.Text = prenumeAngajat;
        }

        private void btnAdaugaAngajat_Click(object sender, EventArgs e)
        {
            if (rolSelectat == 0)
            {
                MessageBox.Show("ALEGETI UN ROL PENTRU ANGAJAT!!!");
            }
            else
            {
                try
                {
                    using (SqlConnection connection = new SqlConnection(connectionString))
                    {
                        connection.Open();
                        SqlCommand insertCommand = new SqlCommand("INSERT INTO Angajati(nume, prenume, " +
                                   "rol) VALUES (@nume, @prenume, @rol);", connection);
                        insertCommand.Parameters.AddWithValue("@nume", txtNume.Text);
                        insertCommand.Parameters.AddWithValue("@prenume", txtPrenume.Text);
                        insertCommand.Parameters.AddWithValue("@rol", rolSelectat);
                        //MessageBox.Show($"Valoarea selectată este: {rolSelectat}");
                        int inserRowCount = insertCommand.ExecuteNonQuery();
                        MessageBox.Show("Angajatul a fost adaugat cu succes!");
                        txtNume.Text = "";
                        txtPrenume.Text = "";
                        refreshDataGriedView();
                        connection.Close();
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }

        private void btnStergeAngajat_Click(object sender, EventArgs e)
        {
            if (angajatSelectat == 0)
            {
                MessageBox.Show("ALEGETI UN ANGAJAT!!!");
            }
            else
            {
                try
                {
                    using (SqlConnection connection = new SqlConnection(connectionString))
                    {
                        connection.Open();
                        SqlCommand deleteCommand = new SqlCommand("DELETE FROM Angajati WHERE id=@id;", connection);
                        deleteCommand.Parameters.AddWithValue("@id", angajatSelectat);
                        int deleteRowCount = deleteCommand.ExecuteNonQuery();
                        MessageBox.Show("Angajatul a fost sters");
                        txtNume.Text = "";
                        txtPrenume.Text = "";
                        refreshDataGriedView();
                        connection.Close();
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }

        private void btnActualizeazaAngajat_Click(object sender, EventArgs e)
        {
            if (angajatSelectat == 0)
            {
                MessageBox.Show("ALEGETI UN ANGAJAT!!!");
            }
            else
            {
                try
                {
                    using (SqlConnection connection = new SqlConnection(connectionString))
                    {
                        connection.Open();
                        SqlCommand updateCommand = new SqlCommand("UPDATE Angajati SET nume=@nume, prenume=@prenume WHERE id=@id;", connection);
                        updateCommand.Parameters.AddWithValue("@nume", txtNume.Text);
                        updateCommand.Parameters.AddWithValue("@prenume", txtPrenume.Text);
                        updateCommand.Parameters.AddWithValue("@id", angajatSelectat);
                        int updateRowCount = updateCommand.ExecuteNonQuery();
                        MessageBox.Show("Angajatul a fost actualizat");
                        txtNume.Text = "";
                        txtPrenume.Text = "";
                        refreshDataGriedView();
                        connection.Close();
                    }
                }
                catch (Exception ex)
                {
                    MessageBox.Show(ex.Message);
                }
            }
        }

        private void CabinetOftalmologic_Load(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Roluri;",
                        connection);
                    childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Angajati;",
                        connection);
                    parentAdapter.Fill(ds, "Roluri");
                    childAdapter.Fill(ds, "Angajati");
                    parentBS.DataSource = ds.Tables["Roluri"];
                    dataGridViewParent.DataSource = parentBS;
                    DataColumn parentColumn = ds.Tables["Roluri"].Columns["id"];
                    DataColumn childColumn = ds.Tables["Angajati"].Columns["rol"];
                    DataRelation relation = new DataRelation("FK_Roluri_Angajati",
                        parentColumn, childColumn);
                    ds.Relations.Add(relation);
                    childBS.DataSource = parentBS;
                    childBS.DataMember = "FK_Roluri_Angajati";
                    dataGridViewChild.DataSource = childBS;

                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void dataGridViewChild_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void dataGridViewParent_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }
    }
}