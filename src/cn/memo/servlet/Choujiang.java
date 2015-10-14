package cn.memo.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.memo.handle.ChoujiangHandle;
import cn.memo.handle.MyHandle;
import cn.memo.json.JsonHandle;
import cn.memo.net.WeChatHttp;

/**
 * Servlet implementation class Choujiang
 */
public class Choujiang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Choujiang() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String rqurl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fchoujiang&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
		request.setCharacterEncoding("UTF-8");   //…Ë÷√±‡¬Î
		request.setAttribute("rqurl", rqurl);
		HttpSession session=request.getSession();
		String subscribe  = (String) session.getAttribute("subscribe");
		String openid = (String) session.getAttribute("openid");
		String area = MyHandle.nvl(request.getParameter("area"),"1");
		request.setAttribute("area", area);
		String intros = ChoujiangHandle.getChoujiangIntro(area);
		request.setAttribute("intros", intros);
		
		if(openid == null || subscribe==null || !subscribe.equals("1")){
			String id = request.getParameter("code");
			if(id == null){
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fchoujiang&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
				return;
			}
			String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c&code="+id+"&grant_type=authorization_code";
			String rst= WeChatHttp.sendGet(step2url); 
			openid =  JsonHandle.getValue(rst, "openid");  
			session.setAttribute("openid", openid);
		        String step3url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c";
		        String rst3= WeChatHttp.sendGet(step3url); 
		        String access_token =  JsonHandle.getValue(rst3, "access_token");
		        String step4url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid;
		        String rst4= WeChatHttp.sendGet(step4url); 
		        subscribe =  JsonHandle.getValue(rst4, "subscribe"); 
		        request.setAttribute("rst4", rst4);
		        session.setAttribute("subscribe", subscribe);
		        
		}
		if("1".equals(subscribe)){
	        	String jieguo  = ChoujiangHandle.choujiang(openid,area);
	        	System.out.println(jieguo);
	        	request.setAttribute("jieguo", jieguo);
	        }else{
	        	request.setAttribute("jieguo", "-2");
	        }
		request.getRequestDispatcher("/choujiang.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
