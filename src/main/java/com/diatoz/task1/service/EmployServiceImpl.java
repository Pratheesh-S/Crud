package com.diatoz.task1.service;

import com.diatoz.task1.dao.EmployDao;
import com.diatoz.task1.entity.EmployEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployServiceImpl implements  EmployService{
    @Autowired
    EmployDao employDao;
    @Override
    public EmployEntity saveEmploy(EmployEntity employEntity) {
       return employDao.save(employEntity);
    }

    @Override
    public String removeEmploy(Integer id) {
       Optional<EmployEntity> employEntity = employDao.findById(id);
       if(employEntity.isPresent())
       {
           employDao.deleteById(id);
           return "Employ with id " + id + "removed successfully";
       }
       else
       {
           return "Employ with id " + id + "not Present";

       }
    }

    @Override
    public List<EmployEntity> getALlEmploy() {
        return employDao.findAll();
    }

    @Override
    public String updateEmploy(EmployEntity employEntity) {
        Optional<EmployEntity> employData = employDao.findById(employEntity.getEmpId());
        if(employData.isPresent())
        {
            employDao.save(employEntity);
            return "Employ with id " + employEntity.getEmpId() + " update successfully";
        }
        else
        {
            return "Employ with id " + employEntity.getEmpId() + " not Present";

        }
    }

    @Override
    public EmployEntity getEmployById(Integer id) {
        Optional<EmployEntity> employEntity = employDao.findById(id);
        return employEntity.orElse(new EmployEntity());
    }
}
