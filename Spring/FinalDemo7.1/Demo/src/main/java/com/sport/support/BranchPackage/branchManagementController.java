package com.sport.support.BranchPackage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.ActivityPackage.activityPlanRepository;
import com.sport.support.ClassMemberListPackage.classMemberList;
import com.sport.support.ClassMemberListPackage.classMemberListRepository;
import com.sport.support.ClassPackage.courseRepository;
import com.sport.support.ManagerPackage.manager;
import com.sport.support.ManagerPackage.managerRepository;
import com.sport.support.MemberListPackage.memberList;
import com.sport.support.MemberListPackage.memberListRepository;
import com.sport.support.MemberPackage.member;
import com.sport.support.MemberPackage.memberRepository;
import com.sport.support.SpecialOfferPackage.specialOfferRepository;
import com.sport.support.TrainerPackage.trainer;
import com.sport.support.TrainerPackage.trainerRepository;


@RestController
@RequestMapping(path="/branch")
public class branchManagementController {
	
	@Autowired
	private branchRepository branchRepository;
	
	@Autowired
	private memberListRepository memberListRepository;
	
	@Autowired
	private classMemberListRepository classMemberListRepository;
	
	@Autowired
	private trainerRepository trainerRepository;
	
	@Autowired
	private managerRepository managerRepository;
	
	@Autowired
	private courseRepository courseRepository;
	
	@Autowired
	private specialOfferRepository specialOfferRepository;
	
	@Autowired
	private activityPlanRepository activityPlanRepository;
	
	@Autowired
	private memberRepository memberRepository;
	
	//information of all branches, returning branch list
	@GetMapping(path="/all")
	public @ResponseBody Iterable<branch> getAllBranch() {
		// This returns a JSON or XML with the users
		return branchRepository.findAll();
	}
	
	//create new branch
	@GetMapping(path="/add")
	public @ResponseBody branch addBranch (@RequestParam String name , @RequestParam int quota, @RequestParam long telephoneNumber, @RequestParam String city, 
			@RequestParam String district, @RequestParam String address) {
		
		branch n = new branch(name, quota, telephoneNumber, city, district, address);
		branchRepository.save(n);
		return n;
	}
	
	@GetMapping(path="/get/{id}")
	public @ResponseBody branch getBranch(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		branch n = branchRepository.findDistinctById(id);
		if(n != null) return n;
		return null;
	}
	

	public String getBranchName(int id) {
		// This returns a JSON or XML with the users
		branch n = branchRepository.findDistinctById(id);
		if(n != null) return n.getName();
		return null;
	}
	
	
	//update branch, info requires branch id
	@GetMapping(path="/update/info")
	public @ResponseBody branch updateBranchInfo(@RequestParam int id, @RequestParam (required=false) String newname, @RequestParam (required=false, defaultValue="0") int newquota,
			@RequestParam(required=false, defaultValue="0") long newphoneNumber,@RequestParam(required=false) String newcity,
			@RequestParam(required=false) String newdistrict, @RequestParam(required=false) String newaddress
			) {
		
		branch n;
		n = branchRepository.findDistinctById(id);
		
		if (n != null) {
			
			if(newname != null) n.setName(newname);
			if(newquota != 0) n.setQuota(newquota);
			if(newphoneNumber!= 0) n.setPhoneNumber(newphoneNumber);
			if(newcity != null) n.setCity(newcity);
			if(newdistrict != null) n.setDistrict(newdistrict);
			if(newaddress != null) n.setAddress(newaddress);	
			branchRepository.save(n);
			return n;
		}
		else return null;
	}
	
	//close branch, requires branch id
	@GetMapping(path="/delete/{id}")
	public @ResponseBody branch deleteBranch(@PathVariable(value="id") int id) {
		
		branch n = branchRepository.findDistinctById(id);
		
		if (n != null) {
			ArrayList<memberList> memList = (ArrayList<memberList>) memberListRepository.findAllByBranchId(n.getId());
			
			for(int i = 0; i < memList.size(); i++) {
				member mem = memberRepository.findDistinctById(memList.get(i).getMemberId());
				mem.setStatue("inactive");
				mem.setStatus("none");
				activityPlanRepository.deleteAllByMemberId(memList.get(i).getMemberId());
				classMemberListRepository.deleteAllByMemberId(memList.get(i).getMemberId());
			}
			
			managerRepository.deleteAllByBranchId(n.getId());
			trainerRepository.deleteAllByBranchId(n.getId());
			courseRepository.deleteAllByBranchId(n.getId());
			specialOfferRepository.deleteAllByBranchId(n.getId());
			memberListRepository.deleteAllByBranchId(n.getId());
			branchRepository.delete(n);
			
			return n;
		}
	    else return null;
	}
	
