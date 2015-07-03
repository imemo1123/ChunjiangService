package cn.memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.memo.handle.MyHandle;
import cn.memo.handle.PageHandle;
import cn.memo.handle.getListHandle;
import cn.memo.json.JsonHandle;

/**
 * Servlet implementation class getBabyList
 */
public class getBabyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getBabyList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String index = MyHandle.nvl(request.getParameter("index"),"0");
		System.out.println(index);
		String area = MyHandle.nvl(request.getParameter("area") , "0");
		HttpSession session = request.getSession();
		//session.setAttribute("area", area);
		int vn = PageHandle.visit();
		request.setAttribute("vn", vn+"");
		int un = getListHandle.getUserNum();
		request.setAttribute("un", un+"");
		int pn = getListHandle.getVontNum();
		request.setAttribute("pn", pn+"");
		//session.setAttribute("openid", "1123");
		//session.setAttribute("subscribe", "1");
		ArrayList sList = (ArrayList) session.getAttribute("slist");
		if(sList == null){
			sList = getListHandle.getListWithArea(area);
			session.setAttribute("slist", sList);
		}
		ArrayList list = getListHandle.getTen(sList, Integer.parseInt(index), 10);
		//ArrayList list = getListHandle.getSomeList(area,index);
		String rstJson = JsonHandle.toJson(list);
		System.out.println(rstJson);
		PrintWriter out = response.getWriter();
		out.print(rstJson);
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
