package com.example.service;

import java.util.HashMap;
import java.util.List;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Plan;
import com.example.entity.PlanCateogry;
import com.example.repo.PlanCategoryRepo;
import com.example.repo.PlanRepo;

@Service
public class PlanServiceImpl implements PlanService {

	@Autowired
	private PlanRepo planRepo;

	@Autowired
	private PlanCategoryRepo planCategoryRepo;

	@Override
	public Map<Integer, String> getPlanCategories() {

		List<PlanCateogry> categories = planCategoryRepo.findAll();

		Map<Integer, String> categoryMap = new HashMap<>();

		categories.forEach(category -> {
			categoryMap.put(category.getCatrgoryId(), category.getCategoryName());
		});
		return categoryMap;
	}

	@Override
	public boolean savePlan(Plan plan) {
		Plan save = planRepo.save(plan);

		// firstway
//		if (save.getPlanId() != null) {
//			return true;
//
//		} else {
//			return false;
//		}

		// secondway
		// return save.getPlanId()!= null ? true :false;

		return save.getPlanId() != null;

	}

	@Override
	public List<Plan> getAllPlans() {

//		List<Plan> findAll = planRepo.findAll();
//		return findAll;
		
		return planRepo.findAll();  

	}

	@Override
	public Plan getPlanById(Integer planId) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			return findById.get();
		}
		return null;
	}

	@Override
	public boolean updatePlan(Plan plan) {
		Plan saved = planRepo.save(plan);
		return saved.getPlanId()!= null;
	}

	@Override
	public boolean deleteById(Integer planId) {
		
		boolean status  = false;
		
		try {
	    planRepo.deleteById(planId);
		return true;
		}catch (Exception e) {
			e.printStackTrace();
		}return status;
	}

	@Override
	public boolean planStatusChange(Integer planId, String status) {
		Optional<Plan> findById = planRepo.findById(planId);
		if(findById.isPresent()) {
			 Plan plan = findById.get();
			 plan.setActiveSw(status);
			 planRepo.save(plan);
			
			// planRepo.save(plan);
			 return true;
			 
		}
		return false;
		
	}

}
