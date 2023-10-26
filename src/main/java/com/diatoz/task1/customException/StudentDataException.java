package com.diatoz.task1.customException;

import java.util.HashMap;
import java.util.Map;

public class StudentDataException extends Exception {
    Map<String, String> error = new HashMap<>();

    public StudentDataException(Map<String, String> error) {
        this.error = error;
    }

    public Map<String, String> getError() {
        return error;
    }

    public void setError(Map<String, String> error) {
        this.error = error;
    }
}
