<?php

$bus_num = $_GET['bus_num'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
   mysql_query("set names utf8");
   $sql = "select * from driver_info where bus_num = '$bus_num'";
   $result = mysql_query($sql, $conn);
   $row = mysql_fetch_array($result);

   $data = array (
      'onoff' => $row['push_flag']
    );
    $data = json_encode($data);
    print_r($data);
 }
 ?>
