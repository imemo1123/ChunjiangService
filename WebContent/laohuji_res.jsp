<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中美篮球对抗赛现场抽奖中奖名单</title>
<script type="text/javascript" src="js/jquery-1.7.2-min.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<style>
html,body{margin:0;padding:0;overflow:hidden;}
body{background:url(images/body_bg.jpg) 0px 0px repeat-x #000;}
.main{width:1000px;height:1000px;position:relative;margin:0 auto;}

.main_bg{background:url(images/main_bg2.jpg) top center no-repeat;height:1000px;}
.num_mask{background:url(images/num_mask.png) 0px 0px no-repeat;height:184px;width:740px;position:absolute;left:50%;top:340px;margin-left:-370px;z-index:9;}
.num_box{height:450px;width:750px;position:absolute;left:50%;top:340px;margin-left:-370px;z-index:8;overflow:hidden;text-align:center;}
.num{background:url(images/num.png) top center repeat-y;width:181px;height:265px;float:left;margin-right:6px;}
.btn{background:url(images/btn_start2.png) 0px 0px no-repeat;width:264px;height:89px;position:absolute;left:50%;bottom:50px;margin-left:-132px;cursor:pointer;clear:both;}
ul{
	list-style: none;
}
li{
	color: rgb(227,189,6);
	font-size: 40px;
	font-family:Microsoft YaHei;
}

.fui li{
display:inline;	
color: rgb(255,200,200);
}
</style>
</head>
<body>
<div class="main_bg">
  <div class="main">
    <div id="res" style="text-align:center;color:#fff;padding-top:180px;font-family:Microsoft YaHei;font-size: 60px; ">中美篮球对抗赛现场抽奖中奖名单</div>
    <ul class="mainul">
    <li>特等奖</li>
    <li>
    	<ul class="fui">
    		<li>1123</li>
    	</ul>
    </li>
    <li>一等奖</li>
    <li>
    	<ul class="fui">
    		<li>1123</li>
    	</ul>
    </li>
    <li>二等奖</li>
    <li>
    	<ul class="fui">
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    	</ul>
    </li>
    <li>三等奖</li>
    <li>
    	<ul class="fui">
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    		<li>1123</li>
    	</ul>
    </li>
    </ul>
      <div class="btn" onclick="javascript:history.goto(-1);"></div>
    </div>
  </div>
</div>
</body>
</html>
</body>
</html>