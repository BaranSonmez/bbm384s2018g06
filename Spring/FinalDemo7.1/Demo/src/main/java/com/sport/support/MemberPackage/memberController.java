package com.sport.support.MemberPackage;


/**
 * This class is controller of member table.
 * It defines all the CRUD operation.
 * Last Update : 11/04/2018
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.ClassMemberListPackage.classMemberList;
import com.sport.support.ClassPackage.course;
import com.sport.support.ClassPackage.courseRepository;
import com.sport.support.FeePackage.fee;
import com.sport.support.FeePackage.feeController;
import com.sport.support.MemberListPackage.memberList;
import com.sport.support.MemberListPackage.memberListController;

import java.util.ArrayList;
import java.util.Date;

@RestController
@RequestMapping(path="/member")
public class memberController {
	
	@Autowired
	private memberListController memberListController;
	
	@Autowired 
	private memberRepository memberRepository;
		
	@Autowired
	private feeController feeController;
	
	@Autowired
	private com.sport.support.MemberListPackage.memberListRepository memberListRepository;
	
	@Autowired
	private com.sport.support.ClassMemberListPackage.classMemberListRepository classMemberListRepository;
	
	@Autowired
	private com.sport.support.ActivityPackage.activityPlanRepository activityPlanRepository;
	
	@Autowired
	private com.sport.support.ClassPackage.courseRepository courseRepository;
	
	
	@GetMapping()
	public String memberHomePage() {
		return "Your options are:\n /Add\n /Get\n /Update\n /Delete\n /All";
	}
	
    //REGISTER
	//branch ve trainer atama yapildi ama burada yapilmamasi gerek???? Become memeberda yapilmali, odemeden atama mantiksiz..
	//anladim 0,0 yapip update yapmissin sonra, ama 0 0 da branch ve trainer varsa sorun, onların listesinde uye gorunur..
	//addToList i burada hic yapmasak soru olur mu, db acisindan?
	@GetMapping(path="/add")
	public @ResponseBody member addNewMember (@RequestParam String name, @RequestParam String surname, @RequestParam String username,
			@RequestParam String password,@RequestParam String mail,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date age) {
		
		member n = memberRepository.findDistinctByUsername(username);
		
		if (n == null) {
			n = new member(name,surname,username,password,"inactive","none",mail,0,0,age);
			memberRepository.save(n);
			return n;
		}
		else return null;

	}
	

	/*
	 * Returns id of the found user with given combination.
	 * Returns -2 when there is not a match.
	 * ---LOGIN---
	 */
	@GetMapping(path="/get")
	public @ResponseBody member getMember(@RequestParam String username, @RequestParam String password) {
		
		return memberRepository.findDistinctByUsernameAndPassword(username, password);
	}
	
	@GetMapping(path="/get/{id}")
	public @ResponseBody member getMemberWithId(@PathVariable(value="id") int id) {
		
		return memberRepository.findDistinctById(id);
	}
	
	/*
	 * Update with given parameters only old user name and password is mandatory. Others are optional.
	 * Returns id when it is successful
	 * Returns -2 when invalid user name password combination is given
	 */
	@GetMapping(path="/update/personalinfo")
	public @ResponseBody member updateMemberPersonalInfo(@RequestParam int id,@RequestParam(required=false) String name, @RequestParam(required=false) String surname,
			@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword,
			@RequestParam(required=false) String mail, @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date age) {
		
		member n = memberRepository.findDistinctById(id);

		if (n != null) {
			if (mail != null) n.setMail(mail);
			if(newusername != null) n.setUsername(newusername);
			if(newpassword !=null) n.setPassword(newpassword);
			if(name != null) n.setName(name);
			if(surname != null) n.setSurname(surname);
			if(age != null) n.setAge(age);
			memberRepository.save(n);
			return n;
		}
		else return null;
	}
	
	@GetMapping(path="/payment/membership")
	public @ResponseBody member updateMembership(@RequestParam int id,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate,
			@RequestParam int branchId, @RequestParam String status) {
		
		member n = memberRepository.findDistinctById(id);
		
		if (n != null) {
			n.setStartDate(startDate);
			n.setStatue("active");
			n.setStatus(status);
			memberRepository.save(n);
			//artik memberliste sadece member oldugunda ekleniyor basta registerde eklenmiyor - Thanks Ali
			if (branchId != 0) memberListController.add(n, branchId); 
			return n;
		}
		else return null;
	}
	
	@GetMapping(path="/upgrade/membership/{id}")
	public @ResponseBody fee upgradeMembership(@PathVariable(value="id") int id) {
		
		member m = memberRepository.findDistinctById(id);
		fee n = new fee();
		if(m.getStatus().equals("platinum")) return null;
		
		else if(m.getStatus().equals("gold")) {
			fee prices = feeController.showFee(memberListController.getMemberList(id).getBranchId());
			n.setPlatinumMembership(prices.getPlatinumMembership()- prices.getGoldMembership());
			return n;
		}
		
		else if(m.getStatus().equals("standard")) {
			fee prices = feeController.showFee(memberListController.getMemberList(id).getBranchId());
			n.setPlatinumMembership(prices.getPlatinumMembership()- prices.getStandardMembership());
			n.setGoldMembership(prices.getGoldMembership()- prices.getStandardMembership());
			return n;
		}
		
		else if(m.getStatus().equals("pool")) {
			fee prices = feeController.showFee(memberListController.getMemberList(id).getBranchId());
			n.setPlatinumMembership(prices.getPlatinumMembership()- prices.getPoolMembership());
			n.setGoldMembership(prices.getGoldMembership()- prices.getPoolMembership());
			n.setStandardMembership(prices.getStandardMembership()- prices.getPoolMembership());
			return n;
		}
		
		return null;
	}
	
	
	/*
	 * Returns 0 when it is safely deleted.
	 * Returns -2 when finding user fails.
	 */
