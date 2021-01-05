<?php // user_lental all output

$user_name = $_GET['nickname'];
$point = $_GET['point'];
$bus_num = $_GET['bus_num'];

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
$today = date('Y-m-d');
 if($sel_db) {
   mysql_query("set names utf8");
   $sql = "insert into point_info(user_name, bus_num , point, point_day) values('$user_name', '$bus_num', '$point', '$today')";
   $result = mysql_query($sql, $conn);
   $sql = "update driver_info set point = point - '$point' where bus_num = '$bus_num'";
   $result = mysql_query($sql, $conn);
 }
 mysql_close($conn);

 ?>
