<?php // user_lental all output

$user_name = $_GET['nickname'];
$point = $_GET['point'];

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
$today = date('Y-m-d');
 if($sel_db) {
   mysql_query("set names utf8");
   $sql = "insert into point_info(user_name, point, point_day) values('$user_name', '$point', '$today')";
   $result = mysql_query($sql, $conn);
   $sql = "update user_info set point = point - '$point' where user_name = '$user_name'";
   $result = mysql_query($sql, $conn);
 }
 mysql_close($conn);

 ?>
