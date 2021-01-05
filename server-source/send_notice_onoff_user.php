<?php

  $user_name = $_GET['nickname'];
  $onoff = $_GET['notice'];

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

if($sel_db) {
  mysql_query("set names utf8");
    $sql = "update user_info set push_flag = '$onoff' where user_name = '$user_name'";
    $result = mysql_query($sql, $conn);
    if($result){
      http_response_code(200);
    }
    else{
      http_response_code(200);
    }
    mysql_close($conn);
}

?>
