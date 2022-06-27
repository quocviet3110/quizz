package com.thitracnghiem.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.LevelDTO;
@Service
public interface ILevelService {
	public List<LevelDTO> findAll();
	public List<LevelDTO> findAll(Pageable pageable);
	public int getTotalItem();
	public LevelDTO findById(int id);
	LevelDTO save(LevelDTO levelDTO);
	void delete(int[] ids);
	public List<LevelDTO> loadHeader();
}
