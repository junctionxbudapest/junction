<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Varian </title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Abhaya+Libre:wght@400;800&family=Alata&display=swap" rel="stylesheet">
</head>
<body>







<?php if (isset($_POST['form_submitted']) or isset($_POST['id'])){
    session_start();
    $_SESSION['login'] = $_POST['login'];

    $new_url = 'follow.php';
    header('Location: '.$new_url);
    ?>

<?php }
else {?>

    <div class="login_form">

        <form action="login.php" method="POST"> <!--address here where to send data-->

            <input class="mail" type="text" name="login"  placeholder="patient1@gmail.com">

            <input class="password" type="text" name="password" placeholder="********">

            <input type="hidden" name="form_submitted" value="1" />



           <input class="login_button" type="submit" value="">
        </form>

    </div>







<?php }?>
</body>
</html>