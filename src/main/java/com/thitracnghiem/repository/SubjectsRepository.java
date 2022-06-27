package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.SubjectsEntity;
@Repository
public interface SubjectsRepository extends JpaRepository<SubjectsEntity, Integer> {
	SubjectsEntity findOneByName(String name);
	
	
	@Query("SELECT s FROM SubjectsEntity s INNER JOIN FETCH s.levels l WHERE l.id=?1")
	public List<SubjectsEntity> findAllByLevel(int idLevel);
	
	
	
}
