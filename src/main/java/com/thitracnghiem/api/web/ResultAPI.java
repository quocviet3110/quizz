package com.thitracnghiem.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thitracnghiem.dto.MarkDTO;
import com.thitracnghiem.dto.ResultDTO;
import com.thitracnghiem.service.IResultService;
@CrossOrigin
@RestController(value = "resultAPIofWeb")
public class ResultAPI {
	@Autowired
	private IResultService iResultService;
	
	@PostMapping("/api/result")
	public MarkDTO createdResult(@RequestBody ResultDTO resultDTO) {	
		MarkDTO dto = iResultService.save(resultDTO);
		return dto;
	}
}
