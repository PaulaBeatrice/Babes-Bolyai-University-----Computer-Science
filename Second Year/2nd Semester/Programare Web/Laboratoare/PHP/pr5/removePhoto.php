<?php
	$con = mysqli_connect("localhost", "root", "", "pw");
	if (!$con) {
	    die('Connection failed: ' . mysqli_error());
	}

	$imagine = $_POST["id"];

	$stmt = $con->prepare("DELETE FROM `imagini` WHERE `imagine` = ?");
	if ($stmt) {
	    $stmt->bind_param("s", $imagine);

	    if ($stmt->execute()) {
	        $stmt->close();
	        header("Location: indexMyPhotos.php");
	        exit;
	    } else {
	        echo "Error: " . $stmt->error;
	    }
	} else {
	    echo "Error preparing statement: " . $con->error;
	}

	mysqli_close($con);
?>
