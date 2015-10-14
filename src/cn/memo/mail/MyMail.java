package cn.memo.mail;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Folder;  
import javax.mail.Message;  
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.Session;  
import javax.mail.Store; 
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

public class MyMail {
	public static void main(String[] args) throws Exception {
		String pop3Server = "pop.163.com";
		String protocol = "pop3";
		String user = "justloveyou1123";
		String pwd = "kissyou770";
		
		Properties properties = new Properties();
		properties.setProperty("mail.store.protocol", protocol);
		properties.setProperty("mail.pop3.host", pop3Server);
		
		Session session = Session.getInstance(properties);
		session.setDebug(true);
		
		Store store = session.getStore();
		store.connect(pop3Server, user, pwd);
		
		Folder folder = store.getFolder("inbox");
		folder.open(Folder.READ_ONLY);
		
		Message[] messages = folder.getMessages();
		
		int mailCounts = messages.length;
		
		//for (Message message : messages) {
		Message message = messages[mailCounts-1];
		new MyMail().mailReceiver(message);
		folder.close(false);  
		store.close();  
	}
	
	
	private void mailReceiver(Message msg)throws Exception{
		// ��������Ϣ
		Address[] froms = msg.getFrom();
		if(froms != null) {
		    //System.out.println("��������Ϣ:" + froms[0]);
		    InternetAddress addr = (InternetAddress)froms[0];
		    System.out.println("�����˵�ַ:" + addr.getAddress());
		    System.out.println("��������ʾ��:" + addr.getPersonal());
		}
		System.out.println("�ʼ�����:" + msg.getSubject());
		// getContent() �ǻ�ȡ��������, Part�൱�����װ
		Object o = msg.getContent();
		if(o instanceof Multipart) {
			Multipart multipart = (Multipart) o ;
			reMultipart(multipart);
		} else if (o instanceof Part){
			Part part = (Part) o; 
			rePart(part);
		} else {
			System.out.println("����" + msg.getContentType());
			System.out.println("����" + msg.getContent());
		}
	}
	
	
	private void rePart(Part part) throws Exception {
		if (part.getDisposition() != null) {

			String strFileNmae = MimeUtility.decodeText(part.getFileName()); //MimeUtility.decodeText�����������������
			System.out.println("���ָ���: " +  MimeUtility.decodeText(part.getFileName()));
			System.out.println("��������: " + MimeUtility.decodeText(part.getContentType()));
			System.out.println("��������:" + part.getContent());
			InputStream in = part.getInputStream();// �򿪸�����������
			// ��ȡ�����ֽڲ��洢���ļ���
			java.io.FileOutputStream out = new FileOutputStream(strFileNmae);
			int data;
			while((data = in.read()) != -1) {
				out.write(data);
			}
			in.close();
			out.close();
		} else {
			if(part.getContentType().startsWith("text/plain")) {
				System.out.println("�ı����ݣ�" + part.getContent());
			} else {
				//System.out.println("HTML���ݣ�" + part.getContent());
			}
		}
	}

	/**
	 * @param multipart // ��ж�������������ʼ�����(����+����+����)��
	 * @throws Exception
	 */
	private void reMultipart(Multipart multipart) throws Exception {
		//System.out.println("�ʼ�����" + multipart.getCount() + "�������");
		// ���δ����������
		for (int j = 0, n = multipart.getCount(); j < n; j++) {
			//System.out.println("�����" + j + "����");
			Part part = multipart.getBodyPart(j);//���, ȡ�� MultiPart�ĸ�������, ÿ���ֿ������ʼ�����,
			// Ҳ��������һ��С����(MultipPart)
			// �жϴ˰��������ǲ���һ��С����, һ����һ������ ���� Content-Type: multipart/alternative
			if (part.getContent() instanceof Multipart) {
				Multipart p = (Multipart) part.getContent();// ת��С����
				//�ݹ����
				reMultipart(p);
			} else {
				rePart(part);
			}
		}
	}
	
}
