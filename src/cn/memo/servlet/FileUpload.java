package cn.memo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import cn.memo.handle.NewsHandle;

/**
 * Servlet implementation class FileUpload
 */
public class FileUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FileUpload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("File upload");
		request.setCharacterEncoding("UTF-8");
		String  filePath=this.getServletConfig().getServletContext().getRealPath("/");
		filePath += "images\\";

		String index = "";
		String fileName = "";
		String content = "";
		String title = "";
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			List items = upload.parseRequest(request);
			Iterator itr = items.iterator();
			while (itr.hasNext()) {
				FileItem item = (FileItem) itr.next();
				if (item.isFormField()) {
					System.out.println("表单参数名:" + item.getFieldName() + "，表单参数值:" + item.getString("UTF-8"));
					if(item.getFieldName().equals("index")){
						index = item.getString("UTF-8");
					}
					if(item.getFieldName().equals("title")){
						title = item.getString("UTF-8");
					}
					if(item.getFieldName().equals("content")){
						content = item.getString("UTF-8");
					}
				} else {
					if (item.getName() != null && !item.getName().equals("")) {
						System.out.println("上传文件的大小:" + item.getSize());
						System.out.println("上传文件的类型:" + item.getContentType());
						// item.getName()返回上传文件在客户端的完整路径名称
						System.out.println("上传文件的名称:" + item.getName());
						fileName = item.getName();
						File tempFile = new File(item.getName());
						//上传文件的保存路径
						File file = new File(filePath, tempFile.getName());
						item.write(file);
						
					}else{
						request.setAttribute("upload.message", "没有选择上传文件！");
					}  
				}
			}
			if(NewsHandle.updateNews(index, title, content, "/images/"+fileName))
				request.setAttribute("upload.message", "上传文件成功！");
			else {
				throw new Exception();
			}
		}catch(FileUploadException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("upload.message", "上传文件失败！");
		}
		request.getRequestDispatcher("/web/uploadResult.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
