<?php

$json = file_get_contents('php://input');
$together = json_decode($json, true);
$count = count($together);
$rental_num = $together['rental_num'];
$flag = $together['flag'];
$user_count = $together['current_user_count'];
$max_count = $together['max_user_count'];
$money = $together['money'];
$type_flag = $together['type_flag'];


$log_txt = $rental_num."|".$flag."|".$user_count."|".$money;

    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
    fwrite($log_file, $log_txt."\r\n");
    fclose($log_file);



$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

if($sel_db) {
    $sql = "update lent_info set together_flag = $flag ,max_user_count = '$max_count', current_user_count = '$user_count' ,together_money = '$money', together_type_flag = '$type_flag' where lent_num = $rental_num";
    $result = mysql_query($sql, $conn);
    if($result){
      http_response_code(200);
    }
    else {

    }

}
