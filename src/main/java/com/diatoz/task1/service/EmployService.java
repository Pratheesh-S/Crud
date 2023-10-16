package com.diatoz.task1.service;

import com.diatoz.task1.entity.EmployEntity;

import java.util.List;

public interface EmployService {

    EmployEntity saveEmploy(EmployEntity employEntity);

    String removeEmploy(Integer id);

    List<EmployEntity> getALlEmploy();

    String updateEmploy(EmployEntity employEntity);

    EmployEntity getEmployById(Integer id);

}
