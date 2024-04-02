package com.example.CanteenManagementSystem.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.CanteenManagementSystem.model.FoodInventory;
import com.example.CanteenManagementSystem.model.NotFoundException;
import com.example.CanteenManagementSystem.model.StudentForm;
import com.example.CanteenManagementSystem.repository.StudentFormRepo;

@Service
public class StudentFormService {
	@Autowired
	StudentFormRepo studentRepo;

	public List<StudentForm> getAllStudents() {
		List<StudentForm> sf = studentRepo.findAll();
		if (sf.size() > 0) {
			return sf;
		} else {
			throw new NotFoundException("no records found");
		}
	}

	public Blob getStudentPhotoByStudentId(int id) throws SQLException {
		Optional<StudentForm> student = studentRepo.findById(id);
		if (student.isEmpty()) {
			throw new NotFoundException("Sorry, Student not found!");
		}
		Blob photoBlob = student.get().getImage();
		if (photoBlob != null) {
			return photoBlob;
		}
		return null;
	}

	public StudentForm addStudent(MultipartFile file, long rfid_Number, String first_name, String last_name,
			String department, String aadhar_number, long mob_number, String address, String password, String email,
			LocalDate date_time,int wallet) throws IOException, SQLException {
		StudentForm s = new StudentForm();
		s.setRfid_Number(rfid_Number);
		s.setFirst_name(first_name);
		s.setLast_name(last_name);
		s.setDepartment(department);
		s.setAadhar_number(aadhar_number);
		s.setAddress(address);
		s.setPassword(password);
		s.setMob_number(mob_number);
		s.setEmail(email);
		s.setDate_time(date_time);
		s.setWallet(wallet);
		if (!file.isEmpty()) {
			byte[] photoBytes = file.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			s.setImage(photoBlob);
		}
		StudentForm s1 = studentRepo.save(s);
		return s1;
	}

	public void deleteStudent(int student_id) {
		studentRepo.deleteById(student_id);

	}

	public void updateStudentFields(StudentForm student, Map<String, Object> fields)
			throws IllegalAccessException, NoSuchFieldException, SerialException, SQLException, IOException {
		for (Map.Entry<String, Object> entry : fields.entrySet()) {
			String fieldName = entry.getKey();
			Object value = entry.getValue();
			System.out.println(fieldName + "--------------");

			try {
				Field field = student.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);

				if ("file".equals(fieldName)) { // Check if the field is 'file'
					MultipartFile file = (MultipartFile) value;
					if (!file.isEmpty()) {
						byte[] photoBytes = file.getBytes();
						Blob photoBlob = new SerialBlob(photoBytes);
						field.set(student, photoBlob);
					} else {
						field.set(student, null);
					}
				}
				if (field.getType() == LocalDate.class && value instanceof String) {
					// Convert String to LocalDate
					LocalDate newValue = LocalDate.parse((String) value, DateTimeFormatter.ISO_DATE);
					field.set(student, newValue);
				} else if (field.getType() == long.class && value instanceof String) {
					// Convert String to long
					long newValue = Long.parseLong((String) value);
					field.set(student, newValue);
				} else {
					field.set(student, value);
				}
			} catch (NoSuchFieldException e) {
				// Handle the case where the field doesn't exist
				System.out.println("Field '" + fieldName + "' does not exist in StudentForm class.");
			}
		}
	}

	public StudentForm saveStudent(StudentForm student) {
		return studentRepo.save(student);
	}

	public StudentForm getStudentById(int student_id) {
		Optional<StudentForm> s = studentRepo.findById(student_id);
		if (s.isPresent()) {
			StudentForm f = s.get();
			return f;
		} else {
			throw new NotFoundException("no Records found");

		}
	}

	
}
