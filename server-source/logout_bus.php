<?php
  $bus_num = $_GET['bus_num'];

  $conn = mysql_connect('localhost', 'root', 'kjh83256@#');
  $sel_db = mysql_select_db("sk_bus", $conn);

   if($sel_db) {
     mysql_query("set names utf8");

     $sql2 = "select lent_num from lent_info where selected_bus = '$bus_num' and account_flag = 1";
     $result2 = mysql_query($sql2, $conn);
     $flag = mysql_num_rows($result2);
     if($flag < 1){
                   $sql = "delete from driver_info where bus_num = '$bus_num'";
                   $result = mysql_query($sql, $conn);
                   $sql = "delete from bus_info where bus_num = '$bus_num'";
                   $result = mysql_query($sql, $conn);

                    echo "{\"flag\":\"1\"}";
     }else{
       echo "{\"flag\":\"0\"}";
     }
   }

 ?>
