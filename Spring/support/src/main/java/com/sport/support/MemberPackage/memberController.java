package com.sport.support.MemberPackage;


/**
 * This class is controller of member table.
 * It defines all the CRUD operation.
 * Last Update : 11/04/2018
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;

@RestController
@RequestMapping(path="/member")
public class memberController {
	
	@Autowired 
	private memberRepository memberRepository;
	
	@GetMapping()
	public String memberHomePage() {
		return "Your options are:\n /Add\n /Get\n /Update\n /Delete\n /All";
	}
	
	/*
	 * Returns -2 when the user name is already taken.
	 * Returns id of the saved member when process is successful.
	 * -1 is separated for other type of errors.
	 * ---REGISTER---
	 */
	@GetMapping(path="/add")
	public @ResponseBody member addNewMember (@RequestParam String name, @RequestParam String surname, @RequestParam String username,
			@RequestParam String password, @RequestParam String statue, 
			@RequestParam String status, @RequestParam String mail,
			@RequestParam  int referenceNumber, @RequestParam int branchAuthority,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date age) {
		
		member n = memberRepository.findDistinctByUsername(username);
		
		if (n == null) {
			n = new member(name,surname,username,password,statue,status,mail,referenceNumber,branchAuthority,age);
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
		
		member n = memberRepository.findDistinctByUsernameAndPassword(username, password);
		
	    if (n != null) return n;
	    else return null;
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
	public @ResponseBody member updateMemberPersonalInfo(@RequestParam(required=false) String name, @RequestParam(required=false) String surname,
			@RequestParam(required=false) String username, @RequestParam(required=false) String password,
			@RequestParam(required=false) int id,@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword,
			@RequestParam(required=false) String mail, @RequestParam(required=false) @DateTimeFormat(pattern="yyyy-MM-dd") Date age) {
		
		member n;
		if (username==null || password==null) n = memberRepository.findDistinctById(id);
		else n = memberRepository.findDistinctByUsernameAndPassword(username,password);
		
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
	public @ResponseBody member updateMembership(@RequestParam(required=false) String username, 
			@RequestParam(required=false) String password, @RequestParam(required=false) int id,
			@RequestParam String status, @RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
			@RequestParam @DateTimeFormat(pattern="yyyy-MM-dd") Date startDate) {
		
		member n;
		if (username==null || password==null) n = memberRepository.findDistinctById(id);
		else n = memberRepository.findDistinctByUsernameAndPassword(username,password);
		
		if (n != null) {
			n.setStartDate(startDate);
			n.setEndDate(endDate);
			n.setStatus(status);
			memberRepository.save(n);
			return n;
		}
		else return null;
	}
	
	
	/*
	 * Returns 0 when it is safely deleted.
	 * Returns -2 when finding user fails.
	 */
	@GetMapping(path="/delete")
	public @ResponseBody member deleteMember(@RequestParam String username, @RequestParam String password) {
		
		member n = memberRepository.findDistinctByUsernameAndPassword(username, password);
		
		if (n != null) {
			memberRepository.delete(n);
			return n;
		}
	    else return null;
	}
	
	@GetMapping(path="/delete/{id}")
	public @ResponseBody member deleteMember(@PathVariable(value="id") int id) {
		
		member n = memberRepository.findDistinctById(id);
		
		if (n != null) {
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
	
}
