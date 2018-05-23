package com.sport.support.ManagerPackage;

import org.springframework.data.repository.CrudRepository;

public interface managerRepository extends CrudRepository<manager, Long>{

	manager findDistinctById(int id);
	manager findDistinctByUsername(String username);
	manager findDistinctByUsernameAndPassword(String username, String password);
	Iterable<manager> findAllByBranchId(int id);
	void deleteAllByBranchId(int id);
}
