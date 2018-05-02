
package com.sport.support.ActivityPackage;

import org.springframework.data.repository.CrudRepository;

public interface activityPlanRepository extends CrudRepository<activityPlan, Long> {

	Iterable<activityPlan> findAllByMemberId(int id);
	activityPlan findDistinctByMemberIdAndMoveId(int memberId, int moveId);
	void deleteAllByMemberId(int id);
}
