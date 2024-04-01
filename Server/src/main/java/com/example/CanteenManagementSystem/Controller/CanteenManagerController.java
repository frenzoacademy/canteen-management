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
	/*@Autowired
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
	*/
	 @Autowired
	    CanteenManagerService canteenManagerService;

	    @GetMapping
	    public ResponseEntity<List<CanteenManager>> getCanteenManagerDetails() {
	        List<CanteenManager> canteenManager = canteenManagerService.getManager();
	        return new ResponseEntity<>(canteenManager, HttpStatus.OK);
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
	    		@RequestParam("first_name") String first_name,
	            @RequestParam("last_name") String last_name,
	            @RequestParam("email") String email, @RequestParam("address") String address,
	            @RequestParam("aadhar_number") long aadhar_number, @RequestParam("password") String password,
	            @RequestParam("mob_number") long mob_number) throws SerialException, IOException, SQLException {
	        
	        
	        CanteenManager cm = canteenManagerService.addManager(file,first_name,last_name,email,address,aadhar_number,password,mob_number);
	        return new ResponseEntity<>(cm, HttpStatus.CREATED);
	    }


	    @GetMapping("/{id}")
	    public ResponseEntity<CanteenManager> getCanteenManagerById(@PathVariable int id) {
	        Optional<CanteenManager> optionalManager = Optional.ofNullable(canteenManagerService.getCanteenManagerById(id));
	        return optionalManager.map(manager -> {
	            byte[] photoBytes = null;
	                Blob photoBlob = manager.getImage();
	                if (photoBlob != null) {
//	                    photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
//	                    String base64Photo = Base64.encodeBase64String(photoBytes);
	                    manager.setImage(photoBlob);
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
