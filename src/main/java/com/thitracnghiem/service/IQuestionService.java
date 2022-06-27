package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.QuestionDTO;

@Service
public interface IQuestionService {
	
	public List<QuestionDTO> findAll(Pageable pageable);
	public List<QuestionDTO> search(String keyword,Pageable pageable);
	public int getTotalItem();
	
	
	public QuestionDTO findById(int id);
	QuestionDTO save(QuestionDTO questionDTO);
	int delete(int[] ids);
	
	public int getTotalItem(String keyword);
	public List<QuestionDTO> findByExam(int idExam);
	public List<QuestionDTO> findByTeacher(String username,Pageable pageable);
	
	
	public int getTotalItemFindByTeacher(String username);
	public List<QuestionDTO> findBySubject(String nameSubject);
	
	public List<QuestionDTO> searchByTeacher(String keyword,String username,Pageable pageable);	
	public int getTotalItemByTeacher(String keyword,String username);
	
	public List<QuestionDTO> searchBySubject(String keyword,String nameSubject);
}
