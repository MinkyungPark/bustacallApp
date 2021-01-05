<?php
	//데이터베이스에 접속해서 토큰들을 가져와서 FCM에 발신요청
function send_notification ($tokens, $message)
	{
		$url = 'https://fcm.googleapis.com/fcm/send';

		$fields = array (
        	'registration_ids' => $tokens,
        	'notification' => array (
        	        "title" => "Bustacall",
					 "body" => "입찰 완료!"
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

$conn = mysql_connect(DB_HOST, DB_USER, DB_PASSWORD);
	$mydb = mysql_select_db(DB_NAME,$conn);
	mysql_query("set names utf8");
	$sql = "select pushtoken from user_info where user_name = '박영수'";
	//$sql = "select pushtoken from driver_info where driver_name = '강령현'";
	$result = mysql_query($sql);
	$tokens = array();

	if(mysql_num_rows($result) > 0 ){

		while ($row = mysql_fetch_assoc($result)) {
			$tokens[] = $row["pushtoken"];
		}
	}

	mysql_close($conn);

        $myMessage = $_POST['message']; //폼에서 입력한 메세지를 받음
		$myMessage = "입찰 완료!";


	$message = array("message" => $myMessage);


$message_status = send_notification($tokens, $message);
	echo $message_status;

 ?>
