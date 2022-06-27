package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.entity.MarkEntity;
import com.thitracnghiem.entity.StudentEntity;
import com.thitracnghiem.entity.TeacherEntity;
import com.thitracnghiem.repository.MarkRepository;
import com.thitracnghiem.repository.StudentRepository;
import com.thitracnghiem.repository.TeacherRepository;
import com.thitracnghiem.service.IMarkService;

@Service
public class MarkService implements IMarkService  {
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private MarkRepository markRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private StudentRepository studentRepository;
	
	private MarkDTO mapToDto(MarkEntity markEntity) {
		MarkDTO markDTO = mapper.map(markEntity, MarkDTO.class);
		markDTO.setNameSubject(markEntity.getExamMark().getSubjects().getName());
		return markDTO;
	}

	private MarkEntity mapToEntity(MarkDTO markDTO) {
		MarkEntity markEntity = mapper.map(markDTO, MarkEntity.class);
		return markEntity;
	}
	private MarkEntity mapToEntity(MarkEntity markEntity,MarkDTO markDTO) {
		markEntity = mapper.map(markDTO, MarkEntity.class);
		return markEntity;
	}
	
	@Override
	public List<MarkDTO> findAllByTeacher(String username, Pageable pageable) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		List<MarkDTO> dto = new ArrayList<>();
		List<MarkEntity> entities = markRepository.findAllByTeacher(teacherEntity.getId(),pageable);
		for (MarkEntity item : entities) {
			MarkDTO markDTO = this.mapToDto(item);
			dto.add(markDTO);
			
		}
		return dto;
	}


	@Override
	public int getTotalItemByTeacher(String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		return (int) markRepository.countByTeacher(teacherEntity.getId());
	}

	@Override
	public List<MarkDTO> search(String keyword, String username, Pageable pageable) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		List<MarkDTO> dto = new ArrayList<>();
		List<MarkEntity> entities = markRepository.findAllByTeacherAndKeyword(keyword,teacherEntity.getId(),pageable);
		for (MarkEntity item : entities) {
			MarkDTO markDTO = this.mapToDto(item);
			dto.add(markDTO);
			
		}
		return dto;
	}

	@Override
	public int getTotalItem(String keyword, String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		return markRepository.countByTeacherAndKeyword(keyword, teacherEntity.getId());
	}

	@Override
	public List<MarkDTO> findAllByStudent(String username) {
		StudentEntity studentEntity = studentRepository.findOneByUsername(username);
		List<MarkDTO> dto = new ArrayList<>();
		List<MarkEntity> entities = markRepository.findAllByStudent(studentEntity.getId());
		for (MarkEntity item : entities) {
			MarkDTO markDTO = this.mapToDto(item);
			dto.add(markDTO);
			
		}
		return dto;
	}

}
