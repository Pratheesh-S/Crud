package com.diatoz.task1.controller;

import com.diatoz.task1.entity.EmployEntity;
import com.diatoz.task1.service.EmployServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployController {
    @Autowired
    EmployServiceImpl employService;

    @RequestMapping(value = "/saveEmploy", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployEntity> saveEmploy(@RequestBody EmployEntity employEntity)
    {
      return ResponseEntity.ok(employService.saveEmploy(employEntity));
    }
    @RequestMapping(value = "/getEmploy", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EmployEntity>> getEmploy( )
    {
        return ResponseEntity.ok(employService.getALlEmploy());
    }

    @RequestMapping(value = "/getEmployById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EmployEntity> getEmployById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(employService.getEmployById(id));
    }

    @RequestMapping(value = "/removeEmploy/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeEmployById(@PathVariable Integer id)
    {
        return ResponseEntity.ok(employService.removeEmploy(id));
    }

    @RequestMapping(value = "/updateEmploy", method = RequestMethod.PUT,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getEmployById(@RequestBody EmployEntity employEntity)
    {
        return ResponseEntity.ok(employService.updateEmploy(employEntity));
    }

    @ExceptionHandler(Exception.class)
    public void showException(Exception e)
    {
        e.printStackTrace();

    }
}
