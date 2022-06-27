package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.QuestionDTO;
import com.thitracnghiem.entity.QuestionEntity;
import com.thitracnghiem.entity.SubjectsEntity;
import com.thitracnghiem.entity.TeacherEntity;
import com.thitracnghiem.repository.QuestionRepository;
import com.thitracnghiem.repository.SubjectsRepository;
import com.thitracnghiem.repository.TeacherRepository;
import com.thitracnghiem.service.IQuestionService;

@Service
public class QuestionService implements IQuestionService {
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private SubjectsRepository subjectsRepository;

	@Override
	public List<QuestionDTO> findAll(Pageable pageable) {
		List<QuestionDTO> dto = new ArrayList<>();
		List<QuestionEntity> entities = questionRepository.findAll(pageable).getContent();
		for (QuestionEntity item : entities) {

			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);

			/*
			 * QuestionDTO questionDTO = new QuestionDTO(); questionDTO.setId(item.getId());
			 * questionDTO.setQues_content(item.getQues_content());
			 * questionDTO.setA_content(item.getA_content());
			 * questionDTO.setB_content(item.getB_content());
			 * questionDTO.setC_content(item.getC_content());
			 * questionDTO.setD_content(item.getD_content());
			 * questionDTO.setAnswer(item.getAnswer());
			 * questionDTO.setLever(item.getLever());
			 * questionDTO.setNameTeacher(item.getTeacherQuestion().getName());
			 * questionDTO.setNameSubject(item.getSubjects().getName());
			 * questionDTO.setId_subject(item.getSubjects().getId());
			 * questionDTO.setNameSubject(item.getSubjects().getName());
			 * dto.add(questionDTO);
			 */
		}
		return dto;
	}

	@Override
	public int getTotalItem() {
		return (int) questionRepository.count();
	}

	@Override
	public QuestionDTO findById(int id) {
		QuestionEntity question = questionRepository.findOne(id);
		return mapToDto(question);
	}

	private QuestionDTO mapToDto(QuestionEntity questionEntity) {
		QuestionDTO questionDTO = mapper.map(questionEntity, QuestionDTO.class);
		questionDTO.setIdSubject(questionEntity.getSubjects().getId());
		questionDTO.setNameSubject(questionEntity.getSubjects().getName());
		return questionDTO;
	}

	private QuestionEntity mapToEntity(QuestionDTO questionDTO) {
		QuestionEntity questionEntity = mapper.map(questionDTO, QuestionEntity.class);
		return questionEntity;
	}
	private QuestionEntity mapToEntity(QuestionEntity questionEntity,QuestionDTO questionDTO) {
		questionEntity = mapper.map(questionDTO, QuestionEntity.class);
		return questionEntity;
	}

	public QuestionService(QuestionRepository questionRepository, ModelMapper mapper,
			TeacherRepository teacherRepository, SubjectsRepository subjectsRepository) {
		super();
		this.questionRepository = questionRepository;
		this.mapper = mapper;
		this.teacherRepository = teacherRepository;
		this.subjectsRepository = subjectsRepository;
	}

	@Override
	@Transactional
	public QuestionDTO save(QuestionDTO questionDTO) {
		TeacherEntity teacher = teacherRepository.findOneByUsername(questionDTO.getNameTeacher());
		SubjectsEntity subjects = subjectsRepository.findOneByName(questionDTO.getNameSubject());
		QuestionEntity questionEntity = new QuestionEntity();
		if (questionDTO.getId() != null) {
			 QuestionEntity old = questionRepository.findOne(questionDTO.getId());
			 questionEntity = this.mapToEntity(old,questionDTO);
			 questionEntity.setSubjects(subjects);
			 questionEntity.setTeacherQuestion(teacher);
		} else {
			questionEntity = this.mapToEntity(questionDTO);
			questionEntity.setSubjects(subjects);
			questionEntity.setTeacherQuestion(teacher);
		}
		QuestionDTO dto = this.mapToDto(questionRepository.save(questionEntity));
		return dto;
	}

	@Override
	@Transactional
	public int delete(int[] ids) {
		for (int id : ids) {
			questionRepository.delete(id);
		}
		boolean check = false;
		for (int id : ids) {
			QuestionEntity questionEntity =questionRepository.findOne(id);
			check = true;
		}	
		if(check) {
			return 0;
		}else {
			return 1;
		}
	}

	@Override
	public List<QuestionDTO> search(String keyword, Pageable pageable) {
		List<QuestionDTO> dto = new ArrayList<>();
		List<QuestionEntity> entities = questionRepository.search(keyword, pageable);
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}

	@Override
	public int getTotalItem(String keyword) {
		return questionRepository.count(keyword);
	}

	@Override
	public List<QuestionDTO> findByExam(int idExam) {
		List<QuestionDTO> dto = new ArrayList<>();	
		List<QuestionEntity> entities = questionRepository.findAllByExam(idExam);
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}

	@Override
	public List<QuestionDTO> findByTeacher(String username,Pageable pageable) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		List<QuestionDTO> dto = new ArrayList<>();	
		List<QuestionEntity> entities = questionRepository.findAllByTeacher(teacherEntity.getId(),pageable);
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}

	@Override
	public int getTotalItemFindByTeacher(String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		return (int) questionRepository.count(teacherEntity.getId());
	}

	@Override
	public List<QuestionDTO> findBySubject(String nameSubject) {
		SubjectsEntity subjectsEntity = subjectsRepository.findOneByName(nameSubject);
		List<QuestionDTO> dto = new ArrayList<>();	
		List<QuestionEntity> entities = questionRepository.findAllBySubject(subjectsEntity.getId());
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}


	@Override
	public List<QuestionDTO> searchByTeacher(String keyword, String username, Pageable pageable) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		List<QuestionDTO> dto = new ArrayList<>();
		List<QuestionEntity> entities = questionRepository.findAllByTeacherAndKeyword(keyword, teacherEntity.getId(),pageable);
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}

	@Override
	public int getTotalItemByTeacher(String keyword, String username) {
		TeacherEntity teacherEntity = teacherRepository.findOneByUsername(username);
		return questionRepository.countByTeacherAndKeyword(keyword, teacherEntity.getId());
	}

	@Override
	public List<QuestionDTO> searchBySubject(String keyword, String nameSubject) {
		SubjectsEntity subjectsEntity = subjectsRepository.findOneByName(nameSubject);
		List<QuestionDTO> dto = new ArrayList<>();	
		List<QuestionEntity> entities = questionRepository.searchBySubject(keyword,subjectsEntity.getId());
		for (QuestionEntity item : entities) {
			QuestionDTO questionDTO = this.mapToDto(item);
			dto.add(questionDTO);
		}
		return dto;
	}


}
