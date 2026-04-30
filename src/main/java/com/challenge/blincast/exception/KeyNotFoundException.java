package com.challenge.blincast.exception;

public class KeyNotFoundException extends RuntimeException {

    public KeyNotFoundException(String key) {
        super("Chave '" + key + "' não encontrada");
    }

    public KeyNotFoundException(String key, Throwable cause) {
        super("Chave '" + key + "' não encontrada", cause);
    }
}