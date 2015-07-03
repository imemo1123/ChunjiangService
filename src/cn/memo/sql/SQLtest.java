package cn.memo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.sina.sae.util.SaeUserInfo;

public class SQLtest {
	public void conn() throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
	        String url = "jdbc:mysql://r.rdc.sae.sina.com.cn:3307/app_memoandfriends";
		String username=SaeUserInfo.getAccessKey();
		String password=SaeUserInfo.getSecretKey();
		String driver="com.mysql.jdbc.Driver";
		Class.forName(driver).newInstance();
		Connection con=DriverManager.getConnection(url,username,password);

		
	}
	
	public static void main(String[] args) {
		try {
			new SQLtest().conn();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
