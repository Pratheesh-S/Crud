package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.entity.CollegeEntity;

import java.util.List;

public interface CollegeService {
    CollegeEntity saveCollege(CollegeEntity collegeEntity) throws IdException;

    String removeCollege(Integer id) throws IdException;

    List<CollegeEntity> getALlCollege();

    String updateCollege(CollegeEntity collegeEntity) throws IdException;

    CollegeEntity getCollegeById(Integer id) throws IdException;
}
