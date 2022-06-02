package shoppingSystem;

import java.util.ArrayList;

public class Person { //普通用户类
	String loginname;//登录账号
	String password;//登录密码
	String name;//名字
	int age;//年龄
	int id;//用户ID,用于唯一区分
	boolean islogin;//判断是否属于会员
	boolean isVIP;//判定是否登录(登录后才可查看购物车)
	goods owngoods[];
	ArrayList<goods> owngoods2 =new ArrayList<goods>();
	public Person(String userName, int userAge,String loginName,String passWord,int userId) {//析构函数，进行信息初始化
		name = userName;
		age = userAge;
		loginname = loginName;
		password = passWord;
		id = userId;
		isVIP = false;
		islogin = false;
	}
	/**
	 * 增加到购物车，购买
	 * @param goodsname 购买货物名称
	 * @param goodsNum	购买货物数量
	 * @return
	 */
	public void buygoods(String buygoodName,int buygoodsNum,int goodsId,ArrayList<goods> NowHavegoods){ 
		int shopgoodId = retshoppinggoodId(this.owngoods2,buygoodName);	
		if(buygoodsNum>NowHavegoods.get(goodsId).num){
			System.out.print("库存数量不足\n");	
		}
		else{
			if(shopgoodId>=0){//购物车已经有,直接添加数量
				this.owngoods2.get(shopgoodId).num = this.owngoods2.get(shopgoodId).num + buygoodsNum;
			}
			if(shopgoodId==-1){ //购物车没有,需要创建货物对象
				goods newshopgoods = new goods(buygoodName,buygoodsNum,NowHavegoods.get(goodsId).price,goodsId);
				this.owngoods2.add(newshopgoods);
			}	
			NowHavegoods.get(goodsId).num = NowHavegoods.get(goodsId).num - buygoodsNum;//库存数量对应减少
			System.out.print("已购买成功!\n");
		}
	}
	/**
	 * 删除购物车货物
	 * @param goodsname 删除的货物名称
	 * @param goodsNum	删除的货物数量
	 * @return
	 */
	public void delgoods(String retgoodName,int retgoodsNum,int goodsId,ArrayList<goods> NowHavegoods){ //删除
		int shopgoodId = retshoppinggoodId(this.owngoods2,retgoodName);	
		if(shopgoodId>=0){//购物车已经有,直接剪掉数量
			if(retgoodsNum<=this.owngoods2.get(shopgoodId).num){
			this.owngoods2.get(shopgoodId).num = this.owngoods2.get(shopgoodId).num - retgoodsNum;
			NowHavegoods.get(goodsId).num = NowHavegoods.get(goodsId).num + retgoodsNum;//库存数量对应增加
			}
			else{
				System.out.print("购物车当前物品没有这么多\n");
			}
		}
		else{ //购物车没有,不进行退
			System.out.print("购物车当前没有该物品,请检查\n");
		}	
		
		
	}
	/**
	 * 修改用户基本信息
	 */
	private boolean changeBaseInfo(String newName,String newPassword,int newAge){ 
		if(islogin){//判定是否登录
			name = newName;
			password = newPassword;
			age = newAge;
			return true;
		}
		else{
			System.out.println("请先登录");
			return false;
		}
	}
	
	public void shoppingcart(){  //购物车商品信息		
			if(owngoods2.size()==0){
				System.out.printf("%s,您好，您的购物车现在空荡荡的什么也没有\n",name);
			}
			else{
				System.out.printf("%s,您好，您的购物车现有如下货物\n",name);
			}
			for(int i=0;i<owngoods2.size();i++){			
				System.out.printf("%d 货物名称:%s 货物数量:%d\n",i+1,owngoods2.get(i).name,owngoods2.get(i).num);
			}

	}
	public static int retshoppinggoodId(ArrayList<goods> sglist,String goodsName){
		for(int i=0;i<sglist.size();i++){
			if(goodsName.equals(String.valueOf(sglist.get(i).name))) 
			{
				return i;
			}
		}
		return -1;
	}
	
}
