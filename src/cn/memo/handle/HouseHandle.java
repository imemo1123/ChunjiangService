package cn.memo.handle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.memo.instance.House;
import cn.memo.sql.SQLConnection;

public class HouseHandle {
	public static List<Map<String,String>> getHouseList(){
		List<Map<String,String>> houseList = new ArrayList<Map<String,String>>();
		String sqlString = "select id,des,pic_id from model";
		SQLConnection conn = new SQLConnection();
		houseList = conn.queryMulData(sqlString, House.getTabNames());
		return houseList;
	}
	public static List<Map<String,String>> getHouseList(String id){
		if(id.equals("0")){
			return getHouseList();
		}
		List<Map<String,String>> houseList = new ArrayList<Map<String,String>>();
		String sqlString = "select id,des,pic_id from model where building='"+id+"';";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		houseList = conn.queryMulData(sqlString, House.getTabNames());
		return houseList;
	}
	public static Map<String, String> getHouseDetail(String id){
		Map<String, String>  house = new HashMap<String, String>();
		String sqlString = "select  ";
		List<String> tabNamesList = House.getDetailTabNames();
		for(int i = 0;i<tabNamesList.size();i++){
			if (i != tabNamesList.size()-1) {
				sqlString += tabNamesList.get(i) + ",";
			}else{
				sqlString += tabNamesList.get(i);
			}
		}
		sqlString += " from model  where id='"+id+"';";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		house = conn.querySingleData(sqlString, House.getDetailTabNames());
		return house;
	}
	
	public static void addReservation(String id){
		String sqlString = "update model set reservation = reservation+1 where id='"+id+"';";
		SQLConnection conn = new SQLConnection();
		conn.executeUpdate(sqlString);
	}
	
	
	public static void addCollection(String id){
		String sqlString = "update model set collection = collection+1 where id='"+id+"';";
		SQLConnection conn = new SQLConnection();
		conn.executeUpdate(sqlString);
	}
	
}
