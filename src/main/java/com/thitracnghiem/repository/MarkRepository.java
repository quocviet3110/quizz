package com.thitracnghiem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thitracnghiem.entity.MarkEntity;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Integer>{
	@Query("SELECT m FROM MarkEntity m INNER JOIN FETCH m.examMark e INNER JOIN FETCH e.teacherExam t WHERE t.id=?1")
	public List<MarkEntity> findAllByTeacher(int idTeacher,Pageable pageable);
	
	@Query("SELECT count(*) FROM MarkEntity m INNER JOIN  m.examMark e INNER JOIN  e.teacherExam t WHERE t.id=?1")
	public int countByTeacher(int idTeacher);
	

	@Query("SELECT m FROM MarkEntity m INNER JOIN FETCH m.studentMark  s INNER JOIN FETCH m.examMark e INNER JOIN FETCH e.teacherExam t WHERE (t.name LIKE %?1%" + " OR m.mark LIKE %?1%"
		+	" OR s.name LIKE %?1%"	+	" OR s.email LIKE %?1%" + " OR e.id LIKE %?1%)" + " AND t.id=?2")
	public List<MarkEntity> findAllByTeacherAndKeyword(String keyword,int idTeacher, Pageable pageable);
	
	@Query("SELECT count(*) FROM MarkEntity m INNER JOIN m.studentMark  s INNER JOIN  m.examMark e INNER JOIN  e.teacherExam t WHERE  (t.name LIKE %?1%" + " OR m.mark LIKE %?1% "
			+ " OR s.name LIKE %?1%"+" OR s.email LIKE %?1%" + " OR e.id LIKE %?1%)" + " AND t.id=?2")
	public int countByTeacherAndKeyword(String keyword,int idTeacher);
	
	@Query("SELECT m FROM MarkEntity m INNER JOIN FETCH m.studentMark s WHERE s.id=?1")
	public List<MarkEntity> findAllByStudent(int idStudent);
}
