package cn.memo.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jdt.internal.compiler.lookup.VariableBinding;

import cn.memo.handle.getListHandle;

/**
 * Servlet implementation class SearchForDetail
 */
public class SearchForDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchForDetail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String val = request.getParameter("val");
		Map<String, String> m = getListHandle.search(val);
		if(m == null){
			request.getRequestDispatcher("wechat/nobody.jsp").forward(request, response);
		}else{
			request.setAttribute("id", m.get("id"));
			request.setAttribute("name", m.get("name"));
			request.setAttribute("pic", m.get("pic"));
			request.setAttribute("pocard", m.get("pocard"));
			request.getRequestDispatcher("wechat/showDetail.jsp").forward(request, response);
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
