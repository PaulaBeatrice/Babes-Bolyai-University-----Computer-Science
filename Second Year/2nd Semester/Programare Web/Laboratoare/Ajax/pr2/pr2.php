<?php
  $mysqli = new mysqli("localhost", "root", "", "pw");

  if($mysqli->connect_error) {
    exit('Could not connect');
  }

  // Obține numărul total de înregistrări
  $sql_count = "SELECT COUNT(*) FROM persoane;";
  $result_count = $mysqli->query($sql_count);
  $row_count = mysqli_fetch_array($result_count, MYSQLI_NUM);
  $total_records = $row_count[0];

  // Specifică dimensiunea paginii și calculează numărul total de pagini disponibile
  $page_size = 3;
  $total_pages = ceil($total_records / $page_size);


  $page_number = $_GET['id'];
  $start = ($page_number - 1) * $page_size;
  if ($start < 0) {
    $start = 0;
  }

  // Interoghează baza de date cu limita și start-ul corespunzătoare paginii curente
  $sql = "SELECT * FROM persoane LIMIT $start, $page_size;";
  $result = $mysqli->query($sql);

  echo "<table>";
   
  // Setez head-ul tabelului
  echo "<tr>";
  echo "<th>" . "Nume" . "</th>";
  echo "<th>" . "Prenume" . "</th>";
  echo "<th>" . "Telefon" . "</th>";
  echo "<th>" . "E-mail" . "</th>";
  echo "</tr>";
  while($row = mysqli_fetch_array($result, MYSQLI_NUM)){
    echo "<tr>";
    echo "<td>" . $row[0] . "</td>";
    echo "<td>" . $row[1] . "</td>";
    echo "<td>" . $row[2] . "</td>";
    echo "<td>" . $row[3] . "</td>";
    echo "</tr>";
  }

  echo "</table>";

  // Verifică dacă trebuie să dezactiveze butonul "Previous"
  if ($page_number <= 1) {
    echo '<button disabled>Previous</button>';
  } else {
    echo "<button onclick=\"sendToServer(".($page_number-1).")\">Previous</button>";
  }

  // Verifică dacă trebuie să dezactiveze butonul "Next"
  if ($page_number >= $total_pages) {
    echo '<button disabled>Next</button>';
  } else {
    echo "<button onclick=\"sendToServer(".($page_number+1).")\">Next</button>";
  }

?>