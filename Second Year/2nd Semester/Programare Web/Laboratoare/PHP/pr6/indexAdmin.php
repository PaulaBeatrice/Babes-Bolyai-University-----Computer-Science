<!DOCTYPE html>
<html>
<head>
    <title>Problema 6</title>
</head>
<body>

<div class ="container">
	<table>
		<tr>
			<td>
			<form action="index.php">
				<button type="submit" >Log out</button>
			</form>
			</td>

			<td>
				<h3>Comentarii in asteptare</h3>
				<?php
					$con = mysqli_connect("localhost", "root", "","pw");
					if (!$con) {
						die('Connection failed: ' . mysqli_error());
					}
					
					$sql = "SELECT * FROM `comentarii` WHERE `approved` = 0;";
					$result = mysqli_query($con, $sql);
					
					while($row = mysqli_fetch_array($result)){
						echo "<table border = 1>";
							echo "<tr>";
								echo "<td>";
								echo "<form action ='saveMessage.php'>";
								echo "<input type='hidden' name='id' value = '".$row["id"]."'>";
								echo "<button type='submit'> Aproba postarea </button>";
								echo "</form>";
								echo "</td>";

								echo "<td>";
								echo "<strong>" .$row["username"]. ":" ."</strong>";
								echo "<p>" .$row["comentariu"] . "</p>";
								echo "</td>";
							echo "</tr>";
						echo "</table>";
					}
				?>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>