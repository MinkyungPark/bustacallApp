<?php
    $bus_num = $_GET["bus_num"];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

$log_txt = $bus_num;

$log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
fwrite($log_file, $log_txt."\r\n");
fclose($log_file);


$sql = "select * FROM bus_info INNER JOIN driver_info ON bus_info.bus_num = driver_info.bus_num and bus_info.bus_num = '$bus_num'";

 if($sel_db) {
        mysql_query("set names utf8");
        $result = mysql_query($sql, $conn);
        $total = mysql_num_rows($result);
        $row = mysql_fetch_array($result);
        if($total){
            http_response_code(200);

            $data = array (
                "birthday" => $row['birthday'],
                "region" => $row['region'],
                "group" => $row['bus_group'],
                "account_bank" => $row['account_bank'],
                "account_num" => $row['account_num'],
                "point" => $row['point'],
                "join_flag" => $row['join_flag'],
                "bus_type" => $row['bus_type'],
                "bus_url" => array(
                  $row['driver_img'], $row['bus_inner_img'],
                  $row['bus_inner_img2'], $row['bus_outer_img'],
                  $row['bus_outer_img2'], $row['license'],
                  $row['confirm']
                ),
                "bus_license" => $row['license'],
                "bus_confirm" => $row['bus_confirm'],
                "nickname" => $row['driver_name'],
                "bus_career" => $row['career'],
                "bus_age" => $row['bus_age'],
                "money" => $row['lent_cost'],
                "bus_num" => $row['bus_num'],
                "user_count" => $row['cur_people'],
                "phone_num" => $row['phone_num'],
                "money_list" => array(
                  $row['money_flag'], $row['money_flag2'],
                  $row['money_flag3'], $row['money_flag4'],
                  $row['money_flag5'], $row['money_flag6']
                ),
                "account_flag" => $row['account_flag']
          );
		$test = json_encode($data);
        print_r($test);

        }

         else{
            http_response_code(400);
              echo"{\"code\":\"401\"}";
         }
    mysql_close($conn);

  }
?>
