<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.ArrayList"%>
<%@page import="cn.memo.handle.JSPHandle"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    //String rst = (String)request.getAttribute("rst");
    String area = (String)request.getAttribute("area");
    if(area ==null || area.length()<=0)
	    area =  "0";
    String openid=(String)session.getAttribute("openid");
    String subscribe = (String) session.getAttribute("subscribe");
    String vn = (String)request.getAttribute("vn");
    String un = (String)request.getAttribute("un");
    String una = (String)request.getAttribute("una");
    String pn = (String)request.getAttribute("pn");
    ArrayList list = (ArrayList)request.getAttribute("list");
    JSPHandle handle = new JSPHandle();
    ArrayList<String> adList = (ArrayList<String>)request.getAttribute("adList");
    
    String path = request.getContextPath();
    String basePath =  request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/images/";
    //basePath = basePath + "images/";
    //out.print(rst);
    
    %>
<!DOCTYPE html>
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=0, minimum-scale=1.0, maximum-scale=1.0">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<title>中美篮球对抗赛篮球宝贝</title>


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
<link href="http://res.data.mvote.net/css/style.css?ver=118" rel="stylesheet" media="screen">
<link href="http://res.data.mvote.net/css/wapstyle.css?ver=118" rel="stylesheet" media="screen">


<script type="application/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/zDrag.js"></script>
<script type="text/javascript" src="../js/zDialog.js"></script>
<script type="text/javascript" src="../js/wxvote.js"></script>
<link href="../css/mycss.css" rel="stylesheet" media="screen">


<link href="../css/banner.css" rel="stylesheet" type="text/css"/>


<script type="text/javascript">



