package fr.wildcodeschool.school.dto;

import java.util.Set;

import fr.wildcodeschool.school.entity.Language;
import fr.wildcodeschool.school.entity.Student;

public class LanguageDTO {

	private Language language;
	private Set<Student> students;
	
	public LanguageDTO() {
	}
	
	public Language getLanguage() {
		return language;
	}
	
	public void setLanguage(Language language) {
		this.language = language;
	}
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
}