	@GetMapping(path="/stat/{id}")
	public @ResponseBody stats getBranchStats(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		
		int standardMemberCount = 0;
		int goldMemberCount = 0;
		int platinumMemberCount = 0;
		int courseStudentCount = 0;
		int trainerCount = 0;
		int managerCount = 0;
		
		branch n = branchRepository.findDistinctById(id);
		
		
		if(n != null) {
			ArrayList<memberList> memLis = (ArrayList<memberList>) memberListRepository.findAllByBranchId(id);
			ArrayList<trainer> tList = (ArrayList<trainer>) trainerRepository.findAllByBranchId(id);
			ArrayList<manager> mList = (ArrayList<manager>) managerRepository.findAllByBranchId(id);
			
			if(tList != null) trainerCount += tList.size();
			if(mList != null) managerCount += mList.size();
			
			if(memLis != null) {
				
				for(int i = 0; i < memLis.size(); i++) {
					ArrayList<classMemberList> CML = (ArrayList<classMemberList>) classMemberListRepository.findAllByMemberId(memLis.get(i).getMemberId());
					
					if(CML != null) {
						courseStudentCount += CML.size();
					}
					
					if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("standard")) {
						standardMemberCount++;
					}
					
					else if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("gold")) {
						goldMemberCount++;
					}
					
					else if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("platinum")) {
						platinumMemberCount++;
					}
				}
				
				return new stats(n.getName(), managerCount, trainerCount, standardMemberCount, goldMemberCount, platinumMemberCount, courseStudentCount);
			}
				
		}
		
		return null;
	}
	
	
	public  stats getStats(int id) {
		// This returns a JSON or XML with the users
		
		int standardMemberCount = 0;
		int goldMemberCount = 0;
		int platinumMemberCount = 0;
		int courseStudentCount = 0;
		int trainerCount = 0;
		int managerCount = 0;
		
		branch n = branchRepository.findDistinctById(id);
		
		
		if(n != null) {
			ArrayList<memberList> memLis = (ArrayList<memberList>) memberListRepository.findAllByBranchId(id);
			ArrayList<trainer> tList = (ArrayList<trainer>) trainerRepository.findAllByBranchId(id);
			ArrayList<manager> mList = (ArrayList<manager>) managerRepository.findAllByBranchId(id);
			
			if(tList != null) trainerCount += tList.size();
			if(mList != null) managerCount += mList.size();
			
			if(memLis != null) {
				
				for(int i = 0; i < memLis.size(); i++) {
					ArrayList<classMemberList> CML = (ArrayList<classMemberList>) classMemberListRepository.findAllByMemberId(memLis.get(i).getMemberId());
					
					if(CML != null) {
						courseStudentCount += CML.size();
					}
					
					if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("standard")) {
						standardMemberCount++;
					}
					
					else if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("gold")) {
						goldMemberCount++;
					}
					
					else if(memberRepository.findDistinctById(memLis.get(i).getMemberId()).getStatus().equals("platinum")) {
						platinumMemberCount++;
					}
				}
				
				return new stats(n.getName(), managerCount, trainerCount, standardMemberCount, goldMemberCount, platinumMemberCount, courseStudentCount);
			}
				
		}
		
		return null;
	}
	
	@GetMapping(path="/all/stats")
	public @ResponseBody Iterable<stats> getAllStats() {
		// This returns a JSON or XML with the users
		
		ArrayList<stats> allStats = new ArrayList<stats>();
		ArrayList<branch> allBranch = (ArrayList<branch>) branchRepository.findAll();
		
		if(allBranch != null) {
			for(int i = 0; i < allBranch.size(); i++) {
				stats newStat = getStats(allBranch.get(i).getId());
				allStats.add(newStat);
			}
			
			return allStats;
		}
		
		
		return null;
	}

}
