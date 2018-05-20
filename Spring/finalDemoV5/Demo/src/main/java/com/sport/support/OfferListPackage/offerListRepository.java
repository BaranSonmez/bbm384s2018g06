package com.sport.support.OfferListPackage;

import org.springframework.data.repository.CrudRepository;

public interface offerListRepository extends CrudRepository <offerList, Long> {
	
	offerList findDistinctByOfferId(int id);
	offerList findDistinctByMemberId(int id);
	offerList findDistinctById(int id);
	offerList findDistinctByOfferIdAndMemberId(int offerId, int memberId);
	Iterable<offerList> findAllByMemberId(int id);
	void deleteAllByOfferId(int id);
	void deleteAllByMemberId(int id);
}
