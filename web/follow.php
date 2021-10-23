
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

      <a href="lk.php">  <img class="ava" src="img/ava.png"></a>

        <?php
        session_start();
        echo "<a href=\"lk.php\"> <p class='login'>" . $_SESSION['login'] . "</p></a>";
        ?>
    </div>
</div>

<div class="main2">

    <?php
    $date_today = date("d F Y");
    echo ("<h2 class='time1'>".$date_today."</h2>");
    ?>


    <form action="lk.php" method="POST"> <!--address here where to send data-->
    <h1 style="margin-top: 1%;">How do you feel?</h1>

        <p class="treg" style="margin: 10px;">Weight</p> <input class="t1" style="width: 50%" type="text" name="weight"> <br>
       <p class="treg" style="margin: 10px;">Sleep</p>  <input  class="t1" style="width: 50%; margin-left: 2.2%" type="text" name="sleep">
        <br>
        <h1 style="font-size: 36px; color: black">Mood</h1><br>

        <label class="mood" style="background: url(img/calm.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=4></label>
        <label class="mood" style="background: url(img/glad.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=5></label>
        <label class="mood" style="width: 100px; height: 140px; background: url(img/energetic.png) no-repeat; background-size: 90% 90%; " ><input type="checkbox" name="mood" value=5></label>
        <label class="mood" style="width: 108px; height: 140px; background: url(img/annoyed.png) no-repeat; background-size: 90% 90%; " ><input type="checkbox" name="mood" value=2></label>
        <label class="mood" style="background: url(img/swings.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=3></label>
        <label class="mood" style="background: url(img/sad.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=1></label>
        <label class="mood" style="background: url(img/alarmed.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=1></label>
        <label class="mood" style="background: url(img/apathy.png) no-repeat; background-size: 100% 100%; " ><input type="checkbox" name="mood" value=3></label>
        <br>

    <h1>What procedure did you have recently?</h1>

        <input class="t1" type="text" name="procedure" value="Type here...">

    <h1>Mark your symptoms</h1>

        <img style="width: 90%; position: relative; left: 3%" src="img/symptoms.png">

        <input class="t2" type="text" name="procedure" value="Indicate your other symptoms">



        <img class="photo" src="img/photo.png">
        <img class="photo" src="img/photo.png">
        <img class="photo" src="img/photo.png">
        <img class="photo" src="img/photo.png">

        <h1>Recommendations</h1>



        <img src="img/mark.png"><p class="rec">Visit a Doctor</p><br>
        <img src="img/mark.png"><p class="rec">Take a blood test</p><br>
        <img src="img/mark.png"><p class="rec">Make an ultrasound</p>

        <input class="send_button" type="submit" value="">

    </form>







</div>







</body>
</html>
