package shoppingSystem;
import java.util.ArrayList;//用于集合
import java.util.Arrays;
import java.util.List; //用于集合
import java.util.Scanner; 

/**
 * 错误写法：
 * static Person persons[];
 * Person p = new Person();
 * Persons[index] = p;
 * @author 33007
 *
 */
public class main {
	static Admin admin = new Admin("管理员",50,"admin","admin",0);
	static int choice = 0;//默认选择序号为0,表示未进行任何选择(主菜单选择)
	static int subchoice = 0;//默认选择序号为0,表示未进行任何选择(子菜单选择)
	static int usernum = 0;//统计注册用户数量
	static goods goodsHave[] = new goods[3];//存储现有物品
	static ArrayList<goods> goodsHave2 = new ArrayList<goods>();
	static Person persons[] = new Person[3]; //默认系统可注册三个人
	static ArrayList<Person> persons2 = new ArrayList<Person>();

	public static void main(String[] args){
		int loginId = -1;
		boolean isnotquit = true; 
		while(isnotquit){
			shoppingSystem.menu.mainMenu();//显示主菜单
			choice = shoppingSystem.menu.choiceMenu(choice);//选择菜单
			switch(choice){
				case 1:{
					shoppingSystem.menu.userinfosubMenu();
					subchoice = shoppingSystem.menu.choiceMenu(subchoice);
					switch(subchoice){
						case 1:{
							if(addUser(usernum,persons2)){
								System.out.print("用户已成功添加\n");
							}
							break;
						}
						case 2:{	
							loginId = loginUser(persons2);
							if(loginId>=0){
								System.out.print("用户已成功登录\n");
							}
							else{
								System.out.print("账号或密码错误\n");
							}
						}
						default:{
							break;
						}
					}
					break;
				}
				case 2:{
					shoppingSystem.menu.vipinfosubMenu();
					subchoice = shoppingSystem.menu.choiceMenu(subchoice);
					switch(subchoice){
						case 1:{
							if(loginId==0){
								System.out.print("请输入用户名:");
								Scanner Nsc = new Scanner(System.in);
								String name = Nsc.next().trim();						
								int userid = retUserId(persons2,name);
								if(userid>0){
									admin.addVIP(persons2,userid);
								}
								else{
									System.out.print("未查到该用户,添加会员失败\n");
								}
							
							}
							else{
								System.out.print("您不是管理员无法进行操作\n");
							}
							break;
						}
						case 2:{
							if(loginId==0){
								System.out.print("请输入用户名");
								Scanner Nsc = new Scanner(System.in);
								String name = Nsc.next().trim();						
								int userid = retUserId(persons2,name);
								if(userid>0){
									System.out.print("请输入新密码\n");
									String newPassword = Nsc.next().trim();
									System.out.print("请输入新年龄\n");
									int newAge = Nsc.nextInt();
									admin.changeInfo(persons2,userid, newPassword, newAge);
									System.out.print("该用户成功修改\n");
								}
								else{
									System.out.print("未查到该用户,添加会员失败\n");
								}
							}
							else{
								System.out.print("您不是管理员无法进行操作\n");
							}
						}
						default:{						
							break;
						}
					}
					break;
					}				
				case 3:{
					shoppingSystem.menu.goodsSubMenu();
					subchoice = shoppingSystem.menu.choiceMenu(subchoice);
					switch(subchoice){
						case 1:{
							if(loginId==0){
								Scanner Nsc = new Scanner(System.in);
								System.out.print("请输入新增货物名称:");							
								String newgoodsName = Nsc.next().trim();	
								System.out.print("请输入新增货物数量:");
								int newgoodsNum = Nsc.nextInt();
								System.out.print("请输入新增货物价格:");
								double newgoodPrice = Nsc.nextFloat();
								int goodsid = retgoodId(goodsHave2,newgoodsName);								
								if(goodsid>=-1){						
									admin.addgoodstoHave(goodsHave2,newgoodsName,newgoodsNum,newgoodPrice,goodsid);
								}					
							}
							else{
								System.out.print("您不是管理员无法进行操作\n");
							}
							break;
						}
						case 2:{
							if(loginId==0){
								Scanner Nsc = new Scanner(System.in);
								System.out.print("请输入删除货物名称:");							
								String newgoodsName = Nsc.next().trim();	
								System.out.print("请输入删除货物数量:");
								int newgoodsNum = Nsc.nextInt();
								int goodsid = retgoodId(goodsHave2,newgoodsName);								
								if(goodsid>=0){						
									admin.delgoodstoHave(goodsHave2,newgoodsName,newgoodsNum,goodsid);
								}
								else if(goodsid==-1){
									System.out.print("现有物品不存在要删除的物品");
								}
							}
							else{
								System.out.print("您不是管理员无法进行操作\n");
							}
							break;
						}
						case 3:{
							admin.selectgoodstoHave(goodsHave2);
						}
						default:{					
							break;
						}
					}
					break;
					}
				case 4:{
					shoppingSystem.menu.shoppingcartSubMenu();
					subchoice = shoppingSystem.menu.choiceMenu(subchoice);
					switch(subchoice){
						case 1:{
							if(loginId==0){
								System.out.print("请使用普通用户查看购物车\n");
							}
							else if(loginId<0){
								System.out.print("请登录\n");
							}
							else{
								admin.selectgoodstoHave(goodsHave2);
								shoppingcartAuthlogin(loginId,persons2,goodsHave2,1);							
								
							}						
							break;
						} 
						case 2:{
							shoppingcartAuthlogin(loginId,persons2,goodsHave2,2);
							admin.selectgoodstoHave(goodsHave2);
							break;
						}
						default:{					
							break;
						}
					}
					break;
					}
				default:
					System.out.print("欢迎下次光临!\n");
					isnotquit = false;
					break;
			}
		}
	}
	public static boolean addUser(int userNum,ArrayList<Person> persons){ //增加用户(用户注册)
		Scanner nsc = new Scanner(System.in);
		try{			
			System.out.print("请输入用户名:");
			String userName = nsc.next().trim();
			System.out.print("请输入年龄:");
			int userAge = nsc.nextInt();
			System.out.print("请输入登录账号:");
			String loginName;
			while(true){
				boolean isnotequal = true;
			    loginName = nsc.next().trim();				   
				for(int i = 1 ; i<=userNum;i++){		
					if(loginName.equals(String.valueOf(persons.get(i-1).loginname))){ //判断是否有相同登录账号						
						isnotequal = false;
						System.out.print("登录账号重复,请重新设置:");
						break;
					}
				}
				if(isnotequal){
					break;
				}
			}			
			System.out.print("请输入密码:");
			String passWord = nsc.next().trim();
			Person p = new Person(userName,userAge,loginName,passWord,usernum+1);				
			persons.add(p);
		}
		catch(Exception e)
		{
			return false;
		}
		usernum = usernum + 1;
		return true;
	}
	
