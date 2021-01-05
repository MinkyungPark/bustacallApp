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


  $user_id = $_GET['nickname'];
  $lent_num = $_GET['rental_num'];
  $adduser = $_GET['user'];




$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
 if($sel_db) {
   mysql_query("set names utf8");

   $sql8 = "select * from tmp_list where lent_num = $lent_num and user_name = '$user_id'";
   $result8 = mysql_query($sql8, $conn);
   $ff = mysql_num_rows($result8);
   if ($ff > 0){
        echo "{\"flag\":\"1\"}";
        return;
    }

  $sql2 = "select * from lent_info where lent_num = $lent_num";
  $result2 = mysql_query($sql2, $conn);
  $row2 = mysql_fetch_array($result2);

  $sql = "update lent_info set current_user_count = current_user_count + $adduser where ";
  $sql .= "lent_num = '$lent_num' and user_id != '$user_id'";
  $result = mysql_query($sql, $conn);

  $sql3 = "select * from lent_info where lent_num = $lent_num";
  $result3 = mysql_query($sql3, $conn);
  $row3 = mysql_fetch_array($result3);
  if (strcmp($row2['current_user_count'], $row3['current_user_count'])){
    http_response_code(200);
    echo "{\"flag\":\"1\"}";


    $sql = "select start_region from lent_info where current_user_count > 19 and lent_num = '$lent_num'";
    $result = mysql_query($sql, $conn);
    $row = mysql_fetch_array($result);

    $sql = "select bus_num, token from driver_info where region = '$row[start_region]' and push_flag = 0";
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

    $sql = "insert into tmp_list(lent_num, user_name, user_count) values ('$lent_num', ";
    $sql .= "'$user_id', '$adduser')";
    mysql_query($sql, $conn);
    $today = date("Y-m-d H:i:s", time());

    for ($i = 0; $i < $buscount; $i++){
      $sql = "insert into push_list(bus_num, lent_num, message, datetime) values($buses[$i], $lent_num, '지역 매물 등록!',$today)";
      mysql_query($sql, $conn);
    }


  }
  else{
    echo "{\"flag\":\"2\"}";
  }
}

 ?>
