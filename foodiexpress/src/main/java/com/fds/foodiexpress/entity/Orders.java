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
<<<<<<< Updated upstream
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
	
	
	
	public Orders() {
		
	}

	

	public Orders(String name, String comment, String quantity, String price, String rName, String cName,
			String cAddress, String cPhone, String cAltPhone, String flag, String rAddress, String cEmail) {
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
=======
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private int orderId;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;
    
    @Column(name = "quantity", nullable = false)
    private String quantity;
    
    @Column(name = "price", nullable = false)
    private String price;
    
    @Column(name = "rname", nullable = false)
    private String rName;
    
    @Column(name="cname", nullable = false)
    private String cName;
    
    @Column(name = "caddress", nullable = false)
    private String cAddress;
    
    @Column(name = "cphone", nullable = false)
    private String cPhone;
    
    @Column(name = "caltphone")
    private String cAltPhone;
    
    @Column(name = "flag", nullable = false)
    private String flag;
    
    @Column(name = "raddress", nullable = false)
    private String rAddress;
    
    // Default constructor
    public Orders() {
    }

    // Constructor with all fields
    public Orders(int orderId, String name, String comment, String quantity, String price, String rName, String cName,
            String cAddress, String cPhone, String cAltPhone, String flag, String rAddress) {
        this.orderId = orderId;
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
    }

    // Constructor without optional fields
    public Orders(String name, String quantity, String price, String rName, String cName, String cAddress,
            String cPhone, String flag, String rAddress) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.rName = rName;
        this.cName = cName;
        this.cAddress = cAddress;
        this.cPhone = cPhone;
        this.flag = flag;
        this.rAddress = rAddress;
    }
>>>>>>> Stashed changes

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

<<<<<<< Updated upstream


	@Override
	public String toString() {
		return "Orders [orderId=" + orderId + ", name=" + name + ", comment=" + comment + ", quantity=" + quantity
				+ ", price=" + price + ", rName=" + rName + ", cName=" + cName + ", cAddress=" + cAddress + ", cPhone="
				+ cPhone + ", cAltPhone=" + cAltPhone + ", flag=" + flag + ", rAddress=" + rAddress + ", cEmail="
				+ cEmail + "]";
	}

	
	
=======
    public void setrAddress(String rAddress) {
        this.rAddress = rAddress;
    }

    @Override
    public String toString() {
        return "Orders [orderId=" + orderId + ", name=" + name + ", comment=" + comment + ", quantity=" + quantity
                + ", price=" + price + ", rName=" + rName + ", cName=" + cName + ", cAddress=" + cAddress + ", cPhone="
                + cPhone + ", cAltPhone=" + cAltPhone + ", flag=" + flag + ", rAddress=" + rAddress + "]";
    }
>>>>>>> Stashed changes
}
