package cn.memo.test;

import java.util.SortedMap;
import java.util.TreeMap;

import cn.memo.handle.WechatPayHandle;
import cn.memo.net.WeChatHttp;

public class WxTest {
	public  void setNotify(){
		SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	        parameters.put("return_code", "SUCCESS");
	        parameters.put("result_code", "SUCCESS");
	        parameters.put("out_trade_no", "10000037");
	        String requestXML = WechatPayHandle.getRequestXml(parameters);
	        String url = "http://memoandfriends.sinaapp.com/wxnotify";
	        String result =WeChatHttp.httpsRequest(url, "POST", requestXML);
	        System.out.println(result);
	}
	
	public static void main(String[] args) {
		new WxTest().setNotify();
	}
}
