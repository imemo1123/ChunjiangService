package cn.memo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import cn.memo.handle.B2CHandle;
import cn.memo.handle.SeatHandle;

/**
 * Servlet implementation class WechatPaySuc
 */
public class WechatPaySuc extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WechatPaySuc() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String flowno = (String) session.getAttribute("flowno");
		String stt = B2CHandle.paySubmit(flowno);
		String code = B2CHandle.getCode(flowno);
		String tel = B2CHandle.getTel(flowno);
		if("3".equals(stt) || "0".equals(stt)){
			request.setAttribute("code", code);
			request.setAttribute("tel", tel);
			request.getRequestDispatcher("/b2c/buysucc.jsp").forward(request, response);
		}else{
			request.getRequestDispatcher("/error.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
