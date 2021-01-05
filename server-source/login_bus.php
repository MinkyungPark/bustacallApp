<?php
    $phone_num = $_GET["phonenum"];
    $certify = $_GET["certificationnum"];
    $token = $_GET["token"];
    $response = new stdClass();

    $log_txt = "login_bus     ".$token."|".$phone_num;

    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
    fwrite($log_file, $log_txt."\r\n");
    fclose($log_file);



$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
$sel_db = mysql_select_db("sk_bus", $conn);
 if($sel_db) {
        mysql_query("set names utf8");
        $sql2 = "select * from certification where phone_num = '$phone_num' and certify = '$certify'";
        $result2 = mysql_query($sql2, $conn);
        $total = mysql_num_rows($result2);

        if($total){
           mysql_query("set names utf8");
           $sql = "insert into driver_info(phone_num, token) values('$phone_num','$token')";
           $result = mysql_query($sql, $conn);
            if($result2){

                $response->code=200;
                echo json_encode($response);
                 $sql3 = "delete from certification where phone_num = '$phone_num' and certify = '$certify'";
                mysql_query($sql3, $conn);
            }
            else{
                http_response_code(400);
                $response->code=400;
                echo json_encode($response);
            }
        }
        else{
            http_response_code(400);
            $response->code=400;
            echo json_encode($response);

        }

        mysql_close($conn);
}

?>
