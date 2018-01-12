<?php




  include "getAuthConn.php";
  $email=$_POST['email']; $pass=$_POST['pass'];

  if($email&&$pass){

    $getHashedPassSQL = "SELECT uPass, uID FROM USERS WHERE uEmailAddress='$email';";

    $conn = getAuthConn();

    $HashedPasswordResult=$conn->query($getHashedPassSQL);

    if($HashedPasswordResult->num_rows>0){//There's a stored password
      $row = $HashedPasswordResult->fetch_assoc();

      if(verify($pass, $row['uPass'])){//The password matches
        $uID = $row['uID'];
        include "createToken.php";
        $token = createToken($uID);

        echo "Success";
        echo "\n$uID\n$token";


      }else{
        echo "Password wrong dummy";
      }

    }else{//No row found with that email address
      echo "Invalid details";
      echo "<br />$getHashedPassSQL";
    }



    $conn->close();
  }else{
    echo "Missing details";
  }


  function verify($password, $hashedPassword) {
    return crypt($password, $hashedPassword) == $hashedPassword;
  }



 ?>
