<?php
$nickname = $_GET["nickname"]; 

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
    
 if($sel_db) {
        mysql_query("set names utf8");
        $sql = "select user_name from user_info where user_name = '$nickname'";
       $result = mysql_query($sql, $conn);
		$total = mysql_num_rows($result);


		if($total){
			http_response_code(400);
			echo"{\"code\":\"400\"}";	
		}
		else{
			echo"{\"code\":\"200\"}";
		}
      }


	mysql_close();
?>
