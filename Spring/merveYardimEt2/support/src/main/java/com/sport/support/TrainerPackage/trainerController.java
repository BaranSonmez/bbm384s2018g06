package com.sport.support.TrainerPackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping(path="/trainer")
public class trainerController {
	
	
	@Autowired
	private trainerRepository trainerRepository;
	
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<trainer> getAllTrainers() {
		// This returns a JSON or XML with the users
		return trainerRepository.findAll();
	}
	
	
	@GetMapping(path="/add")
	public @ResponseBody trainer addTrainer (@RequestParam String name, @RequestParam String surname, @RequestParam String username, @RequestParam String password ) {
		
		trainer n = new trainer(name, surname, username, password);
		trainerRepository.save(n);
		return n;

	}

}
