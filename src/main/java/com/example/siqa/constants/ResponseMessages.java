package com.example.siqa.constants;

public enum ResponseMessages {
    SUCCESSFUL("successful result."),
    FAILURE("failure occurred."),
    VALIDATION_FAILED("validation failed."),
    DELETED("data removed successfully."),
    DATA_EXISTS("data already exists."),
    NO_DATA_FOUND("no data found."),
    UPDATED("data updated successfully."),
;
    public final String message;
    ResponseMessages(String description) {
        this.message = description;
    }
}
