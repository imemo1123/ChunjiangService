 <%@ page contentType="text/html;charset=UTF-8"%>
 <%@ page import="java.util.*;" %>
 <%
// List piclist=new ArrayList();
 String pic = (String)request.getAttribute("pics");
// String pic = basePath + "images/"+(String)piclist.get(0);
 
 
 String name  = (String)request.getAttribute("name");
 String tel  = (String)request.getAttribute("tel");
 String wechat  = (String)request.getAttribute("wechat");
 String rst = (String) request.getAttribute("rst");
 %>


<html>
<meta charset="UTF-8">
<head>
<style>
html{
	font-family: "Microsoft YaHei",Arial,sans-serif;
	font-size: 40px;

}
body{ background: url(images/nbakonline3.jpg) no-repeat;
	background-size:100%;
	
            }
h2{
	border-bottom: 1px solid #b9b9b9;
	font-family: "Microsoft YaHei",Arial,sans-serif;
	font-size: 40px;
	line-height: 40px;
	color: #414141;
	font-weight: normal;
	padding: 0 0 13px 0;
	/**/text-align: left;
}
.myinput{ 
	width: 364px;
	border: 1px solid #dedede;
	color: #000000;
	padding: 5px 10px 7px 17px;
	float: left;
	line-height: 20px;
}
.mytable td{height: 50px;}

.mybutton{
	color: #ffffff;
	font-size: 14px;
	background: #00bcf2;
	/*text-align: center;*/
	padding: 0 36px;
	height: 40px;
	line-height: 40px;
	margin-left: 20px;
	border: none;
	display: block;
	outline: 0;
	-webkit-border-radius: 0!important;
	-moz-border-radius: 0!important;
	border-radius: 0!important;
}

.myform { 
	margin-top: 100px;
	margin-left: 250px;
	width: 600px;
	background: rgba(255, 255, 255, 0.1) ;
	padding: 50px
}

.mytable{
	padding-left:100px
}
</style>
</head>
<body>
<div align="">
	<div  class="myform">
	<%if("0".equals(rst)){ %>
	<div align="">
		<h2>报名成功!</h2>
		<div style="float:left">
			<img width="200" border="0" height="250" id="64642" src="<%=pic %>" title="" alt="">
		</div>
		<table  class="mytable">
			
				<td width="100px">姓名：</td><td width="100px"><span><%=name %></span></td>
			</tr>
			<tr>
				<td>电话：</td><td><span><%=tel %></span></td>
			</tr>
			<tr>
				<td>微信：</td><td><span><%=wechat %></span></td>
			</tr>
		</table>
<br/>
		<div  style="padding-left:350px ">
			<input type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);">

		</div>
	</div>
	<%} %>
	<%if("1".equals(rst)){ %>
			<div align="">
		<h2>报名失败!</h2>
		<input type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);">
		</div>
	<%} %>
	<%if("2".equals(rst)){ %>
		<div align="">
			<h2>报名失败！<br/><br/>该手机已经完成注册，请不要重复报名。</h2>
			<input type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);">
		</div>
	<%} %>
	</div>
</div>
</body>
</html>