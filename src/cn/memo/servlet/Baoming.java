package cn.memo.servlet;

import java.awt.event.FocusAdapter;
import java.io.File;

import com.sina.sae.storage.*;
import com.sina.sae.util.SaeUserInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.memo.handle.BaomingHandle;
import cn.memo.handle.MyHandle;

/**
 * Servlet implementation class Baoming
 */
public class Baoming extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List piclist=new ArrayList();  //���ϴ���ͼƬ��
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Baoming() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name="",tel="",wechat="",pic="",channel="",area="";
		String path=request.getRealPath("/images");
	        System.out.println(path);
	        DiskFileItemFactory factory=new DiskFileItemFactory();
	        ServletFileUpload sfu=new ServletFileUpload(factory);
	        sfu.setHeaderEncoding("UTF-8");  //������������
	        sfu.setSizeMax(1024*1024*10);   //�����ļ���С
	        request.setCharacterEncoding("utf-8");
	        try {
	            List<FileItem> fileItems= sfu.parseRequest(request);  //�������� �õ����б�Ԫ��
	            for (FileItem fi : fileItems) {
	                //�п����� �ļ���Ҳ��������ͨ���� 
	                if (fi.isFormField()) { //���ѡ���� ���� 
	                	String titleString = fi.getFieldName();
	                	if("name".equals(titleString)){
	                		name = new String(fi.getString().getBytes("ISO-8859-1"),"UTF-8");
	                		request.setAttribute("name", name);
	                	}
	                	else if("tel".equals(titleString)){
	                		tel = fi.getString();
	                		request.setAttribute("tel", tel);
	                	}
	                	else if("wechat".equals(titleString)){
	                		wechat = new String(fi.getString().getBytes("ISO-8859-1"),"UTF-8");
	                		request.setAttribute("wechat", wechat);
	                	}else if("channel".equals(titleString)){
	                		channel = fi.getString();
	                	}else if("area".equals(titleString)){
	                		if(fi.getString()==null || fi.getString().length() <= 0)
	                			area = "0";
	                		else
	                			area = MyHandle.nvl(fi.getString() , "0");
	                		
	                	}
	                	String a = new String(fi.getString().getBytes("ISO-8859-1"),"UTF-8");
	                	System.out.println("��ֵΪ��"+a);
	                }else{
	                    // ���ļ�
	                	System.out.println("memo:"+fi.getName());
	                	String realPath= SaeUserInfo.getSaeTmpPath()+"/"; 
	                	File fullFile=new File(fi.getName());
	                	System.out.println(realPath+fullFile.getName());
	                	File uploadFile=new File(realPath,fullFile.getName());
	                	fi.write(uploadFile);
	                     	//�ϴ���Ϻ� ʹ��SaeStorage��storage����д
	                	SaeStorage ss = new SaeStorage();
	                	//ʹ��upload�����ϴ�����Ϊimage��
	                	String newName=tel+fullFile.getName().substring(fullFile.getName().lastIndexOf("."));
	                	ss.upload("images", realPath+fullFile.getName(), newName);
	                	pic = ss.getUrl("images", newName);
	                //	pic="images/news1.jpg";
	                	
	                }                
	            }
	            String rst = BaomingHandle.baoming(name, tel, wechat, pic,area);
	            	request.setAttribute("pics", pic);
	            	request.setAttribute("rst", rst);
	        } catch (Exception e) {
	        	 request.setAttribute("rst", "-1");
	        	// e.printStackTrace();
	        }
	        if("pc".equals(channel))
	        	request.getRequestDispatcher("result.jsp").forward(request, response);
	        else
	        	request.getRequestDispatcher("result1.jsp").forward(request, response);
	        
	}

}
