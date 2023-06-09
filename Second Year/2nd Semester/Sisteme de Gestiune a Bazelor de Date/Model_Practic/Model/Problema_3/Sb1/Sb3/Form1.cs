using Microsoft.Data.SqlClient;
using System.Data;

namespace Sb3
{
    public partial class Form1 : Form
    {
        string connectionString = "Server=DESKTOP-QMOV01R\\SQLEXPRESS;Database=Problema3;Integrated Security=true;TrustServerCertificate=true;";
        DataSet ds = new DataSet();
        SqlDataAdapter parentAdapter = new SqlDataAdapter();
        SqlDataAdapter childAdapter = new SqlDataAdapter();
        BindingSource parentBS = new BindingSource();
        BindingSource childBS = new BindingSource();

        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection connection = new SqlConnection(connectionString))
                {
                    connection.Open();

                    parentAdapter.SelectCommand = new SqlCommand("SELECT * FROM Producatori;", connection);
                    childAdapter.SelectCommand = new SqlCommand("SELECT * FROM Biscuiti;", connection);
                    parentAdapter.Fill(ds, "Producatori");
                    childAdapter.Fill(ds, "Biscuiti");
                    parentBS.DataSource = ds.Tables["Producatori"];
                    tabelProducatori.DataSource = parentBS;
                    DataColumn parentColumn = ds.Tables["Producatori"].Columns["cod_p"];
                    DataColumn childColumn = ds.Tables["Biscuiti"].Columns["cod_p"];
                    DataRelation relation = new DataRelation("FK_Relation", parentColumn, childColumn);
                    ds.Relations.Add(relation);
                    childBS.DataSource = parentBS;
                    childBS.DataMember = "FK_Relation";
                    tabelBiscuiti.DataSource = childBS;

                    txtNume.DataBindings.Add("Text", childBS, "nume_b");
                    txtCalorii.DataBindings.Add("Text", childBS, "nr_calorii");
                    txtPret.DataBindings.Add("Text", childBS, "pret");
                    txtProducator.DataBindings.Add("Text", childBS, "cod_p");

                    connection.Close();
                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }

        }

        private void btnAdd_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    string addQuery = "Insert Into Biscuiti(nume_b,nr_calorii,pret,cod_p) Values (@nume,@calorii,@pret,@cod);";
                    SqlCommand cmd = new SqlCommand(addQuery, conn);
                    cmd.Parameters.AddWithValue("@nume", txtNume.Text);
                    cmd.Parameters.AddWithValue("@calorii", txtCalorii.Text);
                    cmd.Parameters.AddWithValue("@pret", txtPret.Text);
                    cmd.Parameters.AddWithValue("@cod", txtProducator.Text);
                    childAdapter.InsertCommand = cmd;
                    childAdapter.SelectCommand.Connection = conn;

                    childAdapter.InsertCommand.ExecuteNonQuery();

                    if (ds.Tables.Contains("Biscuiti"))
                        ds.Tables["Biscuiti"].Clear();
                    childAdapter.Fill(ds, "Biscuiti");

                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void btnUpdate_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    string updateQuery = "Update Biscuiti SET nume_b = @nume, nr_calorii=@calorii,pret=@pret WHERE cod_b=@cod;";

                    SqlCommand cmd = new SqlCommand(updateQuery, conn);
                    cmd.Parameters.AddWithValue("@nume", txtNume.Text);
                    cmd.Parameters.AddWithValue("@calorii", txtCalorii.Text);
                    cmd.Parameters.AddWithValue("@pret", txtPret.Text);
                    cmd.Parameters.AddWithValue("@cod", tabelBiscuiti.CurrentRow.Cells["cod_b"].Value.ToString());
                    childAdapter.UpdateCommand = cmd;
                    childAdapter.SelectCommand.Connection = conn;

                    childAdapter.UpdateCommand.ExecuteNonQuery();

                    if (ds.Tables.Contains("Biscuiti"))
                        ds.Tables["Biscuiti"].Clear();
                    childAdapter.Fill(ds, "Biscuiti");

                }
            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }

        private void btnDelete_Click(object sender, EventArgs e)
        {
            try
            {
                using (SqlConnection conn = new SqlConnection(connectionString))
                {
                    conn.Open();
                    string deleteQuery = "Delete From Biscuiti Where cod_b = @cod";
                    SqlCommand cmd = new SqlCommand(deleteQuery, conn);
                    cmd.Parameters.AddWithValue("cod", tabelBiscuiti.CurrentRow.Cells["cod_b"].Value.ToString());
                    childAdapter.DeleteCommand = cmd;
                    childAdapter.SelectCommand.Connection = conn;

                    childAdapter.DeleteCommand.ExecuteNonQuery();

                    if (ds.Tables.Contains("Biscuiti"))
                        ds.Tables["Biscuiti"].Clear();
                    childAdapter.Fill(ds, "Biscuiti");
                }

            }
            catch (Exception ex)
            {
                MessageBox.Show(ex.Message);
            }
        }
    }
}