<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>中美篮球对抗赛现场抽奖</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<style>
html,body{margin:0;padding:0;overflow:hidden;}
body{background:url(images/body_bg.jpg) 0px 0px repeat-x #000;}
.main_bg{background:url(images/main_bg.jpg) top center no-repeat;height:1000px;}
.main{width:1000px;height:1000px;position:relative;margin:0 auto;}
.num_mask{background:url(images/num_mask.png) 0px 0px no-repeat;height:184px;width:740px;position:absolute;left:50%;top:340px;margin-left:-370px;z-index:9;}
.num_box{height:450px;width:750px;position:absolute;left:50%;top:340px;margin-left:-370px;z-index:8;overflow:hidden;text-align:center;}
.num{background:url(images/num.png) top center repeat-y;width:181px;height:265px;float:left;margin-right:6px;}
.btn{background:url(images/btn_start.png) 0px 0px no-repeat;width:264px;height:89px;position:absolute;left:50%;bottom:50px;margin-left:-132px;cursor:pointer;clear:both;}
.link{width:264px;height:89px;position:absolute;left:50%;bottom:40px;margin-left:-132px;cursor:pointer;clear:both;text-align:center}
</style>
</head>
<body>
<div class="main_bg">
  <div class="main">
    <div id="res" style="text-align:center;color:#fff;padding-top:180px;font-family:Microsoft YaHei;font-size: 60px; ">中美篮球对抗赛现场抽奖</div>
    <div class="num_mask"></div>
    <div class="num_box">
      <div class="num"></div>
      <div class="num"></div>
      <div class="num"></div>
      <div class="num"></div>
      <div class="btn"></div>
      
    </div>
    <div class="link"><a href="#" style="color:white">显示中奖名单</a></div>
  </div>
</div>
<script>
var nums = new Array(6005,6233,8080,8461,6223,8685,6879,6225,6221,7904);

var index = 0;
function numRand() {
	var x = 9607; //上限
	var y = 6001; //下限
	var rand = parseInt(Math.random() * (x - y + 1) + y);
	return rand;
}
var isBegin = false;
$(function(){
	var u = 265;
	$('.btn').click(function(){
		if(isBegin) return false;
		isBegin = true;
		$(".num").css('backgroundPositionY',0);
		/*
		if(index==10){
			var result = 1123;
		}else{
			var result = numRand();
		}
		*/
		var result=nums[index%nums.length];
		index++;
		$.post("/saveChoujiang",{num:result} , function(json){
			alert(1);
		},"json");
		var num_arr = (result+'').split('');
		$(".num").each(function(index){
			var _num = $(this);
			setTimeout(function(){
				_num.animate({ 
					backgroundPositionY: (u*60) - (u*num_arr[index])
				},{
					duration: 3000+index*1000,
					easing: "easeInOutCirc",
					complete: function(){
						if(index==3) isBegin = false;
					}
				});
			}, index * 300);
		});
	});	
	$(document).keydown(function(e){ 
	    if(!e) var e = window.event;  
	    if(e.keyCode==32){ 
	        if(isBegin) return false;
			isBegin = true;
			$(".num").css('backgroundPositionY',0);
			var result = numRand();
			index++;
			$.post("/saveChoujiang",{num:result} , function(json){
				alert(1);
			},"json");
			var num_arr = (result+'').split('');
			$(".num").each(function(index){
				var _num = $(this);
				setTimeout(function(){
					_num.animate({ 
						backgroundPositionY: (u*60) - (u*num_arr[index])
					},{
						duration: 3000+index*1000,
						easing: "easeInOutCirc",
						complete: function(){
							if(index==3) isBegin = false;
						}
					});
				}, index * 300);
			});
	    } 
	 });
});
</script>

</body>
</html>