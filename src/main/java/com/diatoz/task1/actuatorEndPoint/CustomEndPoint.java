package com.diatoz.task1.actuatorEndPoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Endpoint(id = "pratheesh")
@Component
public class CustomEndPoint {

    @ReadOperation
    public Map<String,Object> getDataOfPratheesh()
    {
        Map<String,Object> data = new HashMap<>();
        data.put("Name","Pratheesh");
        data.put("Employed",true);
        data.put("Company","Diatoz");
        data.put("Joining Year", 2023);
       return data;


    }
}
