package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.instance.Reservation;
import cn.memo.sql.SQLConnection;

public class getListHandle {
	public static ArrayList<Map<String,String>> getList(){
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("name");
		tabList.add("pic");
		tabList.add("pocard");
		String sqlString = "select a.id,a.name,a.pic,IFNULL(b.pocard,0) as pocard from baby a left join pocard b on a.id=b.id where ischeck=1  limit 0,10;";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		list = (ArrayList<Map<String, String>>) conn.queryMulData(sqlString, tabList);
		return list;
	};
	public static ArrayList<Map<String,String>> getListWithArea(String area){
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("name");
		tabList.add("pic");
		tabList.add("pocard");
		String sqlString = "select a.id,a.name,a.pic,IFNULL(b.pocard,0) as pocard from baby a left join pocard b on a.id=b.id where ischeck=1 and area='"+area+"' order by pocard desc;";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		list = (ArrayList<Map<String, String>>) conn.queryMulData(sqlString, tabList);
		return list;
	};
	
	public static ArrayList<Map<String,String>> getSomeList(String area,String index){
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("name");
		tabList.add("pic");
		tabList.add("pocard");
		String sqlString = "select a.id,a.name,a.pic,IFNULL(b.pocard,0) as pocard from baby a left join pocard b on a.id=b.id where ischeck=1 and area='"+area+"'  limit "+index+",10 ;";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		list = (ArrayList<Map<String, String>>) conn.queryMulData(sqlString, tabList);
		return list;
	};
	
	public static int getUserNum() {
		String sqlString = "select count(*) as num from baby where ischeck=1;";
		SQLConnection conn = new SQLConnection();
		List<String> tabList = new ArrayList<String>();
		tabList.add("num");
		Map map = conn.querySingleData(sqlString, tabList);
		String vString = (String)map.get("num");
		int vint = Integer.parseInt(vString);
		
		return vint;
	}
	
	public static int getUserNum(String area) {
		String sqlString = "select count(*) as num from baby where  ischeck=1 and area='"+area+"';";
		SQLConnection conn = new SQLConnection();
		List<String> tabList = new ArrayList<String>();
		tabList.add("num");
		Map map = conn.querySingleData(sqlString, tabList);
		String vString = (String)map.get("num");
		int vint = Integer.parseInt(vString);
		
		return vint;
	}
	
	public static int getVontNum() {
		String sqlString = "select count(*) as num from pocard_flow;";
		SQLConnection conn = new SQLConnection();
		List<String> tabList = new ArrayList<String>();
		tabList.add("num");
		Map map = conn.querySingleData(sqlString, tabList);
		String vString = (String)map.get("num");
		int vint = Integer.parseInt(vString);
		
		return vint;
	}
	
	public static Map<String,String> search(String val){
		String sqlString1 = "select a.id,a.name,a.pic,IFNULL(b.pocard,0) as pocard from baby a left join pocard b on a.id=b.id where ischeck=1 and a.id='"+val+"';";
		String sqlString2 = "select a.id,a.name,a.pic,IFNULL(b.pocard,0) as pocard from baby a left join pocard b on a.id=b.id where ischeck=1 and a.name like'"+val+"';";
		System.out.println(sqlString1);
		System.out.println(sqlString2);
		SQLConnection conn = new SQLConnection();
		if (conn.checkExist(sqlString1)) {
			System.out.println(sqlString1);
			List<String> tabList = new ArrayList<String>();
			tabList.add("id");
			tabList.add("name");
			tabList.add("pic");
			tabList.add("pocard");
			Map map = conn.querySingleData(sqlString1, tabList);
			return map;
		}else if(conn.checkExist(sqlString2)) {
			System.out.println(sqlString2);
			List<String> tabList = new ArrayList<String>();
			tabList.add("id");
			tabList.add("name");
			tabList.add("pic");
			tabList.add("pocard");
			Map map = conn.querySingleData(sqlString2, tabList);
			return map;
		}else {
			return null;
		}
		
	}
	
	public static  ArrayList<Map<String,String>>getTen(ArrayList rlist,int start,int stop){
		ArrayList sList = new ArrayList<Map<String,String>>();
		int j=0;
		for(int i=start;j<stop&&i<rlist.size();i++,j++){
			sList.add(rlist.get(i));
		}
		return sList;
	}
	
}
