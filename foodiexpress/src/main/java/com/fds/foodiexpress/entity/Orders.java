package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private int orderId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "quantity")
	private String quantity;
	
	@Column(name = "price")
	private String price;
	
	@Column(name = "rname")
	private String rName;
	
	@Column(name="cname")
	private String cName;
	
	@Column(name = "caddress")
	private String cAddress;
	
	@Column(name = "cphone")
	private String cPhone;
	
	@Column(name = "caltphone")
	private String cAltPhone;
	
	@Column(name = "flag")
	private String flag;
	
	@Column(name = "raddress")
	private String rAddress;
	
	@Column(name = "cemail")
	private String cEmail;
	
	@Column(name = "fimg")
	private String fImg;
	
	@Column(name = "demail")
	private String dEmail;
	
	
	public Orders() {
		
	}

	public String getfImg() {
		return fImg;
	}

	public void setfImg(String fImg) {
		this.fImg = fImg;
	}
	

	public Orders(String name, String comment, String quantity, String price, String rName, String cName,
			String cAddress, String cPhone, String cAltPhone, String flag, String rAddress, String cEmail, String fImg,
			String dEmail) {
		super();
		this.name = name;
		this.comment = comment;
		this.quantity = quantity;
		this.price = price;
		this.rName = rName;
		this.cName = cName;
		this.cAddress = cAddress;
		this.cPhone = cPhone;
		this.cAltPhone = cAltPhone;
		this.flag = flag;
		this.rAddress = rAddress;
		this.cEmail = cEmail;
		this.fImg = fImg;
		this.dEmail = dEmail;
	}
	
	

	public String getdEmail() {
		return dEmail;
	}

	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}

	public String getcEmail() {
		return cEmail;
	}



	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}



	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getcName() {
		return cName;
	}

	public void setcName(String cName) {
		this.cName = cName;
	}

	public String getcAddress() {
		return cAddress;
	}

	public void setcAddress(String cAddress) {
		this.cAddress = cAddress;
	}

	public String getcPhone() {
		return cPhone;
	}

	public void setcPhone(String cPhone) {
		this.cPhone = cPhone;
	}

	public String getcAltPhone() {
		return cAltPhone;
	}

	public void setcAltPhone(String cAltPhone) {
		this.cAltPhone = cAltPhone;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getrAddress() {
		return rAddress;
	}

	public void setrAddress(String rAddress) {
		this.rAddress = rAddress;
	}








	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", name=" + name + ", comment=" + comment + ", quantity=" + quantity
				+ ", price=" + price + ", rName=" + rName + ", cName=" + cName + ", cAddress=" + cAddress + ", cPhone="
				+ cPhone + ", cAltPhone=" + cAltPhone + ", flag=" + flag + ", rAddress=" + rAddress + ", cEmail="
				+ cEmail + ", fImg=" + fImg + "]";
	}



	

	
	
}
