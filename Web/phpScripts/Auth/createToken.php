<?php

  /*Takes in an id string,
    creates a token string,
    inserts it into the database
    returns the token

  */
  function createToken($id){


    $tokenConn = getAuthConn();

    $token = substr(md5(rand()), 0, 18);
    $makeTokenSQL = "INSERT INTO USER_TOKENS (uID, uToken)
                                  VALUES('$id', '$token');";

    $tokenConn->query($makeTokenSQL);
    $tokenConn->close();

    return $token;

  }



 ?>
