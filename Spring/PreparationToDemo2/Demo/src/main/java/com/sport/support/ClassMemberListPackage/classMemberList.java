package com.sport.support.ClassMemberListPackage;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class classMemberList {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	private int courseId;
	private int memberId;
	private int attendance;
	
	
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getAttendance() {
		return attendance;
	}
	public void setAttendance(int attendance) {
		this.attendance = attendance;
	}
	
	public classMemberList(int courseId, int memberId, int attendance) {
		this.courseId = courseId;
		this.memberId = memberId;
		this.attendance = attendance;
	}
	
	public classMemberList() {
	}
	
}
