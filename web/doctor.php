<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Doctor account</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;800&family=Alata&display=swap" rel="stylesheet">
</head>
<body>
<div class="top">

    <a href="index.html"> <img class="logo2" src="img/logo2.png"></a>


    <div class="lk">

        <a href="doctor.php">  <img class="ava" src="img/doc.png"></a>

        <?php
        session_start();
        echo "<a href=\"doctor.php\"> <p class='login'> Dr. Smith</p></a>";
        ?>
    </div>
</div>

<div class="main2">

    <a href="statistics.php"> <img style="width: 100%; top: 20%; position: relative;" src="img/patient_table.png"> </a> <!--we receive status, but because time-->

    <a href="reg.php" style="margin-left: 95%; position: relative; top: -65%; "> <img src="img/new.png"> </a>

</div>







</body>
</html>
