package cn.memo.handle;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import cn.memo.json.JsonHandle;
import cn.memo.net.WeChatHttp;

public class WechatHandle {
	
	private String APPID = "wx7cc0fb85783397ef";
	private String secret = "ad03d99e0aec031f6b695132ef98451c";
	public String getOpenid(String code) throws ServletException, IOException {
		String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+secret+"&code="+code+"&grant_type=authorization_code";
		String rst= WeChatHttp.sendGet(step2url); 
		String openid =  JsonHandle.getValue(rst, "openid");  
		return openid;
	}
	
	public void getCode(String action , HttpServletResponse response) throws IOException{
		response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+APPID+"&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2f"+action+"&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
	}
	
}
