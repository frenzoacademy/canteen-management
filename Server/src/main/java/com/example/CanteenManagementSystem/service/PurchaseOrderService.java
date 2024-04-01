package com.example.CanteenManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
import com.example.CanteenManagementSystem.repository.PurchaseOrderRepo;

@Service
public class PurchaseOrderService {
	@Autowired
	PurchaseOrderRepo purchaseRepo;

	public List<PurchaseOrder> getOrder() {
		List<PurchaseOrder> p = purchaseRepo.findAll();
		if (p.size() > 0) {
			return p;
		} else {
			throw new NotFoundException("no records found");
		}
	}

	public PurchaseOrder addOrder(PurchaseOrder order) {
		PurchaseOrder p = purchaseRepo.save(order);
		return p;
	}

	public void deleteOrder(int id) {
		purchaseRepo.deleteById(id);
	}

	public PurchaseOrder getStudentById(int student_id) {
		Optional<PurchaseOrder> s = purchaseRepo.findById(student_id);
		if (s.isPresent()) {
			PurchaseOrder st = s.get();
			return st;
		} else {
			throw new NotFoundException("no Records found");
		}

	}

	public PurchaseOrder updatePurchaseOrder(int id, PurchaseOrder updatedPurchaseOrder) {
		return purchaseRepo.findById(id).map((PurchaseOrder purchaseOrder) -> {
			updatedPurchaseOrder.setFood_id(purchaseOrder.getFood_id());
			return purchaseRepo.save(updatedPurchaseOrder);
		}).orElseThrow(() -> new RuntimeException("Purchase Order with ID " + id + " not found"));
	}

}
