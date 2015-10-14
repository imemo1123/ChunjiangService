package cn.memo.handle;

import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.sql.SQLConnection;

public class SeatHandle {
	public static String[] getPrices () {
		SQLConnection connection = new SQLConnection();
		String sql = "select distinct(price) from parea  order by price";
		List<String> tabList = new ArrayList<String>();
		tabList.add("price");
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list = (ArrayList<Map<String, String>>) connection.queryMulData(sql, tabList);
		String[] rst = new String[list.size()];
		for (int i=0;i<list.size();i++) {
			Map<String, String> map = list.get(i);
			rst[i] = map.get("price");
		}
		return rst;
	}
	
	public static ArrayList<Map<String, String>> getAreas(String price) {
		SQLConnection connection = new SQLConnection();
		String sql = "select id,area,num from parea where price='"+price+"';";
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("area");
		tabList.add("num");
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		list = (ArrayList<Map<String, String>>) connection.queryMulData(sql, tabList);
		return list;
	}
	
	public static Map<String, String>getSeatInfo(String id){
		SQLConnection connection = new SQLConnection();
		String sql = "select id,area,num,price from parea where id='"+id+"';";
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("area");
		tabList.add("num");
		tabList.add("price");
		Map<String, String> m = connection.querySingleData(sql, tabList);
		return m;
	}
	
	public static Map<String, String> getOrderInfo(String flowno) {
		SQLConnection connection = new SQLConnection();
		String sql = "select area,num,price,tel,name,addr,datetime,stt from b2c_flow where flowno='"+flowno+"';";
		List<String> tabList = new ArrayList<String>();
		tabList.add("area");
		tabList.add("num");
		tabList.add("price");
		tabList.add("tel");
		tabList.add("name");
		tabList.add("addr");
		tabList.add("datetime");
		tabList.add("stt");
		Map<String, String> m = connection.querySingleData(sql, tabList);
		return m;
	}
}
