package com.mantovani;

public class WrongInputException extends Exception {

    private String message = "Wrong input";

    public WrongInputException() {}

    public WrongInputException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
