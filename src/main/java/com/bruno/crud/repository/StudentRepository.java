package com.bruno.crud.repository;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bruno.crud.model.Student;
import com.bruno.crud.service.StudentService;

@Repository
public class StudentRepository {
	
	@Autowired
	NamedParameterJdbcTemplate jdbcStudent;
	
	@Autowired
	private StudentService studentService;
	
	
	public List<Student> getStudents(){
		Map<String, Object> argMap = new HashMap<>();
		
		StringBuilder sql = new StringBuilder("select * from public.students;");
		
		List<Student> sudents = new ArrayList<>();
		List<Map<String, Object>> rows = jdbcStudent.queryForList(sql.toString(), argMap);
		
		for(Map<String, Object> row:rows) {
			Student student = new Student();
			student.setCpf((String) row.get("cpf"));
			student.setEmail((String) row.get("email"));
			student.setName((String) row.get("name"));
			student.setRa((Long) row.get("ra"));
			sudents.add(student);
		}
		
		return sudents;

	}
	
	public String insertStudent(Student body) {
		
		String sql = "INSERT INTO public.students(ra, name, email, cpf) VALUES (:ra, :name, :email, :cpf) RETURNING name";
		
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("ra", body.getRa());
		argMap.put("name", body.getName());
		argMap.put("email", body.getEmail());
		argMap.put("cpf", studentService.formatCPF(body.getCpf()));
		
		List<Map<String, Object>> rows = jdbcStudent.queryForList(sql, argMap);
		if(rows != null && !rows.isEmpty()) {
			String studentNameInserted = (String) rows.get(0).get("name") ;
			return studentNameInserted;
		} else {
			return null;
		}

	}
	
	public Integer updateStudent(Long ra, Student body) {
		String sql = "UPDATE public.students set name = :name, email = :email, cpf = :cpf where ra = :ra";
		
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("ra", ra);
		argMap.put("name", body.getName());
		argMap.put("email", body.getEmail());
		argMap.put("cpf", studentService.formatCPF(body.getCpf()));
		
		Integer rows = jdbcStudent.update(sql, argMap);
		
		return rows;
		
	}
	
	public Integer deleteStudent(Long ra) {
		String sql = "DELETE from public.students where ra = :ra";
		Map<String, Object> argMap = new HashMap<String, Object>();
		argMap.put("ra", ra);
		
		Integer rows = jdbcStudent.update(sql, argMap);
		
		return rows;
	}
}
