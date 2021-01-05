<?php
  $user_id = $_GET["nickname"];
  $lent_num = $_GET["rental_num"];
  $bus_num = $_GET["bus_num"];

 $log_txt = $user_id."|".$lent_num."|".$bus_num;

    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
    fwrite($log_file, $log_txt."\r\n");
    fclose($log_file);


$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");
       $sql = "update lent_info set type_flag2 = 3 where user_id = '$user_id' and lent_num = $lent_num";
       $result = mysql_query($sql, $conn);
        if($result){
			http_response_code(200);
			$response->code=200;
                echo json_encode($response);
			$sql2 = "update lent_info set selected_bus = '$bus_num' where lent_num = '$lent_num'";
			mysql_query($sql2, $conn);
		}
		else{
			http_response_code(400);
			$response->code=400;
                echo json_encode($response);

		}
	}
?>
