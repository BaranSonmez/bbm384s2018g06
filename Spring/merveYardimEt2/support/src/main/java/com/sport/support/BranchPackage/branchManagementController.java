package com.sport.support.BranchPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/branch")
public class branchManagementController {
	
	@Autowired
	private branchRepository branchRepository;
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<branch> getAllBranch() {
		// This returns a JSON or XML with the users
		return branchRepository.findAll();
	}
	
	@GetMapping(path="/add")
	public @ResponseBody branch addBranch (@RequestParam String name , @RequestParam int quota, @RequestParam long telephoneNumber, @RequestParam String city, 
			@RequestParam String district, @RequestParam String address) {
		
		
		branch n = new branch(name, quota, telephoneNumber, city, district, address);
		branchRepository.save(n);
		return n;

	}

}
