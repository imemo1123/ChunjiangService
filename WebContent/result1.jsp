 <%@ page contentType="text/html;charset=UTF-8"%>
  <%

  String pic = (String)request.getAttribute("pics");
 String name  = (String)request.getAttribute("name");
 String tel  = (String)request.getAttribute("tel");
 String wechat  = (String)request.getAttribute("wechat");
 String rst = (String) request.getAttribute("rst");
 /*
 String pic = "";
 String name="aa";
 String wechat ="33";
 String tel="22";
 String rst="0";
 */
 %>
<html>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<head>
<link href="css/jquery.mobile.structure-1.3.2.css" rel="stylesheet" type="text/css"/>
<link href="css/jquery.mobile-1.3.2.css" rel="stylesheet" type="text/css"/>
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery.mobile-1.3.2.min.js" type="text/javascript"></script>
<style>
.yahei{
	font-family: "Microsoft YaHei",Arial,sans-serif;
	font-size: 20px;

}
	.mybody{ 
		background: url(../images/nbakonline2.jpg) no-repeat;
		background-size:100%;
		width: 100%;
		height: 100%;
		min-height:800px;
     }
      .mydiv{
      	background: rgba(255,255,255,0.6);
      	padding:30px;
      	margin: 20px 20px 20px 20px;
      	margin-top:20%;
     }
</style>

<script type="text/javascript">

function dosome(){
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
	document.submitForm.submit();
}

function go(){
	document.gotoForm.submit();
}
</script>
</head>
<body onload="init()">

<div data-role="page" id="page" class="mybody">
<div class="" >
	<%if("0".equals(rst)){ %>
	<div class="mydiv" align="center">
		<h2>报名成功!</h2>
		
		<table  class="mytable">
			
				<td>姓名：</td><td ><span><%=name %></span></td>
			</tr>
			<tr>
				<td>电话：</td><td><span><%=tel %></span></td>
			</tr>
			<tr>
				<td>微信：</td><td><span><%=wechat %></span></td>
			</tr>
		</table>
<br/>
		<div>
		<form name="gotoForm" id="gotoForm" action="getWeiChatInfo"  method="post" >
		<div data-inline="true" >
			<input data-inline="true" type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);" >
			<input data-inline="true" type="button" class="mybutton" value="去投票" onclick="go()">
			</div>
		</form>
		</div>
	</div>
	<%} %>
	<%if("1".equals(rst)){ %>
			<div class="mydiv" align="center">
		<h2>报名失败!</h2>
		<input type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);">
		</div>
	<%} %>
	<%if("2".equals(rst)){ %>
		<div class="mydiv" align="center">
			<h2>报名失败！<br/><br/>该手机已经完成注册，请不要重复报名。</h2>
			<input type="button" class="mybutton" value="返回" onclick="javascript :history.back(-1);">
		</div>
	<%} %>
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