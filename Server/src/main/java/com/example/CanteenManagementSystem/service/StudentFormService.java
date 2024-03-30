package com.example.CanteenManagementSystem.service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
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
 public List<StudentForm> getAllStudents(){
	List<StudentForm> sf=studentRepo.findAll();
	if(sf.size()>0) {
		return sf;
	}else {
		throw new NotFoundException("no records found");
	}
 }
 public Blob getStudentPhotoByStudentId(int id) throws SQLException {
     Optional<StudentForm> student = studentRepo.findById(id);
     if(student.isEmpty()){
         throw new NotFoundException("Sorry, Student not found!");
     }
     Blob photoBlob = student.get().getImage();
     if(photoBlob != null){
         return photoBlob;
     }
     return null;
 }
//public StudentForm updateStudents(StudentForm student) {
//	Optional<StudentForm> s=studentRepo.findById((int) student.getStudent_id());
//	if(s.isPresent()) {
//		StudentForm sf=s.get();
//		if(student.getFirst_name()!=null) {
//			sf.setFirst_name(student.getFirst_name());
//		}
//		if(student.getLast_name()!=null) {
//			sf.setFirst_name(student.getLast_name());
//		}
//		if(student.getAadhar_number()!=0) {
//			sf.setAadhar_number(student.getAadhar_number());
//		if(student.getAddress()!=null) {
//			sf.setAddress(student.getAddress());
//		}
//		if(student.getDepartment()!=null) {
//			sf.setDepartment(student.getDepartment());
//		}
//		if(student.getMob_number()!=0) {
//			sf.setMob_number(student.getMob_number());
//		}
//		if(student.getEmail()!=null) {
//			sf.setEmail(student.getEmail());
//		}
//		if(student.getDate_time()!=null) {
//			sf.setDate_time(student.getDate_time());
//		}
//		if(student.getImage()!=null) {
//			sf.setImage(student.getImage());
//		}
//		studentRepo.save(sf);
//		return sf;
//		}
//		else {
//			student=studentRepo.save(student);
//			return student;
//		}
//	}
//
//	return null;
//}

 public StudentForm addStudent(MultipartFile file, long rfid_Number, String first_name, String last_name,
			String department, String aadhar_number, long mob_number, String address, String email, Date date_time) throws IOException, SQLException{
	StudentForm s=new StudentForm();
	s.setRfid_Number(rfid_Number);
	s.setFirst_name(first_name);
	s.setLast_name(last_name);
	s.setDepartment(department);
    s.setAadhar_number(aadhar_number);
    s.setAddress(address);
    s.setMob_number(mob_number);
    s.setEmail(email);
    s.setDate_time(date_time);
    
    if (!file.isEmpty()){
        byte[] photoBytes = file.getBytes();
        Blob photoBlob = new SerialBlob(photoBytes);
        s.setImage(photoBlob);
    }
 StudentForm s1=studentRepo.save(s);
	return s1;
}
public void deleteStudent(int student_id) {
	studentRepo.deleteById(student_id);
	
}

/*public StudentForm updateStudentByField(int id, Map<String, Object> fields) {
		Optional<StudentForm> f=studentRepo.findById(id);
		if(f.isPresent()) {
		fields.forEach((key,value)->{
			Field field=ReflectionUtils.findField(StudentForm.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, f.get(), value);
		});
		return studentRepo.save(f.get());
	}

	return null;
}*/

    // 
	public StudentForm updateStudentByField(int id, StudentForm stform) {
        Optional<StudentForm> optionalStudent = studentRepo.findById(id);
        if (optionalStudent.isPresent()) {
            StudentForm student = optionalStudent.get();

            // Get the fields from stform and update the corresponding fields in student
            for (Field field : StudentForm.class.getDeclaredFields()) {
                field.setAccessible(true);
                try {
                    Object value = field.get(stform);
                    if (value != null) {
                        // Special handling for Date fields
                        if (field.getType() == Date.class && value instanceof String) {
                            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                            Date parsedDate = formatter.parse((String) value);
                            field.set(student, parsedDate);
                        } else {
                            field.set(student, value);
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    // Handle the exception as per your requirement
                }
            }

            try {
                // Save the updated student entity
                return studentRepo.save(student);
            } catch (Exception e) {
                e.printStackTrace();
                // Handle the exception as per your requirement
            }
        } else {
            // Handle the case when the student with the given id is not found
            // You might want to throw an exception or return an appropriate response
        }

        return null; // This line will never be reached if the student is saved successfully
    }
}
	

public StudentForm getStudentById(int student_id) {
	Optional <StudentForm>s=studentRepo.findById(student_id);
	if(s.isPresent()) {
		StudentForm f=s.get();
		return f;
	}
	else {
		throw new NotFoundException("no Records found");

	}
}
}


