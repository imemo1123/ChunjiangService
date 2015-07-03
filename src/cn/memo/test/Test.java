package cn.memo.test;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.json.simple.JSONObject;

import cn.memo.json.JsonHandle;
import cn.memo.net.HttpRequest;

import com.sina.sae.util.SaeUserInfo;


public class Test {
	public static void main(String[] args) throws Exception {
//		String url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_memoandfriends";
//		String username=SaeUserInfo.getAccessKey();
//		String password=SaeUserInfo.getSecretKey();
//		String driver="com.mysql.jdbc.Driver";
//		Class.forName(driver).newInstance();
//		Connection con=DriverManager.getConnection(url,username,password);
		
//		
//		String ssString = "%E6%88%91";
//		try {
//			ssString = URLDecoder.decode(ssString, "UTF-8");
//			//System.out.println(ssString);
//		} catch (Exception e) {
//			// TODO: handle exception
//		}
//		String s=HttpRequest.sendGet("http://localhost:8080/getNews", "");
//	        System.out.println("s="+s);
//	        //JsonHandle jh = new JsonHandle();
//	        List list = JsonHandle.getList(s);
//	        Map map = JsonHandle.getMap((String)list.get(0));
//	        String title = (String) map.get("title");
//	        System.out.println(title);
//		String s = "http://up.zgchm.com/UF/201505/yzsqnet.com/Kindeditor/-1/201505292306291293.jpg|#";
//		String strs[] = s.split("\\|");
//		String adpic = strs[0];
//		System.out.println(adpic);
//		
//		 File f =new File("TileTest.java");
//		      String fileName=f.getName();
//		      String prefix=fileName.substring(fileName.lastIndexOf("."));
//		      System.out.println(prefix);
		float p = 0.002f;
		for(int i=1;;i++){
			if(Math.random()<=p){
				System.out.println(i);
				break;
			}
		}
	}
}
