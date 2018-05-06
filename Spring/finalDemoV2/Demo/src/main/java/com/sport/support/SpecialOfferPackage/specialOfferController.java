package com.sport.support.SpecialOfferPackage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.OfferListPackage.offerList;
import com.sport.support.OfferListPackage.offerListController;
import com.sport.support.OfferListPackage.offerListRepository;


@RestController
@RequestMapping(path="/offer")
public class specialOfferController {

	@Autowired
	private specialOfferRepository specialOfferRepository;
	
	@Autowired
	private offerListRepository offerListRepository;
	
	@Autowired
	private offerListController offerListController;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<specialOffer> getAllOffers() {
		// This returns a JSON or XML with the users
		return specialOfferRepository.findAll();
	}
	
	@GetMapping(path="/all/branch/{id}")
	public @ResponseBody Iterable<specialOffer> getAllBranchOffers(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		return specialOfferRepository.findAllByBranchId(id);
	}
	
	@GetMapping(path="/get/{id}")
	public @ResponseBody specialOffer getOffer(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		return specialOfferRepository.findDistinctById(id);
	}
	
	@GetMapping(path="/add")
	public @ResponseBody specialOffer addOffer(@RequestParam String name, @RequestParam int branchId, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date finishDate, @RequestParam int discountAmount,
			@RequestParam int referenceNumberLimit, @RequestParam int attendanceLimit) {
		// This returns a JSON or XML with the users
		
		if(discountAmount < 100 && discountAmount > 0) {
			specialOffer n = new specialOffer(name, branchId, startDate, finishDate, discountAmount, referenceNumberLimit, attendanceLimit);
			specialOfferRepository.save(n);
			return n;
		}
		
		return null;
	}
	
	@GetMapping(path="/delete/{id}")
	public @ResponseBody specialOffer deleteOffer(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		
		specialOffer n = specialOfferRepository.findDistinctById(id);
		
		if(n!=null) {
			specialOfferRepository.delete(n);
			return n;
		}
		
		return null;
	}
	
	@GetMapping(path="/apply")
	public @ResponseBody offerList applyOffer(@RequestParam int offerId, @RequestParam int memberId) {
		
		offerList n = offerListController.addToOfferList(offerId, memberId);
		return n;
		// This returns a JSON or XML with the users
	}
	
	
}
