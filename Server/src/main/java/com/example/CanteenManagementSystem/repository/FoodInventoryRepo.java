package com.example.CanteenManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CanteenManagementSystem.model.FoodInventory;

@Repository
public interface FoodInventoryRepo extends JpaRepository<FoodInventory, Integer>  {

	List<FoodInventory> findAll();

	FoodInventory save(FoodInventory food);

	Optional<FoodInventory> findById(int food_id);
	
	@Query("SELECT fi FROM FoodInventory fi WHERE fi.food_id = :foodId")
	Optional<FoodInventory> findByFoodId(@Param("foodId") int foodId);
//    Optional<FoodInventory> findByFood_Id(int food_id); // Corrected method name

//    Optional<FoodInventory> findByFoodId(int food_id);

	

}
