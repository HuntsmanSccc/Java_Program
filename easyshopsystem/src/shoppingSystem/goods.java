package shoppingSystem;

public class goods {
	String name;
	int num;
	double price;
	int goodsId;
	public goods(String goodsName,int goodsNumber,double goodsPrice,int goodsIdNum){
		name = goodsName;//货物名称
		num = goodsNumber;//货物数量
		price = goodsPrice;//货物价格
		goodsId = goodsIdNum;//货物对应ID
	}
}
