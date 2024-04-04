package com.example.CanteenManagementSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.CanteenManagerRepo;
import com.example.CanteenManagementSystem.repository.UserInfoRepository;

import java.util.Optional;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {
	@Autowired
	private UserInfoRepository studentRepository;

	@Autowired
	private CanteenManagerRepo canteenManagerRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<StudentForm> studentInfo = studentRepository.findByEmail(email);
		if (studentInfo.isPresent()) {
			return new UserInfoUserDetails(studentInfo.get());
		}

		Optional<CanteenManager> canteenManagerInfo = canteenManagerRepository.findByEmail(email);
		if (canteenManagerInfo.isPresent()) {
			return new UserInfoUserDetails(canteenManagerInfo.get());
		}

		throw new UsernameNotFoundException("User not found with email: " + email);
	}
}
