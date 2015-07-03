package cn.memo.handle;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.memo.instance.*;
import cn.memo.sql.SQLConnection;
public class UserHandle {
	private User user;
	public UserHandle(User _user){
		user = _user;
	}
	
	public void decode() {
		try {
			this.user.setName(URLDecoder.decode(this.user.getName(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean signIn() {
		String mobile = user.getMobile();
		String password = user.getPassword();
		SQLConnection conn = new SQLConnection();
		String signSql = "select * from user where mobile='" + mobile + "' and password='" + password +"';";	
		return conn.checkExist(signSql);
	}
	
	public User getUserInfo(){
		String id = user.getId();
		SQLConnection conn = new SQLConnection();
		String signSql = "select id,name,password,mobile,type,salesman from user where id='" + id + "';";
		Map map = conn.querySingleData(signSql, User.getTabNames());
		user.setId((String)map.get("id"));
		user.setMobile((String)map.get("mobile"));
		user.setName((String)map.get("name"));
		user.setType((String)map.get("type"));
		user.setSalesman((String)map.get("salesman"));
		user.setPassword((String)map.get("password"));
		return user;
	}
	
	public User getUserInfoByMobile(){
		String mobile = user.getMobile();
		SQLConnection conn = new SQLConnection();
		String signSql = "select id,name,password,mobile,type,salesman from user where mobile='" + mobile + "';";
		Map map = conn.querySingleData(signSql, User.getTabNames());
		user.setId((String)map.get("id"));
		user.setMobile((String)map.get("mobile"));
		user.setName((String)map.get("name"));
		user.setType((String)map.get("type"));
		user.setSalesman((String)map.get("salesman"));
		user.setPassword((String)map.get("password"));
		return user;
	}
	
	public String regist(){
		SQLConnection conn = new SQLConnection();
		String mobile = user.getMobile();
		String checkSql = "select * from user where mobile='" + mobile +"';";
		if(conn.checkExist(checkSql)){
			return "2";
		}
		String registSql = "insert into user(name,password,mobile,type,salesman) "
						+ "values ('"+user.getName()+"','"
								  +user.getPassword()+"','"
						                  +user.getMobile()+"','"
								  +MyHandle.nvl(user.getType(),"2")+"','"
								  +user.getSalesman()+"');";
		int rst = conn.executeUpdate(registSql);
		if(rst > 0)
			return "0";
		else 
			return "1";
	}
	
	public static List<Map<String, String>> getSalesManList(){
		String sqlString = "select id,name from user where type='1'";
		List<String> paramList  = new ArrayList<String>();
		paramList.add("id");
		paramList.add("name");
		SQLConnection conn = new SQLConnection();
		List<Map<String, String>> rstList = conn.queryMulData(sqlString, paramList);
		return rstList;
		
	}
	
	public static List<Map<String, String>> getCustomerList(String id){
		String sqlString = "select id,name,mobile from user where type='2' and salesman='"+id+"';";
		List<String> paramList  = new ArrayList<String>();
		paramList.add("id");
		paramList.add("name");
		paramList.add("mobile");
		SQLConnection conn = new SQLConnection();
		List<Map<String, String>> rstList = conn.queryMulData(sqlString, paramList);
		return rstList;
	}
}
