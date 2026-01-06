package it.wineoclock.exceptions;

import org.springframework.http.HttpStatus;

public class WineoClockException extends Exception {
    private final HttpStatus status;
    public WineoClockException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
