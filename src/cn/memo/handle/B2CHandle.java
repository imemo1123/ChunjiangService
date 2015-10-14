package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.sql.SQLConnection;

public class B2CHandle {
	public static String saveFlow(String area,String num,String price, String name,String tel,String addr) {
		SQLConnection connection = new SQLConnection();
		String flowno = MyHandle.getSeq();
		String datetime = MyHandle.getDate();
		String sqlString = "INSERT INTO b2c_flow(flowno,area,num,price,tel,name,addr,datetime,stt)VALUES("
						+ "'" + flowno +"',"
						+ "'" + area +"',"
						+ "'" + num +"',"
						+ "'" + price +"',"
						+ "'" + tel +"',"
						+ "'" + name +"',"
						+ "'" + addr +"',"
						+ "'" + datetime +"',"
						+ "'" + 1 +"');";
		System.out.println(sqlString);
		int rst = connection.executeUpdate(sqlString);
		if(rst>0){
			return flowno;
		}else {
			return null;
		}
	}
	
	public static String getPrice(String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql = "select price from b2c_flow where flowno='"+flowno+"';";
		String rst = connection.getOneValue(sql, "price");
		return rst;
	}
	
	public static String getTel(String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql = "select tel from b2c_flow where flowno='"+flowno+"';";
		String rst = connection.getOneValue(sql, "tel");
		return rst;
	}
	
	public static String paySucc(String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql = "select stt from b2c_flow where flowno='"+flowno+"';";
		String stt = connection.getOneValue(sql, "stt");
		if(!("0".equals(stt))){
			stt = "0";
			String sql2 = "update b2c_flow set stt='"+stt+"' where flowno='"+flowno+"';";
			connection.executeUpdate(sql2);
		}
		return stt;
	}
	public static String paySubmit(String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql = "select stt,area,num from b2c_flow where flowno='"+flowno+"';";
		List<String> tabNames = new ArrayList<String>();
		tabNames.add("stt");
		tabNames.add("area");
		tabNames.add("num");
		Map<String , String> m = connection.querySingleData(sql, tabNames);
		String stt = m.get("stt");
		if("1".equals(stt)){
			stt = "3";
			String sql2 = "update b2c_flow set stt='"+stt+"' where flowno='"+flowno+"';";
			connection.executeUpdate(sql2);
			
			String area = m.get("area");
			int num = Integer.getInteger(m.get("num"));
			String sqlString = "update parea set num = num-"+num+" where id='"+area+"';";
			connection.executeUpdate(sqlString);
		}
		return stt;
	}
	
	public static void setOpenid(String openid,String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql2 = "update b2c_flow set openid='"+openid+"' where flowno='"+flowno+"';";
		connection.executeUpdate(sql2);
	}
	
	public static String getCode(String flowno) {
		String code = WechatPayHandle.getRandomString(8);
		String sql2 = "update b2c_flow set code='"+code+"' where flowno='"+flowno+"';";
		SQLConnection connection = new SQLConnection();
		connection.executeUpdate(sql2);
		return code;
	}
	
	public static List<Map<String, String>> getOrderList(String openid) {
		SQLConnection connection = new SQLConnection();
		String sqlString = "select flowno,b.area,a.num,a.price,a.name,a.tel,a.addr,a.datetime,a.stt from b2c_flow a left join parea b on a.area=b.id where a.openid='"+openid+"' and (a.stt='0' or a.stt='3') order by a.datetime desc;";
		List<Map<String, String>> m = new ArrayList<Map<String,String>>();
		List<String> tabNames = new ArrayList<String>();
		tabNames.add("flowno");
		tabNames.add("area");
		tabNames.add("num");
		tabNames.add("price");
		tabNames.add("name");
		tabNames.add("tel");
		tabNames.add("addr");
		tabNames.add("datetime");
		tabNames.add("stt");
		m = connection.queryMulData(sqlString, tabNames);
		return m;
	}
}
