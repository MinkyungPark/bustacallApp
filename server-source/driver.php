<?php
	$dname = $_GET["dname"];

	$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
	$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");
        $sql = "select user_name from user_info where user_name = '$dname'";
       $result = mysql_query($sql, $conn);
        $total = mysql_num_rows($result);


        if($total)
            echo"{\"code\":\"200\"}";
        else
            echo"{\"code\":\"400\"}";
      }


    mysql_close();
?>

