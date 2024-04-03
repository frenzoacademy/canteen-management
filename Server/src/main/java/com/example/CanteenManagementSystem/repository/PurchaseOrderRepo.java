package com.example.CanteenManagementSystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.CanteenManagementSystem.model.PurchaseOrder;
@Repository
public interface PurchaseOrderRepo  extends JpaRepository<PurchaseOrder, Integer> {

	List<PurchaseOrder> findAll();

	PurchaseOrder save(PurchaseOrder order);

	void deleteById(int id);


}
