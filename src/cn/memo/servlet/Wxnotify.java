package cn.memo.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.memo.handle.B2CHandle;
import cn.memo.handle.XMLhandle;

/**
 * Servlet implementation class Wxnotify
 */
public class Wxnotify extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Wxnotify() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 InputStream inStream = request.getInputStream();
		 ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
		 byte[] buffer = new byte[1024];
		 int len = 0;
		 while ((len = inStream.read(buffer)) != -1) {
			 outSteam.write(buffer, 0, len);
		 }
		 System.out.println("~~~~~~~~~~~~~~~~付款成功~~~~~~~~~");
		 outSteam.close();
		 inStream.close();
		 String result  = new String(outSteam.toByteArray(),"utf-8");//获取微信调用我们notify_url的返回信息
		 System.out.println("");
		 Map map = XMLhandle.doXMLParse(result);
		 for(Object keyValue : map.keySet()){
			 System.out.println(keyValue+"="+map.get(keyValue));
		 }
		 if (map.get("result_code").toString().equalsIgnoreCase("SUCCESS")) {
			 String flowno = (String) map.get("out_trade_no");
			 String stt = B2CHandle.paySucc(flowno);
			 
			 System.out.println("wxnotify:stt:"+stt+"flowno:"+flowno);
			 response.getWriter().write(XMLhandle.setXML("SUCCESS", ""));   //告诉微信服务器，我收到信息了，不要在调用回调action了
			 System.out.println("-------------"+XMLhandle.setXML("SUCCESS", ""));
		 }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
