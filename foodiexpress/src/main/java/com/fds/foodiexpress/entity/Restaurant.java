package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="rid")
	private int rId;
	
	@Column(name="rname")
	private String rName;
	
	@Column(name = "password")
	private String password;
	
	@Column(name="address")
	private String address;
	
	@Column(name="city")
	private String city;
	
	@Column(name = "rphone")
	private String rPhone;
	
	@Column(name="gstid")
	private String gstId;
	
	@Column(name = "oname")
	private String oName;
	
	@Column(name = "oemail")
	private String oEmail;
	
	@Column(name = "ophone")
	private String oPhone;
	
	@Column(name = "oaltphone")
	private String oAltPhone;
	
	@Column(name = "otime")
	private String oTime;
	
	@Column(name = "ctime")
	private String cTime;
	
	@Column(name = "holiday")
	private String holiday;
	
	@Column(name = "flag")
	private String flag;
	
	public Restaurant() {
		
	}

	

	public Restaurant(int rId, String rName, String password, String address, String city, String rPhone, String gstId,
			String oName, String oEmail, String oPhone, String oAltPhone, String oTime, String cTime, String holiday,
			String flag) {
		super();
		this.rId = rId;
		this.rName = rName;
		this.password = password;
		this.address = address;
		this.city = city;
		this.rPhone = rPhone;
		this.gstId = gstId;
		this.oName = oName;
		this.oEmail = oEmail;
		this.oPhone = oPhone;
		this.oAltPhone = oAltPhone;
		this.oTime = oTime;
		this.cTime = cTime;
		this.holiday = holiday;
		this.flag = flag;
	}



	public int getrId() {
		return rId;
	}

	public void setrId(int rId) {
		this.rId = rId;
	}

	public String getrName() {
		return rName;
	}

	public void setrName(String rName) {
		this.rName = rName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getrPhone() {
		return rPhone;
	}

	public void setrPhone(String rPhone) {
		this.rPhone = rPhone;
	}

	public String getGstId() {
		return gstId;
	}

	public void setGstId(String gstId) {
		this.gstId = gstId;
	}

	public String getoName() {
		return oName;
	}

	public void setoName(String oName) {
		this.oName = oName;
	}

	public String getoEmail() {
		return oEmail;
	}

	public void setoEmail(String oEmail) {
		this.oEmail = oEmail;
	}

	public String getoPhone() {
		return oPhone;
	}

	public void setoPhone(String oPhone) {
		this.oPhone = oPhone;
	}

	public String getoAltPhone() {
		return oAltPhone;
	}

	public void setoAltPhone(String oAltPhone) {
		this.oAltPhone = oAltPhone;
	}

	public String getoTime() {
		return oTime;
	}

	public void setoTime(String oTime) {
		this.oTime = oTime;
	}

	public String getcTime() {
		return cTime;
	}

	public void setcTime(String cTime) {
		this.cTime = cTime;
	}

	public String getHoliday() {
		return holiday;
	}

	public void setHoliday(String holiday) {
		this.holiday = holiday;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Restaurant [rId=" + rId + ", rName=" + rName + ", password=" + password + ", address=" + address
				+ ", city=" + city + ", rPhone=" + rPhone + ", gstId=" + gstId + ", oName=" + oName + ", oEmail="
				+ oEmail + ", oPhone=" + oPhone + ", oAltPhone=" + oAltPhone + ", oTime=" + oTime + ", cTime=" + cTime
				+ ", holiday=" + holiday + ", flag=" + flag + "]";
	}
	
	
}
