<?php


	$uID = $_POST['uid']; $uToken = $_POST['token'];


	if((!empty($uID))&&(!empty($uToken))){

		header('Content-Type: text/xml'); //Declares the return type an xml 

		//ToDo: Check Tokens

		include("Auth/getConn.php");
		$conn = getConn();

		$output = "<root>";//The root of the Xml tree

		$getVisitsSQL= "SELECT si.In_time, si.Out_time, g.Name 
							FROM SIGN_INS si INNER JOIN Gym g ON si.Gym_id=g.Gym_id
								WHERE si.uID='$uID';";


		$visitsResultSet = $conn->query($getVisitsSQL);
		if($visitsResultSet->num_rows > 0){ //Check that there are visits
			while($visitRow = $visitsResultSet->fetch_assoc()){//Loop through all the visits

				$timeIn = strtotime($visitRow['In_time']);
				$timeOut = strtotime($visitRow['Out_time']);
				$duration = $timeOut - $timeIn; //Gives time in seconds
				$durationHours 		= intval($duration/3600); //3600 seconds in an hour
				$durationMinutes 	= intval(($duration%3600)/60); //Get remaining minutes after hours and divide by 60 to find minutes
				$durationSeconds	= $duration % 60; //Seconds are the remaining time after minutes 
				$output .= "<visit>";
					$output .="	<gym_name>"	.$visitRow['Name']									."</gym_name>
								<date>"		.date("d/m/y", $timeIn)								."</date>
								<time_in>"	.date("h:ia", $timeIn)								."</time_in>
								<time_out>"	.date("h:ia", $timeOut)								."</time_out>
								<duration>"	
										."<hours>$durationHours</hours>
										  <minutes>$durationMinutes</minutes>
										  <seconds>$durationSeconds</seconds>"	

								."</duration>";


				$output .= "</visit>";

			}


		}




		$conn->close();

		$output .= "</root>";
		print($output); //Return the xml tree
	}else{
		echo "Error empty details";
		echo "\nhuh? ".$uID;
	}
?>