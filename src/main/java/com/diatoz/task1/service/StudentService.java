package com.diatoz.task1.service;

import com.diatoz.task1.entity.StudentEntity;

import java.util.List;

public interface StudentService {

    StudentEntity saveStudent(StudentEntity studentEntity);

    String removeStudent(Integer id);

    List<StudentEntity> getALlStudent();

    String updateStudent(StudentEntity studentEntity);

    StudentEntity getStudentById(Integer id);

}
