package com.sport.support.ClassPackage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/course")
public class courseController {

	@Autowired
	private courseRepository courseRepository;

	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<course> getAllMembers() {
		// This returns a JSON or XML with the users
		return courseRepository.findAll();
	}
	
	
	@GetMapping(path="/add")
	public @ResponseBody course addNewCourse (@RequestParam String name , @RequestParam int quota, @RequestParam int branchId,
			@RequestParam(required=false) Date startDate, @RequestParam(required=false) Date endDate, @RequestParam int trainerId, 
			@RequestParam String species, @RequestParam(required=false) Date currentDate, @RequestParam String description, @RequestParam int availableQuota) {
		
		course n = new course(name, quota, branchId, null, null, trainerId, species, null, description, availableQuota);
		courseRepository.save(n);
		return n;

	}
}
