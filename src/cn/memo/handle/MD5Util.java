package cn.memo.handle;

import java.security.MessageDigest;

public class MD5Util {

	    private static String byteArrayToHexString(byte b[]) {
	        StringBuffer resultSb = new StringBuffer();
	        for (int i = 0; i < b.length; i++)
	            resultSb.append(byteToHexString(b[i]));

	        return resultSb.toString();
	    }

	    private static String byteToHexString(byte b) {
	        int n = b;
	        if (n < 0)
	            n += 256;
	        int d1 = n / 16;
	        int d2 = n % 16;
	        return hexDigits[d1] + hexDigits[d2];
	    }

	    public static String MD5Encode(String origin, String charsetname) {
	        String resultString = null;
	        try {
	            resultString = new String(origin);
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            if (charsetname == null || "".equals(charsetname))
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes()));
	            else
	                resultString = byteArrayToHexString(md.digest(resultString
	                        .getBytes(charsetname)));
	        } catch (Exception exception) {
	        }
	        return resultString;
	    }
	    
	    private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
	        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	    public static void main(String[] args) {
		    String src = "appid=wxd930ea5d5a258f4f&auth_code=123456&body=test&device_info=123&mch_id=1900000109&nonce_str=960f228109051b9969f76c82bde183ac&out_trade_no=1400755861&spbill_create_ip=127.0.0.1&total_fee=1&key=8934e7d15453e97507ef794cf7b0519d";
			String sign = MD5Encode(src,"UTF-8");
			System.out.println(sign);
	}
}
