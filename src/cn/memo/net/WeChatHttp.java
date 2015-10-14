package cn.memo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.security.SecureRandom;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletException;

import java.io.*;

public class WeChatHttp {
	public static String sendGet(String url) throws ServletException, IOException{
		
		
		HttpURLConnection conn = null;
		try {
		    // Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] {
				new X509TrustManager(){
					public java.security.cert.X509Certificate[] getAcceptedIssuers()
					{
						return null;
					}

					public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
					{
					
					}

					public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
					{
						
					}
				}
			};
			
		    // Install the all-trusting trust manager
		 
		    SSLContext sc = SSLContext.getInstance("TLS");
		    sc.init(null, trustAllCerts, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		 
		    URL getUrl = new URL(url); 
		    conn = (HttpURLConnection) getUrl.openConnection();
		    conn.connect();
		  //  System.out.println(conn.getResponseCode() + " " + conn.getResponseMessage());
		    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream())); 
			String lines; 
			String rst="";
		               while ((lines = reader.readLine()) != null) { 
		                       rst+=lines; 
		               } 
		               reader.close(); 
		               // �Ͽ����� \
		               conn.disconnect(); 
		               System.out.println(rst);
		               return rst;
		} catch (Exception e) {
		    e.printStackTrace();
		}
		return "";
//		URL getUrl = new URL(url); 
//		HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection(); 
//		connection.connect();
//		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream())); 
//		String lines; 
//		String rst="";
//	               while ((lines = reader.readLine()) != null) { 
//	                       rst+=lines; 
//	               } 
//	               reader.close(); 
//	               // �Ͽ����� \
//	               connection.disconnect(); 
//	               return rst;
	}
	
	public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {
		try {
			// ����SSLContext���󣬲�ʹ������ָ�������ι�������ʼ��
	        	TrustManager[] tm = new TrustManager[] {
	        					new X509TrustManager(){
	        						public java.security.cert.X509Certificate[] getAcceptedIssuers()
	        						{
	        							return null;
	        						}

	        						public void checkClientTrusted(java.security.cert.X509Certificate[] certs, String authType)
	        						{
	        						
	        						}

	        						public void checkServerTrusted(java.security.cert.X509Certificate[] certs, String authType)
	        						{
	        							
	        						}
	        					}
	        	};
	        	SSLContext sc = SSLContext.getInstance("TLS");
	        	sc.init(null, tm, new SecureRandom());
	        	HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	        	URL url = new URL(requestUrl);
	        	HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
	        	conn.setDoOutput(true);
	        	conn.setDoInput(true);
	        	conn.setUseCaches(false);
	        	// ��������ʽ��GET/POST��
	        	conn.setRequestMethod(requestMethod);
	        	conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");
	        	// ��outputStr��Ϊnullʱ�������д����
	        	if (null != outputStr) {
	        	    OutputStream outputStream = conn.getOutputStream();
	        	    // ע������ʽ
	        	    outputStream.write(outputStr.getBytes("UTF-8"));
	        	    outputStream.close();
	        	}
	        	// ����������ȡ��������
	        	InputStream inputStream = conn.getInputStream();
	        	InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
	        	BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
	        	String str = null;
	        	StringBuffer buffer = new StringBuffer();
	        	while ((str = bufferedReader.readLine()) != null) {
	        		buffer.append(str);
	        	}
	        	// �ͷ���Դ
	        	bufferedReader.close();
	        	inputStreamReader.close();
	        	inputStream.close();
	        	inputStream = null;
	        	conn.disconnect();
	        	return buffer.toString();
		} catch (ConnectException ce) {
			ce.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
