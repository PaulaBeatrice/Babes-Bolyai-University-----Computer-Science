<!DOCTYPE html>
<html>
<head>
    <title>Problema 5</title>
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
		</form>
		<hr>
	<div>
	
	<?php
	function test_input($data) {
	  $data = trim($data);
	  $data = stripslashes($data);
	  $data = htmlspecialchars($data);
	  return $data;
	}
	
	$username = "";
	$password = "";
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
		$username = test_input($_POST["username"]);
		$password = test_input($_POST["password"]);
		
		if (preg_match("/^[a-zA-Z][a-zA-Z0-9-_\.]{0,20}$/",$username)) {
			$con = mysqli_connect("localhost", "root", "","pw");
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
			    $stmt->close(); // Închide rezultatul declarației precedente

			    $sqlUpdate = "UPDATE `connected_user` SET `username` = ? WHERE `id` = 1";
			    $stmtUpdate = $con->prepare($sqlUpdate);
			    $stmtUpdate->bind_param("s", $username);

			    if ($stmtUpdate->execute()) {
			        $stmtUpdate->close(); // Închide rezultatul declarației de actualizare

			        header("Location: indexMyPhotos.php");
			    } else {
			        echo "Error: " . $sqlUpdate . "<br>" . $stmtUpdate->error;
			    }
			} else {
			    $stmt->close(); // Închide rezultatul declarației precedente

			    header("Location: index.php");
			}

		}
	}
	?>
</body>
</html>