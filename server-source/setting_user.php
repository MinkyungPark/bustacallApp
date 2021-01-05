<?php
  $user_name = $_GET['orgin_nickname'];
  $user_bank = $_GET['bank_name'];
  $user_account = $_GET['bank_account'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
$log_txt .= $user_name."|".$user_bank."|".$user_account."|";


$log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
fwrite($log_file, $log_txt."\r\n");
fclose($log_file);
 if($sel_db) {
   mysql_query("set names utf8");
    $sql = "update user_info set user_bank = '$user_bank', user_account = '$user_account' where user_name = '$user_name'";
    $result = mysql_query($sql, $conn);
    if($result){
      echo "{\"flag\":\"1\"}";
    }
    else{
      echo "{\"flag\":\"0\"}";
    }
 }
 else{
   echo "{\"flag\":\"0\"}";
 }
 mysql_close($conn);
 ?>
