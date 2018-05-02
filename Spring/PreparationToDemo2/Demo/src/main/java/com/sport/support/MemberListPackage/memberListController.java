package com.sport.support.MemberListPackage;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.MemberPackage.member;
import com.sport.support.TrainerPackage.trainer;
import com.sport.support.TrainerPackage.trainerRepository;

@RestController
@RequestMapping(path="/memberlist")
public class memberListController {
	
	@Autowired
	private memberListRepository memberListRepository;
	
	@Autowired
	private trainerRepository tRepository;


	@GetMapping(path="/add")
	public @ResponseBody memberList addToMemberList (@RequestParam int memberId , @RequestParam int branchId, @RequestParam int trainerId) {
		memberList n = new memberList(memberId,branchId, trainerId);
		return n;

	}
	
	public memberList getMemberList(int memberId) {
		return ((ArrayList<memberList>)memberListRepository.findAllByMemberId(memberId)).get(0);
	}
	
	
	public memberList add(member member, int branchId) {
		
		Random randomG = new Random();
		ArrayList<trainer> tlist = (ArrayList<trainer>) tRepository.findAllByBranchId(branchId);		
		if(tlist.size()==0) {
			memberList tempList = new memberList(member.getId(), branchId, 0);
			memberListRepository.save(tempList);
			return null;
		}
		memberList tempList = new memberList(member.getId(), branchId, tlist.get(randomG.nextInt(tlist.size())).getId());
		memberListRepository.save(tempList);
		
		return null;
	}
	
	public Iterable<memberList> findMemberIds(int id){
		return memberListRepository.findAllByBranchId(id);
	}

	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<memberList> getAllMemberList() {
		// This returns a JSON or XML with the users
		return memberListRepository.findAll();
	}
}