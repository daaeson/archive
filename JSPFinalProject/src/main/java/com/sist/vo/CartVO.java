package com.sist.vo;
import java.io.Serializable;
import java.util.*;
public class CartVO implements Serializable {

	private int bno,gno,account,total_price;
	private String id,buy_ok,dbday;
	private Date regdate;
	private GoodsVO gvo = new GoodsVO(); // 상품 정보가 goodsVO 내에 있기 때문에 여기 있는 정보를 출력
	
	public GoodsVO getGvo() {
		return gvo;
	}
	public void setGvo(GoodsVO gvo) {
		this.gvo = gvo;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getGno() {
		return gno;
	}
	public void setGno(int gno) {
		this.gno = gno;
	}
	public int getAccount() {
		return account;
	}
	public void setAccount(int account) {
		this.account = account;
	}
	public int getTotal_price() {
		return total_price;
	}
	public void setTotal_price(int total_price) {
		this.total_price = total_price;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBuy_ok() {
		return buy_ok;
	}
	public void setBuy_ok(String buy_ok) {
		this.buy_ok = buy_ok;
	}
	public String getDbday() {
		return dbday;
	}
	public void setDbday(String dbday) {
		this.dbday = dbday;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
}
