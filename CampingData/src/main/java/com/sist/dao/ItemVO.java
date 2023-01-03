package com.sist.dao;
/*
	ino            NUMBER,
	image          VARCHAR2(300) CONSTRAINT item_img_nn_2  NOT NULL,
	name           VARCHAR2(500) CONSTRAINT item_name_nn_2 NOT NULL,
	price          NUMBER  CONSTRAINT item_price_nn_2 NOT NULL,
	description    CLOB   CONSTRAINT item_des_nn_2  NOT NULL,
	stock          NUMBER CONSTRAINT item_stock_nn_2  NOT NULL,
	status         VARCHAR2(20) CONSTRAINT item_status_nn_2 NOT NULL,
	discount       NUMBER  CONSTRAINT item_discount_nn_2 NOT NULL,
	delivery_price NUMBER CONSTRAINT item_dvprice_nn_2  NOT NULL,
	like_cnt       NUMBER CONSTRAINT item_like_nn_2  NOT NULL,
	jjim_cnt       NUMBER  CONSTRAINT item_jjim_nn_2 NOT NULL,
	icno           NUMBER CONSTRAINT item_icno_nn_2  NOT NULL,
	CONSTRAINT item_ino_pk_2 PRIMARY KEY (ino),
    CONSTRAINT item_icno_fk_2 FOREIGN KEY(icno)
	REFERENCES ITEM_CATEGORY_2 (icno)
*/
public class ItemVO {
	private int ino,price,stock,discount,delivery_price,like_cnt,jjim_cnt,icno;
	private String image,name,description,status;
	public int getIno() {
		return ino;
	}
	public void setIno(int ino) {
		this.ino = ino;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getDiscount() {
		return discount;
	}
	public void setDiscount(int discount) {
		this.discount = discount;
	}
	public int getDelivery_price() {
		return delivery_price;
	}
	public void setDelivery_price(int delivery_price) {
		this.delivery_price = delivery_price;
	}
	public int getLike_cnt() {
		return like_cnt;
	}
	public void setLike_cnt(int like_cnt) {
		this.like_cnt = like_cnt;
	}
	public int getJjim_cnt() {
		return jjim_cnt;
	}
	public void setJjim_cnt(int jjim_cnt) {
		this.jjim_cnt = jjim_cnt;
	}
	public int getIcno() {
		return icno;
	}
	public void setIcno(int icno) {
		this.icno = icno;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
