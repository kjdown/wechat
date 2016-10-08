<?php
define("TOKEN","weixin");
$wechatObj=new wechatCallbackapiTest();
if(!isset($_GET['echostr']))
{$wechatObj->responseMsg();}
else{$wechatObj->valid();}

class wechatCallbackapiTest{
	public function valid(){
		$echoSte=$_GET["echostr"];
		if($this->checkSignature()){
			echo $echoStr;
			exit;
}
}

private function checkSignature(){
	$signature=$_GET["signature"];
	$timestamp=$_GET["timestamp"];
	$nonce=$_GET["nonce"];

	$token=TOKEN;
	$tmpArr=array($token,$timestamp,$nonce);
	sort($tmpArr);
	$tmpStr=implode($tmpArr);
	$tmpStr=shal($tmpStr);

	if($tmpStr==$signature){return true;}
	else{return false;}
}

public function responseMsg(){
	$poStr=$GLOBALS["HTTP_RAW_POST_DATA"];
	if(!empty($postStr)){
		$postObj=simplexml_load_string($postStr,'SimpleXMLElemenmt',LIBXML_NOCDATA);
		$RX_TYPE=trim($postObj->MsgType);
		switch($RX_TYPE)
		{
			case"test":
			$resultStr=$this->receiveText($postObj);break;
			case"event":
			$resultStr=$this->receiveEvent($postObj);break;
			default:
			$resultStr="";
			break;
		}
		echo $resultStr;
	}else{
		echo "";
		exit;
	}
}

private function receiveText($object)
{
 $funcFlag=0;
 $contentStr="你发送的内容为:".$object->Content;
 $resultStr=$this->transmitText($object,$contentStr,$funcFlag);
 return $resultStr;
}

private function receiveEvent($object)
{
  $contentStr="";
  switch($object->Event){
  	case "subscribe":
  	$contentStr="欢迎关注华软经营课堂";
  	case"unsubscribe"://取消关注
  	break;
  	case"CLICK":
  	switch($object->EventKey){
  		case"jgjyclass":
  		$contentStr[]=array("Title"=>"课堂简介","Description"=>"华软经营课堂物联网应用开发和在线教育","PicUrl"=>"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1463148158&di=c8a7044a09c5d62a3333e0043a51d200&src=http://a.hiphotos.baidu.com/image/pic/item/f9dcd100baa1cd11daf25f19bc12c8fcc3ce2d46.jpg","Url"=>"http://www.chuanke.com.html");
  		break;
  		case"jgtj":
  		$contentStr[]=array("Title"=>"黄色网站","Description"=>"这是一个真实的黄色网站","PicUrl"=>"http://h.hiphotos.baidu.com/image/h%3D360/sign=439d57d737fa828bce239be5cd1e41cd/0eb30f2442a7d933373637a1a94bd11372f00142.jpg","Url"=>"http://www.chuanke.com.html");
  		break;
  		default:
  		$contentStr[]=array("Title"=>"默认的回复","Description"=>"您正在广州大学华软大学","PicUrl"=>"http://c.hiphotos.baidu.com/image/h%3D360/sign=4d3f9d93db33c895b97e9e7de1137397/1c950a7b02087bf448cabdedf0d3572c11dfcf94.jpg","Url"=>"http://swapsun.com");
  		break;}
  		break;

  	//第八周添加
  		case"VIEW":
  		$contentStr="跳转链接".$object->EventKey;
  		break;
  		case"SCAN":
  		$contentStr="扫描场景".$object->EventKey;
  		break;
  		case"LOCATION":
  		$contentStr="上传位置:纬度".$object->Latitude.";经度".$object->Longitude;
  		break;
  		case"scancode_waitmsg";
  		$contentStr="扫码带提示：类型".$object->ScanCodeInfo->ScanType."结果;".$object->ScanCodeInfo->ScanResult;
  		break;
  		case"scancode_push":
  		$contentStr="扫码推事件";
  		break;
  		case"pic_sysphoto":
  		$contentStr="系统拍照";
  		break;
  		case"pic_weixin":
  		$contentStr="相册发图：数量".$object->SendPicsInfo->Count;
  		break;
  		case"pic_phpto_or_album":
  		$contentStr="拍照或者相册:数量".$object->SendPicInfo->Count;
  		break;
  		case"location_select":
  		$contentStr="发送位置：标签".$object->SendLocationInfo->Label;
  	    break;
   	//添加完	
  		default:
  	    $contentStr="receive a new event:".$object->Event."\n广州大学华软软件学院";
  	    break;
  		}
  		if(is_array($contentStr)){
  			$resultStr=$this->transmitNews($object,$contentStr);}else{$resultStr=$this->transmitText($object,$contentStr);}
  			return $resultStr;
  		}
  		private function transmitText($object,$content,$funcFlag=0){
  			$textTpl="<xml>
  			<ToUserName><![CDATA[%s]]></ToUserName>
  			<FromUserName><![CDATA[%s]]></FromUserName>
  			<CreateTime>%s</Createtime>
  			<MsgType><![CDATA[text]]></MsgType>
  			<Content><![CDATA[%s]]></Content>
  			<FuncFlag>%d</FuncFlag>
  			</xml>";
  			$resultStr=sprintf($textTpl,$object->FromUserName,$object->ToUserName,time(),$content,$funcFlag);
  			return $resultStr;
  		}

  		private function transmitNews($object,$arr_item,$funcFlag=0){
  		if(!is_array($arr_item))
  			return;
  		$itemTpl="<item>
  		<Title><![CDATA[%s]]></Title>
  		<Description><![CDATA[%s]]></Description>
  		<PicUrl><![CDATA[%s]]></PicUrl>
  		<Url><![CDATA[[%s]]></Url>
  		</item>";
  		$item_str="";
  		foreach($arr_item as $item)
          $item_str=sprintf($itemTpl,$item['Title'],$item['Description'],$item['PicUrl'],$item['Url']);
      $newsTpl="<xml>
      <ToUserName><![CDATA[%s]]></ToUserName>
      <FromUserName><![CDATA[%s]]></FromUserName>
      <CreateTime>%s</CreateTime>
      <MsgType><![CDATA[news]]></MsgType>
      <Content><![CDATA[]]></Content>
      <ArticleCount>%s</Articlecount>
      <Articles>$item_str</Articles>
      <FuncFlag>%s</FuncFlag>
      </xml>";
      $resultStr=sprintf($newsTpl,$object->FromUserName,$object->ToUserName,time(),count($arr_item),$funcFlag);
      return $resultStr;
  		}
  	}
?>

