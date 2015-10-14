package cn.memo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import cn.memo.handle.B2CHandle;

/**
 * Servlet implementation class WechatPay
 */
public class WechatPay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WechatPay() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String id = (String)request.getParameter("id");
		String area = (String)request.getParameter("area");
		area = new String( ((String)request.getParameter("area")).getBytes("ISO-8859-1"),"UTF-8");
		String num = (String)request.getParameter("num");
		String tprice = (String)request.getParameter("tprice");
		String name = (String)request.getParameter("name");
		//name = new String( ((String)request.getParameter("name")).getBytes("ISO-8859-1"),"UTF-8");
		System.out.println(name);
		String tel = (String)request.getParameter("tel");
		String addr = (String)request.getParameter("addr");
		String flowno = B2CHandle.saveFlow(id, num, tprice, name, tel, addr);
		if(flowno == null){
			
		}else{
			session.setAttribute("flowno", flowno);
			//response.sendRedirect("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx7cc0fb85783397ef&redirect_uri=http%3a%2f%2fmemoandfriends.sinaapp.com%2fwechatPay2&response_type=code&scope=snsapi_base&state=1#wechat_redirect");
			response.sendRedirect("/wechatPay2");
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
