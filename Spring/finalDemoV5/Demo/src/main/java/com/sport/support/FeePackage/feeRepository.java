package com.sport.support.FeePackage;

import org.springframework.data.repository.CrudRepository;

public interface feeRepository extends CrudRepository <fee, Long> {
	
	fee findDistinctByBranchId(int id);
}
