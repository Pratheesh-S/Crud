package com.diatoz.task1.dataValidatorPack;

import com.diatoz.task1.customException.DataNotProper;
import com.diatoz.task1.entity.StudentEntity;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

@Component
public class StudentDataValidation {


    Logger logger = LoggerFactory.getLogger(StudentDataValidation.class);

    public boolean checkTheStudentData(StudentEntity studentEntity) throws DataNotProper, JsonProcessingException {

        logger.info("Inside the student  data validator with data " + studentEntity);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        DateFormat customDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(customDateFormat);
        String jsonData = objectMapper.writeValueAsString(studentEntity);
        logger.info(jsonData);
        JsonNode studentJsonNode = objectMapper.readTree(jsonData);

        JsonSchemaFactory factory = JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)).build();
        JsonSchema schema = factory.getSchema(ClassLoader.getSystemResourceAsStream("schema/studentSchema.json"));
        logger.info(schema.toString());
        Set<ValidationMessage> errors = schema.validate(studentJsonNode);

        if (!errors.isEmpty()) {
            throw new DataNotProper(errors.toString());
        }

        return true;
    }
}


