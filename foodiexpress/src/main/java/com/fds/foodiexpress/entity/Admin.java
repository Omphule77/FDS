package com.fds.foodiexpress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@Column(name = "adminid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private String id;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	@Email
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "altphone")
	private String altphone;

	@Column(name = "address")
	private String address;
	@JsonIgnore
	@Column(name = "password")
	private String password;

	public Admin() {
		super();
	}

	public Admin(String name, @Email String email, String address, String phone, String altphone, String password) {
		super();
		this.name = name;
		this.email = email;
		this.address = address;
		this.phone = phone;
		this.altphone = altphone;
		this.password = password;
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

	public String getAddress() {
		return address;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", altphone=" + altphone
				+ ", address=" + address +"]";
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAltphone() {
		return altphone;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setAltphone(String altphone) {
		this.altphone = altphone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
