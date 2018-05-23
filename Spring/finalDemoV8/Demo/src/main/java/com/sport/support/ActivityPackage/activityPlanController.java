package com.sport.support.ActivityPackage;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sport.support.MovePackage.move;
import com.sport.support.MovePackage.moveRepository;


@RestController
@RequestMapping(path="/activity")
public class activityPlanController {

	
	@Autowired
	private activityPlanRepository activityPlanRepository;
	
	@Autowired
	private moveRepository moveRepository;
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<activityPlan> getAllActivities() {
		// This returns a JSON or XML with the activity plans
		return activityPlanRepository.findAll();
	}
	
	@SuppressWarnings("null")
	@GetMapping(path="/schedule/{id}")
	public @ResponseBody Iterable<plan> getSchedule(@PathVariable(value="id") int id) {
		plan planCont = new plan();
		
		ArrayList<plan> planList = new ArrayList<plan>();
		ArrayList<activityPlan> actList = (ArrayList<activityPlan>) activityPlanRepository.findAllByMemberId(id);
		for(int i = 0; i<actList.size(); i++) {
			plan newPlan = new plan(moveRepository.findDistinctById(actList.get(i).getMoveId()).getName(), actList.get(i).getMemberId(), actList.get(i).getSets(), actList.get(i).isStatus());
			planList.add(newPlan);
		}
		return planList;
	}
		
	//add new activity plan, status default value is false
	@GetMapping(path="/add")
	public @ResponseBody  activityPlan addActivity (@RequestParam int moveId, @RequestParam int memberId, 
			@RequestParam int sets) {
		
		activityPlan n = new activityPlan(moveId, memberId, sets, false);
		activityPlanRepository.save(n);
		
		return n;
	}
	
	@Transactional
	//this deletes a member's all activities
	@GetMapping(path="/delete/all/{id}")
	public @ResponseBody  activityPlan deleteActivities (@PathVariable(value="id") int id) {
		
		activityPlanRepository.deleteAllByMemberId(id);
		
		return null;
	}
	
	//this deletes a member's specific activity
	@GetMapping(path="/delete")
	public @ResponseBody  activityPlan deleteActivity (@RequestParam int memberId, @RequestParam int moveId) {
		
		activityPlan n = activityPlanRepository.findDistinctByMemberIdAndMoveId(memberId, moveId);
		
		if(n != null) activityPlanRepository.delete(n);
		return n;
	}
	
	//member marks the checkbox unmarks marks .... 
	@GetMapping(path="/update")
	public @ResponseBody  activityPlan updateActivity (@RequestParam int memberId, @RequestParam int moveId, 
		   @RequestParam boolean status)
	{
		
		activityPlan n = activityPlanRepository.findDistinctByMemberIdAndMoveId(memberId, moveId);
		
		if(n != null) {
			n.setStatus(status);
			activityPlanRepository.save(n);
			return n;
		}
		return null;
	}
	
	
}
