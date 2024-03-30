package com.example.CanteenManagementSystem.Controller;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.model.StudentFormResponse;
import com.example.CanteenManagementSystem.repository.StudentFormRepo;
import com.example.CanteenManagementSystem.service.StudentFormService;

@ControllerAdvice
@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:3000")
public class StudentFormController {
	@Autowired
	StudentFormService studentService;

	@GetMapping("/{id}")
	public ResponseEntity<StudentFormResponse> getStudentById(@PathVariable int id) {
		StudentForm s = studentService.getStudentById(id);
		StudentFormResponse response = new StudentFormResponse();
		// Convert Blob to byte array
		if (s != null && s.getImage() != null) {
			try {
				Blob photoBytes = studentService.getStudentPhotoByStudentId(id);
				if (photoBytes != null && photoBytes.length() > 0) {
					String base64Photo = Base64.encodeBase64String(photoBytes.getBytes(1, (int) photoBytes.length()));
					response = getStudentFormResponse(s);
					response.setImage(base64Photo);
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
	        response.setDate_Time(s.getDate_time());
		}
		System.out.println("---------------------"+response+"---------");
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping
	public ResponseEntity<List<StudentFormResponse>> getAllStudents() throws SQLException {
		List<StudentForm> students = studentService.getAllStudents();
		List<StudentFormResponse> studentResponses = new ArrayList<>();
		for (StudentForm room : students) {
			Blob photoBytes = studentService.getStudentPhotoByStudentId(room.getStudent_id());
			if (photoBytes != null && photoBytes.length() > 0) {
				String base64Photo = Base64.encodeBase64String(photoBytes.getBytes(1, (int) photoBytes.length()));
				StudentFormResponse studentResponse = getStudentFormResponse(room);
                studentResponse.setImage(base64Photo);
				studentResponses.add(studentResponse);
			}
		}
		return ResponseEntity.ok(studentResponses);
	}
//	@PutMapping
//	public ResponseEntity<StudentForm> updateStudents(@RequestBody StudentForm student) {
//		StudentForm s = stduentService.updateStudents(student);
//		return new ResponseEntity<StudentForm>(s, HttpStatus.OK);
//	}

	@PostMapping
	public ResponseEntity<StudentForm> addStudents(@RequestParam("file") MultipartFile file,
			@RequestParam("rfid_Number") long rfid_Number, @RequestParam("First_name") String First_name,
			@RequestParam("Last_name") String Last_name, @RequestParam("department") String department,
			@RequestParam("aadhar_number") String aadhar_number, @RequestParam("mob_number") long mob_number,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_time)
			throws IOException, SQLException {
		StudentForm s = studentService.addStudent(file, rfid_Number, First_name, Last_name, department, aadhar_number,
				mob_number, address, email, date_time);
		return new ResponseEntity<StudentForm>(s, HttpStatus.CREATED);
	}

	@DeleteMapping("/{student_id}")
	public String deleteStudentForm(@PathVariable("student_id") int student_id) {
		studentService.deleteStudent(student_id);
		return "delete Record";
	}

	/*@PutMapping("/{id}")
	public StudentForm updateStudentByField(@PathVariable int id, @RequestBody StudentForm stform) {
		return studentService.updateStudentByField(id, stform);
	}*/
	
	
	@Autowired
	StudentFormRepo studentFormRepo;
	
	@PutMapping("/{id}")
	public ResponseEntity<StudentForm> updateStudentByField(@PathVariable int id, @RequestParam("file") MultipartFile file,
			@RequestParam("rfid_Number") long rfid_Number, @RequestParam("First_name") String First_name,
			@RequestParam("Last_name") String Last_name, @RequestParam("department") String department,
			@RequestParam("aadhar_number") String aadhar_number, @RequestParam("mob_number") long mob_number,
			@RequestParam("address") String address, @RequestParam("email") String email,
			@RequestParam("date_time") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date_time) throws SerialException, SQLException, IOException {
	    Optional<StudentForm> optionalStudent = studentFormRepo.findById(id);
	    if (!optionalStudent.isPresent()) {
	        return ResponseEntity.notFound().build();
	    }

	    StudentForm student = optionalStudent.get();
	    
	    // Update student fields with request parameters
	    student.setRfid_Number(rfid_Number);
	    student.setFirst_name(First_name);
	    student.setLast_name(Last_name);
	    student.setDepartment(department);
	    student.setAadhar_number(aadhar_number);
	    student.setMob_number(mob_number);
	    student.setAddress(address);
	    student.setEmail(email);
	    student.setDate_time(date_time);
	    
	    if (!file.isEmpty()){
	    	System.out.println("File is not empty");
	        byte[] photoBytes = file.getBytes();
	        Blob photoBlob = new SerialBlob(photoBytes);
	        student.setImage(photoBlob);
	    }

	    try {
	        return ResponseEntity.ok(studentService.saveStudent(student));
	    } catch (Exception e) {
	        e.printStackTrace(); // Handle or log the exception appropriately
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
	}

	private StudentFormResponse getStudentFormResponse(StudentForm room) {
		byte[] photoBytes = null;
		Blob photoBlob = room.getImage();
		if (photoBlob != null) {
			try {
				photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
			} catch (SQLException e) {
				throw new NotFoundException("Error retrieving photo");
			}
		}

		return new StudentFormResponse(room.getStudent_id(), room.getRfid_Number(), room.getFirst_name(),
				room.getLast_name(), room.getDepartment(), room.getAadhar_number(), room.getMob_number(),
				room.getAddress(), room.getEmail(), room.getDate_time());

	}
}
