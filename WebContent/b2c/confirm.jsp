<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
 String stt = (String)request.getAttribute("stt");
 String id = (String)request.getAttribute("id");
 String area = (String)request.getAttribute("area");
 String tprice = (String)request.getAttribute("tprice");
 String num = (String)request.getAttribute("num");
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
	var stt = "<%=stt%>";
	if(stt == "0"){
		$.mobile.changePage ('#page');
	}else{
		$.mobile.changePage ('#page1');
	}
});

var payway = "wechat";
$(function(){
	$(".payway li").click(function(){
		$(".payway li").removeClass("selected");
		$(this).addClass("selected");
	});
});

function buypage(){
	if($("#name").val() ==null || $("#name").val().length<=0){
		alert("请输入姓名");
		return ;
	}
	if($("#tel").val().length != 11){
		alert("请输入正确的手机号！");
		return;
	}
	if(payway =="wechat"){
		document.subform.action="../wechatPay";
		document.subform.name.value= $("#name").val();
		document.subform.tel.value= $("#tel").val();
		document.subform.addr.value= $("#addr").val();
		document.subform.submit();
	}
}

function changePayway(val){
	payway = val;
}
</script>
</head>
<body>
<div data-role="page" id="page">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">中美篮球对抗赛在线购票</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>请完善购买信息</h2></div>
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
			<tr class="">
				<td><h3>姓名：</h3></td>
				<td><input id="name" name="name"></td>
			</tr>
			<tr class="">
				<td><h3>手机：</h3></td>
				<td><input id="tel" name="tel" type="tel"></td>
			</tr>
			<tr class="">
				<td><h3>地址：</h3></td>
				<td><input id="addr" name="addr"></td>
			</tr>
			<tr class="">
				<td><h3>支付方式：</h3></td>
				<td >
					<ul class="payway">
						<li class="itm selected"><a onclick="changePayway('wechat')"><img src="../images/wechat-logo.png" width="100px"></a></li>
						<!-- <li class="itm"><a onclick="changePayway('alipay')"><img src="../images/alipay-logo.jpg" width="100px"></a></li> -->
					</ul>
				</td>
			</tr>
		</table>
		<div class="item-box" data-inline="divue" align="center">
			<a onclick="buypage()"  data-theme="b" data-role="button" data-inline="true">去支付</a>  
			<a onclick="javascript:history.go(-1)"  data-theme="b" data-role="button" data-inline="true">返回</a> 
		</div>
	</div>
</div>
<div data-role="page1" id="page1">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">中美篮球对抗赛在线购票</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>该区域余票不足，请重新选择！</h2></div>
	</div>
	<div class="item-box" data-inline="divue" align="center">
			<a onclick="javascript:history.go(-1)"  data-theme="b" data-role="button" data-inline="true">返回</a> 
	</div>
</div>
<form name="subform" id="subform" action="../wechatPay">
	<input type="hidden" name="id" id="id" value="<%=id %>">
	<input type="hidden" name="area" id="area" value="<%=area %>">
	<input type="hidden" name="num" id="num" value="<%=num %>">
	<input type="hidden" name="tprice" id="area" value="<%=tprice %>">
	<input type="hidden" name="name" id="name" value="">
	<input type="hidden" name="tel" id="tel" value="">
	<input type="hidden" name="addr" id="addr" value="">
</form>
</body>
</html>