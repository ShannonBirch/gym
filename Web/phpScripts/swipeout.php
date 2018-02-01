<?php
 
	function swipeout($uID, $uToken, $time, $conn){


	
	    $getInSwipeSQL = "SELECT Sign_In_ID, In_time FROM SIGN_INS WHERE uID='$uID' AND Out_time='0000-00-00 00:00:00';";

	    $swipeInSQL = "INSERT INTO SIGN_INS (uID, In_time, Out_time, Gym_ID)
	                      Values ('$uID', CURRENT_TIMESTAMP, 'NULL', 'TEST');";



	    $inSwipeResultSet=$conn->query($getInSwipeSQL);
	    if($inSwipeResultSet->num_rows>0){
	      while($row=$inSwipeResultSet->fetch_assoc()){

	        $signInID = $row['Sign_In_ID'];
	        $swipeOutSQL = "UPDATE SIGN_INS SET Out_time=CURRENT_TIMESTAMP WHERE Sign_In_ID='$signInID';";

	        if($time==="old"){

	    		$time = strtotime($row['In_time'])+10800;
	    		if($time < time()){

	    			$swipeOutSQL = "UPDATE SIGN_INS SET Out_time=$time WHERE Sign_In_ID='$signInID';";	  
	    		}
	    	}

	        $conn->query($swipeOutSQL);

	      }
	      return "Success";
	    }else{
	      return "Not swiped in";
	    }



	    



	}


 ?>
