package com.thitracnghiem.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.thitracnghiem.dto.LevelDTO;
import com.thitracnghiem.dto.SubjectsDTO;
import com.thitracnghiem.entity.LevelEntity;
import com.thitracnghiem.entity.SubjectsEntity;
import com.thitracnghiem.repository.LevelRepository;
import com.thitracnghiem.repository.SubjectsRepository;
import com.thitracnghiem.service.ILevelService;
@Service
public class LevelService implements ILevelService {
	
	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private SubjectsRepository subjectsRepository;
	
	public LevelService(LevelRepository levelRepository, ModelMapper mapper) {
		super();
		this.levelRepository = levelRepository;
		this.mapper = mapper;
	}
	private LevelDTO mapToDto(LevelEntity levelEntity) {
		LevelDTO levelDTO = mapper.map(levelEntity,LevelDTO.class);	
		return levelDTO;
	}
	private LevelEntity mapToEntity(LevelDTO levelDTO) {
		LevelEntity levelEntity = mapper.map(levelDTO,LevelEntity.class);
		return levelEntity;
	}
	private LevelEntity mapToEntity(LevelEntity levelEntity,LevelDTO levelDTO) {
		 levelEntity = mapper.map(levelDTO,LevelEntity.class);
		return levelEntity;
	}
	private SubjectsDTO mapToDto(SubjectsEntity subjectsEntity) {
		SubjectsDTO subjectsDTO = mapper.map(subjectsEntity,SubjectsDTO.class);	
		subjectsDTO.setLevel(subjectsEntity.getLevels().getName());
		return subjectsDTO;
	}
	@Override
	public List<LevelDTO> findAll() {
		List<LevelDTO> dto= new ArrayList<LevelDTO>();
		List<LevelEntity> entities = levelRepository.findAll();
		for(LevelEntity item: entities) {
			LevelDTO levelDTO = this.mapToDto(item);
			dto.add(levelDTO);
		}
		return dto;
	}
	@Override
	public List<LevelDTO> findAll(Pageable pageable) {
		List<LevelDTO> list = new ArrayList<LevelDTO>();
		List<LevelEntity> levelEntities = levelRepository.findAll(pageable).getContent();
		for(LevelEntity item : levelEntities) {
			LevelDTO dto = this.mapToDto(item);
			list.add(dto);
		}
		return list;
	}
	@Override
	public int getTotalItem() {
		return (int) levelRepository.count();
	}
	@Override
	public LevelDTO findById(int id) {
		LevelEntity entity = levelRepository.findOne(id);
		return this.mapToDto(entity);
	}
	@Override
	@Transactional
	public LevelDTO save(LevelDTO levelDTO) {
		LevelEntity entity = new LevelEntity();
		if (levelDTO.getId() != null) {
			LevelEntity old = levelRepository.findOne(levelDTO.getId());
			entity = this.mapToEntity(old,levelDTO);
		} else {
			entity = this.mapToEntity(levelDTO);
			
		}
		return this.mapToDto(levelRepository.save(entity));
	}
	@Override
	@Transactional
	public void delete(int[] ids) {
		for (int id : ids) {
			levelRepository.delete(id);
		}
	}
	@Override
	public List<LevelDTO> loadHeader() {
		List<LevelDTO> list = new ArrayList<LevelDTO>();
		List<LevelEntity> levelEntities = levelRepository.findAll();	
		for(LevelEntity item : levelEntities) {
			LevelDTO dto = this.mapToDto(item);
			List<SubjectsDTO> listSubjects = new ArrayList<SubjectsDTO>();
			List<SubjectsEntity> subjectsEntities = subjectsRepository.findAllByLevel(item.getId());
			for(SubjectsEntity subEntity : subjectsEntities) {
				SubjectsDTO subDto = this.mapToDto(subEntity);
				listSubjects.add(subDto);
			}
			dto.setListSub(listSubjects);
			list.add(dto);
		}
		return list;
	}

}
