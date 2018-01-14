<?php
  /*  Takes in and ID and a Token string
      Checks to see if they exist on the database
      Returns bool

  */
  function checkToken($uID, $uToken){


    include "getAuthConn.php";
    $checkTokenConn = getAuthConn();

    $checkTokenSQL = "SELECT uToken FROM USER_TOKENS WHERE uID='$uID' AND uToken='$uToken';";

    $checkResultSet = $checkTokenConn->query($checkTokenSQL);
    $checkTokenConn->close();


    if($checkResultSet->num_rows>0){//Matching token exists for that user ID
      return "True";
    }

    return $checkTokenSQL;

  }


  



 ?>
