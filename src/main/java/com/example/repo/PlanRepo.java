package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Plan;

public interface PlanRepo extends JpaRepository<Plan, Serializable> {

}
