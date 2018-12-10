package fr.wildcodeschool.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.models.School;

public interface SchoolRepository extends JpaRepository<School, Long> {

}
