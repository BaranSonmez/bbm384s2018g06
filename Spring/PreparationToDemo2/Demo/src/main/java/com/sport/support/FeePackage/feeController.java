package com.sport.support.FeePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.MemberPackage.member;



@RestController
@RequestMapping(path="/fee")
public class feeController {

	@Autowired
	private feeRepository feeRepository;
	
	//tum branchlerin fiyat listeleri -- owner kullanabilir
	@GetMapping(path="/all")
	public @ResponseBody Iterable<fee> showAllFees() {
		// This returns a JSON or XML with the users
		return feeRepository.findAll();
	}
	
	//create branchte branch bilgileri girerken kullanilacak
	@GetMapping(path="/add")
	public @ResponseBody fee createFee (@RequestParam int weeklyClass,
			@RequestParam int oneTimeClass,
			@RequestParam int poolMembership,
			@RequestParam int standardMembership,
			@RequestParam int goldMembership,
			@RequestParam int platinumMembership,
			@RequestParam int branchId) {
		
		fee n = new fee(weeklyClass, oneTimeClass, poolMembership, standardMembership, goldMembership, platinumMembership, branchId);
		feeRepository.save(n);
		return n;
	}
	
	//bir branchin membership ve course fiyatlarini gorme --manager
	@GetMapping(path="/get/{id}")
	public @ResponseBody fee showFee(@PathVariable(value="id") int id) {
		return feeRepository.findDistinctByBranchId(id);
	}
	
	//helper
	public @ResponseBody fee showCourseFee(int id) {
		return feeRepository.findDistinctByBranchId(id);
	}
	
	//only for backend usage -not a usecase
	@GetMapping(path="/delete")
	public @ResponseBody void deleteFee() {
		
		feeRepository.deleteAll();
	}
	
	
}
