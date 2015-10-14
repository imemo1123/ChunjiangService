 <%@ page contentType="text/html;charset=UTF-8"%>
  <%@page import="cn.memo.handle.JSPHandle"%>
 <%
 JSPHandle handle = new JSPHandle();
 %>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<head>
<title>蒙面花王选拔赛</title>
<link href="../css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="../css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="../js/jquery.js" type="text/javascript"></script>
<script src="../js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style>
.yahei{
	font-family: "Microsoft YaHei",Arial,sans-serif;
	font-size: 20px;

}
	.mybody{ background: url(../images/background1.jpg) no-repeat;
	background-size:100%;
	width: 100%;height: 100%;
            }
            .mydiv{background: rgba(255,255,255,0.6);padding:30px;}
            .myinput {background: rgba(255,255,255,0.6)}
</style>

<script type="text/javascript">

function dosome(){
	//var area = document.getElementById("areas").value;
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
	//document.submitForm.area.value = area;
	document.getElementById("subt").disabled = true;
	document.submitForm.submit();
}

function init(){
	document.getElementById("subt").disabled = false;
}
</script>
</head>
<body onload="init()">

<div data-role="page" id="page">
<div class="mybody">
	<div data-role="header" data-theme="c">
    	
		<h1><span class="yahei">蒙面花王在线报名</span></h1>
	</div>
	<div style="margin: 20px 20px 20px 20px;" class="mydiv">
         <form name="submitForm" id="submitForm" encType="multipart/form-data" method="post" action="../baoming">
          	<span>姓名：</span><input id="name"  name="name"  value="" type="text" class="myinput">
          	<span>手机：</span><input  id="tel" name="tel"  value="" type="text" >
          	<span>微信：</span><input id="wechat"  name="wechat"  value="" type="text" >
          	<span>照片：</span><input id="pic"  name="pic"  type="file" name="uploadFile" onchange="handleFiles(this)"/>
          	<input type="hidden" name="channel" id="channel" value="mp">
          	<input type='hidden'  id="area" name="area"  value="0"/>
          	<div id="fileList" style=""></div>
          	<br/>
            <input id="subt" type="button" value="提交"  onclick="dosome();" >
            
          </form>
	</div>
	<div style="margin:  10px auto;margin-top: 20px;width:150px">
		<div style=" display:inline;">
		
			<div class="qrcode" >
			<img src="../images/620689287517826921.jpg" width="150px" height="150px">
			</div>
			<div style="margin:10 auto;text-align: center">
			<span class="yahei">花坊姑娘</span>
			</div>
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
</body>
</html>