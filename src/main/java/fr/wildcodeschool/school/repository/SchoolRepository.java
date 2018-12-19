package fr.wildcodeschool.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.wildcodeschool.school.entity.School;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

}
