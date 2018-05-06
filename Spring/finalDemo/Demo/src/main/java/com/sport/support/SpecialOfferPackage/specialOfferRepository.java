package com.sport.support.SpecialOfferPackage;

import org.springframework.data.repository.CrudRepository;

public interface specialOfferRepository extends CrudRepository<specialOffer, Long>{
	
	void deleteAllByBranchId(int id);
	Iterable<specialOffer> findAllByBranchId(int id);
	specialOffer findDistinctById(int id);

}
