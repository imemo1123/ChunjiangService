<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<html>
<head>
<title>using commons Upload to upload file </title>
<script>
	function submitData(){
		var index = document.getElementById("index1").value;
		var content = document.getElementById("content1").value;
		document.form1.index.value=index;
		document.form1.content.value=content;
		document.form1.submit();
	}
</script>
</head>
<style>
* { font-family: "宋体"; font-size: 14px }
</style>
<body>
<p align="center"> 请您选择需要上传的文件</p>
<form id="form1" name="form1" method="post" action="<%=request.getContextPath()%>/FileUpload" enctype="multipart/form-data">
 <table border="0" align="center">
  <tr>
   <td>序号：</td>
   <td>
    <select  id="index1">
    	<option value="0">0</option>
    	<option value="1">1</option>
    	<option value="2">2</option>
    	<option value="3">3</option>
    </select>
    </td>
  </tr>   
   <tr>
   <td>标题：</td>
   <td>
    <input type="text" name="title" id="title">
    </td>
  </tr>   
  <tr>
   <td>上传文件：</td>
   <td><input name="file" type="file" size="20" ></td>
  </tr>    
  <tr>
   <td>内容：</td>
   <td><textarea name="content1" id="content1"></textarea></td>
  </tr>    
  <tr>   
   <td></td><td>
   <input type="hidden"  name="index"  id="index"/>
   <input type="hidden"  name="content"  id="content"/>
    <input type="button"  value="提交"  onclick="submitData()">
    <input type="reset" name="reset" value="重置" >
   </td>
  </tr>
 </table>
</form>
</body>
</html>