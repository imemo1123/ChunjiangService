package cn.memo.instance;

import java.util.ArrayList;
import java.util.List;


public class News {
	private String title,content,picture;
	private int id;
	public static List<String> getDetailTabNames(){
		 List<String> tabNames;
		tabNames = new ArrayList<String>();
		tabNames.add("id");
		tabNames.add("title");
		tabNames.add("content");
		tabNames.add("picture");
		return tabNames;
	}
	
	public static List<String> getTabNames(){
		 List<String> tabNames;
		tabNames = new ArrayList<String>();
		tabNames.add("id");
		tabNames.add("title");
		tabNames.add("picture");
		return tabNames;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContext(String content) {
		this.content = content;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
