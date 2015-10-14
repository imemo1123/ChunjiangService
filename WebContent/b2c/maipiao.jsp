<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String[] prices = (String[])request.getAttribute("prices");
String areas = (String)request.getAttribute("areas");
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>中美篮球对抗赛在线购票</title>
<link href="<%=request.getContextPath()%>/css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath()%>/css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="<%=request.getContextPath()%>/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style type="text/css">
html{
	font: 12px/1.14 "Microsoft Yahei",arial;
}
.pic-box{
	width: 100%;
	  
  	margin-right:  20px ;

}
.det-box{
	  float: left;
  width: 100%;
  position: relative;
}

.item-box{
	  zoom: 1;
	  display: block;
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

.itm{
	  float: left;
  margin: 0 0 5px 6px;
  border: 2px solid #eaeceb;
  display: inline;
  overflow: hidden;
  background-color: #fff;

}

.selected{
	border: 2px solid #396b9e;
	
}

.itm a{
	  display: block;
  padding: 8px 18px 8px 18px;
  text-align: center;
  border: 1px solid #fff;
  font: 12px/1.14 "Microsoft Yahei",arial;
  text-decoration:none;
   color: #2489ce /*{c-body-link-color}*/;
  font-weight: bold;

}
.sel-box{
display: inline;overflow: hidden;
	padding-left: 0px;
	float: left;
}
.m-nums{
	  position: relative;
	  z-index: 2;
	  float: left;
	  border: 1px solid #eaeceb;
	  zoom: 1;
}
.btn{
	float: left;
	width: 28px;
  	height: 28px;
  	line-height: 28px;
  	text-align: center;
  	overflow: hidden;
  	background-color: #fafafa;
  	border: 2px solid #eaeceb;
  	text-decoration:none;
  	font: 20px "Microsoft Yahei",arial;
}
.ipt-num{
	border: 1px solid #eaeceb;
	float: left;
	width: 30px;
	line-height: 26px;
	  text-align: center;
}
.important-text{
  color: #2489ce /*{c-body-link-color}*/;
  font-weight: bold;
}
</style>
<script type="text/javascript">
var price = "<%=prices[0]%>";
var pnum,tprice,area;
function addone(){
	var n = $("#num").val();
	n++;
	$("#num").val(n);
	refreshPrice();
}
function minone(){
	var n = $("#num").val();
	if(n != 1){
		$("#num").val(--n);
	}
	refreshPrice();
}


function chooseArea(obj,value){
	area = value;
	$("#areabox li").removeClass("selected");
	$(obj).parent().addClass("selected");
	refreshPrice();
}

function refreshArea(areas){
	$("#areabox").empty();
	for(var i=0;i<areas.length;i++){
		var optli = ""
		if(i==0){
			optli+= "<li class='itm selected'>"
			area = areas[i].id;
		}else{
			optli+= "<li class='itm'>"
		}
		optli +="<a onclick='chooseArea(this,\""+areas[i].id+"\")'>"+areas[i].area+"</a></li>"
		$("#areabox").append(optli);
	}
}

function refreshPrice(){
	pnum = $("#num").val();
	tprice = price * pnum;
	$("#tprice").html(tprice);
}

function buypage(){
	document.subform.area.value = area;
	document.subform.num.value = pnum;
	document.subform.submit();
}

$(function(){
	refreshPrice();
	var areas = '<%=areas%>';
	
	refreshArea(JSON.parse(areas));
	$("#pricebox li a").click(function(){
		$("#pricebox li").removeClass("selected");
		$(this).parent().addClass("selected");
		price = $(this).html();
		$.post("../getSeatsArea",{price:price} , function(json){
			refreshArea(json);
		},"json");
		refreshPrice();
	});
});

function iFrameHeight() { 
	var ifm= document.getElementById("iframepage"); 
	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument; 
	if(ifm != null && subWeb != null) { 
	ifm.height = subWeb.body.scrollHeight; 
	} 
	} 
</script>
</head> 
<body> 

	<div data-role="header" data-theme="b">
		<h1  style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">中美篮球对抗赛在线购票</h1>
	</div>

	<!-- <iframe  id="mframe" src="../test.html" width="100%" height="100%"></iframe> -->
	<div data-role="content">
		<!----> <div class="pic-box"><img src="../images/toppic.jpg"  width="100%"></div> 
		<table class="det-box">
			<tr class="">
				<td><h3>比赛时间：</h3></td>
				<td><span>2015年8月12日</span></td>
			</tr>
			<tr class="">
				<td><h3>比赛地点：</h3></td>
				<td><span>永州市冷水滩区体育中心</span></td>
			</tr>
			<tr class="">
				<td><h3 style="float: left" width="30">选择票价：</h3></td>
				<td><ul class="sel-box" width="60" id="pricebox">
				<%for(int i=0;i<prices.length;i++){
						if(i==0){
				%>
					<li class="itm selected"><a><%=prices[i] %></a></li>
				<%}else{ %>
					<li class="itm"><a ><%=prices[i] %></a></li>
				<%}} %>
				</ul></td>
			</tr>
			<tr class="">
				<td><h3 style="float: left" width="30">选择区域：</h3></td>
				<td><ul class="sel-box" width="60" id="areabox">
				</ul></td>
			</tr>
			<tr class="">
				<td><h3 style="float: left">数量：</h3></td>
				<td><span class="m-nums">
					<a class="btn btn-low" href="javascript:minone();">-</a>
					<input id="num" name="num" class="ipt-num"  type="text"  data-role='none' value="1">
					<a class="btn btn-add" href="javascript:addone();">+</a>
				</span></td>
			</tr>
			<tr class="">
				<td><h3>总价：</h3></td>
				<td><span id="tprice" class="important-text">100</span></td>
			</tr>
		</table>
		<div class="item-box" data-inline="divue" align="center">
			<a onclick="buypage()"  data-theme="b" data-role="button" data-inline="true">立刻购买</a> 
		</div>
	 </div>

	 <form name="subform" id="subform" action="../buyConfirm">
	 	<input type="hidden" name="area" id="area">
	 	<input type="hidden" name="num" id="num">
	 </form>

	  <iframe src="b2c/a.html" width="100%" scrolling="no" frameborder="0"   height="550px"></iframe>
	  <div data-role="footer" data-theme="c" data-position="fixed" data-id="footernav" >
		<div data-role="navbar" data-grid="a"> <ul> <li><a href="/B2C" class="ui-btn-active"  data-ajax="false">买票</a></li> <li><a href="/orderList" data-ajax="false">我的订单</a></li>  </ul> </div>
	</div>


</body>
</html>