package com.sport.support.OfferListPackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class offerList {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int offerId;
	private int memberId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	
	offerList(){}
	
	public offerList(int offerId, int memberId) {
	
		this.offerId = offerId;
		this.memberId = memberId;
	}
	
}
