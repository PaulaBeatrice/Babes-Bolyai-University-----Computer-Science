<?php
// conectează la baza de date
$mysqli = new mysqli("localhost", "root", "", "pw");

// verifică conexiunea
if($mysqli->connect_error) {
  exit('Could not connect');
}

// selectează toate orasele din tabela Orase
$sql = "SELECT DISTINCT Oras1 FROM trenuri";
$result = $mysqli->query($sql);

// creează un array cu toate orasele
$orase = array();
if ($result->num_rows > 0) {
  while($row = $result->fetch_assoc()) {
    array_push($orase, $row["Oras1"]);
  }
}

// returnează array-ul de orase sub forma de JSON
echo json_encode($orase);

$mysqli->close();
?>
