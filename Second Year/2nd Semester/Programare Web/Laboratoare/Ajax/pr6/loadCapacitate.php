<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "pw");

$query = "SELECT DISTINCT `Capacitate` FROM `articole`";
$statement = $conn->prepare($query);
$statement->execute();
$statement->bind_result($capacitate);

while ($statement->fetch()) {
    echo "<div class=\"list-group-item checkbox\">";
    echo "<label><input type=\"checkbox\" class=\"common_selector capacitate\" name=\"capavitate[]\" value=\"" . $capacitate . "\"> " . $capacitate . "</label>";
    echo "</div>";
}
?>