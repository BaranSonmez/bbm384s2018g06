package com.sport.support.ManagerPackage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.BranchPackage.branchManagementController;
import com.sport.support.MemberPackage.member;
import com.sport.support.TrainerPackage.trainer;

@RestController
@RequestMapping(path="/manager")
public class managerController {
	
	@Autowired
	private managerRepository managerRepository;
	
	@Autowired
	private branchManagementController branchController;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<manager> showManagers() {
		// This returns a JSON or XML with the managers
		return managerRepository.findAll();
	}
	
	@SuppressWarnings("null")
	@GetMapping(path="/allWithBranchNames")
	public @ResponseBody Iterable<managerBNamed> showManagersBranchName() {
		
		ArrayList<managerBNamed> manList = new ArrayList<managerBNamed>();
		
		for (manager m: managerRepository.findAll()) {
			managerBNamed man = new managerBNamed(m.getId(),m.getName(),m.getSurname(),m.getUsername(),m.getPassword(),m.getBranchId(),null);
			man.setBranchName(branchController.getBranchName(m.getBranchId()));
			manList.add(man);
		}
		return manList;
	}
	
	@GetMapping(path="/get")
	public @ResponseBody manager managerLogin(@RequestParam String username, @RequestParam String password) {
		
		return managerRepository.findDistinctByUsernameAndPassword(username, password);
	}
	
	//get a manager information by manager's id -- owner or manager himself(?)
	@GetMapping(path="/get/{id}")
	public @ResponseBody manager showManager(@PathVariable(value="id") int id) {
		return managerRepository.findDistinctById(id);
	   
	}
	
	
	//create new manager
	@GetMapping(path="/add")
	public @ResponseBody manager addManager (@RequestParam String name, @RequestParam String surname, @RequestParam String username,
			@RequestParam String password, @RequestParam int branchId ) {
		
		manager control = managerRepository.findDistinctByUsername(username);
		
		if(control == null) {
			manager n = new manager(name, surname, username, password, branchId);
			managerRepository.save(n);
			return n;
		}
		
		else return null;
	}
	
	//update manager's information by using username and password or manager's id
	@GetMapping(path="/update/info")
	public @ResponseBody manager updateManagerPersonalInfo(@RequestParam int id,
			@RequestParam(required=false) String newname,@RequestParam(required=false) String newsurname,
			@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword,
			@RequestParam(required=false) int newBranchId
			) {
		
		manager n = managerRepository.findDistinctById(id);

		
		if (n != null) {
			
			manager control = managerRepository.findDistinctByUsername(newusername);
			
			if(control == null) {
				if(newusername != null) n.setUsername(newusername);
				if(newpassword !=null) n.setPassword(newpassword);
				if(newname != null) n.setName(newname);
				if(newsurname != null) n.setSurname(newsurname);
				if(newBranchId != 0) n.setBranchId(newBranchId);
				managerRepository.save(n);
				return n;
			}
		}
		
		return null;
	}
	
	//delete manager by manager's id -- done by owner
	@GetMapping(path="/delete/{id}")
	public @ResponseBody manager deleteManager(@PathVariable(value="id") int id) {
		manager n = managerRepository.findDistinctById(id);
		if (n != null) {
			managerRepository.delete(n);
			return n;
		}
	    else return null;
	}

}
