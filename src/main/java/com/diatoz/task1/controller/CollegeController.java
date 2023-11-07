package com.diatoz.task1.controller;

import com.diatoz.task1.customException.DataNotProper;
import com.diatoz.task1.customException.IdException;
import com.diatoz.task1.dataValidatorPack.CollegeDataValidator;
import com.diatoz.task1.entity.CollegeEntity;
import com.diatoz.task1.service.CollegeServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/college")
public class CollegeController {
    @Autowired
    CollegeServiceImpl collegeService;
    @Autowired
    CollegeDataValidator collegeDataValidator;

    Logger logger = LoggerFactory.getLogger(CollegeController.class);

    @RequestMapping(value = "/saveCollege", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "save the college record", description = "save the college details ")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<CollegeEntity> saveCollege(@RequestBody CollegeEntity collegeEntity) throws IdException, DataNotProper, JsonProcessingException {
        if (collegeDataValidator.checkTheCollegeData(collegeEntity))
            return ResponseEntity.ok(collegeService.saveCollege(collegeEntity));
        else
            return null;
    }

    @RequestMapping(value = "/getCollege", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public ResponseEntity<List<CollegeEntity>> getCollege() {
        logger.info("User made a call to fetch the all College");
        return ResponseEntity.ok(collegeService.getALlCollege());
    }

    @RequestMapping(value = "/getCollegeById/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER')")
    public ResponseEntity<CollegeEntity> getCollegeById(@PathVariable Integer id) throws IdException {
        return ResponseEntity.ok(collegeService.getCollegeById(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/removeCollege/{id}", method = RequestMethod.DELETE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> removeCollegeById(@PathVariable Integer id) throws IdException {
        return ResponseEntity.ok(collegeService.removeCollege(id));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping(value = "/updateCollege", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.ALL_VALUE)
    public ResponseEntity<String> getCollegeById(@RequestBody CollegeEntity collegeEntity) throws IdException {
        return ResponseEntity.ok(collegeService.updateCollege(collegeEntity));
    }


}
