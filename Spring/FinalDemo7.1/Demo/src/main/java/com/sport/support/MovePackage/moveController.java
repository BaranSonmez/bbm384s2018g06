package com.sport.support.MovePackage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path="/move")
public class moveController {

	@Autowired
	private moveRepository moveRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<move> getAllMoves() {
		// This returns a JSON or XML with the users
		return moveRepository.findAll();
	}
	
	@GetMapping(path="/add")
	public @ResponseBody move addMove(@RequestParam String name) {
		
		move n = new move(name);
		moveRepository.save(n);
		return n;
		
	}
	
	@GetMapping(path="/delete/{id}")
	public @ResponseBody move delete(@PathVariable(value="id") int id) {
		
		move n = moveRepository.findDistinctById(id);
		
		if(n != null) {
			moveRepository.delete(n);
			return n;
		}
		return null;
	}
	
	@GetMapping(path="/update")
	public @ResponseBody move update(@RequestParam int id,
		@RequestParam (required = false) String newname) {
		
		move n = moveRepository.findDistinctById(id);
		
		if(n != null) {
			if(newname != null) {
				n.setName(newname);
				moveRepository.save(n);
				return n;
			}
		}
		return null;
	}
	
	@GetMapping(path="/showname/{id}")
	public @ResponseBody move showName(@PathVariable(value="id") int id) {
		
		move n = moveRepository.findDistinctById(id);
		if(n != null)
			return n;
		
		return null;
	}
}
