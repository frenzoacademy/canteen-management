package com.example.CanteenManagementSystem.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.service.CanteenManagerService;


@ControllerAdvice
@RestController
@RequestMapping("/canteenManager")
public class CanteenManagerController {
	@Autowired
	CanteenManagerService canteenManagerService;
	@GetMapping
	public ResponseEntity<List<CanteenManager>>getCanteenManagerDetails(){
		List<CanteenManager> canteenManager=canteenManagerService.getManager();
		return new ResponseEntity<List<CanteenManager>>(canteenManager,HttpStatus.OK);
		
	}
	@PatchMapping("/{id}")
	
	public CanteenManager updateManagerByField(@PathVariable int id,@RequestBody Map<String,Object>fields){
		return canteenManagerService.updateManagerByField(id,fields);
	}
	
	@PostMapping
	public ResponseEntity<CanteenManager> addStudent(@RequestBody CanteenManager manager) {
		CanteenManager cm = canteenManagerService.addManager(manager);
		return new ResponseEntity<CanteenManager>(cm, HttpStatus.CREATED);

	}
	@DeleteMapping("/{id}")
	public String deleteStudent(@PathVariable int id) {
		canteenManagerService.deleteManger(id);
		return "delete record";
		
	}
	@GetMapping("/{id}")
	public ResponseEntity<CanteenManager>getManagerById(@PathVariable int id){
		CanteenManager c=canteenManagerService.getManagerById(id);
		return new ResponseEntity<CanteenManager>(c,HttpStatus.OK);
	}
	


}
