package com.sport.support.MemberListPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.MemberPackage.member;

@RestController
@RequestMapping(path="/memberlist")
public class memberListController {
	
	@Autowired
	private memberListRepository memberListRepository;

	@GetMapping(path="/add")
	public @ResponseBody memberList addToMemberList (@RequestParam int memberId , @RequestParam int branchId, @RequestParam int trainerId) {
		
		
		memberList n = new memberList(memberId,branchId, trainerId);
		return n;

	}
	
	
	public memberList update(member member, int branchId) {
		
		memberList tempList = memberListRepository.findDistinctByMemberId(member.getId());
		
		tempList.setBranchId(branchId);
		tempList.setTrainerId(103);
		
		memberListRepository.save(tempList);
		
		return null;
	}
	
	
	public memberList addToList(int memberId, int branchId, int trainerId) {
		
		
		memberList n = new memberList(memberId,branchId, trainerId);
		memberListRepository.save(n);
		return n;
	}
	
	
	
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<memberList> getAllMemberList() {
		// This returns a JSON or XML with the users
		return memberListRepository.findAll();
	}
}