package com.diatoz.task1.dao;

import com.diatoz.task1.entity.EmployEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployDao extends JpaRepository<EmployEntity, Integer> {

}
