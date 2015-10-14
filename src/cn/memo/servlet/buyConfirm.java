package cn.memo.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.SeatHandle;

/**
 * Servlet implementation class buyConfirm
 */
public class buyConfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public buyConfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String area = request.getParameter("area");
		Map<String, String> m = SeatHandle.getSeatInfo(area);
		String num = request.getParameter("num");
		int nnum = Integer.parseInt(num);
		int rnum = Integer.parseInt(m.get("num"));
		if(nnum>rnum){
			request.setAttribute("stt", "1");
		}else{
			request.setAttribute("stt", "0");
		}
		Float tprice = Float.parseFloat(m.get("price")) * nnum;
		request.setAttribute("id", m.get("id"));
		request.setAttribute("area", m.get("area"));
		request.setAttribute("num", num+"");
		request.setAttribute("tprice", tprice+"");
		request.getRequestDispatcher("/b2c/confirm.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
