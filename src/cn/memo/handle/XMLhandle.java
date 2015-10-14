package cn.memo.handle;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;

public class XMLhandle {
	public static Map doXMLParse(String strxml)  {
		if(null == strxml || "".equals(strxml)) {
			return null;
	        }
		Map m = new HashMap();
		
		try {
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource( new StringReader( strxml ))); 
			Element root=doc.getDocumentElement();
			//Element root = doc.getDocumentElement();
			NodeList list = root.getChildNodes();
			for(int i=0;i<list.getLength();i++){
				Node node = list.item(i);
				if(node.getNodeType() == Node.ELEMENT_NODE){
					System.out.println(node.getNodeName()+":"+node.getChildNodes().item(0).getNodeValue());
					String k =node.getNodeName();
					String v = node.getChildNodes().item(0).getNodeValue();
					m.put(k, v);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return m;
	}
	
	public static String setXML(String return_code, String return_msg) {
	        return "<xml><return_code><![CDATA[" + return_code
	                + "]]></return_code><return_msg><![CDATA[" + return_msg
	                + "]]></return_msg></xml>";

	}
	
	
	
	public static void main(String[] args) {
		
		try{ 
			//File f=new File("test.xml"); 
			String string = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg><appid><![CDATA[wx7cc0fb85783397ef]]></appid><mch_id><![CDATA[1248342901]]></mch_id><nonce_str><![CDATA[LtOcfRd7ne1xSZx7]]></nonce_str><sign><![CDATA[8768DDD7AD0BCA142705F34E7D15956A]]></sign><result_code><![CDATA[SUCCESS]]></result_code><prepay_id><![CDATA[wx20150713143937fa9d8707990360003710]]></prepay_id><trade_type><![CDATA[JSAPI]]></trade_type></xml> ";
			DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance(); 
			DocumentBuilder builder=factory.newDocumentBuilder();
			Document doc = builder.parse(new InputSource( new StringReader( string ))); 
			Element root=doc.getDocumentElement();
			String rootName=root.getNodeName();
		        System.out.println("XML文件根节点的名字："+rootName);
		        NodeList list = root.getChildNodes();
		        for(int i = 0; i < list.getLength(); i++){
		        	Node node = list.item(i);
		        	if(node.getNodeType() == Node.ELEMENT_NODE)
		        		System.out.println(node.getNodeName()+":"+node.getChildNodes().item(0).getNodeValue());
		        	else
		        		System.out.println(node.getNodeName()+":"+node.getNodeValue());
		       }
//			for (int i=0;i<nl.getLength();i++){ 
//				System.out.print("车牌号码:" + doc.getElementsByTagName("NO").item(i).getFirstChild().getNodeValue()); 
//				System.out.println("车主地址:" + doc.getElementsByTagName("ADDR").item(i).getFirstChild().getNodeValue()); 
//			} 
		}catch(Exception e){ 
			e.printStackTrace(); 
		} 
	}
	    
}
