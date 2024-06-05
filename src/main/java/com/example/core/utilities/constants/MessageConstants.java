package com.example.core.utilities.constants;

public enum MessageConstants {
    LABEL("Label"),
    NOT_FOUND(" not found!"),
    TASK("Task"),


    ADD("The addition operation was successful!"),
    UPDATE("The update operation was successful!"),
    DELETE("The deletion operation was successful!"),
    GET_ALL("All records were listed!"),
    GET("The record was found!");

    private final String message;

    MessageConstants(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
