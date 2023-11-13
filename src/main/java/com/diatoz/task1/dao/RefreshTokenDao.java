package com.diatoz.task1.dao;

import com.diatoz.task1.entity.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenDao extends JpaRepository<RefreshTokenEntity,String> {

}
