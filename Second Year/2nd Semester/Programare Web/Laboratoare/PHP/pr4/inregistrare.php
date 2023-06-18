<?php
require 'PHPMailer\src\PHPMailer.php';
require 'PHPMailer\src\Exception.php';
require 'PHPMailer\src\SMTP.php';

use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception;

	$con = mysqli_connect("localhost", "root", "","pw");
	if (!$con) {
		die('Connection failed: ' . mysqli_error());
	}
	$username = $_POST["username"];
	$password = $_POST["password"];
	$email = $_POST["email"];
		
	$pattern = '/^[a-zA-Z][a-zA-Z0-9-_\.]{0,20}$/';
	$patternPass = '/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?!.*\s).*$/';

	preg_match($pattern, $username, $matchesUss);
	preg_match($patternPass, $password, $matchesPass);

	if (count($matchesUss)==0){
		echo file_get_contents( "index.html" );
		echo 'Invalid username format';
	}
	else if (count($matchesPass) == 0){
		echo file_get_contents( "index.html" );
		echo 'Invalid password format';
	}
	else if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
		echo file_get_contents( "index.html" );
		echo 'Invalid e-mail format';
	}
	else{
		// verificare existenta user
		 $sql = "SELECT count(*) FROM `users` WHERE `username` = ? AND `password` = ?";
	    $stmt = $con->prepare($sql);
	    $stmt->bind_param("ss", $username, $password);
	    $stmt->execute();
	    $stmt->bind_result($count);
	    $stmt->fetch();

	    if ($count == 1) {
	      echo file_get_contents( "index.html" );
			echo 'User existent!!!';
	    }
		else {
			$mail = new PHPMailer(true);

			// Configurarea serverului SMTP
			$mail->isSMTP();
			$mail->Host = 'smtp.gmail.com';
			$mail->SMTPAuth = true;
			$mail->Username = 'beatriceeebeaaa@gmail.com';
			$mail->Password = 'Informatica10\*';
			$mail->SMTPSecure = 'tls';
			$mail->Port = 587;

			// Destinatar, subiect și conținut
			$mail->setFrom('beatriceeebeaaa@gmail.com', 'Paula');
			$mail->addAddress($email);
			$mail->Subject = 'This is the subject';
			$mail->Body = '<b>This is HTML message.</b><h1>This is headline.</h1>';

			if ($mail->send()) {
				echo 'Message sent successfully...';
			} else {
				echo 'Message could not be sent. Mailer Error: ' . $mail->ErrorInfo;
			}
		}
	}
	mysqli_close($con);
?> 