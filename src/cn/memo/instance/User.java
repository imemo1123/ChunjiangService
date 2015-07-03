package cn.memo.instance;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String name;
	private String id;
	private String password;
	private String salesman;
	private String mobile;
	private String type;
	
	public static List<String> getTabNames(){
		 List<String> tabNames;
		tabNames = new ArrayList<String>();
		tabNames.add("id");
		tabNames.add("name");
		tabNames.add("password");
		tabNames.add("mobile");
		tabNames.add("type");
		tabNames.add("salesman");
		return tabNames;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getSalesman() {
		return salesman;
	}
	public void setSalesman(String salesman) {
		this.salesman = salesman;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
