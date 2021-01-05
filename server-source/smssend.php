<?php
$phone_number = $_GET["phonenum"];
$phone1 = substr($phone_number,0,3);
$phone2 = substr($phone_number,3,4);
$phone3 = substr($phone_number,7,4);
$newPhone = $phone1."-".$phone2."-".$phone3;

$rand_num = sprintf("%06d",mt_rand(0,999999));


$msg = "버스타콜 인증번호 [".$rand_num."]를 입력해주세요.";

$conn = mysql_connect('localhost', 'root', 'kjh83256@#');
    if($conn) {
        $sel_db = mysql_select_db("sk_bus", $conn);
        if($sel_db) {
        mysql_query("set names utf8");
        $sql = "insert into certification(phone_num,certify) values('$phone_number','$rand_num')";
        $result = mysql_query($sql, $conn);
        }else{
            echo "database 선택 실패";
        }
    } else {
    echo "접속 실패";
    }

    mysql_close();

 
       /******************** 인증정보 ********************/
        $sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // 전송요청 URL
        // $sms_url = "https://sslsms.cafe24.com/sms_sender.php"; // HTTPS 전송요청 URL
        $sms['user_id'] = base64_encode("dkl1990"); //SMS 아이디.
        $sms['secure'] = base64_encode("d8d80a6682ca6c2cdaf06afe6f31a400") ;//인증키
        $sms['msg'] = base64_encode($msg);
        $sms['rphone'] = base64_encode($newPhone);
        $sms['sphone1'] = base64_encode("010");
        $sms['sphone2'] = base64_encode("2392");
        $sms['sphone3'] = base64_encode("4866");
        $sms['mode'] = base64_encode("1"); // base64 사용시 반드시 모드값을 1로 주셔야 합니다.
        $sms['smsType'] = base64_encode("S"); // LMS일경우 L

        $host_info = explode("/", $sms_url);
        $host = $host_info[2];
        $path = $host_info[3]."/".$host_info[4];

        srand((double)microtime()*1000000);
        $boundary = "---------------------".substr(md5(rand(0,32000)),0,10);
        //print_r($sms);

        // 헤더 생성
        $header = "POST /".$path ." HTTP/1.0\r\n";
        $header .= "Host: ".$host."\r\n";
        $header .= "Content-type: multipart/form-data, boundary=".$boundary."\r\n";

        // 본문 생성
        foreach($sms AS $index => $value){
            $data .="--$boundary\r\n";
            $data .= "Content-Disposition: form-data; name=\"".$index."\"\r\n";
            $data .= "\r\n".$value."\r\n";
            $data .="--$boundary\r\n";
        }
        $header .= "Content-length: " . strlen($data) . "\r\n\r\n";

        $fp = fsockopen($host, 80);

        if ($fp) {
            fputs($fp, $header.$data);
            $rsp = '';
            while(!feof($fp)) {
                $rsp .= fgets($fp,8192);
            }
            fclose($fp);
            $msg = explode("\r\n\r\n",trim($rsp));
            $rMsg = explode(",", $msg[1]);
            $Result= $rMsg[0]; //발송결과
            $Count= $rMsg[1]; //잔여건수

            //발송결과 알림
			if($Result=="success") {
                $alert = "성공";
				echo"{\"code\":\"200\"}";
            }
            else if($Result=="reserved") {
            }
            else if($Result=="3205") {
                $alert = "잘못된 번호형식입니다.";
				echo"{\"code\":\"400\"}";
            }

            else if($Result=="0044") {
                $alert = "스팸문자는발송되지 않습니다.";
				echo"{\"code\":\"400\"}";
            }

            else {
                $alert = "[Error]".$Result;
				echo"{\"code\":\"400\"}";
            }
        }
        else {
            $alert = "Connection Failed";
        }

         if($nointeractive=="1" && ($Result!="success" && $Result!="Test Success!" && $Result!="reserved") ) {
        }
        else if($nointeractive!="1") {
        }

?>            
