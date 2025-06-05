package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "feedback")
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "f_id")
	private int fId;
	
	@Column(name = "food_quality")
	private String foodQuality;
	
	@Column(name="delivery_quality")
	private String deliveryQuality;
	
	@Column(name = "app_exp")
	private String appExp;
	
	@Column(name = "cemail")
	private String cEmail;
	
	@Column(name = "demail")
	private String dEmail;
	
	public Feedback() {
		
	}


	public Feedback(String foodQuality, String deliveryQuality, String appExp, String cEmail, String dEmail) {
		super();
		this.foodQuality = foodQuality;
		this.deliveryQuality = deliveryQuality;
		this.appExp = appExp;
		this.cEmail = cEmail;
		this.dEmail = dEmail;
	}

	

	public String getdEmail() {
		return dEmail;
	}


	public void setdEmail(String dEmail) {
		this.dEmail = dEmail;
	}


	public int getfId() {
		return fId;
	}

	public void setfId(int fId) {
		this.fId = fId;
	}

	public String getFoodQuality() {
		return foodQuality;
	}

	public void setFoodQuality(String foodQuality) {
		this.foodQuality = foodQuality;
	}

	public String getDeliveryQuality() {
		return deliveryQuality;
	}

	public void setDeliveryQuality(String deliveryQuality) {
		this.deliveryQuality = deliveryQuality;
	}

	public String getAppExp() {
		return appExp;
	}

	public void setAppExp(String appExp) {
		this.appExp = appExp;
	}

	public String getcEmail() {
		return cEmail;
	}

	public void setcEmail(String cEmail) {
		this.cEmail = cEmail;
	}


	@Override
	public String toString() {
		return "Feedback [fId=" + fId + ", foodQuality=" + foodQuality + ", deliveryQuality=" + deliveryQuality
				+ ", appExp=" + appExp + ", cEmail=" + cEmail + ", dEmail=" + dEmail + "]";
	}

	
	
	
}
