package cn.memo.handle;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

public class WechatPayHandle {
	private static String appid = "wx7cc0fb85783397ef";
	private static String mch_id = "1248342901";
	private static String key ="43110319901014161343110319930324";
	
	public static String getRandomString(int length){
	      int rpoint = 0;    
	      String rules = "qwertyuioplkjhgfdsazxcvbnm1234567890";
	      StringBuffer generateRandStr = new StringBuffer();    
	      Random rand = new Random();    
	      //int length = 32;
	      for(int i=0;i<length;i++)
	      {    
	            if(rules!=null){    
	                rpoint = rules.length();    
	                int randNum = rand.nextInt(rpoint);    
	                generateRandStr.append(rules.substring(randNum,randNum+1));    
	            }
	      }
	      return generateRandStr+"";    
	}
	
	public static String getAppid() {
		return appid;
	}
	public static String getMchid() {
		return mch_id;
	}
	
	public static String sign(String signsrc) {
		
		try {
			MessageDigest md5  = MessageDigest.getInstance("MD5");
			char[] charArray = signsrc.toCharArray();
			//byte[] data = System.Text.Encoding.UTF8.GetBytes(pwd);
			byte[] byteArray = new byte[charArray.length];
			 for (int i = 0; i < charArray.length; i++)   
				   byteArray[i] = (byte) charArray[i];
			 byte[] md5Bytes = md5.digest(byteArray);
			 StringBuffer hexValue = new StringBuffer();
			 for (int i = 0; i < md5Bytes.length; i++) {   
				 int val = ((int) md5Bytes[i]) & 0xff;   
				 if (val < 16)   
					 hexValue.append("0");   
				hexValue.append(Integer.toHexString(val));   
			}
			 return hexValue.toString().toUpperCase();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String createSign(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
	        Set es = parameters.entrySet();
	        Iterator it = es.iterator();
	        while(it.hasNext()) {
	            Map.Entry entry = (Map.Entry)it.next();
	            String k = (String)entry.getKey();
	            Object v = entry.getValue();
	            if(null != v && !"".equals(v) 
	                    && !"sign".equals(k) && !"key".equals(k)) {
	                sb.append(k + "=" + v + "&");
	            }
	        }
	        sb.append("key=" + key);
	        System.out.println(sb.toString());
	        String sign = sign(sb.toString());
	        System.out.println(sign);
	        return sign;
	}

	public static String getRequestXml(SortedMap<Object, Object> parameters) {
		StringBuffer sb = new StringBuffer();
		sb.append("<xml>");
		Set es = parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if ("attach".equalsIgnoreCase(k)||"body".equalsIgnoreCase(k)||"sign".equalsIgnoreCase(k)) {
				sb.append("<"+k+">"+"<![CDATA["+v+"]]></"+k+">");
			}else {
				sb.append("<"+k+">"+v+"</"+k+">");
			}
		}
		sb.append("</xml>");
		return sb.toString();
//		try {
//			return new String(sb.toString().getBytes(), "UTF-8");
//			//return new String(sb.toString().getBytes(), "ISO8859-1");
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return null;
//		}
	}
	
	
	public static void main(String[] args) {
		String src = "appid=wxd930ea5d5a258f4f&auth_code=123456&body=test&device_info=123&mch_id=1900000109&nonce_str=960f228109051b9969f76c82bde183ac&out_trade_no=1400755861&spbill_create_ip=127.0.0.1&total_fee=1&key=8934e7d15453e97507ef794cf7b0519d";
		String sign = sign(src);
		System.out.println(sign);
	}

}
