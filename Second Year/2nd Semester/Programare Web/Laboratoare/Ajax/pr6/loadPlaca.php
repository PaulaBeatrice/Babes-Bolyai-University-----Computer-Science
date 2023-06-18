<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "pw");

$query = "SELECT DISTINCT `Placa` FROM `articole`";
$statement = $conn->prepare($query);
$statement->execute();
$statement->bind_result($placaVideo);

while ($statement->fetch()) {
    echo "<div class=\"list-group-item checkbox\">";
    echo "<label><input type=\"checkbox\" class=\"common_selector placa\" name=\"placa[]\" value=\"" . $placaVideo . "\"> " . $placaVideo . "</label>";
    echo "</div>";
}
?>
