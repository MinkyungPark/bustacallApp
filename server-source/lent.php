<?php
function send_notification ($tokens, $message, $lent_num)
{
        $url = 'https://fcm.googleapis.com/fcm/send';

        $fields = array (
            'registration_ids' => $tokens,
            'notification' => array (
                    "title" => "Bustacall",
                     "body" => "지역 매물 등록!"
            ),
            'data' => array (
              'rental_num' => $lent_num
            )
        );


        $headers = array(
            'Authorization:key =' . GOOGLE_API_KEY,
            'Content-Type: application/json'
            );

        $ch = curl_init();
        curl_setopt($ch, CURLOPT_URL, $url);
        curl_setopt($ch, CURLOPT_POST, true);
        curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
        curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
        // curl_setopt ($ch, CURLOPT_SSL_VERIFYHOST, 0);
        // curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
        curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
        $result = curl_exec($ch);
        if ($result === FALSE) {
           die('Curl failed: ' . curl_error($ch));
        }
        curl_close($ch);
        return $result;
}

include ("config.php");

$json = file_get_contents('php://input');
$lent = json_decode($json, true);
$count = count($lent);
$username = $lent['nickname'];
$start_point_one = $lent['start_point_one'];
$end_point_one = $lent['end_point_one'];
$start_point_two = $lent['start_point_two'];
$end_point_two = $lent['end_point_two'];
$day_one = $lent['day_one'];
$time_one = $lent['time_one'];
$day_two = $lent['day_two'];
$time_two = $lent['time_two'];
$currentday = $lent['current_day'];
$rental_reason = $lent['rental_reason'];
$user_count = $lent['user_count'];
$rental_money = $lent['rental_money'];
$type = $lent['type'];
$type2 = $lent['type_two'];
$bus_45 = $lent['bus_45'];
$bus_35 = $lent['bus_35'];
$bus_28 = $lent['bus_28'];
$bus_25 = $lent['bus_25'];
$start_region = $lent['start_region'];
$end_region = $lent['end_region'];
$end_region_two = $lent['end_region_two'];

$modify_day = date("Y-m-d", strtotime($currentday."+7 day"));
$log_txt = $modify_day."|";

    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
    fwrite($log_file, $log_txt."\r\n");
    fclose($log_file);


if (strlen($lent) <= 0)
     http_response_code(400);

$log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
fwrite($log_file, $log_txt."\r\n");
fclose($log_file);


$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

if($sel_db) {
    mysql_query("set names utf8");
       $sql = "insert into lent_info(user_id, ";
       $sql .= "start_region, start_point, end_region ,end_region_two, start_point2, end_point, end_point2, date, time, date2, time2,currentday, end_day,purpose, apply_people, cost, type_flag, type_flag2, bus_45, bus_35, bus_28, bus_25) ";
       $sql .= "values('$username', '$start_region', '$start_point_one', '$end_region', '$end_region_two', '$start_point_two', '$end_point_one', '$end_point_two', '$day_one', ";
       $sql .= "'$time_one', '$day_two', '$time_two','$currentday', '$modify_day','$rental_reason', '$user_count', '$rental_money', $type, $type2,'$bus_45', '$bus_35', '$bus_28', '$bus_25')";
       $result = mysql_query($sql, $conn);

    if($result){
		$sql2 = "select Max(lent_num) as lent_num from lent_info";
		$result2 = mysql_query($sql2, $conn);
		$row = mysql_fetch_array($result2);
         http_response_code(200);

      echo "{\"rental_num\":\"$row[lent_num]\"}";

      $lent_num = $row['lent_num'];
      $sql = "select start_region from lent_info where lent_num in (select Max(lent_num) as lent_num from lent_info)";
      $result = mysql_query($sql, $conn);
      $row = mysql_fetch_array($result);

      $sql = "select bus_num ,token from driver_info where region = '$row[start_region]' and push_flag = 0";
       $result = mysql_query($sql, $conn);

      $tokens = array();
      $buses = array();
      if(mysql_num_rows($result) > 0 ){
        $log_txt .= mysql_num_rows($result)."-------numrow---";
              while ($row = mysql_fetch_assoc($result)) {
                  $tokens[] = $row["token"];
                  $buses[] = $row["bus_num"];
              }
          }
          $buscount = count($buses);
              $myMessage = $_POST['message']; //폼에서 입력한 메세지를 받음
              $myMessage = "입찰 완료!";


          $message = array("message" => $myMessage);
      $message_status = send_notification($tokens, $message, $lent_num);
      $log_txt .= $message_status."----------";
      $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
      fwrite($log_file, $log_txt."\r\n");
      fclose($log_file);


      $today = date("Y-m-d H:i:s", time());

      for ($i = 0; $i < $buscount; $i++){
        $sql = "insert into push_list(bus_num, lent_num, message, datetime) values('$buses[$i]', '$lent_num', '지역 매물 등록!', '$today')";
        mysql_query($sql, $conn);
      }

//		http_response_code(200);
    }
    else{
        http_response_code(400);
         echo"{\"code\":\"400\"}";
    }
mysql_close($conn);
}
?>
