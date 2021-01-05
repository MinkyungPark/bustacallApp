<?php
  $bus_num = $_GET['bus_num'];
  $bank_name = $_GET['account_bank'];
  $account_num = $_GET['account_num'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
$log_txt .= $bus_num."|".$user_bank."|".$user_account."|";


$log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
fwrite($log_file, $log_txt."\r\n");
fclose($log_file);
 if($sel_db) {
   mysql_query("set names utf8");
    $sql = "update driver_info set account_bank = '$bank_name', account_num = '$account_num' where bus_num = '$bus_num'";
    $result = mysql_query($sql, $conn);
    if($result){
      //echo "{\"flag\":\"1\"}";
      http_response_code(200);
    }
    else{
      //echo "{\"flag\":\"0\"}";
      http_response_code(200);
    }
 }
 else{
   //echo "{\"flag\":\"0\"}";
   http_response_code(200);
 }
 mysql_close($conn);
 ?>
