package cn.memo.instance;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	public static List<String>getTabNames() {
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("model_id");
		tabList.add("des");
		tabList.add("pic_id");
		tabList.add("state");
		tabList.add("reserve_time");
		tabList.add("user_name");
		tabList.add("mobile");
		return tabList;
	}
	
	public static List<String>getTabNames2() {
		List<String> tabList = new ArrayList<String>();
		tabList.add("id");
		tabList.add("model_id");
		tabList.add("des");
		tabList.add("pic_id");
		tabList.add("state");
		tabList.add("reserve_time");
		return tabList;
	}
	
	public static List<String>getColTabName(){
		List<String> tabList = new ArrayList<String>();
		tabList.add("model_id");
		tabList.add("des");
		tabList.add("pic_id");
		return tabList;
	}
}
