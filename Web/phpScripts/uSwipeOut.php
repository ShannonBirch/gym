<?php
  $uID = $_POST['uid']; $uToken = $_POST['token'];

  //ToDo: Check token
  //ToDo: 
if($uID&&$uToken){

    include "Auth/getConn.php";

    $conn = getConn();

    include "swipeout.php";
    $response = swipeout($uID, $uToken, $now, $conn);

    $conn->close();
    
    echo $response;
    if(!$response){
      echo "No response";
    }

  }else{
    echo "Something is blank";

  }
  //echo " ".$getInSwipeSQL;
 ?>
