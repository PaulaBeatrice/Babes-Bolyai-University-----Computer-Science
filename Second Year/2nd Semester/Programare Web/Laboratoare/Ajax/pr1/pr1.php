<?php

header("Access-Control-Allow-Origin: *");

$mysqli = new mysqli("localhost", "root", "", "pw");

if($mysqli->connect_error) {
  exit('Could not connect');
}

$sql = "SELECT Oras2 FROM trenuri WHERE Oras1 = ?;";

$stmt = $mysqli->prepare($sql);
$stmt -> bind_param('s', $_POST['q']);
$stmt -> execute();
$stmt -> bind_result($result);

echo "<ol>";

while($stmt -> fetch()){
    echo "<li>" . $result . "</li>";
}

echo "</ol>";

$stmt->close();

?>