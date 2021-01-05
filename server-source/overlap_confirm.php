<?php
$bus_num = $_GET['bus_num'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
   mysql_query("set names utf8");
   $sql = "select * FROM driver_info where bus_num = '$bus_num'";
   $result = mysql_query($sql, $conn);
   $flag = mysql_num_rows($result);

   if($flag > 0){
        echo "{\"flag\":\"0\"}";
      }
  else{
    echo "{\"flag\":\"1\"}";
  }
}

?>
