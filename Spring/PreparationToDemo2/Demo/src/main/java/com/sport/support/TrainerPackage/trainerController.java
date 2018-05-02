package com.sport.support.TrainerPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/trainer")
public class trainerController {
	
	
	@Autowired
	private trainerRepository trainerRepository;
	
	@Autowired
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<trainer> getAllTrainers() {
		// This returns a JSON or XML with the users
		return trainerRepository.findAll();
	}
	
	@GetMapping(path="/all/{id}")
	public @ResponseBody Iterable<trainer> getAllTrainersWithBranch(@PathVariable(value="id") int id) {
		return trainerRepository.findAllByBranchId(id);		
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
		
		trainer n = new trainer(name, surname, username, password,id);
		trainerRepository.save(n);
		return n;

	}
	
	@GetMapping(path="/update/info")
	public @ResponseBody trainer updateTrainerPersonalInfo(@RequestParam int id,
			@RequestParam(required=false) String newname,@RequestParam(required=false) String newsurname,
			@RequestParam(required=false) String newusername, @RequestParam(required=false) String newpassword) 
	{
		
		trainer n = trainerRepository.findDistinctById(id);
		
		if (n != null) {
			if(newusername != null) n.setUsername(newusername);
			if(newpassword != null) n.setPassword(newpassword);
			if(newname != null) n.setName(newname);
			if(newsurname != null) n.setSurname(newsurname);
			trainerRepository.save(n);
			return n;
		}
		else return null;
	}
	
	//COMING SOON
	@GetMapping(path="/delete")
	public @ResponseBody trainer deleteTrainer(@RequestParam int id) {
		
		trainer n = trainerRepository.findDistinctById(id);

		if (n != null) {
			trainerRepository.delete(n);
			return n;
		}
	    else return null;
	}
	

}
