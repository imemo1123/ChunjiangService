package cn.memo.sql;

import java.sql.*;
import java.util.*;

import cn.memo.parameter.Parameter;

import com.sina.sae.util.SaeUserInfo;
public class SQLConnection {

	public Connection startConn(Connection conn){
		String driver = "com.mysql.jdbc.Driver";
		String url = Parameter.DB_URL;
	        String password = Parameter.DB_password;
	        String user =  Parameter.DB_user;
//	        String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_memoandfriends";
//	        String url="jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_memoandfriends";
//	        String user =  SaeUserInfo.getAccessKey();
//	        String password = SaeUserInfo.getSecretKey();
	        try{
	        	Class.forName(driver).newInstance();
	        	 conn = DriverManager.getConnection(url, user, password);
	        	 if(!conn.isClosed()) 
	        	          System.out.println("Succeeded connecting to the Database!");

	        }catch(ClassNotFoundException e) {
	                System.out.println("Sorry,can`t find the Driver!"); 
	                e.printStackTrace();

	        } catch (SQLException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        return conn;
	}
	
	public void closeConn(ResultSet rs,Statement stmt,Connection conn){
		try {
			if(rs != null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库的时候发生错误!");
		}
	}
	public void closeConn(Statement stmt,Connection conn){
		try {
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭数据库的时候发生错误!");
		}
	}
	
	public Map<String,String> querySingleData(String sql,List<String> tabNames){
		Map<String,String> map = new HashMap<String,String>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = startConn(connection);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				for(int i = 0; i<tabNames.size(); i++){
					String tabName = (String)tabNames.get(i);
					String buffString = rs.getString(tabName);
					map.put(tabName, buffString);
				}
			}
		} catch (SQLException e) {
			System.out.println("查询数据库的时候发生错误!");
		}finally{
			closeConn(rs, stmt, connection);
		}
		return map;
	}
	
	public List<Map<String, String>> queryMulData(String sql,List<String> tabNames){
		List<Map<String, String>> list = new ArrayList<Map<String,String>>();
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = startConn(connection);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				Map<String,String> map = new HashMap<String,String>();
				for(int i = 0; i<tabNames.size(); i++){
					String tabName = (String)tabNames.get(i);
					String buffString = rs.getString(tabName);
					map.put(tabName, buffString);
				}
				list.add(map);
			}
		} catch (SQLException e) {
			System.out.println("查询数据库的时候发生错误!");
		}finally{
			closeConn(rs, stmt, connection);
		}
		return list;
	}
	
	public int executeUpdate(String sql){
		int lines = 0;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = startConn(conn);
			stmt = conn.createStatement();
			lines = stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("修改，插入或者删除数据库数据时发生错误!");
		}finally{
			closeConn(stmt,conn);
		}
		return lines;
	}
	
	public boolean checkExist(String sql) {
		boolean isExist = false;
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = startConn(conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				isExist = true;
			}
		} catch (SQLException e) {
			//System.out.println("修改，插入或者删除数据库数据时发生错误!");
			return isExist;
		}finally{
			closeConn(stmt,conn);
		}
		return isExist;
	}
	
	public int getCount(String table) {
		int rst = 0;
		String sql  = "select count(*) as count from "+table+";";
		ResultSet rs = null;
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = startConn(conn);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				String count = rs.getString("count");
				rst = Integer.parseInt(count);
				return rst;
			}
		} catch (SQLException e) {
			//System.out.println("修改，插入或者删除数据库数据时发生错误!");
			return rst;
		}finally{
			closeConn(stmt,conn);
		}
		return rst;
	}
	
	public String getOneValue(String sql,String tab) {
		String rst = "";
		Connection connection = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			connection = startConn(connection);
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
			if(rs.next()){
				rst = rs.getString(tab);
			}
		} catch (SQLException e) {
			System.out.println("查询数据库的时候发生错误!");
		}finally{
			closeConn(rs, stmt, connection);
		}
		return rst;
	}
	
}
