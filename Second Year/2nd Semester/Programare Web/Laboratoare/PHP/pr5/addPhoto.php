<!DOCTYPE html>
<html>
<head>
    <title>Problema 5</title>
</head>
<body>
	<div class="header">
	  <h1>Add photo</h1>
	</div>

	<div class="topnav">
	  <a href="indexMyPhotos.php">Profile</a>
	  <a href="addPhoto.php">Add photos</a>
	  <a href="viewPhotos.php">Feed</a>
	  <a href="index.php">Logout</a>
	</div>

	<div class='container'>
		<form method="POST" action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" enctype="multipart/form-data">
			<input type="file" name="imagine">
			<p>Descrierea pozei</p>
			<textarea id="text" cols="40" rows="2" name="descriere" ></textarea>
			<button type="submit" name="upload">Upload</button>
		</form>
			<?php
				$con = mysqli_connect("localhost", "root", "","pw");
				if (!$con) {
					die('Connection failed: ' . mysqli_error());
				}
				
				$sql="SELECT `username` FROM `connected_user`";
				$result = mysqli_query($con, $sql);
				$username= '';
				while($row = mysqli_fetch_array($result)) {
					$username = $row["username"];
				}
				
				if (isset($_POST['upload'])) {
				    $imagine = $_FILES['imagine']['name'];
				    $descriere = mysqli_real_escape_string($con, $_POST['descriere']);
				    $target = "images/".basename($imagine);

				    $sql = "INSERT INTO imagini (username, imagine, descriere) VALUES (?, ?, ?)";
				    $stmt = $con->prepare($sql);
				    $stmt->bind_param("sss", $username, $imagine, $descriere);

				    if ($stmt->execute() && move_uploaded_file($_FILES['imagine']['tmp_name'], $target)) {
				        $msg = "Image uploaded successfully";
				    } else {
				        $msg = "Failed to upload image";
				    }
				    echo $msg;

				    $stmt->close();
				}

			?>
		</div>
	</div>
</body>
</html>