	public static int loginUser(ArrayList<Person> persons){ //用户登录
		Scanner nsc = new Scanner(System.in);
		System.out.print("请输入账号:");
		String loginName = nsc.next().trim();
		System.out.print("请输入密码:");
		String passWord = nsc.next().trim();	
		return authLoginUser(loginName,passWord,persons);
	}
	public static int authLoginUser(String loginName,String passWord,ArrayList<Person> persons){ //用户登录验证
		boolean isfind = false;
		int loginid = -1;
		if(loginName.equals("admin") && passWord.equals("admin")){		
			System.out.print("管理员欢迎您\n");
			return 0;
		}
		for(int i = 0;i<persons.size();i++)
		{
			try{
				if(loginName.equals(String.valueOf(persons.get(i).loginname)) && passWord.equals(String.valueOf(persons.get(i).password)))
				{				
					isfind = true;//找到并进行标记
					persons.get(i).islogin = true;//登录状态更新
					loginid = i+1;
					continue;
				}
				else{			
					persons.get(i).islogin = false;//将前面登录过的用户进行注销
					if(i == persons.size()&&isfind==false)
					{
						System.out.println("该用户不存在");		
						return -1;
					}				
				}
			}
			catch(Exception e){
				return loginid;
			}
		}
		return loginid;
	}
	/**
	 * 
	 * @param personId 通过确定id判断对应登录人员为谁
	 * @param p	所有注册人员
	 * @param op 1购物车数量叠加 2.购物车商品信息删除
	 */
	public static void shoppingcartAuthlogin(int personId,ArrayList<Person> p,ArrayList<goods> gNowhavelist,int op){ 
			p.get(personId-1).shoppingcart();//显示购物车已有物品			
			Scanner bsc = new Scanner(System.in);
			if(op==1){		
				System.out.print("请输入需要购物的物品名称:");
				String buygoodName = bsc.next().trim();
				System.out.print("请输入需要购物的物品数量:");				
				int buygoodNum = bsc.nextInt();
				int goodsid = retgoodId(goodsHave2,buygoodName);
				if(goodsid>=0){
					p.get(personId-1).buygoods(buygoodName,buygoodNum,goodsid,gNowhavelist);				
				}
				else{
					System.out.print("需要购物的物品现有库存不存在\n");
				}
				
			}
			if(op==2){
				System.out.print("请输入需要删除购物车的物品名称:");
				String buygoodName = bsc.next().trim();
				int goodsid = retgoodId(goodsHave2,buygoodName);
				System.out.print("请输入需要删除购物车的物品数量:");				
				int buygoodNum = bsc.nextInt();
				p.get(personId-1).delgoods(buygoodName, buygoodNum,goodsid,gNowhavelist);			
			}
	}
	public static int retUserId(ArrayList<Person> plist,String pName){
		for(int i=0;i<plist.size();i++){
			if(pName.equals(String.valueOf(plist.get(i).name)))
			{
				return i+1;
			}
		}
		return -1;
	}
	public static int retgoodId(ArrayList<goods> glist,String goodsName){
		for(int i=0;i<glist.size();i++){
			if(goodsName.equals(String.valueOf(glist.get(i).name))) 
			{
				return i;
			}
		}
		return -1;
	}
}

