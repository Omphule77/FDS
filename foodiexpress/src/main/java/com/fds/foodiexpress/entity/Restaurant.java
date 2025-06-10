package com.fds.foodiexpress.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "restaurant")
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rid")
	private int rId;

	@NotNull
	@Size(min = 3, max = 100, message = "Restaurant name must be between 3 and 100 characters")
	@Column(name = "rname")
	private String rName;

	@NotNull
	@Column(name = "password")
	private String password;

	@NotNull
	@Size(min = 5, max = 255, message = "Address must be between 4 and 255 characters")
	@Column(name = "address")
	private String address;

	@NotNull
	@Size(min = 2, max = 50, message = "City must be between 2 and 50 characters")
	@Column(name = "city")
	private String city;

	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Restaurant phone number must be 10 digits")
	@Column(name = "rphone")
	private String rPhone;

	@NotNull
	@Column(name = "gstid")
	private String gstId;

	@NotNull
	@Size(min = 3, max = 100, message = "Owner name must be between 3 and 100 characters")
	@Column(name = "oname")
	private String oName;

	@NotNull
	@Email(message = "Invalid email format")
	@Column(name = "oemail")
	private String oEmail;

	
	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Owner phone number 2 must be 10 digits")
	@Column(name = "ophone")
	private String oPhone;

	@Pattern(regexp = "^[0-9]{10}$", message = "Restaurant phone number must be 10 digits")
	@Column(name = "oaltphone")
	private String oAltPhone;

	@NotNull(message = "Opening time cannot be empty")
	@Column(name = "otime")
	private String oTime;

	@NotNull(message = "Opening time cannot be empty")
	@Column(name = "ctime")
	private String cTime;

	@NotBlank(message = "Holiday must be selected")
	@Column(name = "holiday")
	private String holiday;

	@Column(name = "flag")
	private String flag;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "restroid")
	private List<FoodItems> foodItems;

	public List<FoodItems> getFoodItems() {
		return foodItems;
	}

	public void setFoodItems(List<FoodItems> foodItems) {
		this.foodItems = foodItems;
	}

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

	public void add(FoodItems fi) {
		if (foodItems == null) {
			foodItems = new ArrayList<FoodItems>();
		}
		foodItems.add(fi);
	}

}
