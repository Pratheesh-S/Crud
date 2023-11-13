package com.diatoz.task1.controller;

import com.diatoz.task1.customException.DataNotProper;
import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.customException.StudentDataException;
import com.diatoz.task1.dataValidatorPack.StudentDataValidation;
import com.diatoz.task1.entity.StudentEntity;
import com.diatoz.task1.service.StudentServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("student")
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    @Autowired
    StudentDataValidation studentDataValidation;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "admin/saveStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "save the student record", description = "save the student details along with the college ,note: college details has to be in database")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<StudentEntity> saveStudent(@Validated @RequestBody StudentEntity studentEntity, BindingResult result) throws IdException, DataNotProper, JsonProcessingException, StudentDataException {
        logger.info("Inside the Control class" + studentEntity.getStudentJoinYear());
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();
            for (FieldError fieldError : result.getFieldErrors()) {
                errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
            }

            logger.info(errorMap.toString());
            throw new StudentDataException(errorMap);
        }
        if (studentDataValidation.checkTheStudentData(studentEntity)) {
            return ResponseEntity.ok(studentService.saveStudent((studentEntity)));
        } else
            return null;

    }


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    @RequestMapping(value = "user/getStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentEntity>> getStudent() {
        logger.info("User made a call to fetch the all Student");
        return ResponseEntity.ok(studentService.getALlStudent());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @RequestMapping(value = "admin/getStudentById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Integer id) throws IdException {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @RequestMapping(value = "/removeStudent/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> removeStudentById(@PathVariable Integer id) throws IdException {
        return ResponseEntity.ok(studentService.removeStudent(id));
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<String> getStudentById(@RequestBody StudentEntity studentEntity) throws IdException {
        return ResponseEntity.ok(studentService.updateStudent(studentEntity));
    }


}
