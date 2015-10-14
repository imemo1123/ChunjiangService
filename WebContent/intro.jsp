
<!DOCTYPE html> 
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<title>中美篮球对抗赛篮球宝贝邀请函</title>
<link href="css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style type="text/css">
html{
	font: 12px/1.14 "Microsoft Yahei",arial;
}
h2{
	margin-top: 0;
	font: 20px "Microsoft Yahei",arial;
	border-bottom: 1px solid  #eaeceb;
}
h3{
	color: #fff;
	 margin-top: 0;
	 margin-bottom: 5px;
	font: 20px "Microsoft Yahei",arial;
}
.important-text{
  color: #2489ce;
  font-weight: bold;
}

.payway{
	display: inline;overflow: hidden;
	padding-left: 0px;
	float: left;
}

.itm{
	float: left;
	margin-right: 5px;
	border: 2px solid #eaeceb;
	display: inline;
	overflow: hidden;
	background-color: #fff;
}

.selected{
	border: 2px solid #396b9e;
}
.det-box{
	float: left;
	width: 100%;
	position: relative;
}
</style>
<script type="text/javascript">
function buypage(){
		var diag = new Dialog();
		diag.Width = 400;
		diag.Height = 250;
		diag.Title = "恭喜中奖";
		diag.InvokeElementId="guanzhu";
		//diag.OKEvent = function(){ret();diag.close();};
		diag.show();
}
function dosome(){
	
}
</script>
</head>
<body>
<div data-role="page" id="page">
	<div data-role="header" data-theme="b">
		<h1   style="margin:0 auto;margin-top:.6em;margin-bottom:.8em">篮球宝贝活动邀请函</h1>
	</div>
	<div data-role="content">
		<div class="pic-box"><h2>“加勒比水上乐园”活动</h2></div>
		<div class="pic-box">活动时间：2015年7月30日18:30-21:00</div>
		<div class="pic-box">活动地点：</div>
		<div class="pic-box">备注：可带亲友家属</div>
		<div style="background: #497bae;padding:10px;width:80%;margin:20px auto">
			<div class="pic-box" align=""><h3>是否参与活动：</h3></div>
			<label>
        			<input type="radio" name="radio-choice-0" id="radio-choice-0a">是
    		</label>
    		<label for="radio-choice-0b">否</label>
    		<input type="radio" name="radio-choice-0" id="radio-choice-0b" class="custom">
    		<div class="pic-box" align=""><h3>亲友人数：</h3></div>
    		<input type="tel" name="radio-choice-0" id="radio-choice-2a"  value='0'>

		</div>
		</div>

		<div class="item-box" data-inline="divue" align="center">
			<a onclick="buypage()"  data-theme="b" data-role="button" data-inline="true">确定</a> 
		</div>
	</div>
</div>


<div id="guanzhu" style="background:white;padding:10px 20px; display:none" align="left">
	<div class=''>
	<h3>请输入的你参赛编号：</h3>
		<input  id="id" name="name"  value="" >
		
	<input id="subt"  type="button" value="提交"  onclick="dosome();" >
	</div>
</div>
</body>
</html>