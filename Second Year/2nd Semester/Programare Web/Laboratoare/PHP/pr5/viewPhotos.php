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

	<div class='container'>
		<div class = 'content'>
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
				
				$sql2 = "SELECT * FROM `imagini` WHERE `username` != ?";
				$stmt = $con->prepare($sql2);
				$stmt->bind_param("s", $username);
				$stmt->execute();
				$result = $stmt->get_result();

				while($row = $result->fetch_assoc()) {
				    echo "<h2> User: " . $row["username"] ."</h2>";
				    echo "<img src = 'images/" . $row["imagine"] . "'>";
				    echo "<p>" . $row["descriere"] . "</p>";
				    echo "<hr><br>";
				}

				$stmt->close();
			?>
		</div>
	</div>
	
</body>
</html>