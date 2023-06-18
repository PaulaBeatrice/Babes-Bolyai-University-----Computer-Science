<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "pw");

$query = "SELECT DISTINCT `Memorie` FROM `articole`";
$statement = $conn->prepare($query);
$statement->execute();
$statement->bind_result($memorie);

echo "<form class=\"list-group-item checkbox\">";
while ($statement->fetch()) {
    echo "<div>";
    echo "<label><input type=\"checkbox\" class=\"common_selector memorie\" name=\"memorie[]\" value=\"" . $memorie . "\"> " . $memorie . "</label>";
    echo "</div>";
}
echo "</form>";
?>
