<?php

	$uID=$_POST['uID']; $uToken = $_POST['uToken'];

	if($uID && $uToken){
		include "getAuthConn.php";
		$conn = getAuthConn();
		$deleteSql = "DELETE FROM USER_TOKENS WHERE uID='$uID'AND uToken='$uToken';";


		$conn->query($deleteSql);


		$conn->close();

		echo "Success";

	}else{
		echo "Something is empty";
	}


?>