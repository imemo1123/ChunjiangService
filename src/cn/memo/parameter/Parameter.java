package cn.memo.parameter;

import com.sina.sae.util.SaeUserInfo;

public class Parameter {
	
//	public static String DB_URL = "jdbc:mysql://127.0.0.1:3306/chuanjiang1";
//	public static String DB_password = "chunjiang1";
//	public static String DB_user =  "root";
//	public static String HOST_URL = "http://10.0.2.2:8080/";
	
	public static String HOST_URL = "http://memoandfriends.sinaapp.com/";
	public static String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_memoandfriends";
	public static String DB_password = SaeUserInfo.getSecretKey();
	public static String DB_user =  SaeUserInfo.getAccessKey();

//	public static String HOST_URL = "http://sjlcqngy.sinaapp.com/"; 
//	public static String DB_URL = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_sjlcqngy";
	
}
