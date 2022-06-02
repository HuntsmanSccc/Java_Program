package shoppingSystem;

import java.util.ArrayList;

public class Admin extends Person{	//判定是否是管理员(管理员才可以进行商品信息录入)
	String loginName;
	String password;
	String userName;
	int age;
	int userId;
	public Admin(String userName, int userAge, String loginName, String passWord, int userId) {
		super(userName, userAge, loginName, passWord, userId);
		userName = "管理员";
		loginName = "admin";
		password = "admin";
		age = 50;
		userId = 0;
	}
	public void changeInfo(ArrayList<Person> p,int pid,String newPassword,int newAge){ //修改信息
		p.get(pid-1).password = newPassword;
		p.get(pid-1).age = newAge;
	}
	public void addVIP(ArrayList<Person> p,int pid){

		p.get(pid-1).isVIP = true;
		System.out.print("会员添加成功");
	}
	public void addgoodstoHave(ArrayList<goods> glist,String goodsName,int goodsNumber,double goodsPrice,int gid){		
			if(gid==-1){//物品不存在
				goods ngoods = new goods(goodsName,goodsNumber,goodsPrice,gid);//创建新的物品
				glist.add(ngoods) ;
			}
			else{
				glist.get(gid).num = glist.get(gid).num + goodsNumber;
			}
			System.out.print("货物添加成功\n");
	}
	public void delgoodstoHave(ArrayList<goods> glist,String goodsName,int goodsNumber,int gid){	
			if(goodsNumber>glist.get(gid).num){
				System.out.print("现有该物品小于删除数量\n");
			}
			else{
				glist.get(gid).num = glist.get(gid).num - goodsNumber;
				System.out.print("删除成功\n");
			}		
	}
	public void selectgoodstoHave(ArrayList<goods> nowgoodslist){
		int sortId ;
		for(int i = 0;i<nowgoodslist.size();i++){	
			//sortId= Integer.parseInt(String.valueOf(nowgoodslist.get(i).goodsId)) +1;
			System.out.printf("%d:物品名:%s,现有数量%d,价格为:%f\n",i+1,nowgoodslist.get(i).name,nowgoodslist.get(i).num,nowgoodslist.get(i).price);		
		}
	}
}