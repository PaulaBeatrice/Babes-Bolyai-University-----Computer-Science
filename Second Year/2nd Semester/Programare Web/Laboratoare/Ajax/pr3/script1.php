<?php

  $mysqli = new mysqli("localhost", "root", "", "pw");

  $sql = "SELECT Id FROM studenti;";
  $result = $mysqli -> query($sql);

  echo "<select id=\"select\" onchange=\"fillform(this.value)\">";

  while($row = mysqli_fetch_array($result, MYSQLI_NUM)){
    echo "<option value=\"" . $row[0] . "\"> " . $row[0] . " </option>";
  }

  echo "</select>";

?>