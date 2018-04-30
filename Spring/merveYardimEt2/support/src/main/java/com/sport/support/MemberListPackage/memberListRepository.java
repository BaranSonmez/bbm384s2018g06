package com.sport.support.MemberListPackage;

import org.springframework.data.repository.CrudRepository;

import com.sport.support.MemberPackage.member;


public interface memberListRepository extends CrudRepository<memberList, Long> {
	
	
	memberList findDistinctByMemberId(int id);

}
