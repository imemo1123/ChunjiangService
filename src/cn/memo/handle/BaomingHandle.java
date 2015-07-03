package cn.memo.handle;

import cn.memo.sql.SQLConnection;

public class BaomingHandle {
	public static  String baoming(String name, String tel, String weichat, String pic,String area){
		SQLConnection conn = new SQLConnection();
		
		String checkSql = "select * from baby where tel='" + tel +"';";
		if(conn.checkExist(checkSql)){
			return "2";
		}
		String registSql = "insert into baby(name,tel,weichat,pic,area) "
						+ "values ('"+name+"','"
								  +tel+"','"
						                  +weichat+"','"
								  +pic+"','"
								  +area+"');";
		System.out.println(registSql);
		int rst = conn.executeUpdate(registSql);
		if(rst > 0)
			return "0";
		else 
			return "1";
	}
}
