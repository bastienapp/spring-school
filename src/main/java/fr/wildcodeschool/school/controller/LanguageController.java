package fr.wildcodeschool.school.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import fr.wildcodeschool.school.entity.Language;
import fr.wildcodeschool.school.entity.Student;
import fr.wildcodeschool.school.exception.ResourceNotFoundException;
import fr.wildcodeschool.school.repository.LanguageRepository;
import fr.wildcodeschool.school.repository.StudentRepository;

@RestController
@RequestMapping("/api")
public class LanguageController {

	@Autowired
	LanguageRepository repository;

	@Autowired
	StudentRepository studentRepository;
	
	@GetMapping("/language")
	public List<Language> getAll() {
	    return repository.findAll();
	}
	
	@PostMapping("/language")
	public Language create(@Valid @RequestBody Language model) {
	    return repository.save(model);
	}
	
	@GetMapping("/language/{id}")
	public LanguageDTO getStudentById(@PathVariable(value = "id") Long id) {
	    
		return repository.findById(id).map(language -> {
			
			LanguageDTO languageDTO = new LanguageDTO();
			languageDTO.setLanguage(language);
			languageDTO.setStudents(language.getStudents());
            
            return languageDTO;
        }).orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));
	}
	
	@PutMapping("/language/{id}")
	public Language update(@PathVariable(value = "id") Long id,
	                                        @Valid @RequestBody Language model) {

		Language updated = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));

	    updated.setName(model.getName());

	    return repository.save(updated);
	}
	
	@DeleteMapping("/language/{id}")
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		Language model = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));

	    repository.delete(model);

	    return ResponseEntity.ok().build();
	}

	@PostMapping("/student/{studentId}/language/{id}")
	public ResponseEntity<?> addLanguageToStudent(@PathVariable(value = "id") Long id,
											@PathVariable(value = "studentId") Long studentId) {
		Language language = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));
		
		Student student = studentRepository.findById(studentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

		student.getLanguages().add(language);
		language.getStudents().add(student);
		studentRepository.save(student);
		
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/student/{studentId}/language/{id}")
	public ResponseEntity<?> removeLanguageFromStudent(@PathVariable(value = "id") Long id,
											@PathVariable(value = "studentId") Long studentId) {
		Language language = repository.findById(id)
	            .orElseThrow(() -> new ResourceNotFoundException("Language", "id", id));
		
		Student student = studentRepository.findById(studentId)
	            .orElseThrow(() -> new ResourceNotFoundException("Student", "id", id));

		student.getLanguages().remove(language);
		language.getStudents().remove(student);
		studentRepository.save(student);
		
		return ResponseEntity.ok().build();
	}
}
