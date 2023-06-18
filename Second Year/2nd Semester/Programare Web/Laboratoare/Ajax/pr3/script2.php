<?php
$mysqli = new mysqli("localhost", "root", "", "pw");

$sql2 = "SELECT * FROM studenti WHERE Id = ?;";
$stmt = $mysqli->prepare($sql2);
$stmt -> bind_param('d', $_GET['id']);
$stmt -> execute();
$stmt -> bind_result($aux, $col1, $col2, $col3);

echo "<form>";

while($stmt -> fetch()){
  echo "<label for=\"nume\">Nume:</label><br>";
  echo "<input type=\"text\" id=\"nume\" name=\"nume\" value=" . $col2 . " oninput= \"handleChange()\"  ><br>";

  echo "<label for=\"prenume\">Prenume:</label><br>";
  echo "<input type=\"text\" id=\"prenume\" name=\"prenume\" value=" . $col1 . " oninput= \"handleChange()\"><br>";

  echo "<label for=\"telefon\">Telefon:</label><br>";
  echo "<input type=\"text\" id=\"telefon\" name=\"telefon\" value=" . $col3 . " oninput= \"handleChange()\"><br>";
}

echo "<input type=\"submit\" value=\"Save\" onclick=\"salveaza()\" id=\"save\" disabled>";

echo "</form>";

?>

<script>
   function salveaza(){

      if(true == confirm("Are you sure you want to update this field?")){
              console.error()

        console.log("AICI")

        var theSelect = document.getElementById("select");
        var id = theSelect.options[theSelect.selectedIndex].value;
        var nume = document.getElementById("nume").value;
        var prenume = document.getElementById("prenume").value;
        var nrTel = document.getElementById("telefon").value;

        var xhttp;
        xhttp = new XMLHttpRequest();
        xhttp.onreadystatechange=function(){
          if(this.readyState == 4 && this.status == 200){
            confirm("Update completed");
          }
        };
        xhttp.open("GET", "script3.php?id="+id+"&prenume="+prenume+"&nume="+nume+"&telefon="+nrTel, true);
        xhttp.send(null);

      }

  function handleChange() {
      console.log("aici")
       document.getElementById("save").removeAttribute("disabled");
      // document.getElementById("save").disabled = false;
  }
</script>