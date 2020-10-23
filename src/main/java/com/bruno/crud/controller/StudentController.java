package com.bruno.crud.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bruno.crud.model.Student;
import com.bruno.crud.service.StudentService;

@RestController
@RequestMapping("/api")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	@CrossOrigin
	@GetMapping(value = "/students")
	public List<Student> getStudents(){
		List<Student> alunos = studentService.getStudents();		
		return alunos;
	}
	
	@CrossOrigin
	@PostMapping(value = "/students")
	public ResponseEntity<?> insertStudent(@RequestBody Student body) {
		System.out.println(body);
		if (body.getRa() != null) {
			String response = studentService.insertStudent(body);
			System.out.println(response);
			if (response.equals(body.getName())) {
				return new ResponseEntity<>(response.toString(), HttpStatus.OK);
			}
			return new ResponseEntity<>(response.toString(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Nenhum RA fornecido", HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@PutMapping(value = "/students/{ra}")
	public ResponseEntity<?> updateStudent(@RequestBody Student body, @PathVariable Long ra) throws ParseException {
		if (ra != null) {
			Integer response = studentService.updateStudent(ra, body);
			if (response > 0) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			return new ResponseEntity<>("Houve algum erro ao atualizar os dados do aluno!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Nenhum RA fornecido", HttpStatus.BAD_REQUEST);
	}
	
	@CrossOrigin
	@DeleteMapping(value = "/students/{ra}")
	public ResponseEntity<?> deleteStudent(@PathVariable Long ra) {
		if (ra != null) {
			Integer response = studentService.deleteStudent(ra);
			if (response > 0) {
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
			return new ResponseEntity<>("Houve algum erro ao excluir os dados do aluno!", HttpStatus.OK);
		}
		return new ResponseEntity<>("Nenhum RA fornecido", HttpStatus.BAD_REQUEST);	}
}
