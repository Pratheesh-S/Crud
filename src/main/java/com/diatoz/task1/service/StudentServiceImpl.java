package com.diatoz.task1.service;

import com.diatoz.task1.dao.StudentDao;
import com.diatoz.task1.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;

    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) {
        return studentDao.save(studentEntity);
    }

    @Override
    public String removeStudent(Integer id) {
        Optional<StudentEntity> studentEntity = studentDao.findById(id);
        if (studentEntity.isPresent()) {
            studentDao.deleteById(id);
            return "Student with id " + id + "removed successfully";
        } else {
            return "Student with id " + id + "not Present";

        }
    }

    @Override
    public List<StudentEntity> getALlStudent() {
        return studentDao.findAll();
    }

    @Override
    public String updateStudent(StudentEntity studentEntity) {
        Optional<StudentEntity> studentData = studentDao.findById(studentEntity.getStudentId());
        if (studentData.isPresent()) {
            studentDao.save(studentEntity);
            return "Student with id " + studentEntity.getStudentId() + " update successfully";
        } else {
            return "Student with id " + studentEntity.getStudentId() + " not Present";

        }
    }

    @Override
    public StudentEntity getStudentById(Integer id) {
        Optional<StudentEntity> StudentEntity = studentDao.findById(id);
        return StudentEntity.orElse(new StudentEntity());
    }
}
