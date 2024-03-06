package com.example.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.PlanCateogry;

public interface PlanCategoryRepo extends JpaRepository<PlanCateogry, Serializable> {

}
