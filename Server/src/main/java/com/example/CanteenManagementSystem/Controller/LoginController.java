package com.example.CanteenManagementSystem.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.CanteenManagementSystem.dto.AuthRequest;
import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.CanteenManagerRepo;
import com.example.CanteenManagementSystem.repository.StudentFormRepo;
import com.example.CanteenManagementSystem.service.JwtService;

@RestController
public class LoginController {

	@Autowired
	private JwtService jwtService;

	@Autowired
	private StudentFormRepo studentFormRepo;

	@Autowired
	private CanteenManagerRepo canteenManagerRepo;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PostMapping("/login")
	public Map<String, Object> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));

			String email = authRequest.getEmail();
			Object user = getUserByEmail(email);

			if (authentication.isAuthenticated() && user != null) {
				String role = determineRole(user);

				String token = jwtService.generateToken(email, role);

				Map<String, Object> response = new HashMap<>();
				response.put("token", token);
				response.put("role", role);
				response.put("id", getIdFromUser(user));

				return response;
			} else {
				throw new UsernameNotFoundException("Invalid user request!");
			}
		} catch (AuthenticationException ex) {
			throw new RuntimeException("Authentication failed: " + ex.getMessage());
		}
	}

	private Object getUserByEmail(String email) {
		Optional<StudentForm> student = studentFormRepo.findByEmail(email);
		if (student.isPresent()) {
			return student.get();
		}

		Optional<CanteenManager> canteenManager = canteenManagerRepo.findByEmail(email);
		if (canteenManager.isPresent()) {
			return canteenManager.get();
		}

		throw new UsernameNotFoundException("User not found");
	}

	private String determineRole(Object user) {
		if (user instanceof StudentForm) {
			System.out.println("Student   :");
			return "ROLE_STUDENT";
		} else if (user instanceof CanteenManager) {
			return "ROLE_CANTEEN_MANAGER";
		} else {
			throw new IllegalArgumentException("Invalid user type");
		}
	}

	private int getIdFromUser(Object user) {
		if (user instanceof StudentForm) {
			return ((StudentForm) user).getStudent_id();
		} else if (user instanceof CanteenManager) {
			return ((CanteenManager) user).getId();
		} else {
			throw new IllegalArgumentException("Invalid user type");
		}
	}

}
