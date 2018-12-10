package fr.wildcodeschool.school.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.models.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Page<Student> findBySchoolId(Long schoolId, Pageable pageable);
}
