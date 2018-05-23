package com.sport.support.TrainerPackage;

import java.util.ArrayList;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.ClassMemberListPackage.classMemberListRepository;
import com.sport.support.ClassPackage.course;
import com.sport.support.MemberListPackage.memberList;
import com.sport.support.MemberPackage.member;


@RestController
@RequestMapping(path="/trainer")
public class trainerController {
	
	
	@Autowired
	private trainerRepository trainerRepository;
	
	@Autowired
	private com.sport.support.ClassPackage.courseRepository courseRepository;
	
	@Autowired
	private classMemberListRepository classMemberListRepository;
	
	@Autowired
	private com.sport.support.MemberListPackage.memberListRepository memberListRepository;
	
	@Autowired
	private com.sport.support.MemberPackage.memberRepository memberRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<trainer> getAllTrainers() {
		// This returns a JSON or XML with the users
		return trainerRepository.findAll();
	}
	
	@GetMapping(path="/all/{id}")
	public @ResponseBody Iterable<trainer> getAllTrainersWithBranch(@PathVariable(value="id") int id) {
		return trainerRepository.findAllByBranchId(id);		
	}
	
	@GetMapping(path="/all/trainee/{id}")
	public @ResponseBody Iterable<member> getAllTtrainees(@PathVariable(value="id") int id) {
		ArrayList<memberList> memLis =  (ArrayList<memberList>) memberListRepository.findAllByTrainerId(id);
		ArrayList<member> members = new ArrayList<member>();
		
		for(int i = 0; i < memLis.size(); i++) {
			member newMember = memberRepository.findDistinctById(memLis.get(i).getMemberId());
			members.add(newMember);
		}
		return members;
	}
	
	//view a trainer -done by manager 
	@GetMapping(path="/get/{id}")
	public @ResponseBody trainer getTrainer(@PathVariable(value="id") int id) {
		return trainerRepository.findDistinctById(id);
	}
	
	//trainer login
	@GetMapping(path="/get")
	public @ResponseBody trainer trainerLogin(@RequestParam String username, @RequestParam String password) {
		return trainerRepository.findDistinctByUsernameAndPassword(username,password);
	}	
	
	//create new trainer
	@GetMapping(path="/add")
	public @ResponseBody trainer addTrainer (@RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password, @RequestParam int id ) {
		
		trainer control = trainerRepository.findDistinctByUsername(username);
		
		if(control == null) {
			trainer n = new trainer(name, surname, username, password,id);
			trainerRepository.save(n);
			return n;
		}
		
		return null;
	}
	
	@GetMapping(path="/update/info")
	public @ResponseBody trainer updateTrainerPersonalInfo(@RequestParam int id,
			@RequestParam(required=false) String newname,@RequestParam(required=false) String newsurname,
			@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword) 
	{
		
		trainer n = trainerRepository.findDistinctById(id);
		trainer control = new trainer();
		if (n != null) {
			
			if(newusername != null)
				control = trainerRepository.findDistinctByUsername(newusername);
			
			if(newusername == null || control == null) {
				
				if(newusername != null) n.setUsername(newusername);
				if(newpassword != null) n.setPassword(newpassword);
				if(newname != null) n.setName(newname);
				if(newsurname != null) n.setSurname(newsurname);
				trainerRepository.save(n);
				return n;
			}
		}
		
		return null;
	}
	
	//COMING SOON
	@Transactional
	@GetMapping(path="/delete/{id}")
	public @ResponseBody trainer deleteTrainer(@PathVariable(value="id") int id) {
		
		trainer n = trainerRepository.findDistinctById(id);
		Random randomG = new Random();
		
		if (n != null) {
			
			ArrayList<course> courseLis = (ArrayList<course>) courseRepository.findAllByTrainerId(id);
			for(int i = 0; i < courseLis.size(); i++) {
				classMemberListRepository.deleteAllByCourseId(courseLis.get(i).getId());
			}
			
			ArrayList<memberList> memberLis = (ArrayList<memberList>) memberListRepository.findAllByTrainerId(id);
			trainerRepository.delete(n);
			
			for(int i = 0; i < memberLis.size(); i++) {
				ArrayList<trainer> tlist = (ArrayList<trainer>) trainerRepository.findAllByBranchId(memberLis.get(i).getBranchId());
				if(tlist!=null) {
					memberList m = new memberList(memberLis.get(i).getMemberId(), memberLis.get(i).getBranchId(),tlist.get(randomG.nextInt(tlist.size())).getId());
					memberListRepository.deleteByTrainerId(id);
					memberListRepository.save(m);
				}
				else {
					memberList m = new memberList(memberLis.get(i).getMemberId(), memberLis.get(i).getBranchId(), 0);
					memberListRepository.deleteByTrainerId(id);
					memberListRepository.save(m);
				}
			}
			courseRepository.deleteAllByTrainerId(id);
			
			return n;
		}
	    else return null;
	}
	

}
