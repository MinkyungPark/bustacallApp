<?php
$user_name = $_GET['nickname'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

$sql = "select * from user_info where user_name = '$user_name'";

 if($sel_db) {
	mysql_query("set names utf8");

    $result = mysql_query($sql, $conn);
      $row = mysql_fetch_array($result);


      $phone1 = substr($row['phone_num'],0,3);
      $phone2 = substr($row['phone_num'],3,4);
      $phone3 = substr($row['phone_num'],7,4);
      $newPhone = $phone1."-".$phone2."-".$phone3;

      echo "{\"phone_num\":\"$newPhone\"}";
}

mysql_close($conn);

?>
