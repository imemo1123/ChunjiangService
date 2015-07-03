 <%@ page contentType="text/html;charset=UTF-8"%>
 <%@page import="cn.memo.handle.JSPHandle"%>
 <%
 JSPHandle handle = new JSPHandle();
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<meta charset="UTF-8">
<head>
<title>中美篮球对抗赛篮球宝贝报名</title>
<style>
html{
	font-family: "Microsoft YaHei",Arial,sans-serif;
	font-size: 24px;

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
	/*text-align: left;*/
}
.myinput { 
	width: 364px;
	border: 1px solid #dedede;
	color: #000000;
	padding: 5px 10px 7px 17px;
	float: left;
	line-height: 20px;
}
.myselect{ 
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
	text-align: center;
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
	margin-left: 200px;
	width: 600px;
	background: rgba(255, 255, 255, 0.1) ;
	padding: 50px
}

</style>
<script type="text/javascript">

function dosome(){
	
	var area = document.getElementById("areaS").value;
	var tel = document.getElementById("tel").value;
	var name =  document.getElementById("name").value;
	var wechat =  document.getElementById("wechat").value;
	var pic =  document.getElementById("pic").value;
	if(tel.length != 11){
		alert("请输入正确的手机号！");
		return;
	}
	if(name.length <= 0){
		alert("请输入姓名！");
		return;
	}
	if(wechat.length <= 0){
		alert("请输入微信号！");
		return;
	}
	if(pic.length <= 0){
		alert("请上传一张照片！");
		return;
	}
	
	
	document.submitForm.area.value = area;
	document.getElementById("subt").disabled = true;
	document.submitForm.submit();
	
}

function init(){
	document.getElementById("subt").disabled = false;
}

</script>
</head>
<body onload="init()">
<form name="submitForm" id="submitForm" encType="multipart/form-data" method="post" action="baoming">
<div align="">
	<div  class="myform">
	<div align="center">
		<h2>在线报名</h2>
		
		<table  class="mytable">
			<tr>
				<td>姓名：</td><td><input id="name"  name="name" class="myinput" notnull></td>
			</tr>
			<tr>
				<td>电话：</td><td><input id="tel"  name="tel" class="myinput"></td>
			</tr>
			<tr>
				<td>微信：</td><td><input id="wechat" name="wechat" class="myinput"></td>
			</tr>
			<tr>
				<td>赛区：</td>
				<td>
					<%=handle.getAreaSelect("areaS") %>
					<input type='hidden'  id="area" name="area"  value="0"/>
				</td>
			</tr>
			<tr>
				<td>照片：</td><td><input id="pic"  name="pic" type="file" name="uploadFile" onchange="handleFiles(this)"></td>
			</tr>
			<tr id="mp" style="display:none">
				<td></td>
				<td><div id="fileList" style="width:200px;height:200px;"></div></td>
			</tr>
		</table>
		<br/>
		<div  style="margin: 0 auto; ">
			<input id="subt"  type="button" class="mybutton" value="提交" onclick="dosome();">

		</div>
	</div>
	</div>
	<div class="info" style=" margin-left: 350px;float:left;">
		<div style="float:left; display:inline;margin-right: 100px">
		
			<div class="qrcode" >
			<img src="images/20150614153246.png" width="150px" height="150px">
			</div>
			<div style="margin:10 auto;text-align: center">
			<span >微信公众号</span>
			</div>
		</div>
		<div  style="float:left; display:inline;">
		
			<div class="qrcode" style="">
			<img src="images/dasdasdasd.png" width="150px" height="150px">
			</div>
			<div style="margin:10 auto;text-align: center">
			<span>微信报名端</span>
			</div>
		</div>
	</div>
</div>
<script>
	window.URL = window.URL || window.webkitURL;
	var fileElem = document.getElementById("fileElem"),
	    fileList = document.getElementById("fileList");
	function handleFiles(obj) {
		fileList.innerHTML = "";
		var files = obj.files,
			img = new Image();
		if(window.URL){
			//File API
			  //alert(files[0].name + "," + files[0].size + " bytes");
		      img.src = window.URL.createObjectURL(files[0]); //创建一个object URL，并不是你的本地路径
		      img.width = 200;
		      img.height = 200;
		      img.onload = function(e) {
		         window.URL.revokeObjectURL(this.src); //图片加载后，释放object URL
		      }

		      fileList.appendChild(img);
		}else if(window.FileReader){
			//opera不支持createObjectURL/revokeObjectURL方法。我们用FileReader对象来处理
			var reader = new FileReader();
			reader.readAsDataURL(files[0]);
			reader.onload = function(e){
				alert(files[0].name + "," +e.total + " bytes");
				img.src = this.result;
				img.width = 200;
				img.height = 200;
				fileList.appendChild(img);
			}
		}else{
			//ie
			obj.select();
			obj.blur();
			var nfile = document.selection.createRange().text;
			document.selection.empty();
			img.src = nfile;
			img.width = 200;
			img.height = 200;
			img.onload=function(){
		      //alert(nfile+","+img.fileSize + " bytes");
		    }
			fileList.appendChild(img);
			
			//fileList.style.filter="progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src='"+nfile+"')";
		}
		document.getElementById("mp").style.display = "";
	}
</script>
<input type="hidden" name="channel" id="channel" value="pc">
</form>
</body>
</html>