<%@page import="cn.memo.handle.JSPHandle"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
List<Map<String, String>> orderList = (List<Map<String, String>>)request.getAttribute("orderList");
JSPHandle jh = new JSPHandle();
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
.n-text{
	color: #999;
	font-size: 12px;
}
</style>

<script type="text/javascript">
function gotoDetail(index){
	var params = index.split('|');
	var paramstr = "area="+params[0]+
							"&num="+params[1]+
							"&price="+params[2]+
							"&name="+params[3]+
							"&addr="+params[4]+
							"&tel="+params[5]+
							"&date="+params[6]+
							"&stt="+params[7];
	window.location.href="b2c/orderDetail.jsp?" + paramstr;
}
</script>
</head> 
<body> 
<div data-role="header" data-theme="b">
    	<h1>我的订单</h1>
</div>
<ul data-role="listview">
<%for(int i=0;i<orderList.size();i++){
	Map m = (Map)orderList.get(i);
	String area=(String)m.get("area");
	String num=(String)m.get("num");
	String price=(String)m.get("price");
	String name=(String)m.get("name");
	String addr=(String)m.get("addr");
	String tel=(String)m.get("tel");
	String date=(String)m.get("datetime");
	date = jh.getDateFmt(date, "yyyyMMddhhmmss", "yyyy-MM-dd") ;
	String stt=(String)m.get("stt");
	String param = area+"|"+num+"|"+price+"|"+name+"|"+addr+"|"+tel+"|"+date+"|"+stt;
%>

	<li><a href="javascript:gotoDetail('<%=param%>')"><%=area %>      <%=num %>张<br/><span class="n-text"><%=date%></span></a></li>
	<%} %>
</ul>	

<div data-role="footer" data-theme="c" data-position="fixed" data-id="footernav" >
		<div data-role="navbar" data-grid="a"> <ul> <li><a href="/B2C"  data-ajax="false">买票</a></li> <li><a href="/orderList"  class="ui-btn-active"  data-ajax="false">我的订单</a></li>  </ul> </div>
	</div>

</body>
</html>