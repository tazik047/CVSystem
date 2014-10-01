package ua.nure.pi.dao;

import java.util.Collection;

import ua.nure.pi.entity.AnyTag;
import ua.nure.pi.entity.Student;

public interface StudentDAO {
	
	Student getStudent(long studentsId);
		
	Boolean insertStudent(Student student);
	
	Boolean deleteStudent(Student student);

}
