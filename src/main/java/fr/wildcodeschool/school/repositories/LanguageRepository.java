package fr.wildcodeschool.school.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.wildcodeschool.school.models.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
