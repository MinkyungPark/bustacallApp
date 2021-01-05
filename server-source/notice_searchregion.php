<?php

function passing_time($datetime) {

    $time_lag = time() - strtotime($datetime);



    if($time_lag < 60) {

        $posting_time = "방금";

    } elseif($time_lag >= 60 and $time_lag < 3600) {

        $posting_time = floor($time_lag/60)."분 전";

    } elseif($time_lag >= 3600 and $time_lag < 86400) {

        $posting_time = floor($time_lag/3600)."시간 전";

    } elseif($time_lag >= 86400 and $time_lag < 2419200) {

        $posting_time = floor($time_lag/86400)."일 전";

    } else {

        $posting_time = date("y-m-d", strtotime($datetime));

    }



    return $posting_time;

}


$bus_num = $_GET['bus_num'];
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

$sql = "select * FROM push_list where bus_num = '$bus_num' ORDER BY datetime desc limit 10";

 if($sel_db) {
	mysql_query("set names utf8");

    $result = mysql_query($sql, $conn);
    $total_record = mysql_num_rows($result);
			if($total_record > 0)
            echo "{\"notice_list\":[";
           for ($i=0; $i < $total_record; $i++){
             mysql_data_seek($result, $i);
             $row = mysql_fetch_array($result);

            $data = array (
                "msg" => $row['message'],
                "time" => passing_time($row['datetime'])
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
    echo "{\"message_list\":[";
    $data = array (
    "msg" => null,
    "time" => null
  );
    $test = json_encode($data);
    print_r($test);
    echo "]}";
  }


}

mysql_close($conn);

?>
