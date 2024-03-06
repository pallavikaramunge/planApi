package com.example.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Plan;
import com.example.props.AppProperties;
import com.example.service.PlanService;



@RestController
public class PlanRestController {

	@Autowired
	PlanService planService;
	
	@Autowired
	AppProperties appProps;

	@GetMapping("/category")
	public ResponseEntity<Map<Integer, String>> planCategories() {
		Map<Integer, String> planCategories = planService.getPlanCategories();

		return new ResponseEntity<Map<Integer, String>>(planCategories, HttpStatus.OK);
	}

	@PostMapping("/plan")
	public ResponseEntity<String> savePlan(@RequestBody Plan plan) {
		boolean savePlan = planService.savePlan(plan);
		
		 Map<String, String> messages = appProps.getMessages();
		String msg;

		if (savePlan) {
			msg = messages.get( "planSavingSucc");

		} else {
			msg = messages.get("planSaveFail");
		}

		return new ResponseEntity<String>(msg, HttpStatus.CREATED);
	}

	@GetMapping("/plans")
	public ResponseEntity<List<Plan>> getPlans() {

		List<Plan> allPlans = planService.getAllPlans();

		return new ResponseEntity<List<Plan>>(allPlans, HttpStatus.OK);

	}

	@GetMapping("/plan/{planId}")
	public ResponseEntity<Plan> editPlan(@PathVariable Integer planId) {
		Plan planById = planService.getPlanById(planId);

		return new ResponseEntity<Plan>(planById, HttpStatus.OK);

	}

	@PutMapping("/plan")
	public ResponseEntity<String> updatePlan(@RequestBody Plan plan) {

		boolean updatePlan = planService.updatePlan(plan);
		
		Map<String, String> messages = appProps.getMessages();

		String msg;

		if (updatePlan) {
			msg = messages.get(" planUpdateSucc");
		} else {
			msg = messages.get(" planUpdateFail");
		}

		return new ResponseEntity<>(msg, HttpStatus.OK);

	}

	@DeleteMapping("/plan/{planId}")
	public ResponseEntity<Boolean> deletePlan(@PathVariable Integer planId) {
		boolean deleteById = planService.deleteById(planId);

		return new ResponseEntity<Boolean>(deleteById, HttpStatus.OK);

	}

	@PutMapping("/status-change/{planId}/{status}")
	public ResponseEntity<String> statusChange(@PathVariable Integer planId, @PathVariable String status) {
		boolean planStatusChange = planService.planStatusChange(planId, status);
	   Map<String, String> messages = appProps.getMessages();		
		String msg;
		if (planStatusChange) {
			msg = messages.get("planStatusChange");
		} else {

			msg = messages.get(" planStatusChangeFail");
		}
		return new ResponseEntity<String>(msg, HttpStatus.OK);
	}

}