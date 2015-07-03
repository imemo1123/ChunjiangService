package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.instance.Reservation;
import cn.memo.sql.SQLConnection;

public class CollectionHandle {
	public static String doCollect(String id,String uid){
		String sqlString = "select * from collection where model_id='"+id+"' and user_id='"+ uid + "';";
		SQLConnection conn = new SQLConnection();
		if(conn.checkExist(sqlString)){
			return "2";
		}
		sqlString = "insert into collection(user_id,model_id) values ('" + uid +"','"+ id +"');"; 
		System.out.println(sqlString);
		int rst = conn.executeUpdate(sqlString);
		if(rst > 0){
			HouseHandle.addCollection(id);
			return "0";
		}
		else 
			return "1";
	}
	public static String cancelCollect(String id,String uid){
		String sqlString = "select * from collection where model_id='"+id + "'and user_id='"+uid+"';";
		SQLConnection conn = new SQLConnection();
		if(!conn.checkExist(sqlString)){
			return "2";
		}
		sqlString = "delete  from collection where model_id='"+id + "'and user_id='"+uid+"';";
		int rst = conn.executeUpdate(sqlString);
		if(rst > 0)
			return "0";
		else 
			return "1";
	}
	
	public static String isLike (String id,String uid) {
		String sqlString = "select * from collection where model_id='"+id + "'and user_id='"+uid+"';";
		SQLConnection conn = new SQLConnection();
		if(conn.checkExist(sqlString)){
			return "1";
		}
		return "0";
	}
	
	public static List<Map<String,String>> getCollectionList(String id) {
		List<Map<String,String>> newsList = new ArrayList<Map<String,String>>();
		String sqlString = "select a.model_id,b.des,b.pic_id from collection a,model b  where a.model_id=b.id and a.user_id='"+id+"';";
		System.out.println(sqlString);
		SQLConnection conn = new SQLConnection();
		newsList = conn.queryMulData(sqlString, Reservation.getColTabName());
		return newsList;
	}
	
}
