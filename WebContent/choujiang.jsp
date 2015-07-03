<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 String rqurl = (String)request.getAttribute("rqurl");
String jieguo = (String)request.getAttribute("jieguo");
String rst;
String code="";
if(jieguo == null){
	jieguo = "0";
}
String rsts[] = jieguo.split("\\|");
String jiangping = "", pic="",isdui="ture",intro="请留下您的手机号码，我们的工作人员会联系你发奖";
rst = rsts[0];
int flag = 0;
if(rst.equals("-1")){
	flag = 1;
	rst = rsts[1];
	if(Integer.parseInt(rst)>0){
		jiangping = rsts[3];
		pic = rsts[2];
		code = rsts[4];
		isdui = rsts[5];
	}else{
		pic = "images/p_1.jpg";
	}
}
else if(Integer.parseInt(rst)>0){
	jiangping = rsts[2];
	pic = rsts[1];
	intro = rsts[3];
	code = rsts[4];
}else{
	pic = "images/p_1.jpg";
}
%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>永州实尚汇抽奖</title>
<meta name=”format-detection” content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta content="email=no" name="format-detection" />
<meta content="telephone=no" name="format-detection" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no,minimum-scale=1.0,maximum-scale=1.0" />
<link rel="stylesheet" href="/css/font-awesome.min.css" >
<link href="/js/webix/skins/touch.css" rel="stylesheet" media="screen">
<link href="http://res.data.mvote.net/css/style.css?ver=118" rel="stylesheet" media="screen">
<link href="http://res.data.mvote.net/css/wapstyle.css?ver=118" rel="stylesheet" media="screen">

<link href="/css/votestyle.css?ver=118" rel="stylesheet" media="screen">


<script type="text/javascript" src="js/zDrag.js"></script>
<script type="text/javascript" src="js/zDialog.js"></script>


<link href="../css/banner.css" rel="stylesheet" type="text/css"/>
<style type="text/css">
.demo{width:320px; margin:10px auto 20px auto; min-height:208px; background: url(images/gua.jpg) no-repeat; background-size:100%;}
.msg{text-align:center; height:32px; line-height:32px; font-weight:bold; margin-top:50px}
.shade{
    width: 150px;
    height: 40px;
    margin-left: 120px;
    padding-top: 110px;
}
body{
	font-family: "Microsoft YaHei",Arial,sans-serif;
	overflow-x: hidden;  
    overflow-y: hidden;  
}
h3{
margin-buttom:10px;
font-size:20px;
}


</style>
<link href="../css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<script>
function init(){
	if("<%=flag%>" == "1")
	{
		//alert("每个微信用户一天只能抽一次奖，请明天再来");
		var rst = "<%=rst%>";
		var rstn = parseInt(rst);
		if(rstn > 0){
			if("<%=isdui%>"=="false"){
				document.getElementById("dbtdiv").style.display = "";
			}
		}
	}
	else if("<%=rst%>"== "-2")
	{
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 120;
		//diag.Title = "请关注我们";
		diag.ShowMessageRow=false;
		diag.URL = "http://localhost:8080/wechat/guanzhuDiag.html";
		diag.ShowButtonRow=true;
		diag.show();
		diag.okButton.value="如何添加我们？";
		diag.okButton.style="color:white";
		diag.cancelButton.value="取消";
		diag.okButton.onclick = function(){
				diag.close();
				window.location="http://mp.weixin.qq.com/s?__biz=MzAwOTYwMzA4Ng==&mid=207236527&idx=1&sn=72ab7add7ff0d1ad73aa93118e255cf6#rd";  
		};
		diag.cancelButton.value = function(){
			diag.close();
			window.location="http://mp.weixin.qq.com/s?__biz=MzAwOTYwMzA4Ng==&mid=207236527&idx=1&sn=72ab7add7ff0d1ad73aa93118e255cf6#rd";  
		}
	}
}

function duijiang(){
	var rst = "<%=rst%>";
	var rstn = parseInt(rst);
	if(rstn > 0){
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 250;
		diag.Title = "恭喜中奖";
		//diag.URL = "http://localhost:8080/wechat/duijiangDiag.jsp?jiangping=<%=jiangping%>&code=<%=code%>&id=<%=rst%>";
		  diag.InvokeElementId="guanzhu";
		//diag.OKEvent = function(){ret();diag.close();};
		diag.show();
	}
}

