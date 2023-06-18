<!DOCTYPE html>
<html>
<head>
    <title>Problema 3</title>
    <style>
        table {
            border-collapse: collapse;
        }

        th, td {
            border: 1px solid black;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
	<div class ="container">
		<h1>Note</h1>
		<hr>
	
		<table>
			<tr>
				<th>Nume</th>
				<th>Prenume</th>
				<th>Materie</th>
				<th>Nota</th>
			</tr>
			<?php
				$con = mysqli_connect("localhost", "root", "", "pw");
				if (!$con) {
					die('Connection failed: ' . mysqli_error());
				}
				$sql = "SELECT S.nume as 'nume', S.prenume as 'prenume', M.denumire as 'materie', N.nota as nota FROM studenti_php S, materii M, notare N WHERE S.id = N.idS and M.id = N.idD ";
				$result = mysqli_query($con, $sql);
		
				while($row = mysqli_fetch_array($result)){
					echo "<tr>";
					echo "<td>" . $row["nume"] . "</td>";
					echo "<td>" . $row["prenume"] . "</td>";
					echo "<td>" . $row["materie"] . "</td>";
					echo "<td>" . $row["nota"] . "</td>";
					echo "</tr>";
				}
			?>
		</table>

	<div>
</body>
</html>
