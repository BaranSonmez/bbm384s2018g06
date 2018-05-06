package com.sport.support.MemberPackage;

/**
 * This class is repository of member table.
 * Last Update : 11/04/2018
 */

import org.springframework.data.repository.CrudRepository;

public interface memberRepository extends  CrudRepository<member, Long>{
	
    member findDistinctByUsernameAndPassword(String username, String password);
    member findDistinctByUsername(String username);
    member findDistinctById(int id);

}
