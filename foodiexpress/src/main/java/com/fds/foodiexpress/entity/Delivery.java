package com.fds.foodiexpress.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="delivery")
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agentid")
    private int agentid;
    
    @NotNull
	@Size(min = 3, max = 100, message = "Full name must be between 3 and 100 characters")
	@Column(name = "name")
    private String name;
    
    @Column(name="email", unique=true, nullable=false)
	@NotNull
    @Email(message = "Invalid email format")
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@gmail\\.com$", message = "Email must be in @gmail.com format")
    private String email;
    
	@NotNull
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number 1 must be 10 digits")
    @Column(name="phone")
    private String phone;
    
	
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number 1 must be 10 digits")
    @Column(name="altphone")
    private String altPhone;
    
    @NotBlank(message = "Password cannot be empty")
    @Column(name="password")
    private String password;
    
    @NotNull(message = "Date of birth cannot be empty")
   // @Past(message = "Date of birth must be in the past")
    @Column(name="dob")
    private String dob;
    

	@NotNull
    @Pattern(regexp = "^[A-Z]{2}[0-9]{1,2}[A-Z]{1,2}[0-9]{4}$", message = "Invalid vehicle number format")
    @Column(name="vehicle")
    private String vehicle;
    
	@NotNull
    @Column(name="adharcard", nullable=true)
    private String adharcard;
    
	@NotNull
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    @Column(name="address")
    private String address;
    
    @Column(name="flag")
    private String flag = "1";

    // Getters and Setters
    public int getAgentid() { return agentid; }
    public void setAgentid(int agentid) { this.agentid = agentid; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getAltPhone() { return altPhone; }
    public void setAltPhone(String altPhone) { this.altPhone = altPhone; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getDob() { return dob; }
    public void setDob(String dob) { this.dob = dob; }

    public String getAdharcard() { return adharcard; }
    public void setAdharcard(String adharcard) { this.adharcard = adharcard; }

    public String getVehicle() { return vehicle; }
    public void setVehicle(String vehicle) { this.vehicle = vehicle; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getFlag() { return flag; }
    public void setFlag(String flag) { this.flag = flag; }

    @Override
    public String toString() {
        return "Delivery [agentid=" + agentid + ", name=" + name + ", email=" + email + 
               ", phone=" + phone + ", altPhone=" + altPhone + ", dob=" + dob +
               ", vehicle=" + vehicle + ", adharcard=" + adharcard + ", address=" + address +
               ", flag=" + flag + "]";
    }
}
