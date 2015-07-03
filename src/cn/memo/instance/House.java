package cn.memo.instance;

import java.util.ArrayList;
import java.util.List;

public class House {
	private String id,des,building,detail,pic_id;
	private int bedroom,bathroom,livingroom,size,reservation,collection,left_num;
	
	public static List<String>getTabNames() {
		List<String> list = new ArrayList<String>();
		list.add("id");
		list.add("des");
		list.add("pic_id");
		return list;
	}
	public static List<String>getDetailTabNames() {
		List<String> list = new ArrayList<String>();
		list.add("id");
		list.add("des");
		list.add("bedroom");
		list.add("bathroom");
		list.add("livingroom");
		list.add("size");
		list.add("building");
		list.add("detail");
		list.add("reservation");
		list.add("collection");
		list.add("left_num");
		list.add("pic_id");
		return list;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String desc) {
		this.des = desc;
	}
	public String getBuilding() {
		return building;
	}
	public void setBuilding(String building) {
		this.building = building;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getPic_id() {
		return pic_id;
	}
	public void setPic_id(String pic_id) {
		this.pic_id = pic_id;
	}
	public int getBedroom() {
		return bedroom;
	}
	public void setBedroom(int bedroom) {
		this.bedroom = bedroom;
	}
	public int getBathroom() {
		return bathroom;
	}
	public void setBathroom(int bathroom) {
		this.bathroom = bathroom;
	}
	public int getLivingroom() {
		return livingroom;
	}
	public void setLivingroom(int livingroom) {
		this.livingroom = livingroom;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getReservation() {
		return reservation;
	}
	public void setReservation(int reservation) {
		this.reservation = reservation;
	}
	public int getCollection() {
		return collection;
	}
	public void setCollection(int collection) {
		this.collection = collection;
	}
	public int getLeft_num() {
		return left_num;
	}
	public void setLeft_num(int left) {
		this.left_num = left;
	}
}
