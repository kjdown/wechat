<?php
if(isset($_GET['code'])){
  echo $_GET['code'];
}else{echo "NO code";}
?>

https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxb07bfdf6d733e264&secret=32bb210a8e6c9e0e2b66634ac3b333e6

https://api.weixin.qq.com/sns/oauth2/access_token?appid=wxb07bfdf6d733e264&secret=32bb210a8e6c9e0e2b66634ac3b333e6&code=001oqlzV0HhSlR1Gx1CV08UnzV0oqlzY&grant_type=authorization_code

https://api.weixin.qq.com/sns/userinfo?access_token=OezXcEiiBSKSxW0eoylIeHzovilvP2BTShgcyAiQHgs3NTRgqDrtt0iRJX0phR_fe_HwLFawQ0zbNL438716gbRIlObZKL3ihY6F9mWD7sRdwta_8rOysvo5Eh3G3UeHxUvIlgSEohVriRCADJYEPw&openid=o39Ltvo38WygGSLehlk4FIEQGcZs&lang=zh_CN