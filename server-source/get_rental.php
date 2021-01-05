<?php
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

$sql = "select * FROM lent_info where auction_count < 3 and (together_flag < 1 or (together_flag > 0 and current_user_count > 19)) ORDER BY lent_num desc";

 if($sel_db) {
	mysql_query("set names utf8");

    $result = mysql_query($sql, $conn);
    $total_record = mysql_num_rows($result);
			if($total_record > 0)
            echo "{\"rental_list\":[";
           for ($i=0; $i < $total_record; $i++){
             mysql_data_seek($result, $i);
             $row = mysql_fetch_array($result);
            $data = array (
                "nickname" => $row['user_id'],
                "start_point_one" => $row['start_point'],
                "end_point_one" => $row['end_point'],
                "day_one" => $row['date'],
                "day_two" => $row['date2'],
                "time_one" => $row['time'],
                "time_two" => $row['time2'],
                "start_region" => $row['start_region'],
                "end_region" => $row['end_region'],
                "end_region_two" => $row['end_region_two'],
                "rental_reason" => $row['purpose'],
                "user_count" => $row['apply_people'],
                "user_max_count" => $row['max_count'],
                "rental_money" => $row['cost'],
                "lent_money" => $row['cost2'],
                "bus_45" => $row['bus_45'],
                "bus_35" => $row['bus_35'],
                "bus_28" => $row['bus_28'],
                "bus_25" => $row['bus_25'],
                "type" => $row['type_flag'],
                "type_two" => $row['type_flag2'],
                "rental_num" => $row['lent_num'],
                "current_day" => $row['currentday'],
                "end_day" => $row['end_day'],
                "account_flag" => $row['account_flag'],
                "together" => array(
                "flag" =>  $row['together_flag'],
                "current_user_count" => $row['current_user_count'],
                "max_user_count" => $row['max_user_count'],
                "money" =>  $row['together_money'],
                "text" =>  $row['text']
               )
        );

        $test = json_encode($data);
        print_r($test);
        if($i < $total_record - 1){
                 echo ",";
               }

    }
if($total_record > 0)
	echo "]}";
  if($total_record == 0){
    $data = array (
    "nickname" => null,
    "start_point_one" => null,
    "end_point_one" => null,
    "day_one" => null,
    "day_two" => null,
    "time_one" => null,
    "time_two" => null,
    "start_region" => null,
    "end_region" => null,
    "rental_reason" => null,
    "user_count" => null,
    "user_max_count" => null,
    "rental_money" => null,
    "bus_45" => null,
    "bus_35" => null,
    "bus_28" => null,
    "bus_25" => null,
    "type" => null,
    "type_two" => null,
    "rental_num" => null,
    "current_day" => null,
    "end_day" => null,
    "account_flag" => null,
    "together" => array(
    "flag" =>  null,
    "current_user_count" => null,
    "max_user_count" => null,
    "money" =>  null,
    "text" =>  null
    )
    );
    $test = json_encode($data);
    print_r($test);

  }


}

mysql_close($conn);

?>
