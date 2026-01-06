package it.wineoclock.exceptions;

import org.springframework.http.HttpStatus;

public class NotFound extends WineoClockException {

    public NotFound(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
