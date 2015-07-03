package cn.memo.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import cn.memo.handle.UserHandle;
import cn.memo.instance.User;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class JsonHandle {
	public static <T> T getObject(String jsonStr,Class<T> cls){
		T t =null;
		try{
			Gson gson = new Gson();
			t = gson.fromJson(jsonStr, cls);
		}catch (Exception e){
			e.printStackTrace();
		}
		return t;
	}
	
	public static <T> List<T> getObjList(String jsonStr, Class<T> cls){
		ArrayList<T> listOfT = new ArrayList<T>();
		try{ 
			LinkedList<JsonObject> jsonObjs = new Gson().fromJson(jsonStr, new TypeToken<List<JsonObject>>(){}.getType()); 
			for (JsonObject jsonObj : jsonObjs) {
				listOfT.add(new Gson().fromJson(jsonObj, cls));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return listOfT;
	}
	
	public static List<String> getList(String jsonString) {  
		List<String> list = new ArrayList<String>();  
		Gson gson = new Gson();  
		list = gson.fromJson(jsonString, new TypeToken<List<String>>(){}.getType());  
		return list;  
	}  

	
	public static Map<String,String> getMap(String jsonString){  
		Map<String,String> map =new HashMap<String, String>();
		Gson gson = new Gson();
		map = gson.fromJson(jsonString, new TypeToken<Map<String,String>>(){}.getType());
		return map;
	}
	
	public static List<Map<String,String>> listKeyMap(String jsonString){
		List<Map<String,String>> list=new ArrayList<Map<String,String>>();  
		try {  
			Gson gson = new Gson();  
			list = gson.fromJson(jsonString, new TypeToken<List<Map<String,String>>>(){}.getType());
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public static String getValue(String jsonString,String key){
		String value = "";
		JsonParser jsonparer = new JsonParser();
		if(jsonparer.parse(jsonString).getAsJsonObject().has(key)){
			value = jsonparer.parse(jsonString).getAsJsonObject().get(key).getAsString();
		}
		return value;
	}
	
	public static String toJson(Object obj){
		Gson gson = new Gson();
		return gson.toJson(obj);
	}
	
	public static void  main(String[] args) {
		String jsonString = "{\"password\":\"123456\",\"mobile\":\"15210617670\"}";
		User user = JsonHandle.getObject(jsonString, new User().getClass());
		UserHandle userHandle = new UserHandle(user);
		System.out.print(userHandle.signIn());
	}
}

