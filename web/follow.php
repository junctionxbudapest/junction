
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

    <div class="logo">

    </div>


    <div class="lk">

        <?php
        session_start();
        echo "<p class='login'>" . $_SESSION['login'] . "</p>";
        ?>

        <img src="">
    </div>
</div>

<div class="main2">

    <?php
    $date_today = date("d F Y");
    echo ("<h2 class='time1'>".$date_today."</h2>");
    ?>

    <h1>How do you feel?</h1>
    <h1>What procedure did you have recently?</h1>
    <h1>Mark your symptoms</h1>
    <h1>Recommendations</h1>
</div>







</body>
</html>
