package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.sql.SQLConnection;

public class PageHandle {
	public static int visit() {
		String sqlString = "select valu from params where name='acc_count';";
		SQLConnection conn = new SQLConnection();
		List<String> tabList = new ArrayList<String>();
		tabList.add("valu");
		Map map = conn.querySingleData(sqlString, tabList);
		String vString = (String)map.get("valu");
		int vint = Integer.parseInt(vString);
		vint+=3;
		
		String updateString = "update params set valu='"+vint+"' where name='acc_count';";
		System.out.println(updateString);
		conn.executeUpdate(updateString);
		return vint;
	}
	
	public static ArrayList<String> getAdList(String area){
		String sqlString = "select pic,url from ad where area='"+area+"' order by id;";
		SQLConnection conn = new SQLConnection();
		List<String> tabList = new ArrayList<String>();
		tabList.add("pic");
		tabList.add("url");
		List<Map<String, String>> rstlist = conn.queryMulData(sqlString, tabList);
		ArrayList<String> list = new ArrayList<String>();
		for(Map<String, String> item:rstlist){
			String picString = item.get("pic");
			String urlString = MyHandle.nvl(item.get("url"),"#");
			String rstString = picString+"|"+urlString;
			list.add(rstString);
		}
		return list;
	}
}
