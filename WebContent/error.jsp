<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
 <%
 String return_msg = (String)request.getAttribute("err_msg");
 String spbill_create_ip = (String)request.getAttribute("err_code");
 String openid = (String)session.getAttribute("openid");
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
</script>
</head>
<body>

<div data-role="page1" id="page1">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">中美篮球对抗赛在线购票</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>系统错误，请重试！</h2></div>
		<div class="pic-box"><h2><%=return_msg %></h2><h2><%=spbill_create_ip %></h2></div>
	</div>
	<div class="item-box" data-inline="divue" align="center">
			<a href="http://memoandfriends.sinaapp.com/B2C"  data-theme="b" data-role="button" data-inline="true">返回</a> 
	</div>
</div>
</body>
</html>