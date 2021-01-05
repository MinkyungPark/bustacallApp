<?

    $oCurl = curl_init();

    $url =  "https://sslsms.cafe24.com/smsSenderPhone.php";

    $aPostData['userId'] = "dkl1990";

    $aPostData['passwd'] = "d8d80a6682ca6c2cdaf06afe6f31a400"; 

    curl_setopt($oCurl, CURLOPT_URL, $url);

    curl_setopt($oCurl, CURLOPT_POST, 1);

    curl_setopt($oCurl, CURLOPT_RETURNTRANSFER, 1);

    curl_setopt($oCurl, CURLOPT_POSTFIELDS, $aPostData);

    curl_setopt($oCurl, CURLOPT_SSL_VERIFYPEER, 0);

    $ret = curl_exec($oCurl);

    echo $ret;

    curl_close($oCurl);

?>
