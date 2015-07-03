package cn.memo.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.memo.handle.MyHandle;
import cn.memo.handle.PageHandle;
import cn.memo.handle.getListHandle;

/**
 * Servlet implementation class ToupiaoPage
 */
public class ToupiaoPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ToupiaoPage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String area = MyHandle.nvl(request.getParameter("area") , "0");
		int vn = PageHandle.visit();
		request.setAttribute("vn", vn+"");
		int un = getListHandle.getUserNum();
		request.setAttribute("un", un+"");
		int una = getListHandle.getUserNum(area);
		request.setAttribute("una", una+"");
		int pn = getListHandle.getVontNum();
		request.setAttribute("pn", pn+"");
		session.setAttribute("openid", "1123");
		session.setAttribute("subscribe", "1");
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
