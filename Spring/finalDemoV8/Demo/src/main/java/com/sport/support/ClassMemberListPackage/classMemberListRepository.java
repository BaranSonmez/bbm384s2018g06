package com.sport.support.ClassMemberListPackage;

import org.springframework.data.repository.CrudRepository;

public interface classMemberListRepository extends CrudRepository<classMemberList, Long>{

	Iterable<classMemberList> findAllByMemberId(int id);
	classMemberList findDistinctByCourseIdAndMemberId(int courseId, int memberId);
	Iterable<classMemberList> findAllByCourseIdAndMemberId(int courseId, int memberId);
	Iterable<classMemberList> findAllByCourseId(int courseId);
	void deleteAllByMemberId(int id);
	void deleteAllByCourseId(int id);
	
}
