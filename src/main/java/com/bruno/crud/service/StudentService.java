package com.bruno.crud.service;

import java.text.ParseException;
import java.util.List;

import javax.swing.text.MaskFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bruno.crud.model.Student;
import com.bruno.crud.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository studentRepository;
	
	public List<Student> getStudents() {
		return studentRepository.getStudents();
	} 
	
	public String insertStudent(Student body) {
		return studentRepository.insertStudent(body);
	}
	
	public Integer updateStudent(Long ra, Student body) throws ParseException {
		return studentRepository.updateStudent(ra, body);
	}
	
	public Integer deleteStudent(Long ra) {
		return studentRepository.deleteStudent(ra);
	}
	
	public String formatCPF(String cpf) {
		MaskFormatter formatter;
		try {
			formatter = new MaskFormatter("###.###.###-##");
			formatter.setValueContainsLiteralCharacters(false);
			return formatter.valueToString(cpf);
		} catch (ParseException ex) {
			return cpf;
		}
		
	}

}
