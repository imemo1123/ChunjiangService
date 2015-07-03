<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    
 <%
 String jiangping = (String)request.getParameter("jiangping");
 String code = (String)request.getParameter("code");
 String id = (String)request.getParameter("id");
 
 %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title></title>
<link href="../css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style type="text/css">
body{
	color:white;
	font-family: "Microsoft YaHei",Arial,sans-serif;
	overflow-x: hidden;  
    overflow-y: hidden;  
}
h3{
	text-align: center;
}
a{
	color:white;
	font-family: "Microsoft YaHei",Arial,sans-serif;
	margin-right: 10px;
	margin-left: 10px;
	
}
.helpbtns{
}
</style>
<script type="text/javascript">
function close()
{
	parentDialog.close();
}

function finish(){
	 if(XmlHttpRequest.readyState == 4&& XmlHttpRequest.status == 200){
	        var result = XmlHttpRequest.responseText;
	        if(result =="-1"){
	            alert("兑奖失败，请重试！");
	        }else{
	        	alert("兑奖成功！");
	        	close();
	        	 window.parent.dialogArguments.ret();
	        }
	    }
}
function createXmlHttpRequest(){
    if(window.XMLHttpRequest){
    	return new XMLHttpRequest();
    }else{
    	return new ActiveXObject("Microsoft.XMLHTTP");
    }
}
function dosome(obj){
	    var id = "<%=id%>";
	    var code = "<%=code%>";
	    var tel = document.getElementById("tel").value;
	    var url="../duijiang?code="+code+"&id="+id+"&tel="+tel;
	    XmlHttpRequest = createXmlHttpRequest();
	    XmlHttpRequest.onreadystatechange=finish;
	    XmlHttpRequest.open("GET",url,true);
	    XmlHttpRequest.send(null);
}
    

</script>
</head>
<body>
	<div class='followalert followalertnew dofollowalert' id="guanzhu" style="padding:10px">
	<div class='falertbox'>
	<h3>你中的是<%=jiangping %>，兑奖SN码：<%=code %></h3>

		<div style="margin-bottom:20px;">请留下您的手机号码，我们的工作人员会联系你发奖</div>

		<span>手机：</span><input  id="tel" name="tel"  value="" type="text" >
	<input id="subt"  type="button" value="提交"  onclick="dosome();" >
	</div>
</div>
</body>
</html>