<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Patient personal account</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;800&family=Alata&display=swap" rel="stylesheet">
</head>
<body>
<div class="top">
   <a href="index.html"> <img class="logo2" src="img/logo2.png"></a>

</div>



<div class="main2">

<img style="width: 220px" src="img/ava2.png">

    <?php
    session_start();
    echo "<p class='login2'>" . $_SESSION['login'] . "</p>";
    ?>
    <br>

    <p style="position: relative; top: -25%; left: 30%; line-height: 30px; font-size: 20px; color: #999999;"> 32 y.o, Hungary, Budapest <br> diagnosis: leukemia</p>
    <p style="position: relative; top: -23%; left: 30%; line-height: 30px; font-size: 28px; color: #999999;"> You medical status</p>

    <?php
    if ($_POST['status']==1 or $_SESSION['status']==1){
    echo "<img class='status' src='img/normal.png'>";
    } else {
        echo "<img class='status' src='img/heavy.png'>";
        echo "<img class='appointment' src='img/appointment.png'>";
        echo "<p style='position: relative; text-align: center; top: -10%; left: 3%; line-height: 30px; font-size: 22px;
 color: #999999;'>Your condition seems to have worsened.<br>
Please make an appointment with your healthcare provider.</p>";

    }
    ?>

    <h1 style="margin-top: -0.5%;">Tips for every day</h1>


    <a href="follow.php"><img style="display: inline-block; width: 21.8%; margin-right: 1%;" src="img/add_s.png"></a>
    <img style="display: inline-block; width: 76.3%" src="img/article.png">

    <h1 style="margin-top: 4%;">My treatment</h1>
    <img style="width: 100%; margin-top: 2%;" src="img/treat.png">





    <h4>Complete blood count (CBC) with differential</h4>

    <img src="img/blood.png" style="float: right; position: absolute; width: 85%; left: 50%;">

    <p style="font-family: Alata serif;font-style: normal;font-weight: normal;font-size: 22px;line-height: 33px;color: #999999;">
        In your analysis, there is an increase in indicators:<br>
        erythrocyte sedimentation rate; hemoglobin;<br>
        leukocytes. However, with your diagnosis, such<br>
        indicators may be the norm.<br>
        <br>
        We recommend that you consult a doctor for a<br>
        complete transcript of the analysis.</p>

    <h4>??ardiogram</h4>

    <img src="img/cardio.png" style="float: right; position: absolute; width: 51%; left: 50%;">

    <p style="font-family: Alata serif;font-style: normal;font-weight: normal;font-size: 22px;line-height: 33px; color: #999999;">
        There are a number of deviations in your analysis.<br>
        <br>
    We recommend making an appointment with<br>
    a doctor.</p>

</div>






</body>
</html>
