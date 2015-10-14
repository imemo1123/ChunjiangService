package cn.memo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.MyRandom;
import cn.memo.json.JsonHandle;
import cn.memo.net.WeChatHttp;

/**
 * Servlet implementation class getRandom
 */
public class getRandom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getRandom() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("code");
		if(id == null){
			response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fgetRandom&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
			return;
		}
		String step2url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx7cc0fb85783397ef&secret=ad03d99e0aec031f6b695132ef98451c&code="+id+"&grant_type=authorization_code";
		String rst= WeChatHttp.sendGet(step2url); 
		String openid =  JsonHandle.getValue(rst, "openid");
//		String openid = "1129";
		if(openid.length()>=4){
			int num = MyRandom.getInstance().getRandomNum(openid);
			request.setAttribute("num", num);
			request.getRequestDispatcher("/random.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
