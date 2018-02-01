<?php
  $uID = $_POST['uid']; $uToken = $_POST['token'];

if($uID&&$uToken){
    include "Auth/getConn.php";

    $conn = getConn();

    include "swipeout.php";
    $swipeOutResponse = swipeout($uID, $uToken, $old, $conn); //ToDo: Do something with the response



    $swipeInSQL = "INSERT INTO SIGN_INS (uID, In_time, Out_time, Gym_ID)
                      Values ('$uID', CURRENT_TIMESTAMP, 'NULL', 'TEST');";

    //1900-01-01 00:00:00.000
    $conn->query($swipeInSQL);


    $conn->close();
    echo "Success";
  }else{
    echo "Something is blank";

  }
  //echo " ".$swipeInSQL;
 ?>
