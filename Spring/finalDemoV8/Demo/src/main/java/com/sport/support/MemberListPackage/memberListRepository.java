package com.sport.support.MemberListPackage;

import org.springframework.data.repository.CrudRepository;


public interface memberListRepository extends CrudRepository<memberList, Long> {
	
	Iterable<memberList> findAllByMemberId(int id);
	memberList findDistinctByMemberId(int id);
	memberList findDistinctById(int id);
	void deleteAllByMemberId(int id);
	Iterable<memberList> findAllByBranchId(int id);
	void deleteAllByBranchId(int id);
	Iterable<memberList> findAllByTrainerId(int id);
	void deleteByTrainerId(int id);

}
