<?php

class Tren
{
    public $nr;
    public $tip;
    public $plecare;
    public $sosire;
    public $ora_plecare;
    public $ora_sosire;
}


$conn = new mysqli("localhost", "root", "", "pw");
if (!$conn) {
    die("Connection failed: " . mysqli_connect_error());
}

$n = 0;
$trenuri = array();

if (isset($_GET['intermediar'])) {
    // selectam trenurile care au ca plecare orasul cerut, sau ca sosire orasul cerut
    $stringQ = "SELECT * FROM `trenuri_php` WHERE `plecare`=? OR `sosire`=?";
    $sql = $conn->prepare($stringQ);
    $sql->bind_param('ss', $_GET["plecare"], $_GET["sosire"]);
    $sql->execute();

    $sql->bind_result($nr, $tip, $plecare, $sosire, $oraP, $oraS);

    $intermediar = array();
    $ni = 0;
    while ($sql->fetch()) {
        $intermediar[$ni] = new Tren();
        $intermediar[$ni]->nr = $nr;
        $intermediar[$ni]->tip = $tip;
        $intermediar[$ni]->plecare = $plecare;
        $intermediar[$ni]->sosire = $sosire;
        $intermediar[$ni]->ora_plecare = $oraP;
        $intermediar[$ni]->ora_sosire = $oraS;
        $ni = $ni + 1;
    }

    echo "<p>Rute cu statii intermediare</p>";
    echo "<table>";
    for ($i = 0; $i < $ni-1; $i++) {
        for ($j = 0; $j < $ni; $j++) {
            if (
                // Orașul de plecare al înregistrării inițiale este același cu orașul de plecare specificat în parametrul GET.
                // Orașul de sosire al înregistrării intermediare este același cu orașul de plecare al înregistrării următoare.
                // Orașul de sosire al înregistrării următoare este același cu orașul de sosire specificat în parametrul GET.
                // Ora de sosire a înregistrării inițiale este mai mică decât ora de plecare a înregistrării intermediare.
                $intermediar[$i]->plecare == $_GET["plecare"] &&
                $intermediar[$i]->sosire == $intermediar[$j]->plecare &&
                $intermediar[$j]->sosire == $_GET["sosire"] && strtotime($intermediar[$i]->ora_sosire) < strtotime($intermediar[$j]->ora_plecare)
            ) {
                echo "<tr>";
                echo "<td>" . $intermediar[$i]->nr . "</td>";
                echo "<td>" . $intermediar[$i]->tip . "</td>";
                echo "<td>" . $intermediar[$i]->plecare . ": " . $intermediar[$i]->ora_plecare . "</td>";
                echo "<td>" . $intermediar[$i]->sosire . ": " . $intermediar[$i]->ora_sosire . "</td>";
                echo "<td>    -->    </td>";
                echo "<td>" . $intermediar[$j]->nr . "</td>";
                echo "<td>" . $intermediar[$j]->tip . "</td>";
                echo "<td>" . $intermediar[$j]->plecare . ": " . $intermediar[$j]->ora_plecare . "</td>";
                echo "<td>" . $intermediar[$j]->sosire . ": " . $intermediar[$j]->ora_sosire . "</td>";
                echo "</tr>";
            }
        }
    }

    echo "</table>";
} else {
    $stringQ = "SELECT * FROM `trenuri_php` WHERE `plecare`=? AND `sosire`=?";
    // $stringQ = "SELECT * FROM `trenuri_php` ";
    $sql = $conn->prepare($stringQ);
    $sql->bind_param('ss', $_GET["plecare"], $_GET["sosire"]);
    $sql->execute();

    $sql->bind_result($nr, $tip, $plecare, $sosire, $oraP, $oraS);

    while ($sql->fetch()) {
        $trenuri[$n] = new Tren();
        $trenuri[$n]->nr = $nr;
        $trenuri[$n]->tip = $tip;
        $trenuri[$n]->plecare = $plecare;
        $trenuri[$n]->sosire = $sosire;
        $trenuri[$n]->ora_plecare = $oraP;
        $trenuri[$n]->ora_sosire = $oraS;

        // echo "Detalii despre trenul nr. " . $trenuri[$n]->nr . ":";
        // echo "Tip: " . $trenuri[$n]->tip;
        // echo "Plecare: " . $trenuri[$n]->plecare;
        // echo "Sosire: " . $trenuri[$n]->sosire;
        // echo "Ora plecare: " . $trenuri[$n]->ora_plecare;
        // echo "Ora sosire: " . $trenuri[$n]->ora_sosire;

        $n = $n + 1;

    }

    echo "<p>Rute fara statii intermediare</p>";
    echo "<table>";

    for ($i = 0; $i < $n; $i++) {
        echo "<tr>";
        echo "<td>" . $trenuri[$i]->nr . "</td>";
        echo "<td>" . $trenuri[$i]->tip . "</td>";
        echo "<td>" . $trenuri[$i]->plecare . ": " . $trenuri[$i]->ora_plecare . "</td>";
        echo "<td>" . $trenuri[$i]->sosire . ": " . $trenuri[$i]->ora_sosire . "</td>";
        echo "</tr>";
    }

    echo "</table>";
}