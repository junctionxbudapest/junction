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

   <p class="d1">Patient</p>

    <p class="d1">Cancer type</p>

    <p class="d1">Status</p>



    <p class="d2">Mark Jones</p>
    <p class="d2">Lung cancer</p>
    <p class="d2" style="color: red; margin-left: 7%;">Critical</p>
</div>







</body>
</html>
