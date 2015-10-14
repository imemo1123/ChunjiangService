<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   <%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.HashMap"%>
 <%
 ArrayList list = (ArrayList)request.getAttribute("list");
 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">

<title>篮球宝贝投票排名</title>

<script type="text/javascript" src="../../js/iscroll.js"></script>

<script type="text/javascript">

var myScroll;

function loaded () {
	myScroll = new IScroll('#wrapper', { mouseWheel: true });
}

document.addEventListener('touchmove', function (e) { e.preventDefault(); }, false);

</script>

<style type="text/css">
* {
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
}

html {
	-ms-touch-action: none;
}

body,ul,li {
	padding: 0;
	margin: 0;
	border: 0;
}

body {
	font-size: 12px;
	font-family: ubuntu, helvetica, arial;
	overflow: hidden; /* this is important to prevent the whole page to bounce */
}

#header {
	position: absolute;
	z-index: 2;
	top: 0;
	left: 0;
	width: 100%;
	height: 95px;
	line-height: 45px;
  	background: url(../images/listwrapbg.jpg) no-repeat;
  	background-size: 100%;
	padding-left:  40px;
	padding-top:  10px;
	font-size: 20px;
	font-weight: bold;
}

#footer {
	position: absolute;
	z-index: 2;
	bottom: 0;
	left: 0;
	width: 100%;
	height: 28px;
	background:url(../images/listwrapbgbotm.jpg) no-repeat center bottom;
	 background-size: 100%;
	padding: 0;
}

#wrapper {
	padding-left:  40px;
	position: absolute;
	z-index: 1;
	top: 95px;
	bottom: 28px;
	left: 0;
	width: 100%; background: url(../images/listwrapbgrepx.jpg) repeat-y;
  	background-size: 100%;
	overflow: hidden;
}

#scroller {
	
	position: absolute;
	z-index: 1;
	-webkit-tap-highlight-color: rgba(0,0,0,0);
	width: 100%;
	-webkit-transform: translateZ(0);
	-moz-transform: translateZ(0);
	-ms-transform: translateZ(0);
	-o-transform: translateZ(0);
	transform: translateZ(0);
	-webkit-touch-callout: none;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-webkit-text-size-adjust: none;
	-moz-text-size-adjust: none;
	-ms-text-size-adjust: none;
	-o-text-size-adjust: none;
	text-size-adjust: none;
}

#scroller table {
	width: 100%;
	list-style: none;
	padding: 0;
	margin: 0;
	text-align: left;
  	background-size: 100%;
}

#scroller tr {
	padding: 0 10px;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
}
.tablehead{
	width: 100%;
	text-align: left;
}
.con1{
	text-indent: 9px;
}
.con1text{
	  width: 83px;
  height: 22px;
  line-height: 22px;
  display: inline-block;
  background: #f3ebca;
  color: #000;
  margin-left: 5px;
}
img{ vertical-align:middle;} 
</style>
</head>
<body onload="loaded()">
<div id="header">篮球宝贝票数排名
<table width="100%" class="tablehead">
<col width="38%">
<col width="35%">
<col width="30%">
	<tr><td>排名</td><td>名字</td><td>票数</td></tr>
</table>
</div>

<div id="wrapper">
	<div id="scroller">
		<table>
<col width="140px">
<col width="140px">
<col width="140px">
<%for(int i=0;i<list.size();i++){
		Map m = (HashMap)list.get(i);
		String name = (String)m.get("name");
		String id = (String)m.get("id");
		String pic = (String)m.get("pic");
		String pocard = (String)m.get("pocard");
		if(i==0){
		%>

			<tr><td ><img src="../images/jpfirst.png"><strong style="color: #ff6600">1st</strong></td><td><%=id+"."+name %></td><td><%=pocard %></td></tr>
		<%}else if(i==1){ %>
			<tr><td><img src="../images/jpsecond.png"><strong style="color: #636666">2nd</strong></td><td><%=id+"."+name %></td><td><%=pocard %></td></tr>
		<%}else if(i==2){ %>
			<tr><td><img src="../images/jpthird.png"><strong style="color: #b75601">3rd</strong></td><td><%=id+"."+name %></td><td><%=pocard %></td></tr>
		<%}else{ %>
			<tr><td><%=i+1 %></td><td><%=id+"."+name %></td><td><%=pocard %></td></tr>
		<%}} %>
		</table>
	</div>
</div>

<div id="footer"></div>

</body>
</html>