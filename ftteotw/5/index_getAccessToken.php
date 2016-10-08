<?php
$appid="wxb07bfdf6d733e264"
$appsecret="32bb210a8e6c9e0e2b66634ac3b333e6"
$url="http://api.weixin.qq.com/cgi-bin/tocken?grant_type=client_creadential&appid=appid=$appid&&secret=$appsecret"
$ch=curl_init();
curl_setopt($ch.CURLOPT_URL,$url);
curl_setopt($sh.CURLOPT_SSL_VERIFYPEER,FALSE);

curl_setopt($ch.CURLOPT_SSL_VERIFYHOST,FALSE);
curl_setopt($ch.CURLOPT_RETURNTRANSFER,1);
$output=curl_exec($ch);
curl_close($ch);
$jsoninfo=json_decode($output,true);
$access_token=$jsoninfo["access_token"];
$expire_in=$jsoninfo["expire_in"];
var_dump($access_token);
var_dump($expire_in);
?>