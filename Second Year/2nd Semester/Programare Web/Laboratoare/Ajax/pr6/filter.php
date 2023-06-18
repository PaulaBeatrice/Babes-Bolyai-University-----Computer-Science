<?php
// echo "<pre>";
// var_dump($_POST);
// echo "</pre>";

$conn = new mysqli("localhost", "root", "", "pw");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}


$producator = isset($_POST['producator']) ? $_POST['producator'] : null;
$procesor = isset($_POST['procesor']) ? $_POST['procesor'] : null;
$memorie = isset($_POST['memoria']) ? $_POST['memoria'] : null;
$capacitate = isset($_POST['capacitate']) ? $_POST['capacitate'] : null;
$placa = isset($_POST['placa']) ? $_POST['placa'] : null;

// echo '<pre>';
// print_r("ALESE");
// print_r($producator);
// print_r($procesor);
// print_r($memorie);
// print_r($capacitate);
// print_r($placa);
// echo '</pre>';

// echo implode(', ', $producator);


// $query = "SELECT * FROM `articole` WHERE `Producator` IN ('" . implode("', '", $producator) . "')
//             AND `Procesor` IN ('" . implode("', '", $procesor) . "')
//             AND `Memorie` IN ('" . implode("', '", $memorie) . "')
//             AND `Capacitate` IN ('" . implode("', '", $capacitate) . "')
//             AND `Placa` IN ('" . implode("', '", $placa) . "')";

// $result = mysqli_query($conn, $query);

// while($row = mysqli_fetch_array($result)){
//     echo "<div>" . $row['Producator'] . " - " . $row['Procesor'] . " - " . $row['Memorie'] . " - " . $row['Capacitate'] . " - " . $row['Placa'] . "</div>";
// }

$query = "SELECT DISTINCT * FROM `articole`";

if(isset($producator) || isset($procesor) || isset($capacitate) || isset($placa) || isset($memorie)) {
    $query .= " WHERE ";

    if(isset($producator)) {
        $query .= "`Producator` IN ('" . implode("', '", $producator) . "') ";
    }

    if(isset($procesor)) {
        if(isset($producator)) {
            $query .= "AND ";
        }
        $query .= "`Procesor` IN ('" . implode("', '", $procesor) . "') ";
    }

    if(isset($capacitate)) {
        if(isset($producator) || isset($procesor)) {
            $query .= "AND ";
        }
        $query .= "`Capacitate` IN ('" . implode("', '", $capacitate) . "') ";
    }

    if(isset($placa)) {
        if(isset($producator) || isset($procesor) || isset($capacitate)) {
            $query .= "AND ";
        }
        $query .= "`Placa` IN ('" . implode("', '", $placa) . "') ";
    }

    if(isset($memorie)) {
        if(isset($producator) || isset($procesor) || isset($capacitate) || isset($placa)) {
            $query .= "AND ";
        }
        $query .= "`Memorie` IN ('" . implode("', '", $memorie) . "') ";
    }
}

$result = mysqli_query($conn, $query);

while($row = mysqli_fetch_array($result)){
    echo "<div>" . $row['Producator'] . " - " . $row['Procesor'] . " - " . $row['Memorie'] . " - " . $row['Capacitate'] . " - " . $row['Placa'] . "</div>";
}

?>
