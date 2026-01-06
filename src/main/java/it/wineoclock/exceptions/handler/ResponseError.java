package it.wineoclock.exceptions.handler;

import java.time.LocalDateTime;

public record ResponseError(String errorMessage, int errorCode, LocalDateTime timestamp, String errorState) {

}
