<?php
  $lent_num = $_GET["rental_num"];

  $conn = mysql_connect('localhost', 'root', 'kjh83256@#');
  $sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");

        $sql = "select bus_num1, bus_num2, bus_num3,type_flag2, end_day, end_region_two from lent_info where lent_num = $lent_num";
        $result = mysql_query($sql, $conn);
        $total_record = mysql_num_rows($result);
        if($total_record > 0){
            $row2 = mysql_fetch_array($result);
            $endday = str_replace("-",". ",$row2[end_day]);
            $sql = "select * ";
            $sql .= "FROM bus_info INNER JOIN driver_info ";
            $sql .= "ON bus_info.bus_num = driver_info.bus_num and (bus_info.bus_num = '$row2[bus_num1]' OR bus_info.bus_num = '$row2[bus_num2]' OR bus_info.bus_num = '$row2[bus_num3]')";
            $result = mysql_query($sql, $conn);
            $total_record = mysql_num_rows($result);
        if($total_record > 0)
            echo "{\"bus_list\":[";
           for ($i=0; $i < $total_record; $i++){
             mysql_data_seek($result, $i);
             $row = mysql_fetch_array($result);
            $bus_list = array (
                      "bus_url" => array(
                        $row['driver_img'],$row['bus_inner_img'],
                        $row['bus_inner_img2'], $row['bus_outer_img'], $row['bus_outer_img2']),
            "nickname" => $row['driver_name'],
            "bus_career" => $row['career'],
            "money" => $row['lent_cost'],
            "bus_num" => $row['bus_num'],
            "bus_type" => $row['bus_type'],
			"account_flag" => $row['account_flag'],
			"phone_num" => $row['phone_num'],
            "money_list" => array($row['money_flag'], $row['money_flag2'],
                                  $row['money_flag3'], $row['money_flag4'],
                                  $row['money_flag5'], $row['money_flag6'])

        );
        $test = json_encode($bus_list);
        print_r($test);
        if($i < $total_record - 1){
                 echo ",";
               }

      }
    if($total_record > 0)
        echo "],\"type_two\":\"$row2[type_flag2]\",\"rental_num\":\"$lent_num\",\"end_day\":\"$endday\",\"end_region_two\":\"$row2[end_region_two]\"}";
//  else
//      echo "{\"type_two\":\"$row2[type_flag2]\"}";
   }
   else{
        echo "{\"type_two\":\"0\"}";
   }
}
else{
     echo"{\"code\":\"400\"}";
   }
mysql_close($conn);


?>
