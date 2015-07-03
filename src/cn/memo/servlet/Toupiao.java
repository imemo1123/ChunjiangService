package cn.memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.ToupiaoHandel;

/**
 * Servlet implementation class Toupiao
 */
public class Toupiao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Toupiao() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String openid = request.getParameter("openid");
		String id = request.getParameter("id");
		System.out.println(id);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date= df.format(new Date());// new Date()为获取当前系统时间
		String vnString = ToupiaoHandel.toupiao(openid, id, date)+"";
		PrintWriter out = response.getWriter();
		out.print(vnString);
		out.flush();
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
