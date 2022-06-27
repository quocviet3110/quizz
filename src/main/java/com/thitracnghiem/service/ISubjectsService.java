package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.SubjectsDTO;

@Service
public interface ISubjectsService {
	public List<SubjectsDTO> findAll();
	public List<SubjectsDTO> findAll(Pageable pageable);
	public int getTotalItem();
	public SubjectsDTO findById(int id);
	SubjectsDTO save(SubjectsDTO subjectsDTO);
	void delete(int[] ids);
}
