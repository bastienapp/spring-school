package fr.wildcodeschool.school.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.wildcodeschool.school.dto.LanguageDTO;
import fr.wildcodeschool.school.dto.SchoolDTO;
import fr.wildcodeschool.school.entity.School;
import fr.wildcodeschool.school.entity.Student;
import fr.wildcodeschool.school.exception.ResourceNotFoundException;
import fr.wildcodeschool.school.repository.SchoolRepository;
import fr.wildcodeschool.school.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class SchoolController {

	@Autowired
	SchoolRepository schoolRepository;

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/school")
	public List<School> getAllSchools() {
	    return schoolRepository.findAll();
	}
	
	@PostMapping("/school")
	public School createSchool(@Valid @RequestBody School school) {
	    return schoolRepository.save(school);
	}
	
	@GetMapping("/school/{id}")
	public SchoolDTO getSchoolById(@PathVariable(value = "id") Long schoolId) {
		return schoolRepository.findById(schoolId).map(school -> {
			
			Page<Student> students = studentRepository.findBySchoolId(schoolId, null);
			
			SchoolDTO schoolDTO = new SchoolDTO();
			schoolDTO.setSchool(school);
			schoolDTO.setStudents(students.getContent());
			
            return schoolDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));
	}
	
	@PutMapping("/school/{id}")
	public School updateSchool(@PathVariable(value = "id") Long schoolId,
	                                        @Valid @RequestBody School schoolDetails) {

	    School school = schoolRepository.findById(schoolId)
	            .orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));

	    school.setName(schoolDetails.getName());
	    school.setAddress(schoolDetails.getAddress());

	    School updatedSchool = schoolRepository.save(school);
	    return updatedSchool;
	}
	
	@DeleteMapping("/school/{id}")
	public ResponseEntity<?> deleteSchool(@PathVariable(value = "id") Long schoolId) {
	    School school = schoolRepository.findById(schoolId)
	            .orElseThrow(() -> new ResourceNotFoundException("School", "id", schoolId));

	    schoolRepository.delete(school);

	    return ResponseEntity.ok().build();
	}
}
