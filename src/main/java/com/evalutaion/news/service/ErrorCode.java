package com.evalutaion.news.service;

public enum ErrorCode {
    AUD_INVALID_REQUEST("Client request is invalid"),
    AUD_DOWN_STREAM_TIMEOUT("Downstream service timeout"),
    AUD_NO_MORE_AVAILABLE_ITEMS("No more items are available"),
    AUD_JSON_PROCESSING_FAILURE("Json object process failed");

    public String message;

    ErrorCode(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
