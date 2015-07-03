<%@page import="cn.memo.handle.JSPHandle"%>
<%@page import="cn.memo.handle.PageHandle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
JSPHandle ph = new JSPHandle();
String id = ph.nvl((String)request.getParameter("id"),(String)request.getAttribute("id"));
//String name = ph.nvl(new String(request.getParameter("name").getBytes("UTF-8"),"UTF-8"),new String(  ((String)request.getAttribute("name")).getBytes("UTF-8"),"UTF-8"));
String name = ph.nvl((String)request.getParameter("name"),(String)request.getAttribute("name"));
String pocard = ph.nvl((String)request.getParameter("pocard"),(String)request.getAttribute("pocard"));
String pic = ph.nvl((String)request.getParameter("pic"),(String)request.getAttribute("pic"));
String openid = (String)session.getAttribute("openid");
String subscribe = (String)session.getAttribute("subscribe");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>中美篮球对抗赛篮球宝贝</title>
<meta name=”format-detection” content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta content="email=no" name="format-detection" />
<meta content="telephone=no" name="format-detection" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0" />
<link href="http://res.data.mvote.net/css/bootstrap.css" rel="stylesheet" media="screen">
<link rel="stylesheet" href="/css/font-awesome.min.css" >
<link href="/js/webix/skins/touch.css" rel="stylesheet" media="screen">
<link href="http://res.data.mvote.net/css/style.css?ver=118" rel="stylesheet" media="screen">
<link href="http://res.data.mvote.net/css/wapstyle.css?ver=118" rel="stylesheet" media="screen">

<link href="/css/votestyle.css?ver=118" rel="stylesheet" media="screen">
<link href="/favicon.ico" type="image/x-icon" rel=icon>
<link href="/favicon.ico" type="image/x-icon" rel="shortcut icon">
<script src="http://res.data.mvote.net/js/jquery.js"></script>
<script src="http://res.data.mvote.net/js/config.js?ver=118"></script>
<script src="http://res.data.mvote.net/js/bootstrap.min.js"></script>
<script type="text/javascript" src="http://dup.baidustatic.com/js/zm.js"></script>

<script type="text/javascript" src="../js/zDrag.js"></script>
<script type="text/javascript" src="../js/zDialog.js"></script>

<link href="/css/wapstyle.css?ver=118" rel="stylesheet" media="screen">
<style>
.buttonStyle{
	background:#3498db;
	color:white;
	font-family: "Microsoft YaHei",Arial,sans-serif;
	padding:5px 5px 5px 5px;
}
</style>
<script>
function createXmlHttpRequest(){
    if(window.XMLHttpRequest){
    	return new XMLHttpRequest();
    }else{
    	return new ActiveXObject("Microsoft.XMLHTTP");
        
    }
}
//当用户账号输入框失去焦点时调用该方法
var index;
function toupiao(obj){
    //获取输入框输入的值
    if("<%=subscribe%>" == "1"){
    	
	    var openid = "<%=openid%>";
	    index = obj;
	    var url="../toupiao?openid="+openid+"&id="+obj;
	   
	    //调用方法创建XMLHttpRequest对象
	    XmlHttpRequest = createXmlHttpRequest();
	    //设置回调函数
	    XmlHttpRequest.onreadystatechange=finish;
	    //初始化xmlhttprequest
	    XmlHttpRequest.open("GET",url,true);
	    //发送请求
	   // xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	    XmlHttpRequest.send(null);
    }else{
    	var diag = new Dialog();
    	diag.Width = 400;
    	diag.Height = 120;
    	//diag.Title = "请关注我们";
    	diag.ShowMessageRow=false;
    	diag.URL = "http://memoandfriends.sinaapp.com/wechat/guanzhuDiag.html";
    	diag.ShowButtonRow=true;
    	diag.show();
    	diag.okButton.value="如何添加我们？";
    	diag.okButton.style="color:white";
    	diag.cancelButton.value="取消，继续浏览";
    	diag.okButton.onclick = function(){
    			diag.close();
    			window.location="http://mp.weixin.qq.com/s?__biz=MzAwOTYwMzA4Ng==&mid=207236527&idx=1&sn=72ab7add7ff0d1ad73aa93118e255cf6#rd";  
    	};
    	
    	
    }
}
//回调函数
function finish(){
    if(XmlHttpRequest.readyState == 4&& XmlHttpRequest.status == 200){
        var result = XmlHttpRequest.responseText;
        if(result =="-1"){
            alert("每个微信用户一天只能投一票！");
        }else{
            //alert("投票成功！谢谢支持！");
            var obj  = document.getElementById("pocard_"+index);
        	obj.innerHTML = result;
        	var diag = new Dialog();
    		diag.Width = 400;
    		diag.Height = 110;
    		diag.Title = "投票成功";
    		diag.InvokeElementId="chenggong";
    		diag.ShowButtonRow=true;
    		diag.show();
    		diag.okButton.value="去抽奖";
    		diag.okButton.style="color:white";
    		diag.cancelButton.value="取消，继续浏览";
    		diag.okButton.onclick = function(){
    				diag.close();
    				window.location="https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fchoujiang&response_type=code&scope=snsapi_base&state=1#wechat_redirect";  
    		};
        }
    }
}

function baoming(){
	window.location="baoming.jsp";  
}

</script>
</head>
<body class="weixinstyle">
<div class='topbar'>
<a onclick="javascript:history.back(-1);"target='_top'><i class=" icon-angle-left"></i> 返回</a>
</div>
<div class="wapwrap">


	<div class="container bodycontainer" style='min-height:350px;'>	
		<div class="page-header"><%=name %></div>
		<div class='oppageimg' style='margin-bottom:20px;text-align:center;'>
			<img src='<%=pic %>' />
		</div>
						
		<div class='vopagecontentbody'>
			<p></p>
			
		</div>
		<div class='votenuminfo'>
			已获票数：<span id="pocard_<%=id%>"><%=pocard %></span>
		</div>
		<div class="" style="margin: 20px auto;text-align:center">
			<button class="btn btn-info wxvotebutton " name='669161' onclick="toupiao('<%=id%>')"  style="width:40%;margin-right:10%"><i class='icon-thumbs-up'></i> 投Ta一票</button>
			<button class='btn btn-info wxvotebutton' type='button' onclick="baoming()" style="width:40%">我要报名</button>
		</div>
		<input type='hidden' value="" name="wxparam" id="wxparam"  />
	
	</div>
</div>
<div id="chenggong" style="background:white;padding:10px 20px; display:none" align="left">
	<div class=''>
	<h3>投票成功！</h3>
		<div style="margin-bottom:20px ;">你获得了一次抽奖机会！</div>
		<div style="clear:both"></div>
</div>

</body>
</html>