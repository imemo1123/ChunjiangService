package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.servlet.setEnable;
import cn.memo.sql.SQLConnection;

public class ToupiaoHandel {
	
	private static ArrayList<String> vipList;
	public  ToupiaoHandel() {
		vipList = new ArrayList<String>();
		vipList.add("oDuZis7TlA-UIFGkKjTrfnq_qcA8");
		vipList.add("oDuZis3LIMQasr9ZV2IOja2UzbZg");
		vipList.add("1123");
	}
	public static int toupiao(String openid, String id,String date) {
		vipList = new ArrayList<String>();
		vipList.add("oDuZis7TlA-UIFGkKjTrfnq_qcA8");
		vipList.add("1123");
		boolean isVip = false;
		for(String vid : vipList){
			if(openid.equals(vid)){
				isVip = true;
				break;
			}
		}
		SQLConnection connection = new SQLConnection();
		int vint;
		String sql = "select * from pocard_flow where openid='"+openid+"' and date='"+date+"';";
		if(connection.checkExist(sql) && !isVip){
			return -1;
		}else{
			String sql2 = "select pocard from pocard where id = '"+id+"';";
			if(connection.checkExist(sql2)){
				List<String> tabList = new ArrayList<String>();
				tabList.add("pocard");
				Map map = connection.querySingleData(sql2, tabList);
				String vString = (String)map.get("pocard");
				vint  = Integer.parseInt(vString);
				vint ++;
				String sql3 = "update pocard set pocard='"+vint+"' where id = '"+id+"';";
				connection.executeUpdate(sql3);
				addToFlow(openid, id, date);
				return vint;
			}else{
				String sql4 = "insert into pocard(id,pocard) values('"+id+"','1');";
				//System.out.println(sql4);
				connection.executeUpdate(sql4);
				addToFlow(openid, id, date);
				return 1;
			}
			
		}
	}
	
	private static void addToFlow(String openid,String userid,String date) {
		SQLConnection connection = new SQLConnection();
		String sql4 = "insert into pocard_flow(openid,userid,date) values('"+openid+"','"+userid+"','"+date+"');";
		System.out.println(sql4);
		connection.executeUpdate(sql4);

	}
	
	public static void setEnable(String openid) {
		SQLConnection connection = new SQLConnection();
		String sql = "insert into enable_flow(openid) values ('"+openid+"');";
		connection.executeUpdate(sql);
	}
	
	public static String getEnable(String openid) {
		SQLConnection connection = new SQLConnection();
		String sql = "select * from enable_flow where openid = '"+openid+"';";
		if( connection.checkExist(sql))
			return "1";
		else
			return "0";
	
	}
}
