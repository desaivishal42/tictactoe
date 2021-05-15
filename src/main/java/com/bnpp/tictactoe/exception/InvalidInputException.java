package com.bnpp.tictactoe.exception;

public class InvalidInputException extends Exception {

    private String message;

    public InvalidInputException(String message) {
        super(message);
    }
}