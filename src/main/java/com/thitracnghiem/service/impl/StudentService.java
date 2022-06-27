package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.StudentDTO;
import com.thitracnghiem.entity.AccountEntity;
import com.thitracnghiem.entity.StudentEntity;
import com.thitracnghiem.repository.AccountRepository;
import com.thitracnghiem.repository.StudentRepository;
import com.thitracnghiem.service.IStudentService;

@Service
public class StudentService implements IStudentService{
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private AccountRepository accountRepository;
	private ModelMapper mapper;
	public StudentService(StudentRepository studentRepository, ModelMapper mapper) {
		super();
		this.studentRepository = studentRepository;
		this.mapper = mapper;
	}
	private StudentDTO mapToDto(StudentEntity studentEntity) {
		StudentDTO studentDTO = mapper.map(studentEntity, StudentDTO.class);
		return studentDTO;
	}

	private StudentEntity mapToEntity(StudentDTO studentDTO) {
		StudentEntity studentEntity = mapper.map(studentDTO, StudentEntity.class);
		return studentEntity;
	}
	private StudentEntity mapToEntity(StudentEntity studentEntity,StudentDTO studentDTO) {
		 studentEntity = mapper.map(studentDTO, StudentEntity.class);
		return studentEntity;
	}

	@Override
	@Transactional
	public StudentDTO save(StudentDTO studentDTO) {
		AccountEntity accountEntity = accountRepository.findOneByUsername(studentDTO.getUsername());
		StudentEntity studentEntity = new StudentEntity();
		if (studentDTO.getId() != null) {
			StudentEntity oldStudent = studentRepository.findOne(studentDTO.getId());
			oldStudent.setAccountStudent(accountEntity);
			studentEntity = this.mapToEntity(oldStudent,studentDTO);
		} else {
			studentEntity = this.mapToEntity(studentDTO);
			studentEntity.setAccountStudent(accountEntity);
		}
		return this.mapToDto(studentRepository.save(studentEntity));
	}
	@Override
	public List<StudentDTO> findAll(Pageable pageable) {
		List<StudentDTO> dto = new ArrayList<StudentDTO>(); 
		List<StudentEntity> studentEntities = studentRepository.findAll(pageable).getContent();
		for( StudentEntity entitys : studentEntities) {
			StudentDTO studentDTO = this.mapToDto(entitys);
			dto.add(studentDTO);
			
		}
		return dto;
	}
	@Override
	public int getTotalItem() {
		return (int) studentRepository.count();
	}
	@Override
	public List<StudentDTO> search(String keyword, Pageable pageable) {
		List<StudentDTO> dto = new ArrayList<StudentDTO>(); 
		List<StudentEntity> studentEntities = studentRepository.search(keyword,pageable);
		for( StudentEntity entitys : studentEntities) {
			StudentDTO studentDTO = this.mapToDto(entitys);
			dto.add(studentDTO);
			
		}
		return dto;
	}
	@Override
	public int getTotalItem(String keyword) {
		return studentRepository.count(keyword);
	}
	@Override
	public StudentDTO findOneByUsername(String username) {
		StudentEntity studentEntity = studentRepository.findOneByUsername(username);
		return mapToDto(studentEntity);
	}

}
