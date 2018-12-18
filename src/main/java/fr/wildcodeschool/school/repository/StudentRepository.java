package fr.wildcodeschool.school.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	
	Page<Student> findBySchoolId(Long schoolId, Pageable pageable);
}
