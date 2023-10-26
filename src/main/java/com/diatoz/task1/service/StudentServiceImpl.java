package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.dao.CollegeDao;
import com.diatoz.task1.dao.StudentDao;
import com.diatoz.task1.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentDao studentDao;
    @Autowired
    CollegeDao collegeDao;

    @Override
    public StudentEntity saveStudent(StudentEntity studentEntity) throws IdException, DataAccessException {
        Integer collegeId = studentEntity.getCollege().getCollegeId();
        if (studentEntity.getStudentId() != null && studentDao.findById(studentEntity.getStudentId()).isPresent()) {
            throw new IdException("Student with Given Id Already Present and you don't need to provide Id it will Auto generate");
        }
        if (collegeId == null) {
            throw new IdException(" College data is missing for a student, Please provide the valid college data ");
        }
        if (collegeDao.findById(collegeId).isEmpty()) {
            throw new IdException(" College With given id not present in data base");
        }
        studentDao.save(studentEntity);

        Optional<StudentEntity> studentData = studentDao.findById(studentEntity.getStudentId());
        if (studentData.isEmpty()) {
            throw new IdException(" Unable to save the Student Details in database, Check manually  ");
        }
        return studentData.get();
    }

    @Override
    public String removeStudent(Integer id) throws IdException, DataAccessException {
        Optional<StudentEntity> studentEntity = studentDao.findById(id);
        if (studentEntity.isPresent()) {
            studentDao.deleteById(id);
            return "Student with given id " + id + " removed successfully";
        } else {
            throw new IdException("Student with given  id " + id + " not Present in DataBase");

        }
    }

    @Override
    public List<StudentEntity> getALlStudent() throws DataAccessException {
        return studentDao.findAll();
    }

    @Override
    public String updateStudent(StudentEntity studentEntity) throws IdException, DataAccessException {
        Optional<StudentEntity> studentData = studentDao.findById(studentEntity.getStudentId());
        if (studentData.isPresent()) {
            studentDao.save(studentEntity);
            return "Student with id " + studentEntity.getStudentId() + " update successfully";
        } else {
            throw new IdException("Student with given  id " + studentEntity.getStudentId() + " not Present in DataBase");

        }
    }

    @Override
    public StudentEntity getStudentById(Integer id) throws IdException, DataAccessException {
        Optional<StudentEntity> studentEntity = studentDao.findById(id);
        if (studentEntity.isPresent())
            return studentEntity.get();
        throw new IdException("Student with given id not present in data base");
    }
}
