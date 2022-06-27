package com.thitracnghiem.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.TeacherDTO;
import com.thitracnghiem.entity.AccountEntity;
import com.thitracnghiem.entity.TeacherEntity;
import com.thitracnghiem.repository.AccountRepository;
import com.thitracnghiem.repository.TeacherRepository;
import com.thitracnghiem.service.ITeacherService;

@Service
public class TeacherService implements ITeacherService {
	private ModelMapper mapper;
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private AccountRepository accountRepository;

	public TeacherService(ModelMapper mapper, TeacherRepository teacherRepository) {
		super();
		this.mapper = mapper;
		this.teacherRepository = teacherRepository;
	}

	private TeacherDTO mapToDto(TeacherEntity teacherEntity) {
		TeacherDTO teacherDTO = mapper.map(teacherEntity, TeacherDTO.class);
		return teacherDTO;
	}

	@SuppressWarnings("unused")
	private TeacherEntity mapToEntity(TeacherDTO teacherDTO) {
		TeacherEntity teacherEntity = mapper.map(teacherDTO, TeacherEntity.class);
		return teacherEntity;
	}
	
	private TeacherEntity mapToEntity(TeacherEntity teacherEntity,TeacherDTO teacherDTO) {
		teacherEntity = mapper.map(teacherDTO, TeacherEntity.class);
		return teacherEntity;
	}

	@Override
	public TeacherDTO findOneByUsername(String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		return mapToDto(teacherEntity);
	}

	@Override
	@Transactional
	public TeacherDTO save(TeacherDTO teacherDTO) {
		AccountEntity accountEntity = accountRepository.findOneByUsername(teacherDTO.getUsername());
		TeacherEntity teacherEntity = new TeacherEntity();
		if (teacherDTO.getId() != null) {
			 TeacherEntity old = teacherRepository.findOne(teacherDTO.getId());
			 teacherEntity = this.mapToEntity(old,teacherDTO);
			 teacherEntity.setAccountTeacher(accountEntity);
		} else {
				teacherEntity = this.mapToEntity(teacherDTO);
				teacherEntity.setAccountTeacher(accountEntity);
		}
		TeacherDTO dto = this.mapToDto(teacherRepository.save(teacherEntity));
		return dto;
	}

}
