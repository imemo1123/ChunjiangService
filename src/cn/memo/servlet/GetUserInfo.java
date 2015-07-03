package cn.memo.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.UserHandle;
import cn.memo.instance.User;
import cn.memo.json.JsonHandle;

/**
 * Servlet implementation class GetUserInfo
 */
public class GetUserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("收到请求:getUserInfo" );
//		String result = null;
//		BufferedReader br = new BufferedReader(
//			new InputStreamReader((ServletInputStream) request.getInputStream(), "utf-8"));
//		StringBuffer sb =new StringBuffer("");
//		String temp;
//		while((temp=br.readLine())!=null){
//			sb.append(temp);
//		}
//		br.close();
//		result = sb.toString();
//		System.out.println("请求报文:" + result);
//		
		response.setContentType("text/html;charset=utf-8"); 
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
		User user = JsonHandle.getObject(result, new User().getClass());
		UserHandle userHandle = new UserHandle(user);
		Map<String, Object>rstMap = new HashMap<String, Object>();
		
			user = userHandle.getUserInfo();
			System.out.println(user.getId());
			rstMap.put("id", user.getId());
			rstMap.put("name", user.getName());
			rstMap.put("mobile", user.getMobile());
		
		result= JsonHandle.toJson(rstMap);
		System.out.println("返回报文:" + result);
		PrintWriter pw = response.getWriter();
		pw.write(result);
		pw.flush();
		pw.close();
	}

}
