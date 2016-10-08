<?php
$access_token="WeWWvdrQAwIdkDCZEipgpNCbB7bHjqoHAPKH_lAv2AQExOKGOgCV_6Ux9ieE7SGcgONopvqKr3dBX3AbmS07mJwHDhzKpURjpc1p1fFiuQLO_ZIyXloUvf14Bbbz6aVVOWEjABAVDL";


//生成临时二维码
$qrcode='{"expire_seconds":604800,"action_name":"QR_SCENE","action_info":{"scene":{"scene_id":1234}}}';

//生成永久二维码
//qrcode='{"expire_seconds":604800,"action_name":"QR_SCENE","action_info":{"scene":{"scene_id":1234}}}';

$url="https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=$access_token";
$result=https_request($url,$qrcode);
var_dump($result);
$jsoninfo=json_decode($result,true);
$ticket=$jsoninfo["ticket"];
var_dump($ticket);
function https_request($url,$data=null){
	$curl=curl_init();
	curl_setopt($curl,CURLOPT_URL,$url);
    curl_setopt($curl,CURLOPT_SSL_VERIFYPEER,FALSE);
    curl_setopt($curl,CURLOPT_SSL_VERIFYHOST,FALSE);
    if(!empty($data)){
    	curl_setopt($curl,CURLOPT_POST,1);
    	curl_setopt($curl,CURLOPT_POSTFIELDS,$data);
    }
    curl_setopt($curl,CURLOPT_RETURNTRANSFER,1);
    $output=curl_exec($curl);
    var_dump($output);
    curl_close($curl);
    return $output;
}?>