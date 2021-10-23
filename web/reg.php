<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Follow up</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;800&family=Alata&display=swap" rel="stylesheet">
</head>
<body>
<div class="top">

    <a href="index.html"> <img class="logo2" src="img/logo2.png"></a>


    <div class="lk">

        <a href="lk.php">  <img class="ava" src="img/doc.png"></a>

        <?php
        session_start();
        echo "<a href=\"doctor.php\"> <p class='login'> Dr. Smith</p></a>";
        ?>
    </div>
</div>

<div class="main2">

    <h1>Please,<br> fill out the form</h1>


    <form action="statistics.php" method="POST"> <!--address here where to send data-->
        <p class="treg">Name</p>
        <input class="t1" style="display: inline-block; width: 80%" type="text" name="name"> <br>

        <p class="treg">Age</p>
        <input class="t1" style="display: inline-block; width: 80%; margin-left: 2.7%" type="text" name="age" > <br>

        <p class="treg">Sex</p>
        <p style="font-size: 26px; margin-left: 0;" >Male</p> <input type="radio" name="sex" value="male">
        <p style="font-size: 26px; margin-left: 2%;" >Female</p> <input type="radio" name="sex" value="female">  <br>


        <p class="treg">Cancer type</p>
        <input class="t1" style="display: inline-block; width: 73%;" type="text" name="type"> <br>


        <p class="treg">Stage</p>
        <p style="font-size: 26px; margin-left: 0; " >1</p> <input type="radio" name="sex" value="1">
        <p style="font-size: 26px; margin-left: 2%; " >2</p><input type="radio" name="sex" value="2">
        <p style="font-size: 26px; margin-left: 2%;" >3</p> <input type="radio" name="sex" value="3">  <br>

        <p class="treg">Radiation therapy</p>
        <p style="font-size: 26px" >Yes</p> <input type="radio" name="radiation" value="yes">
        <p style="font-size: 26px; margin-left: 2%;" >No</p> <input type="radio" name="radiation" value="no"> <br>

        <p class="treg">Weight</p>
        <input class="t1" style="display: inline-block; width: 80%" type="text" name="weight"> <br>

        <p class="treg">Height</p>
        <input class="t1" style="display: inline-block; width: 80%; margin-left: 1%" type="text" name="type"> <br>

        <input type="hidden" name="form_submitted" value="1" />
        <input class="send_button" style="float: none; margin-top: 3%; width: 33%;" type="submit" value="">
    </form>



</div>







</body>
</html>
