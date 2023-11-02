package com.diatoz.task1.dao;

import com.diatoz.task1.entity.AccountDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginDao extends JpaRepository<AccountDetails,String> {
}
