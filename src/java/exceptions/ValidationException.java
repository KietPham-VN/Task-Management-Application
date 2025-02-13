/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exceptions;
import java.util.HashMap;
/**
 *
 * @author vothimaihoa
 */


public class ValidationException extends Exception {
    private HashMap<String,String> errors = new HashMap<>();

    public ValidationException(HashMap<String,String> errors) {
        this.errors = errors;
    }

    public HashMap<String,String> getErrors() {
        return errors;
    }

    @Override
    public String getMessage() {
        StringBuilder message = new StringBuilder("Validation failed: ");
        for (String errorField : errors.keySet()) {
            message.append("\n").append(errorField).append(": ").append(errors.get(errorField));
        }
        return message.toString();
    }
}

