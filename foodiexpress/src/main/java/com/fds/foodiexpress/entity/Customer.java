package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

	@NotNull	
	@Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
	@Column(name = "name")
	private String name;

	@NotNull
	@Email(message = "Invalid email format")
	@Column(name = "email")
	private String email;

	@NotNull
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number 1 must be 10 digits")
	@Column(name = "phone")
	private String phone;

	//@Pattern(regexp = "^[0-9]{10}$", message = "Alternate Phone number must be 10 digits")
	
	@Column(name = "altphone")
	private String altPhone;

	@NotNull
	@Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
	@Column(name = "address")
	private String address;


	@NotNull
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
