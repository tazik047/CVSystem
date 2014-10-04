package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.Student;

public interface StudentDAO {
	
	Collection<Student> getStudents();
	
	Student getStudent(long studentsId);
		
	Boolean insertStudent(Student student);
	
	Boolean deleteStudent(Student student);

}
