<?php
$username = $_GET['nickname'];
$start_region = $_GET['start_region'];
$end_region = $_GET['end_region'];
$start_point_one = $_GET['start_point'];
$end_point_one = $_GET['end_point'];
$date = $_GET['day_one'];
$type = $_GET['type'];
$flag = $_GET['flag'];
$time = $_GET['time'];
$purpose = $_GET['reason'];
$max_user_count = $_GET['max_user_count'];
$current_user_count = $_GET['current_user_count'];
$money = $_GET['money'];
$text = $_GET['text'];
$lent_money = $_GET['rental_money'];
$together_type_flag = $_GET['type_flag'];
$end_region_two = $_GET['end_region_two'];

$log_txt = $username."| ".$start_region."| ".$end_region."| ".$start_point_one."| ";
$log_txt .= $end_point_one."| ".$date."| ".$type."| ".$flag."| ";
$log_txt .= $purpose."| ".$max_user_count."| ".$current_user_count."| ".$money."| ".$text."| ".$lent_money;

  $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
  fwrite($log_file, $log_txt."\r\n");
  fclose($log_file);

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

if($sel_db) {

    mysql_query("set names utf8");
    $sql = "insert into lent_info(user_id, start_point, end_point, purpose, ";
    $sql .= "start_region, end_region, date, cost, end_region_two, type_flag, together_flag, time, ";
    $sql .= "max_user_count, current_user_count, together_money, together_type_flag, text) values(";
    $sql .= "'$username', '$start_point_one', '$end_point_one', '$purpose', ";
    $sql .= "'$start_region', '$end_region', '$date', '$lent_money', '$end_region_two',";
    $sql .= "'$type', '$flag', '$time', '$max_user_count', '$current_user_count', '$money', '$together_type_flag', '$text')";

    $result = mysql_query($sql, $conn);
    if($result){
      http_response_code(200);
    }
    else{
      http_response_code(400);
    }
}

?>
