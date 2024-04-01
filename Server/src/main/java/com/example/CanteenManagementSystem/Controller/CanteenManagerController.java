package com.example.CanteenManagementSystem.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.CanteenManager;
import com.example.CanteenManagementSystem.repository.CanteenManagerRepo;
import com.example.CanteenManagementSystem.service.CanteenManagerService;

@ControllerAdvice
@RestController
@RequestMapping("/canteenManager")
@CrossOrigin(origins = "http://localhost:3000")
public class CanteenManagerController {
	@Autowired
	CanteenManagerService canteenManagerService;

	@GetMapping
	public ResponseEntity<List<CanteenManager>> getAllCanteenManagers() throws Exception {
		List<CanteenManager> canteenManagers = canteenManagerService.getAllCanteenManagers();
		for (CanteenManager manager : canteenManagers) {
			Blob imageBlob = manager.getImage();
			if (imageBlob != null && imageBlob.length() > 0) {
				try {
					String base64Image = Base64.encodeBase64String(imageBlob.getBytes(1, (int) imageBlob.length()));
					manager.setImage(imageBlob);
					manager.setImageBase64(base64Image);
				} catch (SQLException e) {
					e.printStackTrace(); // Handle exception
				}
			}
		}
		return ResponseEntity.ok(canteenManagers);
	}

	private CanteenManager getCanteenManagerResponse(CanteenManager manager) throws SQLException {
		byte[] imageBytes = null;
		Blob imageBlob = manager.getImage();
		if (imageBlob != null) {
			try {
				imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
			} catch (SQLException e) {
				throw new SQLException("Error retrieving image bytes");
			}
		}

		return new CanteenManager(manager.getId(), manager.getFirst_name(), manager.getLast_name(), manager.getEmail(),
				manager.getAddress(), manager.getAadhar_number(), manager.getPassword(), manager.getMob_number(),
				imageBytes);
	}

	@Autowired
	CanteenManagerRepo canteenManagerRepo;

	@PutMapping("/{id}")
	public ResponseEntity<CanteenManager> updateManagerByField(@PathVariable int id,
			@RequestParam(value = "file", required = false) MultipartFile file,
			@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("aadhar_number") long aadhar_number, @RequestParam("password") String password,
			@RequestParam("mob_number") long mob_number) {

		try {
			Optional<CanteenManager> optionalManager = canteenManagerRepo.getCanteenManagerById(id);
			if (!optionalManager.isPresent()) {
				return ResponseEntity.notFound().build();
			}

			CanteenManager manager = optionalManager.get();
			manager.setFirst_name(first_name);
			manager.setLast_name(last_name);
			manager.setEmail(email);
			manager.setAddress(address);
			manager.setAadhar_number(aadhar_number);
			manager.setPassword(password);
			manager.setMob_number(mob_number);

			if (!file.isEmpty()) {
				System.out.println("File is not empty");
				byte[] photoBytes = file.getBytes();
				Blob photoBlob = new SerialBlob(photoBytes);
				manager.setImage(photoBlob);
				Blob imageBlob = manager.getImage();

				String base64Image = Base64.encodeBase64String(imageBlob.getBytes(1, (int) imageBlob.length()));

				manager.setImageBase64(base64Image);
			}

			CanteenManager updatedManager = canteenManagerService.updateManager(manager);
			return ResponseEntity.ok(updatedManager);
		} catch (IOException | SQLException e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@PostMapping
	public ResponseEntity<CanteenManager> addManager(@RequestParam("file") MultipartFile file,
			@RequestParam("first_name") String first_name, @RequestParam("last_name") String last_name,
			@RequestParam("email") String email, @RequestParam("address") String address,
			@RequestParam("aadhar_number") long aadhar_number, @RequestParam("password") String password,
			@RequestParam("mob_number") long mob_number) throws SerialException, IOException, SQLException {

		CanteenManager cm = canteenManagerService.addManager(file, first_name, last_name, email, address, aadhar_number,
				password, mob_number);
		return new ResponseEntity<>(cm, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CanteenManager> getCanteenManagerById(@PathVariable int id) {
		Optional<CanteenManager> optionalManager = Optional.ofNullable(canteenManagerService.getCanteenManagerById(id));
		return optionalManager.map(manager -> {
			byte[] photoBytes = null;
			Blob photoBlob = manager.getImage();
			if (photoBlob != null) {
				try {
					photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
				} catch (SQLException e) {
					e.printStackTrace();
				}
				String base64Photo = Base64.encodeBase64String(photoBytes);
				manager.setImage(photoBlob);
				manager.setImageBase64(base64Photo);
			}
			return ResponseEntity.ok(manager);
		}).orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCanteenManager(@PathVariable int id) {
		try {
			canteenManagerService.deleteCanteenManager(id);
			return ResponseEntity.ok("Canteen manager with ID " + id + " deleted successfully.");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Failed to delete canteen manager with ID " + id + ".");
		}
	}

}
