package cn.memo.handle;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.catalina.util.Base64;

public class MyHandle {
	public static String nvl(String v,String w) {
		if(v==null){
			return w;
		}else {
			return v;
		}
	}
	public static String nvl(String v) {
		if(v==null){
			return "";
		}else {
			return v;
		}
	}
	
	public static String getBASE64fromImgPath(String path) {
		String str = null;
		try{
			path = getRootPath() + path;
			FileInputStream fis=new FileInputStream(path);
			ByteArrayOutputStream baos=new ByteArrayOutputStream();
			byte[] buffer=new byte[1024];
			int count=0;
			while((count=fis.read(buffer))>=0){
				baos.write(buffer);
			}
			str=new String(Base64.encode(baos.toByteArray()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}
	
	public static String getRootPath(){
		String result = "";
		System.out.println(result);
		return result;
	}
	
	public static void main(String[] args) {
		String string = MyHandle.getBASE64fromImgPath("/images/topbar.jpg");
		System.out.println(string);
	}
	
	public static String getDate(String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt);//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	public static String getDate() {
		return getDate("yyyyMMddHHmmss");//设置日期格式
	}
	
}
