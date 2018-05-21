package com.sport.support.ClassMemberListPackage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.ClassPackage.course;
import com.sport.support.ClassPackage.courseController;


@RestController
@RequestMapping(path="/enrolledcourses")
public class classMemberListController {
	
	@Autowired
	private classMemberListRepository classMemberListRepository;
	
	@Autowired
	private courseController cController;
	
	//all enrolled courses with every members' attendance
	@GetMapping(path="/all")
	public @ResponseBody Iterable<classMemberList> getAllClassesMembers() {
		// This returns a JSON or XML with the users
		return classMemberListRepository.findAll();
	}
	
	//helper function
	//if already enrolled return null
	public classMemberList enrollCourse(int memberId, course course) {
		
		ArrayList<classMemberList> previousList = (ArrayList<classMemberList>) classMemberListRepository.findAllByCourseIdAndMemberId(course.getId(),memberId);
		if (previousList.size()!=0) return null;
		
		classMemberList n = new classMemberList(course.getId(), memberId, 0);
		
		if(course.getAvailableQuota()>0) {
			
			course.setAvailableQuota(course.getAvailableQuota()-1);
			classMemberListRepository.save(n);
		}
		
		return n;
	}
	//helper function
	public classMemberList dropCourse(int memberId, course course) {
			
		classMemberList n = classMemberListRepository.findDistinctByCourseIdAndMemberId(course.getId(), memberId);
		course.setAvailableQuota(course.getAvailableQuota() + 1);
		classMemberListRepository.delete(n);
		return n;
	}
	//all courses of a member--my courses button
	@GetMapping(path="/all/my/{id}")
	public @ResponseBody Iterable<course> getAllMyClasses(@PathVariable(value="id") int id) {
		ArrayList<course> courseList = new ArrayList<course>();
		ArrayList<classMemberList> rawlist = (ArrayList<classMemberList>) classMemberListRepository.findAllByMemberId(id);
		if (rawlist.size()==0) return null;
		for (classMemberList cml : rawlist) {
			courseList.add(cController.getOneCourseHelper(cml.getCourseId()));
		}
		if (courseList.size() != 0) return courseList;
		else return null;
	}
	
	@GetMapping(path="/is/enrolled")
	public @ResponseBody boolean isEnrolled(@RequestParam int memberId, @RequestParam int courseId) {
		
		classMemberList n = classMemberListRepository.findDistinctByCourseIdAndMemberId(courseId, memberId);
		if(n == null) return false;	
		return true;
	}
	
	
	

}
