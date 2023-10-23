package com.diatoz.task1.service;

import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.dao.CollegeDao;
import com.diatoz.task1.dao.StudentDao;
import com.diatoz.task1.entity.CollegeEntity;
import com.diatoz.task1.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CollegeServiceImpl  implements CollegeService{
    @Autowired
    CollegeDao collegeDao;
    @Autowired
    StudentDao studentDao;

    @Override
    public CollegeEntity saveCollege(CollegeEntity collegeEntity) throws IdException, DataAccessException {
        if(collegeEntity.getCollegeId()!=null && collegeDao.findById(collegeEntity.getCollegeId()).isPresent())
        {
            throw new IdException("College with Given Id Already Present and you don't need to provide Id it will Auto generate");
        }
        return collegeDao.save(collegeEntity);
    }

    @Override
    public String removeCollege(Integer id) throws IdException,DataAccessException {
        Optional<CollegeEntity> collegeEntity = collegeDao.findById(id);
        if (collegeEntity.isPresent()) {
            List<StudentEntity> allStudentWithCollegeId = studentDao.findStudentsByCollege_CollegeId(id);
            if(!allStudentWithCollegeId.isEmpty())
            {
                throw new IdException("Unable to delete the College with a given id " + id +
                        " because there are "
                + allStudentWithCollegeId.size() + " Student studying in the college ");
            }
            collegeDao.deleteById(id);
            return "College with given id " + id + " removed successfully";
        } else {
            throw new IdException("College with given  id "+ id + " not Present in DataBase");

        }
    }

    @Override
    public List<CollegeEntity> getALlCollege() throws DataAccessException {
        return collegeDao.findAll();
    }

    @Override
    public String updateCollege(CollegeEntity collegeEntity) throws IdException,DataAccessException {
        Optional<CollegeEntity> collegeData = collegeDao.findById(collegeEntity.getCollegeId());
        if (collegeData.isPresent()) {
            collegeDao.save(collegeEntity);
            return "College with id " + collegeEntity.getCollegeId() + " update successfully";
        } else {
            throw new IdException("College with given  id "+ collegeEntity.getCollegeId() + " not Present in DataBase");

        }
    }

    @Override
    public CollegeEntity getCollegeById(Integer id) throws IdException,DataAccessException {
        Optional<CollegeEntity> collegeEntity = collegeDao.findById(id);
        if(collegeEntity.isPresent())
            return collegeEntity.get();
        throw new IdException("College with given id not present in data base");
    }
}
