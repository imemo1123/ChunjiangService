package cn.memo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import cn.memo.handle.MyHandle;
import cn.memo.handle.PageHandle;
import cn.memo.handle.getListHandle;
import cn.memo.json.JsonHandle;
import cn.memo.net.HttpRequest;
import cn.memo.net.WeChatHttp;

/**
 * Servlet implementation class GetWeiChatInfo
 */
public class GetWeiChatInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetWeiChatInfo() {
        super();
        // TODO Auto-generated constructor stub
    }


/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");   //…Ë÷√±‡¬Î
		HttpSession session = request.getSession();
		String subscribe  = (String) session.getAttribute("subscribe");
		String openid = (String) session.getAttribute("openid");
		String area = MyHandle.nvl(request.getParameter("area") , "0");
		if( (openid == null || openid.length()<=0 || subscribe == null || !subscribe.equals("1"))){
			String id = request.getParameter("code");
			if(id == null){
				//request.getRequestDispatcher("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fgetWeiChatInfo&response_type=code&scope=snsapi_userinfo&state=STATE&uin=MTUzOTAzMTA2MQ%3D%3D&key=7a1543cb0be31e315dbdfb1584a26e73c7a12d9f8acb7ecc25f5822985b2784a6033f58de861cb6f9e7dd5be43cf9a8f&version=26020036&pass_ticket=RVFbtWXbbCqxvyAG1cxxFrA4ZqDy8rHI1ix4sfmIXMQ4t3%2FHLsmoJjs0ZUC9pxXU").forward(request, response);
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fgetWeiChatInfo&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
				return;
			}
			//System.out.println(id);
			//request.setAttribute("code", id);
			//String step2url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c";
			String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c&code="+id+"&grant_type=authorization_code";
			//String step1url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+code+"&lang=zh_CN";
			//String s=HttpRequest.sendGet(step2url, "");
			String rst= WeChatHttp.sendGet(step2url); 
	
		        //String access_token =  JsonHandle.getValue(rst, "access_token");
		        openid =  JsonHandle.getValue(rst, "openid");  
		        request.setAttribute("openid", openid);
		        
		        String step3url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c";
		        String rst3= WeChatHttp.sendGet(step3url); 
		        String access_token =  JsonHandle.getValue(rst3, "access_token");
	
		        String step4url = "https://api.weixin.qq.com/cgi-bin/user/info?access_token="+access_token+"&openid="+openid;
		        String rst4= WeChatHttp.sendGet(step4url); 
		        
		        subscribe =  JsonHandle.getValue(rst4, "subscribe"); 
		        request.setAttribute("rst4", rst4);
		        session.setAttribute("subscribe", subscribe);
		        session.setAttribute("openid", openid);
		}else{
		
		}
		
		request.setAttribute("area", area);
	        int vn = PageHandle.visit();
		request.setAttribute("vn", vn+"");
		int un = getListHandle.getUserNum();
		int una = getListHandle.getUserNum(area);
		request.setAttribute("un", un+"");
		request.setAttribute("una", una+"");
		int pn = getListHandle.getVontNum();
		request.setAttribute("pn", pn+"");
		ArrayList list = getListHandle.getListWithArea(area);
		session.setAttribute("slist", list);
		ArrayList sList = getListHandle.getTen(list, 0, 10);
		request.setAttribute("list", sList);
		ArrayList<String> adList = PageHandle.getAdList(area);
		request.setAttribute("adList", adList);
	        
		request.getRequestDispatcher("/wechat/showList1.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	
}
