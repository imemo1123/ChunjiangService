<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
 String stt = (String)request.getAttribute("stt");
 String area = (String)request.getAttribute("area");
 String tprice = (String)request.getAttribute("tprice");
 String num = (String)request.getAttribute("num");
 String json = (String)request.getAttribute("json");
 String result = (String)request.getAttribute("result");
 %>
<!DOCTYPE html> 
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>中美篮球对抗赛在线购票</title>
<link href="../css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<script src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>

<style type="text/css">
html{
	font: 12px/1.14 "Microsoft Yahei",arial;
}
h2{
	margin-top: 0;
	font: 20px "Microsoft Yahei",arial;
	border-bottom: 1px solid  #eaeceb;
}
h3{
	  line-height: 24px;
	  font-size: 12px;
	  font-weight: 400;
	  color: #999;
	  padding-top: 6px;
	  width: 60px;
	  top: 0;
	  display: inline;
}
.important-text{
  color: #2489ce;
  font-weight: bold;
}

.payway{
	display: inline;overflow: hidden;
	padding-left: 0px;
	float: left;
}

.itm{
	float: left;
	margin-right: 5px;
	border: 2px solid #eaeceb;
	display: inline;
	overflow: hidden;
	background-color: #fff;
}

.selected{
	border: 2px solid #396b9e;
}
.det-box{
	float: left;
	width: 100%;
	position: relative;
}
</style>
<script type="text/javascript">
$(function(){
	var jsonstr = '<%=json%>';
	var josn = JSON.parse(jsonstr);
	if(parseInt(josn.agent)<5){  
        alert("您的微信版本低于5.0无法使用微信支付");  
        return;  
    }
	
	if (typeof WeixinJSBridge === "undefined"){
		if (document.addEventListener){
			document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
		}
	}else{
		onBridgeReady();
	}
    
});


function onBridgeReady() {
	var jsonstr = '<%=json%>';
	var josn = JSON.parse(jsonstr);
	WeixinJSBridge.invoke('getBrandWCPayRequest',{
        "appId" : josn.appId,                  //公众号名称，由商户传入  
        "timeStamp":josn.timeStamp,          //时间戳，自 1970 年以来的秒数  
        "nonceStr" : josn.nonceStr,         //随机串  
        "package" : josn.packageValue,      //<span style="font-family:微软雅黑;">商品包信息</span>  
        "signType" : josn.signType,        //微信签名方式:  
        "paySign" : josn.paySign           //微信签名  
        },function(res){
	        if(res.err_msg == "get_brand_wcpay_request:ok" ) {
	            window.location.href="../wechatPaySuc";
	        }else{
	            alert(res.err_msg);
	            alert("支付失败！");
	            window.location.href="http://memoandfriends.sinaapp.com/B2C";     
	            //<span style="font-family:微软雅黑;">当失败后，继续跳转该支付页面让用户可以继续付款，贴别注意不能直接调转jsp，</span><span style="font-size:10.5pt">不然会报</span><span style="font-size:12.0pt"> system:access_denied。</span>  
	        }
        });
}
</script>
</head>
<body>
<div data-role="page" id="page">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">中美篮球对抗赛在线购票</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>你的订单信息</h2></div>
		<table class="det-box">
			<tr class="">
				<td><h3>区域：</h3></td>
				<td><span class="important-text"><%=area %></span></td>
			</tr>
			<tr class="">
				<td><h3>数量：</h3></td>
				<td><span class="important-text"><%=num %>张</span></td>
			</tr>
			<tr class="">
				<td><h3>总价：</h3></td>
				<td><span class="important-text"><%=tprice %>元</span></td>
			</tr>
		</table>
	</div>
</div>
<form name="subform" id="subform" action="../">
	<input type="hidden" name="appid" id="appid" value="">
	<input type="hidden" name="mch_id" id="mch_id" value="">
	<input type="hidden" name="nonce_str" id="nonce_str" value="">
	<input type="hidden" name="prepay_id" id="prepay_id" value="">
	<input type="hidden" name="result_code" id="result_code" value="">
	<input type="hidden" name="sign" id="sign" value="">
</form>
</body>
</html>