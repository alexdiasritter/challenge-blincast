package com.challenge.blincast.dto;

import org.springframework.http.HttpStatus;

public record DocumentResponse(
        String status,
        int code
) {
    // Métodos factory usando HttpStatus
    public static DocumentResponse ok() {
        return new DocumentResponse("ok", HttpStatus.OK.value());                    // 200
    }

    public static DocumentResponse created() {
        return new DocumentResponse("ok", HttpStatus.CREATED.value());              // 201
    }

    public static DocumentResponse badRequest() {
        return new DocumentResponse("error", HttpStatus.BAD_REQUEST.value());       // 400
    }

    public static DocumentResponse notFound() {
        return new DocumentResponse("error", HttpStatus.NOT_FOUND.value());         // 404
    }

    public static DocumentResponse conflict() {
        return new DocumentResponse("error", HttpStatus.CONFLICT.value());          // 409
    }

    public static DocumentResponse internalError() {
        return new DocumentResponse("error", HttpStatus.INTERNAL_SERVER_ERROR.value()); // 500
    }
}