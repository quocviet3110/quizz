package com.thitracnghiem.service;

import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.DetailExamDTO;

@Service
public interface IDetailExamService {
	void save(DetailExamDTO detailExamDTO);
}
