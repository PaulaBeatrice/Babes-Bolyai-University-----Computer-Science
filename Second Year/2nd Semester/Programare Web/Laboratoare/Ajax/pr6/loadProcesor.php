<?php
// Create connection
$conn = new mysqli("localhost", "root", "", "pw");
$query = "SELECT DISTINCT `Procesor` FROM `articole`";
$statement = $conn->prepare($query);
$statement->execute();
$statement->bind_result($procesor);

echo "<form class=\"list-group-item checkbox\">";
while ($statement->fetch()) {
    echo "<div>";
    echo "<label><input type=\"checkbox\" class=\"common_selector procesor\" name=\"procesor[]\" value=\"" . $procesor . "\"> " . $procesor . "</label>";
    echo "</div>";
}
echo "</form>";
?>
