package com.sport.support.MemberPackage;

/**
 * This class is controller of member table.
 * It defines all the CRUD operation.
 * Last Update : 11/04/2018
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


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
	public @ResponseBody int addNewMember (@RequestParam String name, @RequestParam String surname, @RequestParam String username,
			@RequestParam String password, @RequestParam String statue, 
			@RequestParam String status, @RequestParam String mail,
			@RequestParam  int referenceNumber, @RequestParam int branchAuthority) {
		
		member n = memberRepository.findDistinctByUsername(username);
		
		if (n == null) {
			n = new member(name,surname,username,password,statue,status,mail,referenceNumber,branchAuthority);
			memberRepository.save(n);
			return n.getId();
		}
		
		else return -2;

	}
	
	/*
	 * Returns id of the found user with given combination
	 * Returns -2 when there is not a match.
	 * ---LOGIN---
	 */
	@GetMapping(path="/get")
	public @ResponseBody int getMember(@RequestParam String username, @RequestParam String password) {
		
		member n = memberRepository.findDistinctByUsernameAndPassword(username, password);
		
	    if (n != null) return n.getId();
	    else return -2;
	}
	
	/*
	 * Update with given parameters only old user name and password is mandatory. Others are optional.
	 * Returns id when it is successful
	 * Returns -2 when invalid user name password combination is given
	 */
	@GetMapping(path="/update/personalinfo")
	public @ResponseBody int updateMemberPersonalInfo(@RequestParam(required=false) String name, @RequestParam(required=false) String surname,
			@RequestParam String username, @RequestParam String password,
			@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword,
			@RequestParam(required=false) String mail) {
		
		member n = memberRepository.findDistinctByUsernameAndPassword(username,password);
		
		if (n != null) {
			if (mail != null) n.setMail(mail);
			if(newusername != null) n.setUsername(newusername);
			if(newpassword !=null) n.setPassword(newpassword);
			if(name != null) n.setName(name);
			if(surname != null) n.setSurname(surname);
			memberRepository.save(n);
			return n.getId();
		}
		else return -2;
	}
	
	/*
	 * Returns 0 when it is safely deleted.
	 * Returns -2 when finding user fails
	 */
	@GetMapping(path="/delete")
	public @ResponseBody int deleteMember(@RequestParam String username, @RequestParam String password) {
		
		member n = memberRepository.findDistinctByUsernameAndPassword(username, password);
		
		if (n != null) {
			memberRepository.delete(n);
			return 0;
		}
	    else return -2;
	}
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<member> getAllMembers() {
		// This returns a JSON or XML with the users
		return memberRepository.findAll();
	}
	
}
