package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.MarkDTO;

@Service
public interface IMarkService {
	public List<MarkDTO> findAllByTeacher(String username,Pageable pageable);
	public int getTotalItemByTeacher(String username);
	public List<MarkDTO> search(String keyword,String username,Pageable pageable);
	public int getTotalItem(String keyword,String username);
	public List<MarkDTO> findAllByStudent(String username);
}
