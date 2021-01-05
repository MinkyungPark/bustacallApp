<?php
//데이터베이스에 접속해서 토큰들을 가져와서 FCM에 발신요청
function send_notification ($tokens, $message, $lent_num)
{
        $url = 'https://fcm.googleapis.com/fcm/send';

        $fields = array (
            'registration_ids' => $tokens,
            'notification' => array (
                    "title" => "Bustacall",
                     "body" => "입찰 완료!"
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

    $lent_num = $_GET["rental_num"];
    $bus_num = $_GET["bus_num"];
    $lent_cost = $_GET["money"];
    $money_one = $_GET["money_one"];
    $money_two = $_GET["money_two"];
    $money_three = $_GET["money_three"];
    $money_four = $_GET["money_four"];
    $money_five = $_GET["money_five"];
    $money_six = $_GET["money_six"];

    $log_txt = "send_bus  ".$bus_num."|".$lent_num."|".$lent_cost."|".$money_one."|".$money_two;




$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");

        $sql3 = "update bus_info set money_flag = '$money_one', money_flag2 = '$money_two', ";
        $sql3 .= "money_flag3 = '$money_three', money_flag4 = '$money_four', money_flag5 = '$money_five', ";
        $sql3 .= "money_flag6 = '$money_six' where bus_num = '$bus_num'";
        $result3 = mysql_query($sql3, $conn);

        $sql4 = "update driver_info set lent_cost = '$lent_cost' where bus_num = '$bus_num'";
        $result4 = mysql_query($sql4, $conn);

        $sql = "select bus_num1 ,bus_num2 ,bus_num3 from lent_info where lent_num = $lent_num";
        $result = mysql_query($sql, $conn);
        $row = mysql_fetch_array($result);


        if((!strcmp($row['bus_num1'], "")) && strcmp($row['bus_num1'], $bus_num)){
          $sql2 = "update lent_info set bus_num1 = '$bus_num', auction_flag = 1 where lent_num = '$lent_num'";
          $result2 = mysql_query($sql2, $conn);
          if($result2){
            http_response_code(200);
            echo "{\"flag\":\"1\"}";
            $sql4 = "update lent_info set auction_count = auction_count + 1, cost2 = '$lent_cost' where lent_num = '$lent_num'";
            $result4 = mysql_query($sql4, $conn);
          }
          else{
            http_response_code(400);
          }
          $log_txt .= " 중 복 탐  ";

          return;
        }
        else if((!strcmp($row['bus_num2'], "")) && strcmp($row['bus_num2'], $bus_num) && strcmp($row['bus_num1'], $bus_num)){
          $sql2 = "update lent_info set bus_num2 = '$bus_num', auction_flag = 1 where lent_num = '$lent_num'";
          $result2 = mysql_query($sql2, $conn);
          if($result2){
            http_response_code(200);
            echo "{\"flag\":\"1\"}";
            $sql4 = "update lent_info set auction_count = auction_count + 1, cost2 = '$lent_cost' where lent_num = '$lent_num'";
            $result4 = mysql_query($sql4, $conn);
          }
          else{
            http_response_code(400);
          }
          return;
        }
        else if((!strcmp($row['bus_num3'], "")) && strcmp($row['bus_num3'], $bus_num) && strcmp($row['bus_num2'], $bus_num) && strcmp($row['bus_num1'], $bus_num)){
          $sql2 = "update lent_info set bus_num3 = '$bus_num', auction_flag = 1 where lent_num = '$lent_num'";
          $result2 = mysql_query($sql2, $conn);
          if($result2){

                $sql6 = "select * from lent_info where lent_num = $lent_num";
                $result6 = mysql_query($sql6, $conn);
                $row6 = mysql_fetch_array($result6);

                $log_txt .= "user : ".$row6['user_id'];

                $sql5 = "select pushtoken from user_info where user_name = '$row6[user_id]' and push_flag = 0";
                $result5 = mysql_query($sql5, $conn);
                $tokens = array();
                if(mysql_num_rows($result5) > 0 ){
                        while ($row5 = mysql_fetch_assoc($result5)) {
                            $tokens[] = $row5["pushtoken"];
                        }
                    }
                  $log_txt .= "  tokens : ".$tokens[0]."  ".$tokens[1];
                    $sql7 = "update lent_info set type_flag2 = 2 where lent_num = $lent_num and user_id in (select user_name from user_info where pushtoken != '')";
                    $result7 = mysql_query($sql7);

                        $myMessage = $_POST['message']; //폼에서 입력한 메세지를 받음
                        $myMessage = "입찰 완료!";


                    $message = array("message" => $myMessage);


                $message_status = send_notification($tokens, $message, $lent_num);

                    $log_txt .= " message ".$message_status;
            echo "{\"flag\":\"1\"}";
            http_response_code(200);
            $sql4 = "update lent_info set auction_count = auction_count + 1, cost2 = '$lent_cost' where lent_num = '$lent_num'";
            $result4 = mysql_query($sql4, $conn);

            $log_txt .= "push send";
            $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
            fwrite($log_file, $log_txt."\r\n");
            fclose($log_file);
          }
          else{
            http_response_code(400);
          }
          return;
        }
        else{
          echo "{\"flag\":\"0\"}";
          $log_txt .= "입찰 실패";
          $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
          fwrite($log_file, $log_txt."\r\n");
          fclose($log_file);
          return;
        }

    mysql_close($conn);
    }

?>
