package com.example.CanteenManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.CanteenManagementSystem.model.StudentForm;


public interface UserInfoRepository extends JpaRepository<StudentForm, Integer> {

	Optional<StudentForm> findByEmail(String email);

}
