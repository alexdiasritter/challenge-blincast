package com.challenge.blincast.exception;

public class KeyAlreadyExistsException extends RuntimeException {

    public KeyAlreadyExistsException(String key) {
        super("Chave '" + key + "' já existe");
    }

    public KeyAlreadyExistsException(String key, Throwable cause) {
        super("Chave '" + key + "' já existe", cause);
    }
}