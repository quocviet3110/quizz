package com.thitracnghiem.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.StudentDTO;
import com.thitracnghiem.service.IStudentService;
@CrossOrigin
@RestController(value = "studentAPIofWeb")
public class StudentAPI {
	@Autowired
	private IStudentService iStudentService;
	
	@PostMapping(name="/api/web/student")
	public StudentDTO created(@RequestBody StudentDTO studentDTO ) {	
		return iStudentService.save(studentDTO);
	}
	@PutMapping(name="/api/web/student")
	public StudentDTO update(@RequestBody StudentDTO studentDTO ) {	
		return iStudentService.save(studentDTO);
	}


}
