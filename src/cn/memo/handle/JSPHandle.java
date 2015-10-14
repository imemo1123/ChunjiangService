package cn.memo.handle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.catalina.manager.JspHelper;

import cn.memo.sql.SQLConnection;

public class JSPHandle {
	public String getAreaSelect(String docid){
		SQLConnection conn = new SQLConnection();
		
		String sqlString = "select id,name from area;";
		ArrayList<Map<String,String>> list = new ArrayList<Map<String,String>>();
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("name");
		list = (ArrayList<Map<String, String>>) conn.queryMulData(sqlString, tabList);
		String rstString = "<select id="+docid+" class='myselect'>";
		for(Map<String,String> m: list){
			String id = m.get("id");
			String name = m.get("name");
			rstString +="<option value='"+id+"'>"+name+"</option>";
		}
		rstString += "</select>";
		return rstString;
	}
	
	public String nvl(String v,String w) {
		if(v==null || v.length()<=0){
			return w;
		}else {
			return v;
		}
	}
	public String nvl(String v) {
		if(v==null){
			return "";
		}else {
			return v;
		}
	}
	public String getDateFmt(String dateString,String rfmt, String fmt) {
		Date date =new Date();
		try {
			date = new SimpleDateFormat(rfmt).parse(dateString);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		SimpleDateFormat df = new SimpleDateFormat(fmt);//设置日期格式
		return df.format(date);// new Date()为获取当前系统时间
	}
	
}	
