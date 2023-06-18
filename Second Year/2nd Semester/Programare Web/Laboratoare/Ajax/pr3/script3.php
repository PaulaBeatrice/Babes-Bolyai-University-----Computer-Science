<?php

$mysqli = new mysqli("localhost", "root", "", "pw");

$sql = "UPDATE studenti SET Nume=?, Prenume=?, Telefon=? WHERE Id=?;";
$stmt = $mysqli->prepare($sql);
$stmt -> bind_param("sssd", $_GET['nume'], $_GET['prenume'], $_GET['telefon'], $_GET['id']);
$stmt -> execute();

?>