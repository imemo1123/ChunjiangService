<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String num = String.valueOf(request.getAttribute("num"));
%>
<!DOCTYPE html> 
<html>
<head>
<meta charset="utf-8">
<title>篮球宝贝抽签</title>
<link href="css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style type="text/css">
.n-text{
	color: #999;
	font-size: 12px;
}
h2{
		text-align: center;
	font-size:160px;
	color: white;
}
</style>
</head> 
<body> 
		<div style="background: #497bae;padding:10px;width:80%;margin:20px auto">
			<h2><%=num %></h2>
		</div>
</body>
</html>