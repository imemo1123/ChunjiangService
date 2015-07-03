package cn.memo.servlet;

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

public class Baoming1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List piclist=new ArrayList();  //���ϴ���ͼƬ��
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Baoming1() {
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
		request.setCharacterEncoding("gbk");
                PrintWriter out = response.getWriter();
                //ʹ��SaeUserInfo�õ��������д��·��
                String realPath= SaeUserInfo.getSaeTmpPath()+"/"; 
                try {
                        //ʹ��common-upload�ϴ��ļ������·����
                     boolean isMultipart=ServletFileUpload.isMultipartContent(request);
                     if(!isMultipart)return;
                     DiskFileItemFactory factory=new DiskFileItemFactory();
                     ServletFileUpload upload=new ServletFileUpload(factory);
                     upload.setFileSizeMax(1024*1024);
                     List<FileItem> items=null;
                 items=upload.parseRequest(request);
                     for(FileItem item:items)
                     {
                       if(!item.isFormField())
                       {
                          File fullFile=new File(item.getName());
                          File uploadFile=new File(realPath,fullFile.getName());
                      item.write(uploadFile);
                      //�ϴ���Ϻ� ʹ��SaeStorage��storage����д
                      SaeStorage ss = new SaeStorage();
                      //ʹ��upload�����ϴ�����Ϊimage��
                      ss.upload("images", realPath+fullFile.getName(), fullFile.getName());
                      
                      out.print("upload file:"+realPath+fullFile.getName()+"</br>");
                      out.print("file url:"+ss.getUrl("images", fullFile.getName())+"</br>");
                       }
                     }
                     out.print("upload end...");
                } catch (Exception e) {
                        out.print("ERROR:"+e.getMessage()+"</br>");
                } finally{
                        out.flush();
                        out.close();
                }
	}

}
