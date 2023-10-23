package com.diatoz.task1.dao;

import com.diatoz.task1.entity.CollegeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollegeDao  extends JpaRepository<CollegeEntity,Integer> {
}
