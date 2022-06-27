package com.thitracnghiem.service;

import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.dto.ResultDTO;

@Service
public interface IResultService {
	public MarkDTO save(ResultDTO resultDTO);
}
