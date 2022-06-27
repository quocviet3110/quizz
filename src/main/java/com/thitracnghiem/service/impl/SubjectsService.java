package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.SubjectsDTO;
import com.thitracnghiem.entity.LevelEntity;
import com.thitracnghiem.entity.SubjectsEntity;
import com.thitracnghiem.repository.LevelRepository;
import com.thitracnghiem.repository.SubjectsRepository;
import com.thitracnghiem.service.ISubjectsService;

@Service
public class SubjectsService implements ISubjectsService{
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private SubjectsRepository subjectsRepository;
	
	public SubjectsService(ModelMapper mapper, SubjectsRepository subjectsRepository) {
		super();
		this.mapper = mapper;
		this.subjectsRepository = subjectsRepository;
	}
	@Autowired
	private LevelRepository levelRepository;
	
	@Override
	public List<SubjectsDTO> findAll() {
		List<SubjectsDTO> dto= new ArrayList<SubjectsDTO>();
		List<SubjectsEntity> entities = subjectsRepository.findAll();
		for(SubjectsEntity item: entities) {
			SubjectsDTO subjectsDTO = this.mapToDto(item);
			dto.add(subjectsDTO);
		}
		return dto;
	}
	private SubjectsDTO mapToDto(SubjectsEntity subjectsEntity) {
		SubjectsDTO subjectsDTO = mapper.map(subjectsEntity,SubjectsDTO.class);	
		subjectsDTO.setLevel(subjectsEntity.getLevels().getName());
		return subjectsDTO;
	}
	private SubjectsEntity mapToEntity(SubjectsDTO subjectsDTO) {
		SubjectsEntity subjectsEntity = mapper.map(subjectsDTO,SubjectsEntity.class);	
		return subjectsEntity;
	}
	private SubjectsEntity mapToEntity(SubjectsEntity subjectsEntity,SubjectsDTO subjectsDTO) {
		subjectsEntity = mapper.map(subjectsDTO,SubjectsEntity.class);	
		return subjectsEntity;
	}

	@Override
	public List<SubjectsDTO> findAll(Pageable pageable) {
		List<SubjectsDTO> dto= new ArrayList<SubjectsDTO>();
		List<SubjectsEntity> entities = subjectsRepository.findAll(pageable).getContent();
		for(SubjectsEntity item: entities) {
			SubjectsDTO subjectsDTO = this.mapToDto(item);
			dto.add(subjectsDTO);
		}
		return dto;	
		}

	@Override
	public int getTotalItem() {
		return (int) subjectsRepository.count();
	}

	@Override
	public SubjectsDTO findById(int id) {
		SubjectsEntity subjectsEntity = subjectsRepository.findOne(id);
		return mapToDto(subjectsEntity);
	}

	@Override
	@Transactional
	public SubjectsDTO save(SubjectsDTO subjectsDTO) {
		LevelEntity level= levelRepository.findOneByName(subjectsDTO.getLevel());
		SubjectsEntity subjectsEntity = new SubjectsEntity();
		if (subjectsDTO.getId() != null) {
			SubjectsEntity old = subjectsRepository.findOne(subjectsDTO.getId());
			subjectsEntity = this.mapToEntity(old,subjectsDTO);
			subjectsEntity.setLevels(level);
		} else {
			subjectsEntity = this.mapToEntity(subjectsDTO);
			subjectsEntity.setLevels(level);
		}
		return this.mapToDto(subjectsRepository.save(subjectsEntity));
	}

	@Override
	@Transactional
	public void delete(int[] ids) {
		for (int id : ids) {
			subjectsRepository.delete(id);
		}
	}
}
