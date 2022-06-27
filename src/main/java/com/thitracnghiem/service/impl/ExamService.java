package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.ExamDTO;
import com.thitracnghiem.entity.DetailExamEntity;
import com.thitracnghiem.entity.ExamEntity;
import com.thitracnghiem.entity.SubjectsEntity;
import com.thitracnghiem.entity.TeacherEntity;
import com.thitracnghiem.repository.DetailExamRepository;
import com.thitracnghiem.repository.ExamRepository;
import com.thitracnghiem.repository.SubjectsRepository;
import com.thitracnghiem.repository.TeacherRepository;
import com.thitracnghiem.service.IExamService;

@Service
public class ExamService implements IExamService {
	private ModelMapper mapper;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SubjectsRepository subjectsRepository;
	
	@Autowired
	private DetailExamRepository detailExamRepository;
	
	public ExamService(ModelMapper mapper, ExamRepository examRepository) {
		super();
		this.mapper = mapper;
		this.examRepository = examRepository;
	}

	private ExamDTO mapToDto(ExamEntity examEntity) {
		ExamDTO examDTO = mapper.map(examEntity, ExamDTO.class);
		examDTO.setIdSubject(examEntity.getSubjects().getId());
		examDTO.setNameSubject(examEntity.getSubjects().getName());
		return examDTO;
	}

	private ExamEntity mapToEntity(ExamDTO examDTO) {
		ExamEntity examEntity = mapper.map(examDTO, ExamEntity.class);
		return examEntity;
	}
	private ExamEntity mapToEntity(ExamEntity entity,ExamDTO examDTO) {
		entity = mapper.map(examDTO, ExamEntity.class);
		return entity;
	}

	@Override
	@Transactional
	public ExamDTO save(ExamDTO examDTO) {
		TeacherEntity teacher = teacherRepository.findOneByUsername(examDTO.getUsername());
		SubjectsEntity subjects = subjectsRepository.findOneByName(examDTO.getNameSubject());
		ExamEntity examEntity = new ExamEntity();
		if (examDTO.getId() != null) {
			ExamEntity oldExam = examRepository.findOne(examDTO.getId());		
			examEntity = this.mapToEntity(oldExam,examDTO);
			examEntity.setSubjects(subjects);
			examEntity.setTeacherExam(teacher);
			
		} else {
			examEntity = this.mapToEntity(examDTO);
			examEntity.setSubjects(subjects);
			examEntity.setTeacherExam(teacher);
		}
		return this.mapToDto(examRepository.save(examEntity));
	}

	@Override
	public ExamDTO findById(int id) {
		ExamEntity entity = examRepository.findOne(id);
		return this.mapToDto(entity);
	}

	@Override
	@Transactional
	public void delete(int[] ids) {
		for (int id : ids) {
			List<DetailExamEntity> detailExamEntities = detailExamRepository.findAllByExam(id);
			for(DetailExamEntity entity : detailExamEntities) {
				detailExamRepository.delete(entity.getIdKeys());
			}
			examRepository.delete(id);
		}
		
	}

	@Override
	public List<ExamDTO> findByTeacher(String username, Pageable pageable) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		List<ExamDTO> dto = new ArrayList<>();	
		List<ExamEntity> entities = examRepository.findAllByTeacher(teacherEntity.getId(),pageable);
		for (ExamEntity item : entities) {
			ExamDTO examDTO = this.mapToDto(item);
			dto.add(examDTO);
		}
		return dto;
	}

	@Override
	public int getTotalItemFindByTeacher(String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);	
		return examRepository.count(teacherEntity.getId());
	}

	@Override
	public List<ExamDTO> findAllBySubject(int idSubject) {
		SubjectsEntity subjectsEntity = subjectsRepository.findOne(idSubject);
		List<ExamDTO> dto = new ArrayList<>();	
		List<ExamEntity> entities = examRepository.findAllBySubject(subjectsEntity.getId());
		for (ExamEntity item : entities) {
			ExamDTO examDTO = this.mapToDto(item);
			dto.add(examDTO);
		}
		return dto;
	}

	@Override
	public List<ExamDTO> findAll(Pageable pageable) {
		List<ExamDTO> dto = new ArrayList<>();	
		List<ExamEntity> entities = examRepository.findAll(pageable).getContent();
		for (ExamEntity item : entities) {
			ExamDTO examDTO = this.mapToDto(item);
			dto.add(examDTO);
		}
		return dto;
	}

	@Override
	public int getTotalItem() {
		return (int) examRepository.count();
	}

	@Override
	public int getTotalItem(String keyword) {
		return (int) examRepository.count(keyword);
	}

	@Override
	public List<ExamDTO> search(String keyword, Pageable pageable) {
		List<ExamDTO> dto = new ArrayList<>();	
		List<ExamEntity> entities = examRepository.search(keyword, pageable);
		for (ExamEntity item : entities) {
			ExamDTO examDTO = this.mapToDto(item);
			dto.add(examDTO);
		}
		return dto;
	}

}
