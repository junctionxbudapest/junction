
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Follow up</title>
    <link rel="stylesheet" type="text/css" href="style.css">
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
        <p>First name</p>
        <input type="text" name="name">

        <p>Second name</p>
        <input type="text" name="surname">

        <p>Phone</p>
        <input type="text" name="phone">

        <p>Email</p>
        <input type="text" name="mail">

        <p>Cancer type</p>
        <input type="text" name="type">

        <p>Stage</p>
        <input type="text" name="stage">

        <p>Radiation therapy</p>
        <input type="radio" name="radiation" value="yes">
        <input type="radio" name="radiation" value="no">

        <p>Body area treated</p>
        <input type="text" name="area">



        <input type="hidden" name="form_submitted" value="1" />
        <input type="submit" value="Submit">
    </form>

</div>







</body>
</html>
