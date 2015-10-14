package cn.memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.memo.handle.ChoujiangHandle;

/**
 * Servlet implementation class Duijiang
 */
public class Duijiang extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Duijiang() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");   //…Ë÷√±‡¬Î
		HttpSession session=request.getSession();
		String openid = (String) session.getAttribute("openid");
		System.out.println(openid);
		if(openid == null || openid.length() <=0)
			return;
		String id = request.getParameter("id");
		String code = request.getParameter("code");
		String tel = request.getParameter("tel");
		String name = request.getParameter("name");
		String area = request.getParameter("area");
		int rst = ChoujiangHandle.zhongjiang(openid, id, tel, code,name,area);
		PrintWriter out = response.getWriter();
		out.print(rst);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
