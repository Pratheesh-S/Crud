package com.diatoz.task1.dao;

import com.diatoz.task1.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDao extends JpaRepository<StudentEntity, Integer> {

}
