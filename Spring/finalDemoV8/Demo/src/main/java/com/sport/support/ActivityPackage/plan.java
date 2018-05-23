package com.sport.support.ActivityPackage;

import org.springframework.beans.factory.annotation.Autowired;

import com.sport.support.MemberPackage.memberRepository;
import com.sport.support.MovePackage.move;
import com.sport.support.MovePackage.moveRepository;

public class plan {
	
	
	private String name;
	private int memberId;
	private int sets;
	private boolean status;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getSets() {
		return sets;
	}
	public void setSets(int sets) {
		this.sets = sets;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public plan(){}
	
	public plan(String name, int memberId, int sets, boolean status) {
		this.name = name;
		this.memberId = memberId;
		this.sets = sets;
		this.status = status;
	}
	

	public plan createPlan(int moveId, int memberId, int sets, boolean status ){
		
		if(true) {
			plan newPlan = new plan("selam", memberId, sets, status);
			return newPlan;
		}
		
		return null;
		
	}	

}
