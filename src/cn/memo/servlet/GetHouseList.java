package cn.memo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.HouseHandle;
import cn.memo.json.JsonHandle;

/**
 * Servlet implementation class GetHouseList
 */
public class GetHouseList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetHouseList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8"); 
		System.out.println("收到请求:getHouseList" );
		String result = null;
		BufferedReader br = new BufferedReader(
			new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
		StringBuffer sb =new StringBuffer("");
		String temp;
		while((temp=br.readLine())!=null){
			sb.append(temp);
		}
		br.close();
		result = sb.toString();
		System.out.println("请求报文:" + result);
		String id = JsonHandle.getValue(result, "id");
		List<Map<String, String>> rstList = HouseHandle.getHouseList(id);
		String rpsString= JsonHandle.toJson(rstList);
		System.out.println("返回报文:" + rpsString);
		PrintWriter pw = response.getWriter();
		pw.write(rpsString);
		pw.flush();
		pw.close();
	}

}
