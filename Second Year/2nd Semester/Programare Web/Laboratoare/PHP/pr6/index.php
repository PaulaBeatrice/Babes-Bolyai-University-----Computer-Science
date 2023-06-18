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
				<h2>Autentificare admin:</h2>
				<form  action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" method = "POST" enctype="multipart/form-data">
					<input type = 'hidden' name = 'tip' value ='login'>
					<label>Username</label><br>
					<input type="text" name='username' required><br>
					<label>Password</label><br>
					<input type="password" name='password' required>
					<br><br>
					<button type="submit" >Login </button>
				</form>
			</td>

			<td>
				<article>
					<h1>Reteta - pizza de casa</h1>
					<p>Pizza, unul dintre cele mai faimoase preparate italiene, si-a castigat popularitatea prin faptul ca poate fi pregatita in numeroase moduri si este intotdeauna savuroasa. In plus, prepararea nu este asa dificila pe cat pare si poti oricand sa iti gatesti acasa o pizza personalizata, dupa gustul tau.
					Afla in cele ce urmeaza cum prepari blatul de pizza, ce retete poti incerca si cateva sfaturi de care sa tii cont pentru o pizza reusita.</p>

					<h2>Cum se face blatul?</h2>
					<p>Primul pas este prepararea aluatului de pizza, pentru care ai nevoie de ingrediente pe care le ai probabil deja in bucatarie. In cazul in care nu ai drojdie la indemana, afla cum prepari maia naturala acasa, pe care sa o pastrezi apoi pentru a face aluat de pizza sau paine de casa.</p>
					<h4>Ingrediente</h4>
					<ul>
						<li>350g faina</li>
						<li>7g drojdie uscata</li>
						<li>240ml apa calduta</li>
						<li>2 lingurite zahar</li>
						<li>2 lingurite ulei de masline</li>
						<li>o lingura sare</li>
					</ul>

					<h5>Mod de preparare</h5>
					<p>Incepe prin a pregati drojdia, amestecand-o cu apa calduta si zaharul si lasa compozitia sa se odihneasca pentru 10 minute. Vei sti ca este gata si ca drojdia este activata atunci cand incep sa apara bule la suprafata apei. Amesteca apoi faina cu sarea si uleiul de masline intr-un bol sau poti folosi chiar si robotul de bucatarie. Odata ce compozitia este omogenizata, poti adauga amestecul de drojdie si continua sa framanti sau sa lasi robotul de bucatarie sa isi faca treaba.</p>
					<br>
					<p>Alatul de pizza este gata atunci cand este elastic, omogen si usor lipicios. Muta apoi aluatul intr-un vas curat, pe care l-ai uns in prealabil cu ulei de masline si lasa-l la dospit pentru doua ore, intr-un loc caldut. Pentru a obtine un blat de pizza mai bun, il poti lasa la dospit peste noapte, in frigider.</p>
					<br>
					<p>Aluatul de pizza este dospit atunci cand isi dubleaza volumul. Imparte-l in doua parti egale, pe care le vei modela sub forma unor bile si pe care le vei intinde cu sucitorul. Poti pudra tava in care vei coace pizza cu putin malai si apoi poti adauga blatul de pizza. Ai grija ca marginile sa fie putin mai groase decat in centrul aluatului.</p>

					<br>
					<p>Foloseste o furculita pentru a intepa blatul in cateva locuri, astfel incat sa nu se umfle prea tare in timpul coacerii. Apoi e momentul sa adaugi sosul de rosii si alte toppinguri preferate.</p>
				</article>
				<br><br>
				<h3> Comentarii</h3>
				<?php
					$con = mysqli_connect("localhost", "root", "","pw");
					if (!$con) {
						die('Connection failed: ' . mysqli_error());
					}
					
					$sql = "SELECT * FROM `comentarii` WHERE `approved` = 1 ;";
					$result = mysqli_query($con, $sql);
					echo "<table>";
					while($row = mysqli_fetch_array($result)){
						echo "<tr>";
							echo "<th>" .$row["username"]. ":" ."</th>";
							echo "<td>" .$row["comentariu"] ."</td>";
						echo "</tr>";
					}
					echo "</table>";
				?>
				<br>
				<h3> Adauga un nou comentariu </h3>
				<form action="<?php echo htmlspecialchars($_SERVER['PHP_SELF']);?>" method = "POST">
					<input type = 'hidden'  name = 'tip' value = 'comentariu'>
					<input type = 'text' name = 'username'><br><br>
					<input type = 'text' name = 'comment'><br><br>
				
					<button type ='submit'>Submit</button>
				</form>
			</td>
		</tr>
	</table>
	</div>
	
	<?php
		function test_input($data) {
		  $data = trim($data);
		  $data = stripslashes($data);
		  $data = htmlspecialchars($data);
		  return $data;
		}
		
		$username = "";
		$password = "";
		$message = "";
		$tip = "";
		if ($_SERVER["REQUEST_METHOD"] == "POST") {
			$tip = test_input($_POST["tip"]);
			if ($tip =='login'){	
				$username = test_input($_POST["username"]);
				$password = test_input($_POST["password"]);
				if (preg_match("/^[a-zA-Z][a-zA-Z0-9-_\.]{0,30}$/",$username)) {
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
					    header("Location: indexAdmin.php");
					} else {
					    header("Location: index.php");
					}

					$stmt->close();
					$con->close();
					mysqli_close($con);
				}
				else{
					echo "error";
				}			
			}
			else  {
				if ($tip =='comentariu'){
					$con = mysqli_connect("localhost", "root", "", "pw");
				if (!$con) {
				    die('Connection failed: ' . mysqli_error());
				}

				$username = test_input($_POST["username"]);
				$comentariu = test_input($_POST["comment"]);

				$sql = "INSERT INTO `comentarii` (username, comentariu, approved) VALUES (?, ?, 0)";
				$stmt = $con->prepare($sql);
				$stmt->bind_param("ss", $username, $comentariu);

				if ($stmt->execute()) {
				    echo "Sending succesfull";
				    header("index.php");
				} else {
				    echo "Error: " . $stmt->error;
				}

				$stmt->close();
				mysqli_close($con);
				}
			}
		}
	?>
</body>
</html>