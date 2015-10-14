<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%
 String area = (String)request.getParameter("area");
 String num = (String)request.getParameter("num");
 String price = (String)request.getParameter("price");
 String name = (String)request.getParameter("name");
 String tel = (String)request.getParameter("tel");
 String addr = (String)request.getParameter("addr");
 String date = (String)request.getParameter("date");
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
	color: #fff;
	 margin-top: 0;
	 margin-bottom: 5px;
	font: 20px "Microsoft Yahei",arial;
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
.l-td{
	text-align: right;
	width: 50%;
}
</style>

</head>
<body>
<div data-role="page" id="page">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">我的订单</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>订单信息</h2></div>
		<div style="background: #497bae;padding:10px;width:80%;margin:20px auto">
		<table width="100%">
			<tr><td class="l-td"><h3>区域：</h3></td><td><h3><%=area %></h3></td></tr>
			<tr><td class="l-td"><h3>数量：</h3></td><td><h3><%=num %></h3></td></tr>
			<tr><td class="l-td"><h3>金额：</h3></td><td><h3><%=price %></h3></td></tr>
			<tr><td class="l-td"><h3>姓名：</h3></td><td><h3><%=name %></h3></td></tr>
			<tr><td class="l-td"><h3>地址：</h3></td><td><h3><%=addr %></h3></td></tr>
			<tr><td class="l-td"><h3>购票手机：</h3></td><td><h3><%=tel %></h3></td></tr>
			<tr><td class="l-td"><h3>购票时间：</h3></td><td><h3><%=date %></h3></td></tr>
		</table>
		</div>
		<div class="item-box" data-inline="divue" align="center">
			<a onclick="history.go(-1)"  data-theme="b" data-role="button" data-inline="true">返回</a> 
		</div>
	</div>
</div>
</body>
</html>