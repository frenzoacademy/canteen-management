package com.example.CanteenManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.CanteenManagementSystem.model.CanteenManager;

@Repository
public interface CanteenManagerRepo extends JpaRepository<CanteenManager,Integer>{

	Optional<CanteenManager> findById(int id);

	Optional<CanteenManager> getCanteenManagerById(int id);



}
