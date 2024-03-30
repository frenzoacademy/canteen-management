package com.example.CanteenManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CanteenManagementSystem.model.FoodInventory;

@Repository
public interface FoodInventoryRepo extends JpaRepository<FoodInventory, Integer>  {

	List<FoodInventory> findAll();

	FoodInventory save(FoodInventory food);

	Optional<FoodInventory> findById(int food_id);

}
