package com.sport.support.MovePackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class move {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;

	
	
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
	
	public move(){}
	
	public move(String name){
		this.name=name;
	}

}
