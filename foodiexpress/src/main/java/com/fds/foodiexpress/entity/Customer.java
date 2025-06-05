package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "customer")
public class Customer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "customer_Id")
	private int cId;

	@NotNull(message = "*required")
	@Column(name = "name")
	private String name;

	@NotNull(message = "*required")
	@Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must be in @gmail.com format")
	@Column(name = "email")
	private String email;

	@NotNull(message = "*required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
	@Column(name = "phone")
	private String phone;

	@NotNull(message = "*required")
	@Pattern(regexp = "\\d{10}", message = "Phone number must be exactly 10 digits")
	@Column(name = "altphone")
	private String altPhone;

	@NotNull(message = "*required")
	@Column(name = "address")
	private String address;

//	@Pattern(
//	        regexp = "^(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{6,}$",
//	        message = "Password must be at least 6 characters long, include one uppercase letter, one number, and one special character"
//	    )
	@NotNull(message = "*required")
	@Column(name = "password")
	private String password;

	public Customer() {

	}

	public Customer(String name, String email, String phone, String altPhone, String address, String password) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.altPhone = altPhone;
		this.address = address;
		this.password = password;
	}

	public int getcId() {
		return cId;
	}

	public void setcId(int cId) {
		this.cId = cId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAltPhone() {
		return altPhone;
	}

	public void setAltPhone(String altPhone) {
		this.altPhone = altPhone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Customer [cId=" + cId + ", name=" + name + ", email=" + email + ", phone=" + phone + ", altPhone="
				+ altPhone + ", address=" + address + ", password=" + password + "]";
	}

}
