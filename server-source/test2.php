<?php
    $driver_name = $_POST['nickname'];
    $birthday = $_POST['birthday'];
    $region = $_POST['region'];
    $bus_group = $_POST['bus_group'];
    $license = $_POST['license'];
    $bus_num = $_POST['bus_num'];
    $phone_num = $_POST['phone_num'];
    $account_bank = $_POST['account_bank'];
    $account_num = $_POST['account_num'];
    $career = $_POST['bus_career'];
    $bus_age = $_POST['bus_age'];
    $bus_type = $_POST['bus_type'];
    $lent_cost = $_POST['money'];
    $cur_people = $_POST['user_count'];
    $money_list = $_POST['money_list'];
    $account_flag = $_POST['account_flag'];
    $data = array();
    $fpath = array();
    $file_path = "./picture/";
    $var = $_POST['result'];

    
    for($x = 0; $x < 7; $x++){
          $y = $x + 1;
          $fpath[$x] = $file_path . basename( $_FILES['uploaded_file'.$y]['name']);
          if(move_uploaded_file($_FILES['uploaded_file'.$y]['tmp_name'], $file_path1)){
            $filename = md5("habony_".$_FILES['uploaded_file'.$y]['name']);
              $data[$x] = $filename;
          }
    }
    
    $log_txt = "";

    $log_txt .= $driver_name." |";
    $log_txt .= $birthday." |"; 
    $log_txt .= $region." |"; 
    $log_txt .= $bus_group." |";
    $log_txt .= $license." |";
    $log_txt .= $bus_num." |";
    $log_txt .= $phone_num." |";
    $log_txt .= $account_bank." |";
    $log_txt .= $account_num." |";
    $log_txt .= $career." |";
    $log_txt .= $bus_age ." |";
    $log_txt .= $bus_type ." |";
    $log_txt .= $lent_cost ." |";
    $log_txt .= $cur_people ." |";
    $log_txt .= $money_list ." |";
    $log_txt .= $account_flag ." |";
    $log_txt .= $data[0] ." |";
    $log_txt .= $data[1] ." |";
    $log_txt .= $data[2] ." |";
    $log_txt .= $data[3] ." |";
    $log_txt .= $data[4] ." |";
    $log_txt .= $data[5] ." |";
    $log_txt .= $data[6] ." |";

    
    for($i = 0; $i < 7; $i++)
      $data[$i] = "http://203.252.166.242:8080/picture/".$data[$i];

    
    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
   fwrite($log_file, $log_txt."\r\n");
   fclose($log_file);

    
$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);

 if($sel_db) {
        mysql_query("set names utf8");

        $sql = "update driver_info set driver_name = '$driver_name', birthday = '$birthday', ";
        $sql .= "region = '$region', bus_group = '$bus_group', license = '$license', ";
        $sql .= "driver_img = '$data[0]', bus_num = '$bus_num', ";
        $sql .= "phone_num = '$phone_num', account_bank = '$account_bank', account_num = '$account_num', ";
        $sql .= "career = '$career', lent_cost = '$lent_cost' where phone_num = '$phone_num'";
        $result = mysql_query($sql, $conn);
        if($result){
            $sql2 = "insert into bus_info(bus_num, bus_type, bus_age, bus_confirm, bus_inner_img, ";
            $sql2 .= "bus_inner_img2, bus_outer_img, bus_outer_img2, bus_img, bus_img2, cur_people, ";
            $sql2 .= "money_flag, money_flag2, money_flag3, money_flag4, money_flag5, money_flag6, account_flag) ";
            $sql2 .= "values ( '$bus_num', '$bus_type', '$bus_age', '$bus_confirm', ";
            $sql2 .= "'$data[1]', '$data[2]', '$data[3]', '$data[4]', '$data[5]', '$data[6]', ";
            $sql2 .= "'$cur_people', '$money_flag', '$money_flag2', '$money_flag3', '$money_flag4',";
            $sql2 .= "'$money_flag5', '$money_flag6', '$account_flag')";
            $result2 = mysql_query($sql2, $conn);
            if($result2){
              http_response_code(200);
            }
            else
              http_response_code(200);
        }
        else
          http_response_code(200);  
    mysql_close($conn);
  }
?>

