<!DOCTYPE html>
<html>
<head>
    <title>Problema 3</title>
</head>
<body>
	<div class ="container">
		<h1>Login</h1>
		<hr>
		<form action = "<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" method = "POST" enctype="multipart/form-data">
			<label>Username</label><br>
			<input type="text" name='username' required><br>
			<label>Password</label><br>
			<input type="password" name='password' required>
			<br><br>
			<button type="submit" >Login </button>
			<br><br>
			<button type="button" onclick="window.location.href = 'showNote.php';">Show Notes</button>
		</form>
		<hr>
	</div>
	
	<?php
		function test_input($data) {
		  $data = trim($data); // eliminam caracterele inutile
		  $data = stripslashes($data); // eliminam "\"
		  $data = htmlspecialchars($data); // converteste caracterele speciale in entitati HTML
		  return $data;
		}
		
		$username = "";
		$password = "";
		$errorMessage = "";

		if ($_SERVER["REQUEST_METHOD"] == "POST") {
		    $username = test_input($_POST["username"]);
		    $password = test_input($_POST["password"]);

		    if (preg_match("/^[a-zA-Z][a-zA-Z0-9-_\.]{0,30}$/", $username)) {
		        $con = mysqli_connect("localhost", "root", "", "pw");
		        if (!$con) {
		            die('Connection failed: ' . mysqli_error());
		        }

		        $sql = "SELECT count(*) FROM `users` WHERE `username` = ? AND `password` = ?";
				$stmt = $con->prepare($sql);
				$stmt->bind_param("ss", $username, $password);
				$stmt->execute();
				$stmt->bind_result($count);
				$stmt->fetch();

				if ($count == 1) {
				    header("Location: addNota.php"); // redirectioneaza spre addNota.php
				} else {
				    header("Location: index.php"); // redirectioneaza spre index.php
				}

				$stmt->close();
				$con->close();

		        mysqli_close($con);
		    } else {
		    	$errorMessage = "Username-ul nu respectă pattern-ul dorit.";
			  }
		}
	?>

	<div class="error-message">
			<?php echo $errorMessage; ?>
	</div>
		
</body>
</html>
