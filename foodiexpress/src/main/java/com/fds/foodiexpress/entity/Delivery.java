package com.fds.foodiexpress.entity;

import jakarta.persistence.*;

@Entity
@Table(name="delivery")
public class Delivery {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="agentid")
    private int agentid;
    
    @Column(name="name")
    private String name;
    
    @Column(name="email", unique=true, nullable=false)
    private String email;
    
    @Column(name="phone")
    private String phone;
    
    @Column(name="altphone")
    private String altPhone;
    
    @Column(name="password")
    private String password;
    
    @Column(name="dob")
    private String dob;
    
    @Column(name="vehicle")
    private String vehicle;
    
    @Column(name="adharcard", nullable=true)
    private String adharcard;
    
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
