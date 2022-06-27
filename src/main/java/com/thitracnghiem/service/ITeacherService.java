package com.thitracnghiem.service;

import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.TeacherDTO;

@Service
public interface ITeacherService {
	TeacherDTO findOneByUsername(String username);
	public TeacherDTO save(TeacherDTO teacherDTO);
}
