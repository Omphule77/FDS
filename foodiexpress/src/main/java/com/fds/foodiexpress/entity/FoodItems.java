package com.fds.foodiexpress.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fooditems")
public class FoodItems {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	
	@Column(name = "rs")
	private String rs;
	
	@Column(name = "comment")
	private String comment;
	
	@Column(name = "fimg")
	private String fImg;
	
	@Column(name = "rname")
	private String rName;
	
	public FoodItems(){
		
	}

	
	
	public String getrName() {
		return rName;
	}



	public void setrName(String rName) {
		this.rName = rName;
	}



	public String getfImg() {
		return fImg;
	}


	public void setfImg(String fImg) {
		this.fImg = fImg;
	}


	


	public FoodItems(String name, String type, String rs, String comment, String fImg, String rName) {
		super();
		this.name = name;
		this.type = type;
		this.rs = rs;
		this.comment = comment;
		this.fImg = fImg;
		this.rName = rName;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRs() {
		return rs;
	}

	public void setRs(String rs) {
		this.rs = rs;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}



	@Override
	public String toString() {
		return "FoodItems [id=" + id + ", name=" + name + ", type=" + type + ", rs=" + rs + ", comment=" + comment
				+ ", fImg=" + fImg + ", rName=" + rName + "]";
	}

	
	
}
