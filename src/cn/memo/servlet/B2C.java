package cn.memo.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.SeatHandle;
import cn.memo.json.JsonHandle;

/**
 * Servlet implementation class B2C
 */
public class B2C extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public B2C() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");   //…Ë÷√±‡¬Î
		String[] prices = SeatHandle.getPrices();
		ArrayList list = SeatHandle.getAreas(prices[0]);
		String rstJson = JsonHandle.toJson(list);
		request.setAttribute("prices", prices);
		request.setAttribute("areas", rstJson);
		request.getRequestDispatcher("/b2c/maipiao.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
