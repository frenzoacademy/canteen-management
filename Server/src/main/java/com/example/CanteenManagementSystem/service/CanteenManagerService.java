package com.example.CanteenManagementSystem.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils.FieldFilter;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.repository.CanteenManagerRepo;

@Service
public class CanteenManagerService {
	@Autowired 
	CanteenManagerRepo canteenRepo;

	public List<CanteenManager> getManager() {
		List<CanteenManager> cm=canteenRepo.findAll();
		if(cm.size()>0) {
		return cm;
	}else {
		throw new NotFoundException("no records found");
	}
	}


	public CanteenManager addManager(CanteenManager manager) {
		 CanteenManager c = canteenRepo.save(manager);
			return c;
		}

		
	

	public void deleteManger(int id) {
		canteenRepo.deleteById(id);
		
	}


//	public CanteenManager updateManager(CanteenManager manager) {
//		Optional<CanteenManager> cm=canteenRepo.findById(manager.getId());{
//			if(cm.isPresent()) {
//				CanteenManager c=cm.get();
//				if(manager.getFirst_name()!=null) {
//					c.setFirst_name(manager.getFirst_name());
//				}
//				if(manager.getLast_name()!=null) {
//					c.setFirst_name(manager.getLast_name());
//
//			}
//				if(manager.getRole()!=null) {
//					c.setRole(manager.getRole());
//
//				}
//				if(manager.getAddress()!=null) {
//					c.setAddress(manager.getAddress());
//
//		}
//				if(manager.getAadhar_number()!=0) {
//					c.setAadhar_number(manager.getAadhar_number());
//				}
//					if(manager.getPassword()!=null) {
//						c.setPassword(manager.getPassword());
//					}
//					if(manager.getConfirm_password()!=null) {
//						c.setConfirm_password(manager.getConfirm_password());
//					}
//					 if(manager.getMob_number()!=0) {
//						 c.setMob_number(manager.getMob_number());
//					 }
//					 canteenRepo.save(c);
//					 return c;
//					}
//			else {
//				manager=canteenRepo.save(manager);
//				return manager;
//			}
//
//		}}
//
//
	public CanteenManager updateManagerByField(int id, Map<String, Object> fields) {
		Optional<CanteenManager> c=canteenRepo.findById(id);
		if(c.isPresent()) {
			fields.forEach((key,value)->{
				Field field=ReflectionUtils.findRequiredField(CanteenManager.class, key);
				field.setAccessible(true);
				ReflectionUtils.setField(field, c.get(), value);
			});
		}
		
		return canteenRepo.save(c.get());
	}


	public CanteenManager getManagerById(int id) {
        Optional<CanteenManager>s=canteenRepo.findById(id);
        if(s.isPresent()) {
        	CanteenManager c=s.get();
    		return c;

        }else {
			throw new NotFoundException("no Records found");

        }
	}	
	}


