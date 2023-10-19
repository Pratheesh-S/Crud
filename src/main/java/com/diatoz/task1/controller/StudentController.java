package com.diatoz.task1.controller;

import com.diatoz.task1.entity.StudentEntity;
import com.diatoz.task1.service.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    StudentServiceImpl studentService;

    Logger logger = LoggerFactory.getLogger(StudentController.class);

    @RequestMapping(value = "/saveStudent", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentEntity> saveStudent(@RequestBody StudentEntity studentEntity) {


        return ResponseEntity.ok(studentService.saveStudent(studentEntity));
    }

    @RequestMapping(value = "/getStudent", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<StudentEntity>> getStudent() {
        logger.info("User made a call to fetch the all Student");
        return ResponseEntity.ok(studentService.getALlStudent());
    }

    @RequestMapping(value = "/getStudentById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StudentEntity> getStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @RequestMapping(value = "/removeStudent/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeStudentById(@PathVariable Integer id) {
        return ResponseEntity.ok(studentService.removeStudent(id));
    }

    @RequestMapping(value = "/updateStudent", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getStudentById(@RequestBody StudentEntity studentEntity) {
        return ResponseEntity.ok(studentService.updateStudent(studentEntity));
    }

    @ExceptionHandler(Exception.class)
    public String showException(Exception e) {
        e.printStackTrace();
        return "Check the data You Entered";
    }
}
