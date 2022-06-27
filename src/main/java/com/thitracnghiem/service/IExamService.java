package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.ExamDTO;
@Service
public interface IExamService {
	ExamDTO save(ExamDTO examDTO);
	public ExamDTO findById(int id);
	void delete(int[] ids);
	public List<ExamDTO> findByTeacher(String username,Pageable pageable);
	public int getTotalItemFindByTeacher(String username);
	public List<ExamDTO> findAllBySubject(int idSubject);
	public List<ExamDTO> findAll(Pageable pageable);
	public int getTotalItem();
	public int getTotalItem(String keyword);
	public List<ExamDTO> search(String keyword,Pageable pageable);
}
