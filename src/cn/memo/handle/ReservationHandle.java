package cn.memo.handle;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.instance.Reservation;
import cn.memo.instance.User;
import cn.memo.sql.SQLConnection;

public class ReservationHandle {
	public static String doReserve(String id,String uid,String reserveDate){
//		String sqlString = "select * from reservation where model_id='"+id+"' and user_id='"+ uid + "';";
		SQLConnection conn = new SQLConnection();
//		if(conn.checkExist(sqlString)){
//			return "2";
//		}
		User user = new User();
		user.setId(uid);
		user = new UserHandle(user).getUserInfo();
		
		String sqlString2 = "insert into reservation(user_id,user_name,mobile,model_id,salesman_id,create_time,reserve_time,state) values ('" 
						+ user.getId() +"','"
						+ user.getName() +"','"
						+ user.getMobile() +"','"
						+ id +"','"
						+ user.getSalesman() +"','"
						+ MyHandle.getDate() +"','"
						+ reserveDate +"','"
						+ "0');";
		System.out.print(sqlString2);
		int rst = conn.executeUpdate(sqlString2);
		if(rst > 0){
			HouseHandle.addReservation(id);
			return "0";
		}	
		else 
			return "1";
	}
	public static String cancelReserve(String id){
		String sqlString = "select * from reservation where id='"+id + "';";
		SQLConnection conn = new SQLConnection();
		if(!conn.checkExist(sqlString)){
			return "2";
		}
		sqlString = "delete from reservation where id='"+id + "';";
		System.out.println(sqlString);
		int rst = conn.executeUpdate(sqlString);
		if(rst > 0)
			return "0";
		else 
			return "1";
	}
	
	public static String dealReserve(String id){
		String sqlString = "select * from reservation where id='"+id + "';";
		SQLConnection conn = new SQLConnection();
		if(conn.checkExist(sqlString)){
			return "2";
		}
		sqlString = "update reservation set state ='1' where id='"+id + "';";
		int rst = conn.executeUpdate(sqlString);
		if(rst > 0)
			return "0";
		else 
			return "1";
	}
	
	public static List<Map<String,String>> getReservationList4User(String id) {
		List<Map<String,String>> newsList = new ArrayList<Map<String,String>>();
		String sqlString = "select a.id,a.model_id,b.des,b.pic_id,a.state,a.reserve_time from reservation a,model b  where a.model_id=b.id and user_id='"+id+"'order by a.create_time";
		SQLConnection conn = new SQLConnection();
		newsList = conn.queryMulData(sqlString, Reservation.getTabNames2());
		return newsList;
	}
	public static List<Map<String,String>> getReservationList4Salesman(String id) {
		List<Map<String,String>> newsList = new ArrayList<Map<String,String>>();
		String sqlString = "select a.id,a.model_id,b.des,b.pic_id,a.state,a.reserve_time,a.user_name,a.mobile from reservation a,model b  where a.model_id=b.id and salesman_id='"+id+"'order by a.create_time";
		SQLConnection conn = new SQLConnection();
		newsList = conn.queryMulData(sqlString, Reservation.getTabNames());
		return newsList;
	}
	
	
}
