package com.sport.support.BranchPackage;

public class stats {

	private String branchName;
	private int managerCount;
	private int trainerCount;
	private int standardMemberCount;
	private int goldMemberCount;
	private int platinumMemberCount;
	private int courseStudentCount;
	
	public stats() {	
		// TODO Auto-generated constructor stub
	}
	
	public String getBranchName() {
		return branchName;
	}
	
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	
	public int getStandardMemberCount() {
		return standardMemberCount;
	}
	
	public void setStandardMemberCount(int standardMemberCount) {
		this.standardMemberCount = standardMemberCount;
	}
	
	public int getGoldMemberCount() {
		return goldMemberCount;
	}
	
	public void setGoldMemberCount(int goldMemberCount) {
		this.goldMemberCount = goldMemberCount;
	}
	
	public int getPlatinumMemberCount() {
		return platinumMemberCount;
	}
	
	public void setPlatinumMemberCount(int platinumMemberCount) {
		this.platinumMemberCount = platinumMemberCount;
	}
	
	public int getCourseStudentCount() {
		return courseStudentCount;
	}
	
	public void setCourseStudentCount(int courseStudentCount) {
		this.courseStudentCount = courseStudentCount;
	}

	public int getManagerCount() {
		return managerCount;
	}

	public void setManagerCount(int managerCount) {
		this.managerCount = managerCount;
	}

	public int getTrainerCount() {
		return trainerCount;
	}

	public void setTrainerCount(int trainerCount) {
		this.trainerCount = trainerCount;
	}

	public stats(String branchName, int managerCount, int trainerCount, int standardMemberCount, int goldMemberCount,
			int platinumMemberCount, int courseStudentCount) {
		super();
		this.branchName = branchName;
		this.managerCount = managerCount;
		this.trainerCount = trainerCount;
		this.standardMemberCount = standardMemberCount;
		this.goldMemberCount = goldMemberCount;
		this.platinumMemberCount = platinumMemberCount;
		this.courseStudentCount = courseStudentCount;
	}
	
	
	
	
}