//	@GetMapping(path="/delete")
//	public @ResponseBody member deleteMember(@RequestParam String username, @RequestParam String password) {
//		
//		member n = memberRepository.findDistinctByUsernameAndPassword(username, password);
//		
//		if (n != null) {
//			memberRepository.delete(n);
//			return n;
//		}
//	    else return null;
//	}
	
	@GetMapping(path="/delete/{id}")
	public @ResponseBody member deleteMember(@PathVariable(value="id") int id) {
		
		member n = memberRepository.findDistinctById(id);
		
		if (n != null) {
			classMemberListRepository.deleteAllByMemberId(n.getId());
			memberListRepository.deleteAllByMemberId(n.getId());
			activityPlanRepository.deleteAllByMemberId(n.getId());
			memberRepository.delete(n);
			return n;
		}
	    else return null;
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<member> getAllMembers() {
		// This returns a JSON or XML with the users
		return memberRepository.findAll();
	}
	
	@Transactional
	@GetMapping(path="/ban/{id}")
	public @ResponseBody member banMember(@PathVariable(value="id") int id) {
		
		member n = memberRepository.findDistinctById(id);
		
		if (n != null) {
			
			ArrayList<classMemberList> cMemLis = (ArrayList<classMemberList>) classMemberListRepository.findAllByMemberId(n.getId());
			
			for(int i = 0; i < cMemLis.size(); i++) {
				
				courseRepository.findDistinctById(cMemLis.get(i).getCourseId()).setAvailableQuota
				(courseRepository.findDistinctById(cMemLis.get(i).getCourseId()).getAvailableQuota() + 1);
			}
			
			classMemberListRepository.deleteAllByMemberId(id);
			memberListRepository.deleteAllByMemberId(n.getId());
			activityPlanRepository.deleteAllByMemberId(n.getId());
			
			n.setStatue("banned");
			n.setStatus("none");
			return n;
		}
	    else return null;
	}
	
	@GetMapping(path="/all/{id}")
	public @ResponseBody Iterable<member> getAllMembersWithBranch(@PathVariable(value="id") int id) {
		// This returns a JSON or XML with the users
		ArrayList<memberList> mllist = (ArrayList<memberList>) memberListController.findMemberIds(id);
		if (mllist.size()==0) return null;
		ArrayList<member> mlist = new ArrayList<member>();
		for (memberList ml : mllist) {
			mlist.add(memberRepository.findDistinctById(ml.getMemberId()));
		}
		if(mlist.size()==0) return null;
		return mlist;
	}
	
	
	
	
	@Transactional
	@GetMapping(path="/cancel")
	public @ResponseBody member cancelMembership(@RequestParam String username, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate) {
		
		member n = memberRepository.findDistinctByUsername(username);
		
		if (n != null) {
		
			memberListRepository.deleteAllByMemberId(n.getId());
			activityPlanRepository.deleteAllByMemberId(n.getId());
			
			ArrayList<classMemberList>  classList = (ArrayList<classMemberList>) classMemberListRepository.findAllByMemberId(n.getId());
			
			for(int i = 0; i < classList.size(); i++) {

				courseRepository.findDistinctById(classList.get(i).getCourseId()).setAvailableQuota
				(courseRepository.findDistinctById(classList.get(i).getCourseId()).getAvailableQuota() + 1);
				
			}
			
			classMemberListRepository.deleteAllByMemberId(n.getId());
			
			n.setStatus("none");
			n.setEndDate(endDate);
			n.setStatue("inactive");
			return n;
		}
	    else return null;
	}
	
	
	
	
	
}
