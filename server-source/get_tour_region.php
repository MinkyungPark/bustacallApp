<?php

    $areaCode = $_GET['areaCode'];
    $sigunguCode = $_GET['sigunguCode'];
    $contentTypeId = $_GET['contentTypeId'];

    $log_txt = "--".$serviceKey."--".$areaCode."--".$sigunguCode."--".$contentTypeId;
    $log_file = fopen("./log.txt","a") or die("쓰기 모드가 왜 안되냐고 권한도 줬는데 ㅡㅡ");
   fwrite($log_file, $log_txt."\r\n");
   fclose($log_file);
   $str = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?serviceKey=VRyrW8MxOtTcNpDoBx%2B5KHCveEnGWiqPRqAQOHXr%2Bc%2FfTBeLD%2F15%2Fp29EplcH%2Brtrp34D9sc3izbEHgzzf8wfw%3D%3D&numOfRows=10&pageSize=10&pageNo=1&startPage=1&arrange=A&listYN=Y&contentTypeId='.$contentTypeId.'&areaCode='.$areaCode.'&sigunguCode='.$sigunguCode.'&MobileOS=AND&MobileApp=BustaCall&_type=json';

      echo("<script>location.href='$str';</script>");

?>
