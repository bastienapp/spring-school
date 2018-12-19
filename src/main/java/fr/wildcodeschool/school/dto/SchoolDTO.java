package fr.wildcodeschool.school.dto;

import java.util.List;

import fr.wildcodeschool.school.entity.School;
import fr.wildcodeschool.school.entity.Student;

public class SchoolDTO {

	private School school;
	
	private List<Student> students;

	public SchoolDTO() {
	}

	public School getSchool() {
		return school;
	}

	public void setSchool(School school) {
		this.school = school;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
}
