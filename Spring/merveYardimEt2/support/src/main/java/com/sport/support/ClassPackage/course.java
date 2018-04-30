package com.sport.support.ClassPackage;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name ="class", schema="test2")
public class course {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private String name;
	private int quota;
	private int branchId;
	private Date startDate; 
	private Date endDate;
	private int trainerId;
	private String species;
	private Date currentDate;
	private String description;
	private int availableQuota;
	

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
	public int getQuota() {
		return quota;
	}
	public void setQuota(int quota) {
		this.quota = quota;
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
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(int trainerId) {
		this.trainerId = trainerId;
	}
	public String getSpecies() {
		return species;
	}
	public void setSpecies(String species) {
		this.species = species;
	}
	public Date getCurrentDate() {
		return currentDate;
	}
	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAvailableQuota() {
		return availableQuota;
	}
	public void setAvailableQuota(int availableQuota) {
		this.availableQuota = availableQuota;
	}
	
	public course(){}
	public course(String name, int quota, int branchId, Date startDate, Date endDate, int trainerId,
			String species, Date currentDate, String description, int availableQuota) {
		
		this.name = name;
		this.quota = quota;
		this.branchId = branchId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.trainerId = trainerId;
		this.species = species;
		this.currentDate = currentDate;
		this.description = description;
		this.availableQuota = availableQuota;
	}
	
	

}
