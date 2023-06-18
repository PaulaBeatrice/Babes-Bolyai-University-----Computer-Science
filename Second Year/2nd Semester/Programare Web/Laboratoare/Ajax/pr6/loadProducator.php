<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "pw");

$query = "SELECT DISTINCT `Producator` FROM `articole`";
$statement = $conn->prepare($query);
$statement->execute();
$statement->bind_result($producator);

while ($statement->fetch()) {
    echo "<div class=\"list-group-item checkbox\">";
    echo "<label><input type=\"checkbox\" class=\"common_selector producator\" name=\"producator[]\" value=\"" . $producator . "\"> " . $producator . "</label>";
    echo "</div>";
}
?>