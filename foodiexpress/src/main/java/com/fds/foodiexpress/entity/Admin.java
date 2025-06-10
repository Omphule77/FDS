package com.fds.foodiexpress.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "admin")
public class Admin {
	@Id
	@Column(name = "adminid")
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int id;

	@Column(name = "name")
	@NotBlank(message = "Name cannot be empty")
	@Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
	private String name;

	@Column(name = "email")
	@NotBlank(message = "Email cannot be empty")
	@Email(message = "Please provide a valid email address")
	@Size(max = 255, message = "Email cannot exceed 255 characters")
	private String email;

	@Column(name = "phone")
	 @NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String phone;

	@Column(name = "altphone")
	 @NotBlank(message = "Phone number cannot be empty")
	@Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be 10 digits")
	private String altphone;

	@Column(name = "address")
	@NotBlank(message = "Address cannot be empty")
    @Size(min = 5, max = 500, message = "Address must be between 5 and 500 characters")
	private String address;
	
	@JsonIgnore
	@Column(name = "password")
	@NotBlank(message = "Password cannot be empty")
    @Size(min = 8, message = "Password must be at least 8 characters long")
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
				+ ", address=" + address + "]";
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
