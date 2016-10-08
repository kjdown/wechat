<?php

$code=$_GET["code"];
$userinfo=getUserInfo($code);
function getUserInfo($code){
	$appid=""
    $appsecret=""
    $access_token_url="";
    $access_token_json=http_request($access_token_url);
    $access_token_array=json_decode($access_token_json,true);
    $openid=$access_token_array['openid'];
    $new_access_token_url="";
    $access_token_json=http_request($access_token_url);
    $access_token_array=json_decode($access_token_json,true);
    $new_access_token=$new_access_token_array['access_token'];
    $userinfo_url="";
    $userinfo_json=http_request($userinfo_url);
    $userinfo_array=json_decode($userinfo_json,true);
    return $userinfo_array;
}
function https_request($url){
	$curl=curl_init();
    curl_setopt($curl, CURLOPT_URL, $url);
    curl_setopt($curl, CURLOPT_SSL_VERIFYPEER, FALSE);
    curl_setopt($curl, CURLOPT_SSL_VERIFYHOST, FALSE);
    curl_setopt($curl, CURLOPT_RETURNTRANSFER, 1);
    $data=curl_exec($curl);
    if(curl_error($curl)){return 'ERROR'.curl_error($curl);}
    curl_close($curl);
    return $data;
}
?>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name=viewport content="width=device-widt,user-scalable=no,initial-scale=1">
	<link rel="stylesheet" href="css/jquery.mobile_1.3.2.css">
	<script src="js/jquery-1.10.2.js"></script>
	<script src="js/jquery.mobile-1.3.2.js"></script>
	<title>OAurh2.0认证</title>
</head>
<body>
	<script type="text/javascript">
    document.addEventListener('WeixinJSBridgeReady',function onBridgeReady(){WeixinJSBridge.call('hideOptionMenu');});
    document.addEventListener('WeixinJSBridgeReady',function onBridgeReady(){WeixinJSBridge.call('hideToolbar');});	
 </script>
 <div data-role="page" id="page1">
 <div data-role="content">
 <div style="text-align:center">
 <img style="width:99%;height:%"src="<?php echo $userinfo["headimgurl"];?>">
 </div>
 <UL data-role="listview" data-inset="true">
 <LI>
 <p>
 <div class="fieldcontain">
 <label for="subscribe">是否关注</label>
 <input name="subscribe" id="subscribe" value="<?php echo $userinfo["subscribe"];?>"type="text">
 </div>

 <div class="fieldcontain">
 <label for="openid">OpenID</label>
 <input name="openid" id="openid" value="<?php echo $userinfo["openid"];?>" type="text">
 </div>

 <div class="fieldcontain">
 <label for="nickname">昵称</label>
 <input name="nickname" id="nickname" value="<?php echo $userinfo["nickname"];?>" type="text">
 </div>
 <div class="fieldcontain">
 <label for="sex">性别</label>
 <input name="sex" id="sex" value="<?php echo $userinfo["sex"];?>" type="text">
 </div>

 <div class="fieldcontain">
 <label for="country">国家</label>
 <input name="country" id="country" value="<?php echo $userinfo["country"];?>" type="text">
 </div>

 <div class="fieldcontain">
 <label for="province">省份</label>
 <input name="province" id="province" value="<?php echo $userinfo["province"];?>" type="text">
 </div>

 <div class="fieldcontain">
 <label for="city">城市</label>
 <input name="city" id="city" value="<?php echo $userinfo["city"];?>" type="text">
 </div>

 <div class="fieldcontain">
 <label for="subscribe_time">关注时间</label>
 <input name="subscribe_time" id="subscribe_time" value="<?php echo $userinfo["subscribe_time"];?>" type="text">
 </div>

 </p>
 </LI>
 </UL>
 </div>
 <div data-theme="a" data-role="footer" data-position="fixed">
 <h3>精英微信</h3>
 </div>
 </div>
</body>
</html>


}