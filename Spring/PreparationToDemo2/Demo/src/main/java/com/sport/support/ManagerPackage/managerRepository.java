package com.sport.support.ManagerPackage;

import org.springframework.data.repository.CrudRepository;

public interface managerRepository extends CrudRepository<manager, Long>{

	manager findDistinctById(int id);
	manager findDistinctByUsernameAndPassword(String username, String password);
}
