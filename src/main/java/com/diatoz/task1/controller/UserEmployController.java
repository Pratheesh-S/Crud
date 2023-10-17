package com.diatoz.task1.controller;

import com.diatoz.task1.entity.EmployEntity;
import com.diatoz.task1.service.EmployServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Profile("user")
@RequestMapping("/user")
public class UserEmployController {
    @Autowired
    EmployServiceImpl employService;


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


}
