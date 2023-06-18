<?php
    $con = mysqli_connect("localhost", "root", "", "pw");
    if (!$con) {
        die('Connection failed: ' . mysqli_error());
    }

    $id = $_GET["id"];
    $sql = "UPDATE `comentarii` SET `approved` = 1 WHERE `id` = ?";
    $stmt = mysqli_prepare($con, $sql);
    mysqli_stmt_bind_param($stmt, "i", $id);
    mysqli_stmt_execute($stmt);

    // Verifică dacă interogarea a fost executată cu succes
    if (mysqli_stmt_affected_rows($stmt) > 0) {
        header("Location: indexAdmin.php");
    } else {
        echo "Failed to update comment";
    }

    mysqli_stmt_close($stmt);
    mysqli_close($con);
?>
