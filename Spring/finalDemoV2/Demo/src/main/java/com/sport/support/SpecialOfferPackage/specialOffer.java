package com.sport.support.SpecialOfferPackage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class specialOffer {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private int branchId;
	private Date startDate;
	private Date finishDate;
	private int discountAmount;
	private int referenceNumberLimit;
	private int attendanceLimit;//indirim yuzdesi
	
	
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
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public int getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(int discountAmount) {
		this.discountAmount = discountAmount;
	}
	public int getReferenceNumberLimit() {
		return referenceNumberLimit;
	}
	public void setReferenceNumberLimit(int referenceNumberLimit) {
		this.referenceNumberLimit = referenceNumberLimit;
	}
	public int getAttendanceLimit() {
		return attendanceLimit;
	}
	public void setAttendanceLimit(int attendanceLimit) {
		this.attendanceLimit = attendanceLimit;
	}
	specialOffer(){}
	
	public specialOffer(String name, int branchId, Date startDate, Date finishDate, int discountAmount, 
			int referenceNumberLimit, int attendanceLimit) {
		
		this.name = name;
		this.branchId = branchId;
		this.startDate = startDate;
		this.finishDate = finishDate;
		this.discountAmount = discountAmount;
		this.referenceNumberLimit = referenceNumberLimit;
		this.attendanceLimit = attendanceLimit;
	}
	
	

}
