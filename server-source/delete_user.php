<?php
$username = $_GET['nickname'];

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

$log_txt = $username;
$log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
fwrite($log_file, $log_txt."\r\n");
fclose($log_file);

if($sel_db) {
  mysql_query("set names utf8");
  $sql = "delete from user_info where user_name = '$username'";
  $result = mysql_query($sql, $conn);


  $sql = "delete from tmp_list where user_name = '$username'";
  $result = mysql_query($sql, $conn);

  $sql = "delete from point_info where user_name = '$username'";
  $result = mysql_query($sql, $conn);


}

?>
