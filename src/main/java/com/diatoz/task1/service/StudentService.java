package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity studentEntity) throws IdException;

    String removeStudent(Integer id) throws IdException;

    List<StudentEntity> getALlStudent();

    String updateStudent(StudentEntity studentEntity) throws IdException;

    StudentEntity getStudentById(Integer id) throws IdException;

}
