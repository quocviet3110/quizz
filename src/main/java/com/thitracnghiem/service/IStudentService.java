package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.StudentDTO;

@Service
public interface IStudentService {
	public StudentDTO save(StudentDTO studentDTO);
	public List<StudentDTO> findAll(Pageable pageable);
	public int getTotalItem();
	public List<StudentDTO> search(String keyword,Pageable pageable);
	public int getTotalItem(String keyword);
	StudentDTO findOneByUsername(String username);
	
}
