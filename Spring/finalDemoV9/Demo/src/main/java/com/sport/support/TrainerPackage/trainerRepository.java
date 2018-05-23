package com.sport.support.TrainerPackage;

import org.springframework.data.repository.CrudRepository;

public interface trainerRepository extends CrudRepository<trainer, Long>{

	trainer findDistinctByUsernameAndPassword(String username, String password);
	trainer findDistinctByUsername(String username);
	trainer findDistinctById(int id);
	Iterable<trainer> findAllByBranchId(int id);
	void deleteAllByBranchId(int id);

}
