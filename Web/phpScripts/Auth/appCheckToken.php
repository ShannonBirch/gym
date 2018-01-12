<?php

$uID = $_POST['uID']; $uToken = $_POST['uToken'];

if($uID&&$uToken){
  include "checkToken.php";

  if(checkToken($uID, $uToken)==="True"){
    echo "True";
  }else{
    echo "False ";
    $sql = checkToken($uID, $uToken);

    echo "\n".$sql;
  }


}




 ?>
