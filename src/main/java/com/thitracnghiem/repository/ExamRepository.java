package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.ExamEntity;

@Repository
public interface ExamRepository extends JpaRepository<ExamEntity, Integer> {
	@Query("SELECT e FROM ExamEntity e INNER JOIN FETCH e.teacherExam t WHERE t.id=?1")
	public List<ExamEntity> findAllByTeacher(int idTeacher, Pageable pageable);
	
	@Query("SELECT count(e) FROM ExamEntity e INNER JOIN e.teacherExam t WHERE t.id=?1")
	public int count(int idTeacher);
	
	@Query("SELECT e FROM ExamEntity e INNER JOIN e.subjects s WHERE  e.status=1 AND s.id=?1")
	public List<ExamEntity> findAllBySubject(int idSubject);
	
	@Query("SELECT e FROM ExamEntity e INNER JOIN e.subjects s INNER JOIN e.teacherExam t WHERE t.name LIKE %?1% OR s.name LIKE %?1% " + " OR e.time LIKE %?1%"
			+ " OR e.date LIKE %?1%" + " OR e.number LIKE %?1%" + " OR e.codeExam LIKE %?1%")
	public List<ExamEntity> search(String keyword, Pageable pageable);
	
	@Query("SELECT count(e) FROM ExamEntity e INNER JOIN e.subjects s INNER JOIN e.teacherExam t WHERE t.name LIKE %?1% OR s.name LIKE %?1% " + " OR e.time LIKE %?1%"
			+ " OR e.date LIKE %?1%" + " OR e.number LIKE %?1%" + " OR e.codeExam LIKE %?1%")
	public int count(String keyword);
}
