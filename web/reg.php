
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


    <form action="somewhere.php" method="POST"> <!--address here where to send data-->
        <p>Name</p>
        <input type="text" name="name"> <br>

        <p>Sex</p>
        <input type="radio" name="sex" value="male"> <p>male</p>
        <input type="radio" name="sex" value="female"> <p>female</p> <br>

        <p>Age</p>
        <input type="text" name="age"> <br>

        <p>Cancer type</p>
        <input type="text" name="type"> <br>


        <p>Stage</p>
        <input type="radio" name="sex" value="1"> <p>1</p>
        <input type="radio" name="sex" value="2"> <p>2</p>
        <input type="radio" name="sex" value="3"> <p>3</p> <br>

        <p>Radiation therapy</p>
        <input type="radio" name="radiation" value="yes">
        <input type="radio" name="radiation" value="no"> <br>

        <p>Weight</p>
        <input type="text" value="weight"> <br>

        <p>Height</p>
        <input type="text" name="type"> <br>

        <input type="hidden" name="form_submitted" value="1" />
        <input type="submit" value="Submit">
    </form>

</div>







</body>
</html>
