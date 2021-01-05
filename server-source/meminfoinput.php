<?php
  $user_name = "김방영";//$_GET["usename"];
  $user_account = "110584-754-887554";//$_GET["useracc"];
  
  $conn = mysql_connect('localhost', 'root', 'kjh83256@#');
  $sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");
        $sql = "update user_info set user_account = '$user_account' where user_name = '$user_name'";
        $result = mysql_query($sql, $conn);
        
        if($result){
           echo "{\"code\":\"200\"}";       
        }
        else{
          echo"{\"code\":\"400\"}";
        }
    mysql_close();
    }
?>

