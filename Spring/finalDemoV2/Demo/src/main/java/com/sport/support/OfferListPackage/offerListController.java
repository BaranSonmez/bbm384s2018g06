package com.sport.support.OfferListPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.MovePackage.move;



@RestController
@RequestMapping(path="/offerlist")
public class offerListController {
	
	
	@Autowired
	private offerListRepository offerListRepository;
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<offerList> getAllList() {
		// This returns a JSON or XML with the users
		return offerListRepository.findAll();
	}
	
	@GetMapping(path="/add")
	public @ResponseBody offerList addToOfferList(@RequestParam int offerId, @RequestParam int memberId) {
		
		offerList n = new offerList(offerId, memberId);
		offerListRepository.save(n);
		return n;
	}
	
	@GetMapping(path="/check")
	public @ResponseBody boolean checkOfferList(@RequestParam int offerId, @RequestParam int memberId) {
		
		offerList n = offerListRepository.findDistinctByOfferIdAndMemberId(offerId, memberId);
		if(n != null) return true;
		return false;
	}

}
