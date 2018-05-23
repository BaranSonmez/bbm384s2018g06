package com.sport.support.ClassPackage;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.ClassMemberListPackage.classMemberList;
import com.sport.support.ClassMemberListPackage.classMemberListController;
import com.sport.support.ClassMemberListPackage.classMemberListRepository;
import com.sport.support.FeePackage.fee;

@RestController
@RequestMapping(path="/course")
public class courseController {

	@Autowired
	private courseRepository courseRepository;

	@Autowired
	private com.sport.support.FeePackage.feeController feeController;
	
	@Autowired
	private classMemberListController cMLController;
	
	@Autowired
	private classMemberListRepository cMLRepository;
	
	//all courses without branch selection
	@GetMapping(path="/all")
	public @ResponseBody Iterable<course> getAllMembers() {
		
		return courseRepository.findAll();
	}
	//fee list of a branch -- pull course fee from here (weekly and one-time check)
	@GetMapping(path="/payment/{id}")
	public @ResponseBody fee showCourseFees(@PathVariable(value="id") int id) {
		
		int bi = courseRepository.findDistinctById(id).getBranchId();		
		return feeController.showCourseFee(bi);
	}
	//all courses of a given branch
	@GetMapping(path="/all/{id}")
	public @ResponseBody Iterable<course> showBranchCourses(@PathVariable(value="id") int id) {
		
		return courseRepository.findAllByBranchId(id);
	}
	//enroll to a course id=course_id
	@GetMapping(path="/enroll/{id}")
	public @ResponseBody classMemberList enroll(@PathVariable(value="id") int id, @RequestParam int memberId ){
		
		course n = courseRepository.findDistinctById(id);
		return cMLController.enrollCourse(memberId, n);
	}
	//drop a course, id=course_id
	@GetMapping(path="/drop/{id}")
	public @ResponseBody classMemberList drop(@PathVariable(value="id") int id, @RequestParam int memberId ){
		
		course n = courseRepository.findDistinctById(id);
		return cMLController.dropCourse(memberId, n);
	}
	//show course info
	@GetMapping(path="/get/{id}")
	public @ResponseBody course getOneCourse(@PathVariable(value="id") int id){
		return courseRepository.findDistinctById(id);
	}

	public course getOneCourseHelper(int id){
		return courseRepository.findDistinctById(id);
	}
	//create new course --manager
	@GetMapping(path="/add")
	public @ResponseBody course addNewCourse (@RequestParam String name , @RequestParam int quota, @RequestParam int branchId,
			@RequestParam(required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date startDate, @RequestParam (required=false)@DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, @RequestParam int trainerId, 
			@RequestParam String species, @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date cDate, @RequestParam String description) {
		
		//quota and available quota is the same initially
		course n = new course(name, quota, branchId, startDate, endDate, trainerId, species, cDate , description, quota);
		courseRepository.save(n);
		return n;
	}
	
	//delete course 
	@GetMapping(path="/delete/{id}")
	public @ResponseBody course deleteCourse(@PathVariable(value="id") int id) {
		
		course n = courseRepository.findDistinctById(id);
		
		if (n != null) {
			courseRepository.delete(n);
			cMLRepository.deleteAll(cMLRepository.findAllByCourseId(id));;
			return n;
		}
	    else return null;
	}
	
	
	
}