function finish(){
	 if(XmlHttpRequest.readyState == 4&& XmlHttpRequest.status == 200){
	        var result = XmlHttpRequest.responseText;
	        if(result =="-1"){
	            alert("兑奖失败，请重试！");
	        }else{
	        	alert("兑奖成功！");
	        	ret();
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
	    var id = "<%=rst%>";
	    var code = "<%=code%>";
	    var tel = document.getElementById("tel").value;
	    if(tel.length != 11){
			alert("请输入正确的手机号！");
			return;
		}
	    var url="../duijiang?code="+code+"&id="+id+"&tel="+tel;
	    XmlHttpRequest = createXmlHttpRequest();
	    XmlHttpRequest.onreadystatechange=finish;
	    XmlHttpRequest.open("GET",url,true);
	    XmlHttpRequest.send(null);
}

function ret(){
	window.location="<%=rqurl%>";  
}
</script>


<div id='wx_pic' style='margin:0 auto;display:none;'>
	<img src='../images/4afbfbedab64034fff5c9bbbadc379310b551dfd.jpg'/>
</div>

</head>

<body class="weixinstyle" onload="init()">

<body onload="init()">

<h3 style="height: 1.5em;line-height: 1.5em;">中美篮球对抗赛每日抽奖 </h3>

<div class='blockcell'>
<i class='icon-warning-sign'></i>投票规则：必须关注“永州时尚汇”的用户才能抽奖。每个微信每天只能抽一次。
</div>
<div id="main">
   <div class="demo">
		<div class="shade"><canvas width="150" height='40'></canvas></div>
		<div id="dbtdiv"  style="width: 100px;line-height: 15px;margin-left: 200px;margin-top: 15px;display:none" ><input type="button"  id="dbt"  onclick="duijiang();" value="去兑奖"></div>
   </div>
</div>


<div class='blockcell'>
<i class='icon-warning-sign'></i> 今日奖品：<br/>
1、中美篮球对抗赛门票一张（每日抽出1张）<br/>
兑奖热线：18974630914；<br/>
2、大木源漂流门票一张（每日抽出5张）<br/>
兑奖热线：18974630914；<br/>
3、加勒比水上乐园门票一张（每日抽出5张）<br/>
兑奖方式：上门报手机号码即可领取；<br/>
兑奖地址：冷水滩区翠竹路潇湘公园南大门（八中对面）<br/>
4、舜德羽毛球馆20元优惠券（每日抽出20张）<br/>
兑奖方式：上门报手机号码即可领取<br/>
兑奖地址：冷水滩区舜德摩尔2号楼4楼<br/>
订场电话：0746-8128333；<br/>
5、韩国爱丽自然妆免费体验券（每日抽出20张）<br/>
兑奖方式：上门报手机号码即可领取；<br/>
兑奖地址：永州市冷水滩区百业街576号<br/>
客服电话：0746-8336760；<br/>
6、卡卡造型洗剪吹体验券（每日抽出20张）<br/>
兑奖方式：上门报手机号码即可领取<br/>
兑奖地址：永州市冷水滩区新步步高二楼（肯德基楼上）。<br/>



</div>

<div id="guanzhu" style="background:white;padding:10px 20px; display:none" align="left">
	<div class=''>
	<h3>你中的是<%=jiangping %><!-- ，兑奖SN码：<%=code %> --></h3>

		<div style="margin-bottom:20px ;"><%=intro %></div>
<div style="clear:both"></div>
		手机：<input  id="tel" name="tel"  value="" >
	<input id="subt"  type="button" value="提交"  onclick="dosome();" >
	</div>
</div>


<script type="text/javascript">
var bodyStyle = document.body.style;

bodyStyle.mozUserSelect = 'none';
bodyStyle.webkitUserSelect = 'none';

var img = new Image();
var canvas = document.querySelector('canvas');
canvas.style.backgroundColor='transparent';
canvas.style.position = 'absolute';
img.src = "<%=pic%>";

img.addEventListener('load', function(e) {
	var ctx;
    var w = 150,
    	h = 40;
    var offsetX = canvas.offsetLeft,
    	offsetY = canvas.offsetTop;
    var mousedown = false;

    function layer(ctx) {
        ctx.fillStyle = 'gray';
        ctx.fillRect(0, 0, w, h);
    }

    function eventDown(e){
        e.preventDefault();
        mousedown=true;
    }

    function eventUp(e){
    	var rst = "<%=rst%>";
    	var rstn = parseInt(rst);
    	if(rstn > 0){
	    	 if("<%=flag%>" == "0"){
	    		 document.getElementById("dbtdiv").style.display = "";
	    	 }
    	}
        e.preventDefault();
        mousedown=false;
    }

    function eventMove(e){
        e.preventDefault();
        if(mousedown) {
             if(e.changedTouches){
                 e=e.changedTouches[e.changedTouches.length-1];
             }
             var x = (e.clientX + document.body.scrollLeft || e.pageX) - offsetX || 0,
                 y = (e.clientY + document.body.scrollTop || e.pageY) - offsetY || 0;
             with(ctx) {
                 beginPath()
                 arc(x, y, 10, 0, Math.PI * 2);
                 fill();
             }
        }
    }

    canvas.width=w;
    canvas.height=h;
    canvas.style.backgroundImage='url('+img.src+')';
    ctx=canvas.getContext('2d');
    ctx.fillStyle='transparent';
    
    if("<%=flag%>" == "0"){
    	ctx.fillRect(0, 0, w, h);
    	layer(ctx);
    }
    	

    ctx.globalCompositeOperation = 'destination-out';

    canvas.addEventListener('touchstart', eventDown);
    canvas.addEventListener('touchend', eventUp);
    canvas.addEventListener('touchmove', eventMove);
    canvas.addEventListener('mousedown', eventDown);
    canvas.addEventListener('mouseup', eventUp);
    canvas.addEventListener('mousemove', eventMove);
});
</script>

</body>
</html>