function createXmlHttpRequest(){
    if(window.XMLHttpRequest){
    	return new XMLHttpRequest();
    }else{
    	return new ActiveXObject("Microsoft.XMLHTTP");
        
    }
}
var index;
function toupiao(obj){
    //获取输入框输入的值
    if("<%=subscribe%>" == "1"){
    	
	    var openid = "<%=openid%>";
	    index = obj;
	    var url="toupiao?openid="+openid+"&id="+obj;
	   
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
	window.location="wechat/baoming.jsp";  
}

var nnum,tnum;
function init(){
	nnum = 10;
	tnum = parseInt("<%=una%>");
	var areaobj = document.getElementById("areaS");
	areaobj.value = "<%=area%>";
	areaobj.onchange = function (){
		//alert(areaobj.value);
		var areavalue = areaobj.value;
		window.location="ChangeArea?&area="+areavalue;
	}
}


function go(){
	document.searchForm.submit();
}
/*
function init(){
	alert(1);
	$.post("../getNews",{} , function(json){
		alert(json);
	},"json");
	
}*/
</script>
<style>
.buttonStyle{
	background:#3498db;
	color:white;
	font-family: "Microsoft YaHei",Arial,sans-serif;
	padding:5px 5px 5px 5px;
}
</style>


<div id='wx_pic' style='margin:0 auto;display:none;'>
	<img src='../images/4afbfbedab64034fff5c9bbbadc379310b551dfd.jpg'/>
</div>

</head>
<body class="weixinstyle" onload="init()">



<div class="wapwrap" >
	
		<div class="navbar navbar-poll wapnavbar">
		    <div class="navbar-inner "  >
			</div>  
		</div>
<div class="" >
                                             
<div class='votetitle'>
<h4 style="height: 1.5em;line-height: 1.5em;">中美篮球对抗赛篮球宝贝 
<div style='text-align:center;float:right;margin-right:20px;'>
<button class='btn btn-info wxvotebutton' type='button' onclick="baoming()">我要报名</button>
</div>
</h4>
<div>
	<span style="font-size:18px">选择赛区：</span>
	<%=handle.getAreaSelect("areaS") %>
</div>
</div>



<div class="main">
		<div class="indexPicBox mb10">
			<div class="hotPic">
				<div class="num">
				<%for(int i=1;i<=adList.size();i++){
					if(i==1){%>
					<span class="cur"  style="height:20px;width:22px;">1</span>
					<%}if(i!=1){%>
					<span><%=i %></span>
					<%}} %>
				</div>
				<ul class="pic">
					<%for(String item:adList){
						String[] strs = item.split("\\|");
						String adpic = strs[0];
						String adurl = strs[1];
					%>
					
					<li style="display:block;">
						<a href="<%=adurl%>"><img alt="" src="<%=adpic %>" /> </a>
					</li>
					<%} %>
					
				</ul>
			</div>

<!--轮播结束-->
		</div>
	</div>
	<div style="clear:both"></div>
<!-- 
<div style="width:100%;display:block">
	<span style="font-size: 12px;">选择赛区：</span>
	<%=handle.getAreaSelect("area") %>
</div>
 -->

<div class='wxvoteinfo'>
	<ul class='inline'>
	<li>参与选手<BR><%=un %></li>
	<li class='left-border'>累计投票<BR><%=pn %></li>
	<li class='left-border'>访问次数<BR><%=vn %></li>
	</ul>
</div>
<div class='blockcell'>
<i class='icon-time'></i> 投票开始时间：2015-06-15 21:50:10
</div>
<div class='blockcell'>
<i class='icon-time'></i> 投票截止时间：2015-07-11 23:55:10</div>
<div class='blockcell'>
<i class='icon-warning-sign'></i> 投票规则：每个微信每天只能投1票。
</div>
<div class='blockcell'>
<i class='icon-warning-sign'></i> 奖项设置：<br/>
1.冠亚季分别获得5000,3000,2000元现金奖励和不低于8000,5000,3000元奖品；<br/>
2.决赛获胜人员分别获得3000元奖品；<br/>
3.入围决赛成员均可获得1000元奖品；<br/>
4.以上获奖人员均可获得组委会颁发的荣誉证书。
</div>

<div align="center">
<form name="searchForm" method="post" action="../searchForDetail">
<input id="val" name="val"  type = "text" class="searchInput" style="width:60%" placeholder="输入选手编号或者姓名搜索">
<input  class="btn btn-info wxvotebutton" type="button"  onclick="go()" value="搜索"  style="width:100px;vertical-align:middle;margin-top: 6px;margin-bottom: 10px;"/>
</form>
</div>

	<div class='voteoplist' >
		<div id="plistleft" class='plistleft leftoptions ' >
		<%
		for(int i=0;i<list.size();i++){
			Map m = (HashMap)list.get(i);
			String name = (String)m.get("name");
			String id = (String)m.get("id");
			String pic = (String)m.get("pic");
			String pocard = (String)m.get("pocard");
			String classString = "";
			if(i%2==0){
		%>
			<div class='wxop' >
					<div class='wxopimg '>
							<a href="wechat/showDetail.jsp?id=<%=id%>&pic=<%=pic %>&name=<%=name%>&pocard=<%=pocard%>&openid=<%=openid%>&subscribe=<%=subscribe%>">
								<img src="<%=pic %>"  name="1"  />
							</a>
					</div>
					<div class='wxoptxt'>
							<a href="wechat/showDetail.jsp?id=<%=id%>&pic=<%=pic%>&name=<%=name%>&pocard=<%=pocard%>&openid=<%=openid%>&subscribe=<%=subscribe%>">
								<%=id+"."+name %>
							</a>
					</div>
					<div class='wxopvotediv'>
						<div class='wxvbtn'>
							<button class='btn btn-info wxvotebutton'  name='669161'  onclick="toupiao(<%=id%>)"><i class='icon-thumbs-up'></i> 投票</button>
						</div>
						<div class='wxvinfo' id='wxinfo_669161'>
							<span id="pocard_<%=id%>"><%=pocard %></span>票
						</div>	
				</div>
			</div>
		<%} }%>
		</div>
	<div  id="plistright" class='plistleft plistright rightoptions ' >
	<%for(int i=0;i<list.size();i++){
		Map m = (HashMap)list.get(i);
		String name = (String)m.get("name");
		String id = (String)m.get("id");
		String pic = (String)m.get("pic");
		String pocard = (String)m.get("pocard");
		String classString = "";
		if(i%2==1){
			
		%>
			<div class='wxop' >
				<div class='wxopimg '>
					<a href="wechat/showDetail.jsp?id=<%=id%>&pic=<%=pic %>&name=<%=name%>&pocard=<%=pocard%>&openid=<%=openid%>&subscribe=<%=subscribe%>">
						<img src="<%=pic %>"  name="1"  />
					</a>
				</div>
				<div class='wxoptxt'>
					<a href="wechat/showDetail.jsp?id=<%=id%>&pic=<%=pic%>&name=<%=name%>&pocard=<%=pocard%>&openid=<%=openid%>&subscribe=<%=subscribe%>">
						<%=id+"."+name %>
					</a>
				</div>
				<div class='wxopvotediv'>
					<div class='wxvbtn'>
						<button class='btn btn-info wxvotebutton'  name='669161'  onclick="toupiao(<%=id%>)"><i class='icon-thumbs-up'></i> 投票</button>
					</div>
					<div class='wxvinfo' id='wxinfo_669161'>
							<span id="pocard_<%=id%>"><%=pocard %></span>票
					</div>	
				</div>
			</div>	
	<%} }%>
					
	</div>
	</div>
</div>
</div>
<div style="clear:both"></div>

<div id='loadingpagealert' style="width:100%;display:none">
<img src='/images/loading.gif'> 正在加载更多选手请稍后...
</div>

<input type='hidden' value="" name="wxparam" id="wxparam" />

<div id="chenggong" style="background:white;padding:10px 20px; display:none" align="left">
	<div class=''>
	<h3>投票成功！</h3>
		<div style="margin-bottom:20px ;">你获得了一次抽奖机会！</div>
		<div style="clear:both"></div>
</div>


<script type="text/javascript">
//焦点图
$(function(){
	var sw = 0;
	myShow(sw);
	$(".hotPic .num span").mouseover(function(){
		sw = $(".hotPic .num span").index(this);
		myShow(sw);
});
	function myShow(i){
		$(".hotPic .num span").eq(i).addClass("cur").siblings("span").removeClass("cur");
		$(".hotPic .pic li").eq(i).stop(true,true).fadeIn(600).siblings("li").fadeOut(600);
		//$(".hotPic .text p").eq(i).show().siblings("p").hide();
	}
	//滑入停止动画，滑出开始动画
	$(".hotPic").hover(function(){
		if(myTime){
		   clearInterval(myTime);
		}
	},function(){
		myTime = setInterval(function(){
		  myShow(sw);
		  sw++;
		  if(sw==$(".hotPic .pic li").length){sw=0;}
		} , 1500);
	});
	//自动开始
	var myTime = setInterval(function(){
	   myShow(sw);
	   sw++;
	   if(sw==$(".hotPic .pic li").length){sw=0;}
	} , 1500);
});

var loadpagedone = false;
var loadingpage = false;
window.onscroll=function(){
	if(nnum < tnum){
		if (getScrollTop()+getClientHeight()==getScrollHeight()){
				//alert(3);
				var area = document.getElementById("areaS").value;
				getMore(nnum,area);
		}
	}
	else
	{
		$("#loadingpagealert").hide();
	}
}

function getMore(_index,_area){
	if(loadpagedone)
		return false;
	if(loadingpage)
		return false;
	loadingpage = true;

	$("#loadingpagealert").show();
	$.post("../getBabyList",{area:_area,index:_index} , function(json){
		//alert(json);
		$(".loadingpagealert").hide();
		nnum += json.length;
		for(var i=0;i<json.length;i++){
			if(i%2 == 0){
				var optdiv = getWxOptionBox(json[i]);
        		$("#plistleft").append(optdiv);
			}else{
				var optdiv = getWxOptionBox(json[i]);
        		$("#plistright").append(optdiv);
			}
		}
		loadingpage = false;
		$("#loadingpagealert").hide();
	},"json");
}

function getWxOptionBox (json){
	var _url =  "wechat/showDetail.jsp?id="+json.id+"&pic="+json.pic+"&name="+json.name+"&pocard="+json.pocard;
	var _div = "<div class='wxop '>";
		_div += "<div class='wxopimg '>";
			_div += "<a href='"+_url+"'>";
			_div += "<img src='"+json.pic+"'  /></a>";
		_div += "</div>";
		
		_div += "<div class='wxoptxt'>";
			_div += "<a href='"+_url+"'>"+json.id+"."+json.name+"</a>";
		_div += "</div>";
		_div += "<div class='wxopvotediv'><div class='wxvbtn'>";
		_div += "<button class='btn btn-info wxvotebutton'  onclick='toupiao("+json.id+")'><i class='icon-thumbs-up'></i> "+"投票"+"</button></div>";
		_div += "<div class='wxvinfo'>";
		_div += "<span id='pocard_"+json.id+"'>"+json.pocard+"</span>"+"票";
		_div += "</div>";
		_div += "</div></div>";
		return _div;
}
	</script>

</div>
</body>
</html>