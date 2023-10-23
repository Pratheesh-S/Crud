package com.diatoz.task1.intercepterpack;

import com.diatoz.task1.entity.StudentEntity;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.networknt.schema.JsonSchema;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Set;
import java.util.stream.Collectors;

//@Component
public class InputDataValidateInterceptor implements HandlerInterceptor {


    Logger logger =   LoggerFactory.getLogger(InputDataValidateInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try {
            String jsonData = request.getReader().lines().collect(Collectors.joining());
            ObjectMapper objectMapper = new ObjectMapper();
            StudentEntity studentEntity = objectMapper.readValue(jsonData, StudentEntity.class);
            request.setAttribute("studentEntity",studentEntity);
            JsonNode studentJsonNode = objectMapper.readTree(jsonData);

            JsonSchemaFactory factory = JsonSchemaFactory.builder(JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V7)).build();
            JsonSchema schema = factory.getSchema(ClassLoader.getSystemResourceAsStream("schema/studentSchema.json"));

            Set<ValidationMessage> errors = schema.validate(studentJsonNode);

            if (!errors.isEmpty()) {

                response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                response.getWriter().write(errors.toString());
                response.getWriter().flush();
                response.getWriter().close();
                return false; // Stop further processing
            }
        }catch (Exception e)
        {
            response.getWriter().write(e.getCause().getMessage());
        }
        return true;
    }
}
