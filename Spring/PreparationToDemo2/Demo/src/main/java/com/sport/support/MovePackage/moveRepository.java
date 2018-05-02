package com.sport.support.MovePackage;

import org.springframework.data.repository.CrudRepository;

public interface moveRepository extends CrudRepository<move, Long> {

	move findDistinctById(int id);
	move findDistincyByName(String name);
}
