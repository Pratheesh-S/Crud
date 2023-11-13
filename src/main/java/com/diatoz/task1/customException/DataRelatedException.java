package com.diatoz.task1.customException;

import java.util.HashMap;
import java.util.Map;

public class DataRelatedException extends Exception{
    Map<String,String> error = new HashMap<>();

    public DataRelatedException(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}
