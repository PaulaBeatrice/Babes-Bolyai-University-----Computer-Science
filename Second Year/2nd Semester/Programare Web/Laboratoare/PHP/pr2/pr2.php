<?php
$conn = new mysqli("localhost", "root", "", "pw");
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$n = 0;
// Preluam nr de produse ce se vor afisa per pagina, din formular
if (isset($_POST['numar_produse'])) {
    $n = $_POST['numar_produse'];
}

if (isset($_GET['numar_produse'])) {
    $n = $_GET['numar_produse'];
}

// produsele din baza de dtae
$sqlCount = "SELECT COUNT(*) AS total FROM produse";
$resultCount = $conn->query($sqlCount);
$rowCount = $resultCount->fetch_assoc();
$totalProduse = $rowCount['total'];

// nr de pg
$numarPagini = ceil($totalProduse / $n);

// pagina curenta
$paginaCurenta = 1;
if (isset($_GET['pagina'])) {
    $paginaCurenta = $_GET['pagina'];
}

// limita
$offset = ($paginaCurenta - 1) * $n;

// preluarea produselor
$sql = "SELECT * FROM `produse` LIMIT $offset, $n";
$result = $conn->query($sql);

echo "<table style='border: 1px solid black; border-collapse: collapse;'>";
echo "<tr>";
echo "<th style='border: 1px solid black;'>Nume</th>";
echo "<th style='border: 1px solid black;'>Descriere</th>";
echo "<th style='border: 1px solid black;'>Pret</th>";
echo "</tr>";
while ($row = $result->fetch_assoc()) {
    echo "<tr>";
    echo "<td style='border: 1px solid black;'>" . $row['nume'] . "</td>";
    echo "<td style='border: 1px solid black;'>" . $row['descriere'] . "</td>";
    echo "<td style='border: 1px solid black;'>" . $row['pret'] . "</td>";
    echo "</tr>";
}
echo "</table>";



echo "<div class='paginare'>";
if ($paginaCurenta > 1) {
    echo "<a href='pr2.php?pagina=" . ($paginaCurenta - 1) . "&numar_produse=" . $n . "'>Anterior</a>";
}
if ($paginaCurenta < $numarPagini) {
    echo "<a href='pr2.php?pagina=" . ($paginaCurenta + 1) . "&numar_produse=" . $n . "'>Următor</a>";
}
echo "</div>";

echo "<form method='post' action='pr2.php'>";
echo "<label>Număr produse pe pagină:</label>";
echo "<select name='numar_produse'>";
echo "<option value='5'>5</option>";
echo "<option value='10'>10</option>";
echo "<option value='20'>20</option>";
echo "</select>";
echo "<input type='submit' value='Actualizează'>";
echo "</form>";
?>
