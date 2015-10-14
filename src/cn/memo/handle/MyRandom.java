package cn.memo.handle;


import cn.memo.sql.SQLConnection;

public class MyRandom {
	
	private static MyRandom instance;
	static int total = 29;
	static int[] nums;
	static int nnum;
	static int[] stts;
	private  MyRandom(){
		nnum = 0;
		nums = new int[total];
		stts = new int[total];
		for(int i=0;i<total;i++){
			nums[i] = i+1;
			stts[i]=0;
		}
	}
	
	public static MyRandom getInstance() {
		if(instance == null){
			instance =  new MyRandom();
		}
		return instance;
	}
	
	public int getRandomNum(String openid){
		
		SQLConnection connection = new SQLConnection();
		String sql = "select num from myrandom where openid='"+openid+"';";
		if(connection.checkExist(sql)){
			String rst = connection.getOneValue(sql, "num");
			return Integer.parseInt(rst);
		}
		if(nnum >= total){
			return 0;
		}
		int index = (int) (Math.random() * total);
		if(stts[index]==1){
			return getRandomNum(openid);
		}else{
			int rst = nums[index];
			String sql3 = "select num from myrandom where num='"+rst+"';";
			if(connection.checkExist(sql3)){
				return getRandomNum(openid);
			}else{
				stts[index]=1;
				nnum ++;
				String sql2= "insert into myrandom(openid,num) values('"+openid+"','"+rst+"');";
				connection.executeUpdate(sql2);
				return rst;
			}
		}
	}
	
	public void init(int tot){
		total = tot;
		nnum = 0;
		nums = new int[total];
		stts = new int[total];
		for(int i=0;i<total;i++){
			nums[i] = i+1;
			stts[i]=0;
		}
		SQLConnection connection = new SQLConnection();
		String sql = "truncate table myrandom;";
		connection.executeUpdate(sql);
	}
	
	public static void main(String[] args) {
		MyRandom.getInstance().init(60);
		System.out.println(MyRandom.getInstance().getRandomNum("1127"));
	}
}
