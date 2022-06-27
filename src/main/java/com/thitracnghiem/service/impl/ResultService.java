package com.thitracnghiem.service.impl;

import java.util.Date;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.dto.ResultDTO;
import com.thitracnghiem.entity.ExamEntity;
import com.thitracnghiem.entity.MarkEntity;
import com.thitracnghiem.entity.QuestionEntity;
import com.thitracnghiem.entity.ResultEntity;
import com.thitracnghiem.entity.StudentEntity;
import com.thitracnghiem.repository.ExamRepository;
import com.thitracnghiem.repository.MarkRepository;
import com.thitracnghiem.repository.QuestionRepository;
import com.thitracnghiem.repository.ResultRepository;
import com.thitracnghiem.repository.StudentRepository;
import com.thitracnghiem.service.IResultService;

@Service
public class ResultService implements IResultService {
	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ResultRepository resultRepository;

	@Autowired
	private ExamRepository examRepository;

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private MarkRepository markRepository;

	private ResultDTO mapToDto(ResultEntity resultEntity) {
		ResultDTO dto = mapper.map(resultEntity, ResultDTO.class);
		return dto;
	}

	private ResultEntity mapToEntity(ResultDTO resultDTO) {
		ResultEntity entity = mapper.map(resultDTO, ResultEntity.class);
		return entity;
	}

	private ResultEntity mapToEntity(ResultEntity entity, ResultDTO resultDTO) {
		entity = mapper.map(resultDTO, ResultEntity.class);
		return entity;
	}
	private MarkDTO mapToDto(MarkEntity markEntity) {
		MarkDTO markDTO = mapper.map(markEntity, MarkDTO.class);
		markDTO.setNameSubject(markEntity.getExamMark().getSubjects().getName());
		return markDTO;
	}
	@Override
	@Transactional
	public MarkDTO save(ResultDTO resultDTO) {
		StudentEntity studentEntity = studentRepository.findOneByUsername(resultDTO.getUsername());
		ExamEntity examEntity = examRepository.findOne(resultDTO.getIdExam());
		Integer[] ids = resultDTO.getIds();
		int count = 0;
		String[] answer = resultDTO.getAnswer();

		for (int i = 0; i < ids.length; i++) {

			QuestionEntity questionEntity = questionRepository.findOne(ids[i]);

			ResultEntity resultEntity = new ResultEntity();
			resultEntity.setStudents(studentEntity);
			resultEntity.setAnswer(answer[i]);
			resultEntity.setQuestionResult(questionEntity);
			resultEntity.setExamResult(examEntity);
			resultEntity.setStatus(1);
			this.mapToDto(resultRepository.save(resultEntity));

			if (questionEntity.getAnswer().equals(answer[i])) {
					count++;
				}
			
			}
			MarkEntity markEntity = new MarkEntity();
			markEntity.setExamMark(examEntity);
			markEntity.setStudentMark(studentEntity);
			markEntity.setMark((double) Math.round((count*(10.0/ids.length))*100)/100);
			markEntity.setDate( new Date());
			markRepository.save(markEntity);
			MarkDTO dto =this.mapToDto(markEntity);
		return 	dto;

	}

}
