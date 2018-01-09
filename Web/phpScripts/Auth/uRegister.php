<?php



function generateHash($password) {
	    if (defined("CRYPT_BLOWFISH") && CRYPT_BLOWFISH) {
	        $salt = '$2y$11$' . substr(md5(uniqid(rand(), true)), 0, 22);
	        return crypt($password, $salt);
	    }
	}



  $email = $_POST['email']; $pass = $_POST['pass'];





  if($email&&$pass){

    $emailCheckSQL = "SELECT uEmailAddress FROM USERS  WHERE uEmailAddress='".$email."';";



    include "getAuthConn.php";
    $conn = getAuthConn();

    $emailCheckResult=$conn->query($emailCheckSQL);
    if($emailCheckResult->num_rows>0){//The email address is in the system
        echo "Error<br />
            Email Address in Use";


    }else{//The email address is not currently in the system

			//ToDo: Implement check on UID so that there are no duplicates;
			$uID = substr(md5(rand()), 0, 18);

	    $insertSQL = "INSERT INTO USERS(uID, uEmailAddress, uPass)
	                        VALUES('".$uID."', '".$email."', '".generateHash($pass)."');";


      $conn->query($insertSQL);


			include "createToken.php";
			$token = createToken($uID);

			//ToDo: Register other information
			//ToDo: return information to the app
      echo "Success";

    }


    $conn->close();
  }else{
    print "error<br/>
            Something is empty";
























  }

?>
