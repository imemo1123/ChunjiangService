package cn.memo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sina.sae.util.SaeUserInfo;

/**
 * Servlet implementation class Test
 */
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getRealPath("images/ad11.jpg"));
////		PrintWriter out = response.getWriter();
////		out.println("02");
////		String URL="jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_memoandfriends";
////		String sql = "SELECT * FROM news";
////		// 通过SaeUserInfo提供的静态方法获取应用的access_key和secret_key
////		String Username=SaeUserInfo.getAccessKey();
////		String Password=SaeUserInfo.getSecretKey();
////		String Driver="com.mysql.jdbc.Driver";
////		
////		try {
////			Class.forName(Driver).newInstance();
////			
////			Connection con=DriverManager.getConnection(URL,Username,Password);
////			
////			Statement stmt = null;
////			ResultSet rs = null;
////			stmt = con.createStatement();
////			
////			rs = stmt.executeQuery(sql);
////			out.println("4");
////			if(rs.next()){
////				out.println("5");
////				String buffString = rs.getString("picture");
////				out.println("6");
////				out.print(buffString);
////				
////			}
//		} catch (InstantiationException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IllegalAccessException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.println("01");
		doGet(request, response);
	}

}
