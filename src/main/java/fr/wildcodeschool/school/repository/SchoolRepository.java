package fr.wildcodeschool.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.entity.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}
