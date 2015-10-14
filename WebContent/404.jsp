<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    String rst = (String)request.getAttribute("rst4");
    String openid=(String)session.getAttribute("openid");
    String subscribe=(String)session.getAttribute("subscribe");
    String nickname=(String)session.getAttribute("nickname");
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>404页面已关闭！!</title>
</head>
<body>
<p><%=rst %></p>
<br/>
<p>id=<%=openid %></p>
<p>subscribe=<%=subscribe %></p>
<p>nickname=<%=nickname %></p>
</body>
</html>