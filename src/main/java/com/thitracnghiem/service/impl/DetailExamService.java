package com.thitracnghiem.service.impl;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.DetailExamDTO;
import com.thitracnghiem.entity.DetailExamEntity;
import com.thitracnghiem.entity.ExamDetailsKey;
import com.thitracnghiem.entity.ExamEntity;
import com.thitracnghiem.entity.QuestionEntity;
import com.thitracnghiem.repository.DetailExamRepository;
import com.thitracnghiem.repository.ExamRepository;
import com.thitracnghiem.repository.QuestionRepository;
import com.thitracnghiem.service.IDetailExamService;

@Service
public class DetailExamService implements IDetailExamService {
	@Autowired
	private DetailExamRepository detailExamRepository;
	@Autowired
	private ExamRepository examRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private ModelMapper mapper;
	

	private DetailExamDTO mapToDto(DetailExamEntity detailExamEntity) {
		DetailExamDTO detailExamDTO = mapper.map(detailExamEntity, DetailExamDTO.class);
		detailExamDTO.setIdExam(detailExamEntity.getExams().getId());
		detailExamDTO.setIdQuestion(detailExamEntity.getQuestions().getId());
		return detailExamDTO;
	}

	private DetailExamEntity mapToEntity(DetailExamDTO detailExamDTO) {
		DetailExamEntity examEntity = mapper.map(detailExamDTO, DetailExamEntity.class);
		return examEntity;
	}
	private DetailExamEntity mapToEntity(DetailExamEntity detailExamEntity,DetailExamDTO detailExamDTO) {
		 detailExamEntity = mapper.map(detailExamDTO, DetailExamEntity.class);
		return detailExamEntity;
	}
	
	public DetailExamService(DetailExamRepository detailExamRepository, ModelMapper mapper) {
		super();
		this.detailExamRepository = detailExamRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public void save(DetailExamDTO detailExamDTO) {
		ExamEntity examEntity = examRepository.findOne(detailExamDTO.getIdExam());
		
		for (int id : detailExamDTO.getIds()) {
			QuestionEntity questionEntity = questionRepository.findOne(id);
			ExamDetailsKey examDetailsKey = new ExamDetailsKey();
			examDetailsKey.setId_exam(detailExamDTO.getId());
			examDetailsKey.setId_question(id);
			DetailExamEntity detailExamEntity = new DetailExamEntity();
			detailExamEntity.setExams(examEntity);
			detailExamEntity.setQuestions(questionEntity);
			detailExamEntity.setIdKeys(examDetailsKey);
			
			detailExamRepository.save(detailExamEntity);
		}
	}



}
