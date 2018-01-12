
<?php


	 function getAuthConn(){
	 	//Connection credentials
    //uAuth
    //3A.w9KUO&2V9
		 $servername ="localhost"; $username = "shannonb_uAuth"; $password = "3A.w9KUO&2V9"; $dbname = "shannonb_uAuth";

		$conn = new mysqli($servername, $username, $password, $dbname);
					// Check connection
					if ($conn->connect_error) {
					    die("Connection failed: " . $conn->connect_error);
					}
		return $conn;
	 }
?>
