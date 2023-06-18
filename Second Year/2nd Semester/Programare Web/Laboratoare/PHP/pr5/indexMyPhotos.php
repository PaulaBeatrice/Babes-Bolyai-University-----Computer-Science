<!DOCTYPE html>
<html>
<head>
    <title>Problema 5</title>
</head>
<body>
	<div class="header">
	  <h1>Photos</h1>
	</div>

	<div class="topnav">
	  <a href="indexMyPhotos.php">Profile</a>
	  <a href="addPhoto.php">Add photos</a>
	  <a href="viewPhotos.php">Feed</a>
	  <a href="index.php">Logout</a>
	</div>

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
			
		$sql2 = "SELECT * FROM `imagini` WHERE `username` = ?";
		$stmt2 = $con->prepare($sql2);
		$stmt2->bind_param("s", $username);
		$stmt2->execute();
		$result = $stmt2->get_result();

		while($row = $result->fetch_assoc()) {
		    echo "<img src='images/" . $row["imagine"] . "'>";
		    echo "<p>" . $row["descriere"] . "</p>";
		    echo "<form action='removePhoto.php' method='POST'>";
		    echo "<input type='hidden' name='id' value='" . $row['imagine'] . "'>";
		    echo "<button type='submit'>Delete</button>";
		    echo "</form>";
		}

		$stmt2->close();

	?>
	

</body>
</html>