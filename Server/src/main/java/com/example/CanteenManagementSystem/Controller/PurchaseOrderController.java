package com.example.CanteenManagementSystem.Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.FoodInventoryRepo;
import com.example.CanteenManagementSystem.repository.PurchaseOrderRepo;
import com.example.CanteenManagementSystem.repository.StudentFormRepo;
import com.example.CanteenManagementSystem.service.FoodInventoryService;
import com.example.CanteenManagementSystem.service.PurchaseOrderService;

@ControllerAdvice
@RestController
@RequestMapping("/purchase")
@CrossOrigin(origins = "http://localhost:3000")
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseService;

	@GetMapping
	public ResponseEntity<List<PurchaseOrder>> getPurchaseOrder() {
		List<PurchaseOrder> purchaseorder = purchaseService.getOrder();
		return new ResponseEntity<List<PurchaseOrder>>(purchaseorder, HttpStatus.OK);
	}

	@Autowired
	StudentFormRepo studentRepo;

	/*@PostMapping
	public ResponseEntity<?> addBulkOrders(@RequestBody List<PurchaseOrder> orders) {
	    List<PurchaseOrder> addedOrders = new ArrayList<>();

	    for (PurchaseOrder order : orders) {
	        Optional<StudentForm> optionalStudent = studentRepo.findById(order.getStudentForm().getStudent_id());
	        
	        if (optionalStudent.isPresent()) {
	            StudentForm student = optionalStudent.get();
	            
	            int updatedWalletBalance = student.getWallet() - order.getTotalAmount();
	            if (updatedWalletBalance >= 0) {
	                student.setWallet(updatedWalletBalance);
	                studentRepo.save(student);
	                PurchaseOrder addedOrder = purchaseService.addOrder(order);
	                addedOrders.add(addedOrder);
	            } else {
	                return ResponseEntity.badRequest().body("Insufficient balance for student ID: " + student.getStudent_id());
	            }
	        } else {
	            return ResponseEntity.badRequest().body("Student ID not found: " + order.getStudentForm().getStudent_id());
	        }
	    }

	    return new ResponseEntity<>(addedOrders, HttpStatus.CREATED);
	}*/
	@PostMapping
	@Transactional
	public List<PurchaseOrder> addOrders(@RequestBody List<PurchaseOrder> newOrders) {
	    List<PurchaseOrder> savedOrders = new ArrayList<>();
    	System.out.println("newOrders---"+newOrders.size());

	    for (PurchaseOrder newOrder : newOrders) {
	    	
	        Optional<StudentForm> studentOptional = studentRepo.findById(newOrder.getStudentForm().getStudent_id());
	        if (studentOptional.isPresent()) {
	            StudentForm student = studentOptional.get();
	            int currentBalance = student.getWallet();
	            int bill = newOrder.getTotalAmount();
	            if (currentBalance >= bill) {
	                student.setWallet(currentBalance - bill);
	                studentRepo.save(student);
	            } else {
	                throw new IllegalArgumentException("Insufficient balance for student with ID: " + student.getStudent_id());
	            }
	        } else {
	            throw new IllegalArgumentException("Student not found with ID: " + newOrder.getStudentForm().getStudent_id());
	        }

//	        List<FoodInventory> foodItems = newOrder.get;
	        Optional<FoodInventory> foodData = foodInventoryRepository.findByFoodId(newOrder.getFood_id());
	        
	        if(foodData.isPresent()) {
	        	FoodInventory foodInventry = foodData.get();
	        	System.out.println("Before Update :"+foodInventry.getQuantity());
	        	
	        	int prevQuantity = foodInventry.getQuantity();
	        	int newQuantity = (int) newOrder.getQuantity();
	        	
	        	foodInventry.setQuantity(prevQuantity-newQuantity);
	        	foodInventry = foodInventoryRepository.save(foodInventry);
	        	System.out.println("After Update :"+foodInventry.getQuantity());
	        	
	        	
	        }
	        
	        
	        System.out.println("foodItems :"+foodData);
	        
	        savedOrders.add(purchaseOrderRepository.save(newOrder));
	    }

	    return savedOrders;
	}

	
	@Autowired
	FoodInventoryService foodInventoryService;
	
	@Autowired
	FoodInventoryRepo foodInventoryRepository;
	@Autowired
	PurchaseOrderRepo purchaseOrderRepository;
	
	
	@PutMapping("/{id}")
	public ResponseEntity<PurchaseOrder> updatePurchaseOrder(@PathVariable int id,
			@RequestBody PurchaseOrder purchaseOrder) {
		PurchaseOrder updatedPurchaseOrder = purchaseService.updatePurchaseOrder(id, purchaseOrder);
		return ResponseEntity.ok(updatedPurchaseOrder);
	}

	@DeleteMapping("/{id}")
	public String deleteOrders(@PathVariable int id) {
		purchaseService.deleteOrder(id);
		return "delete record";
	}

	@GetMapping("/{id}")
	public ResponseEntity<PurchaseOrder> getStudentById(@PathVariable int id) {
		PurchaseOrder p = purchaseService.getStudentById(id);
		return new ResponseEntity<PurchaseOrder>(p, HttpStatus.OK);
	}

}
