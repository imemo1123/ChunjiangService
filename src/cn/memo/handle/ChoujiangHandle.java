package cn.memo.handle;

import java.nio.channels.SelectableChannel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import cn.memo.sql.SQLConnection;

public class ChoujiangHandle {
	public static String choujiang(String openid ) {
		SQLConnection connection = new SQLConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date= df.format(new Date());// new Date()为获取当前系统时间
		String sqlString = "select * from choujiang_flow where openid='"+openid+"' and date='"+date+"';";
		if(connection.checkExist(sqlString) && !openid.equals("1124")){
			String sqlString2 = "select rst,jiangping,pic,code from choujiang_flow left join choujiang on choujiang.id = choujiang_flow.rst where openid='"+openid+"' and choujiang_flow.date='"+date+"' ";
			ArrayList<String> tabNames = new ArrayList<String>();
			tabNames.add("rst");
			tabNames.add("jiangping");
			tabNames.add("pic");
			tabNames.add("code");
			Map<String,String> m = connection.querySingleData(sqlString2, tabNames);
			String id = m.get("rst");
			if(id.equals("0"))
				return "-1|0";
			else{
				String code = m.get("code");
				String sqlString3 = "select * from zhongjiang where code='"+code+"';";
				return "-1|"+id+"|" + m.get("pic")+"|"+m.get("jiangping")+"|"+code+"|"+connection.checkExist(sqlString3);
			}
		}else{
			sqlString = "select id,jiangping,chance,pic,num,date,dnum,intro from choujiang where enddate > '" + date+"'  order by id;" ;
			System.out.println(sqlString);
			List<Map<String, String>> rst = new ArrayList<Map<String,String>>();
			ArrayList<String> tabList = new ArrayList<String>();
			tabList.add("id");
			tabList.add("jiangping");
			tabList.add("chance");
			tabList.add("pic");
			tabList.add("num");
			tabList.add("date");
			tabList.add("dnum");
			tabList.add("intro");
			rst = connection.queryMulData(sqlString, tabList);
			for(Map<String, String>m : rst){
				String idString = m.get("id");
				String dsql = "select count(*) as count from choujiang_flow where rst='"+idString+"' and date='"+date+"';";
				System.out.println(dsql);
				String dString = connection.getOneValue(dsql, "count");
				int dnum = Integer.parseInt(m.get("dnum"));
				int nnum = Integer.parseInt(dString);
				String cString = m.get("chance");
				float change = Float.parseFloat(cString);
				String tdate = m.get("date");
				int num = Integer.parseInt(m.get("num"));
				if( num>0 && ("1123".equals(tdate) || nnum < dnum) ){
					if (Math.random()<=change) {
						String pic = m.get("pic");
						String id = m.get("id");
						String jiangping = m.get("jiangping");
						String intro = m.get("intro");
						String code = (int) (Math.random() * 100000000) +""+ connection.getCount("zhongjiang");
						String addString = "insert into choujiang_flow(openid,rst,date,code) values ('"+openid+"','"+id+"','"+date+"','"+code+"');";
						connection.executeUpdate(addString);
						return id+"|" + pic+"|"+jiangping+"|"+intro+"|"+code;
					}
				}
			}
			String addString = "insert into choujiang_flow(openid,rst,date) values ('"+openid+"','0',+'"+date+"');";
			connection.executeUpdate(addString);
			return "0";
		}
	}
	public static int zhongjiang(String openid,String id,String tel,String code) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");//设置日期格式
		String date= df.format(new Date());// new Date()为获取当前系统时间
		SQLConnection connection = new SQLConnection();
		String checksql = "select * from choujiang_flow where openid='" +openid+"' and rst ='"+id+"' and code='"+code+"';";
		System.out.println(checksql);
		if(connection.checkExist(checksql)){
			String updatesql = "update choujiang set num=num-1 where id='"+id+"';";
			System.out.println(updatesql);
			connection.executeUpdate(updatesql);
			
			String insertsql = "insert into zhongjiang(openid,tel,id,date,code) values('"+openid+"','"+tel+"','"+id+"','"+date+"','"+code+"');";
			System.out.println(insertsql);
			connection.executeUpdate(insertsql);
			return 0;
		}
		return -1;
	}
}
