<?php

	include "getAuthConn.php";

function generateHash($password) {
	    if (defined("CRYPT_BLOWFISH") && CRYPT_BLOWFISH) {
	        $salt = '$2y$11$' . substr(md5(uniqid(rand(), true)), 0, 22);
	        return crypt($password, $salt);
	    }
	}



  $email = $_POST['email']; $pass = $_POST['pass'];





  if($email&&$pass){

    $emailCheckSQL = "SELECT uEmailAddress FROM USERS  WHERE uEmailAddress='".$email."';";




    $conn = getAuthConn();

    $emailCheckResult=$conn->query($emailCheckSQL);
    if($emailCheckResult->num_rows>0){//The email address is in the system
        echo "Error<br />
            Email Address in Use";


    }else{//The email address is not currently in the system


			//ToDo: Implement check on UID so that there are no duplicates;

			$uID = getUserID();

	    $insertSQL = "INSERT INTO USERS(uID, uEmailAddress, uPass)
	                        VALUES('".$uID."', '".$email."', '".generateHash($pass)."');";


      $conn->query($insertSQL);


			include "createToken.php";
			$token = createToken($uID);

			//ToDo: Register other information
			//ToDo: return information to the app
      echo "Success";
			echo "\n$uID";
			echo "\n$token";

    }


    $conn->close();
  }else{
    print "error<br/>
            Something is empty";


  }



	function getUserID(){
		/*	Generates a random user ID
				Checks if it exists
				If it does exist
				recursively calls the function until the generated user ID doesn't exist
				returns a string userID
		*/

		$userID = substr(md5(rand()), 0, 18);
		$checkUIDSQL = "SELECT uID FROM USERS WHERE uID='$userID';";

		$uIDConn = getAuthConn();
		$uIDCheckResult=$uIDConn->query($checkUIDSQL);
		$uIDConn->close();
		if($uIDCheckResult->num_rows>0){
			return getUserID;
		}else{
			return $userID;
		}


	}




?>
