package com.example.CanteenManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.CanteenManagementSystem.model.StudentForm;
@Repository
public interface StudentFormRepo extends JpaRepository<StudentForm, Integer> {

	List<StudentForm> findAll();

	StudentForm save(StudentForm student);


}
