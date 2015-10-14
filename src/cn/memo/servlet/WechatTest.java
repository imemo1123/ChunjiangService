package cn.memo.servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;
import cn.memo.handle.B2CHandle;
import cn.memo.handle.MyHandle;
import cn.memo.handle.SeatHandle;
import cn.memo.handle.WechatPayHandle;
import cn.memo.handle.XMLhandle;
import cn.memo.json.JsonHandle;
import cn.memo.net.WeChatHttp;

/**
 * Servlet implementation class WechatTest
 */
public class WechatTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WechatTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		//获取openid
//		String id = request.getParameter("code");
//		if(id == null){
//			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fgetWeiChatInfo&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
//			return;
//		}
//		String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c&code="+id+"&grant_type=authorization_code";
//		String rst= WeChatHttp.sendGet(step2url); 
//		String openid =  JsonHandle.getValue(rst, "openid");  
		String openid = "oDuZis6OLatbU13m9OiZswvbZ6V4";
	        session.setAttribute("openid", openid);
	        //end
	        
	        //微信预支付
	        //String flowno = (String) session.getAttribute("flowno");
	        String flowno = "10000031";
	        String appid = WechatPayHandle.getAppid();
	        String mch_id = WechatPayHandle.getMchid();
	        String nonce_str = WechatPayHandle.getRandomString(32);
	        String body = "%e4%b8%ad%e7%be%8e%e7%af%ae%e7%90%83%e5%af%b9%e6%8a%97%e8%b5%9b%e9%97%a8%e7%a5%a8";
	        String out_trade_no = flowno;
	        String total_fee = B2CHandle.getPrice(flowno);
	        String fmt_fee = (int)(Float.parseFloat(total_fee) * 100) +"";
	        
	        String spbill_create_ip  = MyHandle.getRemortIP(request);
	        String notify_url = "http://124.229.172.255:8080/wxnotify";
	        
	        SortedMap<Object,Object> parameters = new TreeMap<Object,Object>();
	        parameters.put("appid", appid);

	        parameters.put("mch_id", mch_id);
	        parameters.put("nonce_str", nonce_str);
	        parameters.put("body", body);
	        parameters.put("out_trade_no", out_trade_no);
	        parameters.put("total_fee", fmt_fee);
	        parameters.put("spbill_create_ip",spbill_create_ip);
	        parameters.put("notify_url", notify_url);
	        parameters.put("trade_type", "JSAPI");
	        parameters.put("openid", openid);
	        String sign = WechatPayHandle.createSign( parameters);
	        parameters.put("sign", sign);
	        String requestXML = WechatPayHandle.getRequestXml(parameters);
	        System.out.println(requestXML);
	        String requestXML2 = new String(requestXML.getBytes(), "ISO8859-1");
	        System.out.println(requestXML2);
	        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	        String result =WeChatHttp.httpsRequest(url, "POST", requestXML2);
	        System.out.println(result);
	        Map m = XMLhandle.doXMLParse(result);
	        
	        String  return_code = (String) m.get("return_code");
	        System.out.println(return_code);
	        if("SUCCESS".equals(return_code)){
	        	SortedMap<Object,Object> params = new TreeMap<Object,Object>();
	                params.put("appId", appid);
	                params.put("timeStamp", Long.toString(new Date().getTime()));
	                params.put("nonceStr",  WechatPayHandle.getRandomString(32));
	                params.put("package", "prepay_id="+m.get("prepay_id"));
	                params.put("signType", "MD5");
	                String paySign =  WechatPayHandle.createSign(params);
	                params.put("packageValue", "prepay_id="+m.get("prepay_id")); 
	                params.put("paySign", paySign);
	                params.put("sendUrl", "");                               //付款成功后跳转的页面
	                String userAgent = request.getHeader("user-agent");
	                char agent = userAgent.charAt(userAgent.indexOf("MicroMessenger")+15);
	                params.put("agent", new String(new char[]{agent}));//微信版本号，用于前面提到的判断用户手机微信的版本是否是5.0以上版本。
	                String json = JsonHandle.toJson(params).toString();
	                request.setAttribute("json", json);
	                
	                Map<String, String> seatinfo = SeatHandle.getOrderInfo(flowno);
	                request.setAttribute("area", seatinfo.get("area"));
	                request.setAttribute("tprice", seatinfo.get("price"));
	                request.setAttribute("num", seatinfo.get("num"));
	                request.getRequestDispatcher("b2c/wx_waitforpay.jsp").forward(request, response);
	        }else{
	        	 request.getRequestDispatcher("error.jsp").forward(request, response);
	        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
