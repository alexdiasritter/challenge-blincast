package com.challenge.blincast.dto;

public record DocumentRequest(
        String action,
        String key,
        String value
) {}