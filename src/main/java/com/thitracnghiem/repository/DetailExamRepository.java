package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.DetailExamEntity;
import com.thitracnghiem.entity.ExamDetailsKey;

@Repository
public interface DetailExamRepository extends JpaRepository<DetailExamEntity, ExamDetailsKey> {
	
	@Query("SELECT d FROM DetailExamEntity d INNER JOIN d.exams e WHERE e.id =?1")
	List<DetailExamEntity> findAllByExam(int idExam);
	
}
