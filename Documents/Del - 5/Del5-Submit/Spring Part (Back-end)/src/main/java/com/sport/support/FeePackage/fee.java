package com.sport.support.FeePackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class fee {

	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int weeklyClass;
	private int oneTimeClass;
	private int poolMembership;
	private int standardMembership;
	private int goldMembership;
	private int platinumMembership;
	private int branchId;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getWeeklyClass() {
		return weeklyClass;
	}
	public void setWeeklyClass(int weeklyClass) {
		this.weeklyClass = weeklyClass;
	}
	public int getOneTimeClass() {
		return oneTimeClass;
	}
	public void setOneTimeClass(int oneTimeClass) {
		this.oneTimeClass = oneTimeClass;
	}
	public int getPoolMembership() {
		return poolMembership;
	}
	public void setPoolMembership(int poolMembership) {
		this.poolMembership = poolMembership;
	}
	public int getStandardMembership() {
		return standardMembership;
	}
	public void setStandardMembership(int standardMembership) {
		this.standardMembership = standardMembership;
	}
	public int getGoldMembership() {
		return goldMembership;
	}
	public void setGoldMembership(int goldMembership) {
		this.goldMembership = goldMembership;
	}
	public int getPlatinumMembership() {
		return platinumMembership;
	}
	public void setPlatinumMembership(int platinumMembership) {
		this.platinumMembership = platinumMembership;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	
	public fee(){}
	
	fee(int weeklyclass, int oneTimeClass, int poolMembership, int standardMembership, int goldMembership,
			int platinumMembership, int branchId) {
		this.weeklyClass = weeklyclass;
		this.oneTimeClass = oneTimeClass;
		this.poolMembership = poolMembership;
		this.standardMembership = standardMembership;
		this.goldMembership = goldMembership;
		this.platinumMembership = platinumMembership;
		this.branchId = branchId;
	}
	
}
