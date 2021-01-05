<?php
    $user_name = $_GET["nickname"];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
$data = array();

 if($sel_db) {
		mysql_query("set names utf8");
        $sql2 = "select * from user_info where user_name = '$user_name'";
        $result2 = mysql_query($sql2, $conn);
        $total = mysql_num_rows($result2);
		$row = mysql_fetch_array($result2);
        if($total){
	        http_response_code(200);
			 echo "{\"code\":\"200\", \"user_nickname\":\"$row[user_name]\",\"user_phonenum\":\"$row[phone_num]\",\"user_account\":\"$row[user_account]\",\"user_point\":\"$row[point]\",\"rental_list\":[";
            $sql = "select * from lent_info where user_id = '$user_name' ";
            $sql .= "or (lent_num in (select lent_num from tmp_list where user_name = '$user_name'))";
            $result = mysql_query($sql, $conn);
            $total2 = mysql_num_rows($result);
            for ($i = 0; $i < $total2; $i++){
                mysql_data_seek($result, $i);
				$row = mysql_fetch_array($result);

                $data = array (
                    "rental_num" => "$row[lent_num]",
                    "bus_num1" => "$row[bus_num1]",
                    "bus_num2" => "$row[bus_num2]",
                    "bus_num3" => "$row[bus_num3]",
                    "nickname" => "$row[user_id]",
                    "start_point_one" => "$row[start_point]",
                    "start_point_two" => "$row[start_point2]",
                    "end_point_one" => "$row[end_point]",
                    "end_point_two" => "$row[end_point2]",
                    "start_region" => $row['start_region'],
                    "end_region" => $row['end_region'],
                    "end_region_two" => $row['end_region_two'],
                    "user_count" => "$row[apply_people]",
                    "rental_money" => "$row[cost]",
                    "lent_money" => $row['cost2'],
                    "type_two" => "$row[type_flag2]",
                    "type" => "$row[type_flag]",
                    "bus_45" => "$row[bus_45]",
                    "bus_35" => "$row[bus_35]",
                    "bus_28" => "$row[bus_28]",
                    "bus_25" => "$row[bus_25]",
                    "current_day" => "$row[currentday]",
                    "rental_reason" => "$row[purpose]",
                    "day_one" => "$row[date]",
                    "time_one" => "$row[time]",
                    "day_two" => "$row[date2]",
                    "time_two" => "$row[time2]",
                    'together' => array (
                      "flag" => "$row[together_flag]",
                      "max_user_count" => "$row[max_user_count]",
                      "money" => "$row[together_money]",
                      "type_flag" => "$row[together_type_flag]",
                      "current_user_count" => "$row[current_user_count]",
                      "text" => "$row[text]"
                    )
                );
                $data = json_encode($data);
                print_r($data);
				if($i<$total2-1){
                 echo ",";
               }

            }
            echo "]}";
        }

         else{
			http_response_code(400);
              echo"{\"code\":\"401\"}";
         }
    mysql_close();
    }
?>
