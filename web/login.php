<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Varian </title>
    <link rel="stylesheet" type="text/css" href="style.css">
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
    <h2>Registration Form</h2>
    <form action="login.php" method="POST"> <!--address here where to send data-->
        <p>Login</p>
        <input type="text" name="login">

        <p>Password</p>
        <input type="text" name="password">

        <input type="hidden" name="form_submitted" value="1" />
        <input type="submit" value="Submit">
    </form>
<?php }?>
</body>
</html>