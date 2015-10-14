package cn.memo.servlet;

import java.io.IOException;

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
 * Servlet implementation class ChoujiangPage
 */
public class ChoujiangPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChoujiangPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");   //…Ë÷√±‡¬Î
		String rqurl = "http://localhost:8080/choujiangPage";
		request.setAttribute("rqurl", rqurl);
		HttpSession session=request.getSession();
		String subscribe  ="1";
		String openid = "1124";
		session.setAttribute("openid", openid);
		String area = MyHandle.nvl(request.getParameter("area"),"1");
		request.setAttribute("area", area);
		String intros = ChoujiangHandle.getChoujiangIntro(area);
		request.setAttribute("intros", intros);
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
		// TODO Auto-generated method stub
	}

}
