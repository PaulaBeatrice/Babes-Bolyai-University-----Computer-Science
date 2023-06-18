<?php
	$con = mysqli_connect("localhost", "root", "", "pw");
	if (!$con) {
	    die('Connection failed: ' . mysqli_error());
	}

	$username = $_POST["username"];
	$password = $_POST["password"];

	$patternUss = '/^[a-zA-Z][a-zA-Z0-9-_\.]{0,20}$/'; // Începe cu o literă, urmată de 0-20 de caractere
	$patternPass = '/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/'; // cel putin o cifra, o litera mica, mare, fara spatii
	preg_match($patternUss, $username, $matchesUss);
	preg_match($patternPass, $password, $matchesPass);

	// var_dump($matchesUss);
	// var_dump($matchesPass);

	if (count($matchesUss) == 0 || count($matchesPass) == 0) {
		echo "Campurile nu au fost completate corespunzator!!!";
	    echo file_get_contents("index.html");
	} else {
	    // Verificare existență utilizator
	    $sql = "SELECT count(*) FROM `users` WHERE `username` = ? AND `password` = ?";
	    $stmt = $con->prepare($sql);
	    $stmt->bind_param("ss", $username, $password);
	    $stmt->execute();
	    $stmt->bind_result($count);
	    $stmt->fetch();

	    if ($count == 1) {
	        header("Location: wellcome.html");
	    } else {
	        echo "Credentiale inexistente!!!";
	        echo file_get_contents("index.html");
	    }
	}

	mysqli_close($con);
?>
