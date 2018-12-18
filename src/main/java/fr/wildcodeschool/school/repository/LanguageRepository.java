package fr.wildcodeschool.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
