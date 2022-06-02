package shoppingSystem;

import java.util.Scanner;

public class menu {
	/**
	 * 主菜单显示界面
	 */
	public static void mainMenu(){
		System.out.println("*********沃德卡普购物系统*********");
		System.out.println("1.用户管理");
		System.out.println("2.会员管理");
		System.out.println("3.商品管理");
		System.out.println("4.购物车管理");
		System.out.println("*********请输入对应子菜单序号******");
	}
	/**
	 * 用户管理子菜单显示界面
	 */
	public static void userinfosubMenu(){
		System.out.println("*********沃德卡普购物系统(用户管理)*********");
		System.out.println("1.用户注册");
		System.out.println("2.用户登录");
		System.out.println("*********请输入对应操作序号******");
	}
	/**
	 * 用户管理子菜单显示界面
	 */
	public static void vipinfosubMenu(){
		System.out.println("*********沃德卡普购物系统(会员管理)*********");
		System.out.println("1.会员添加");
		System.out.println("2.会员信息更改");
		System.out.println("*********请输入对应操作序号******");
	}
	/**
	 * 商品管理子菜单显示界面
	 */
	public static void goodsSubMenu(){
		System.out.println("*********沃德卡普购物系统(商品管理)*********");
		System.out.println("1.商品信息录入");
		System.out.println("2.商品信息更改");
		System.out.println("3.商品信息查询");
		System.out.println("*********请输入对应操作序号******");
	}
	/**
	 * 购物车子菜单显示界面
	 */
	public static void shoppingcartSubMenu(){
		System.out.println("*********沃德卡普购物系统(购物车)*********");
		System.out.println("1.购物车商品数量叠加");
		System.out.println("2.购物车商品信息删除");
		System.out.println("*********请输入对应功能序号******");
	}
	
	public static int choiceMenu(int choice){
		Scanner sc = new Scanner(System.in);//接收用户要进入的菜单
		while(true){//直到输入正确的序号退出循环
			try{
				choice = Integer.parseInt(sc.nextLine().trim()) ;//要求用户输入为数据				
				break;
			}
			catch(java.lang.NumberFormatException e){
				System.out.println("输入不合法，请重新输入选择的序号:");
			}
		}
		return choice;
	}
}
