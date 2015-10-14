package cn.memo.handle;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.util.Base64;

import cn.memo.sql.SQLConnection;

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
	
	
	
	public static String getDate(String fmt) {
		SimpleDateFormat df = new SimpleDateFormat(fmt);//设置日期格式
		return df.format(new Date());// new Date()为获取当前系统时间
	}
	public static String getDate() {
		return getDate("yyyyMMddHHmmss");//设置日期格式
	}
	
	
	
	
	public static String getSeq(){
		SQLConnection connection = new SQLConnection();
		String sqlString = "update params set valu   =  last_insert_id(valu+1) where name='order_seq';";
		connection.executeUpdate(sqlString);
		String seq = connection.getOneValue("select valu from params  where name='order_seq';", "valu");
		return seq;
	}
	
	public static String getRemortIP(HttpServletRequest request) {
		if (request.getHeader("x-forwarded-for") == null) { 
			return request.getRemoteAddr(); 
		} 
		return request.getHeader("x-forwarded-for"); 
	} 
	
	
	
	public static void main(String[] args) {
	}
	
	
}
