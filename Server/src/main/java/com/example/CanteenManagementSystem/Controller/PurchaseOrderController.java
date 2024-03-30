package com.example.CanteenManagementSystem.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.service.PurchaseOrderService;


@ControllerAdvice
@RestController
@RequestMapping("/purchase")
public class PurchaseOrderController {
    @Autowired 
    PurchaseOrderService  purchaseService;
    @GetMapping
    public ResponseEntity <List<PurchaseOrder>>getPurchaseOrder(){
    	List<PurchaseOrder>purchaseorder=purchaseService.getOrder();
    	return new ResponseEntity<List<PurchaseOrder>>(purchaseorder,HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<PurchaseOrder>addDetails(@RequestBody PurchaseOrder order){
    	PurchaseOrder p=purchaseService.addOrder(order);
    	return new ResponseEntity<PurchaseOrder>(p,HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public String deleteOrders(@PathVariable int id) {
    	purchaseService.deleteOrder(id);
    	return "delete record";
    }
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseOrder>getStudentById(@PathVariable int id){
    	PurchaseOrder p=purchaseService.getStudentById(id);
    	return new ResponseEntity<PurchaseOrder>(p,HttpStatus.OK);
    }


}
