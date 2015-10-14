package cn.memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.memo.handle.B2CHandle;
import cn.memo.handle.NewsHandle;
import cn.memo.json.JsonHandle;
import cn.memo.net.WeChatHttp;

/**
 * Servlet implementation class OrderList
 */
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OrderList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession();
		String openid = (String) session.getAttribute("openid");
		if(openid==null){
			String id = request.getParameter("code");
			if(id == null){
				response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2forderList&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
				return;
			}
			String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c&code="+id+"&grant_type=authorization_code";
			String rst= WeChatHttp.sendGet(step2url); 
			openid =  JsonHandle.getValue(rst, "openid");  
			session.setAttribute("openid", openid);
		}
		//openid = "1123";
		List<Map<String, String>> rstList = B2CHandle.getOrderList(openid);
		request.setAttribute("orderList",rstList );
		request.getRequestDispatcher("/b2c/orderList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
