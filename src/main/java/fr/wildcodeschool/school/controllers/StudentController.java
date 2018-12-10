package fr.wildcodeschool.school.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.wildcodeschool.school.exceptions.ResourceNotFoundException;
import fr.wildcodeschool.school.models.Student;
import fr.wildcodeschool.school.repositories.SchoolRepository;
import fr.wildcodeschool.school.repositories.StudentRepository;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	StudentRepository studentRepository;
	
	@Autowired
	SchoolRepository schoolRepository;
	
	@GetMapping("/school/{schoolId}/student")
    public Page<Student> getAllStudentsBySchoolId(@PathVariable (value = "schoolId") Long schoolId,
                                                Pageable pageable) {
        return studentRepository.findBySchoolId(schoolId, pageable);
    }
	
	@PostMapping("/school/{schoolId}/student")
	public Student createStudent(@PathVariable (value = "schoolId") Long schoolId,
												@Valid @RequestBody Student student) {
		return schoolRepository.findById(schoolId).map(school -> {
			student.setSchool(school);
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
	}
	
	@PutMapping("/school/{schoolId}/student/{id}")
	public Student updateStudent(@PathVariable (value = "schoolId") Long schoolId,
											@PathVariable(value = "id") Long studentId,
	                                        @Valid @RequestBody Student studentDetails) {

		if(!schoolRepository.existsById(schoolId)) {
            throw new ResourceNotFoundException("School", "id", schoolId);
        }

        return studentRepository.findById(studentId).map(student -> {
        	student.setName(studentDetails.getName());
            return studentRepository.save(student);
        }).orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
	}

	
	@DeleteMapping("/school/{schoolId}/student/{id}")
	public ResponseEntity<?> deleteStudent(@PathVariable (value = "schoolId") Long schoolId,
											@PathVariable(value = "id") Long studentId) {
		
		if(!schoolRepository.existsById(schoolId)) {
            throw new ResourceNotFoundException("School", "id", schoolId);
        }
		
		return studentRepository.findById(studentId).map(student -> {
			studentRepository.delete(student);
            return ResponseEntity.ok().build();
       }).orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
	}
}
