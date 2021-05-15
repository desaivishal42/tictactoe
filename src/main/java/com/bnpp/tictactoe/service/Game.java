package com.bnpp.tictactoe.service;

import com.bnpp.tictactoe.exception.InvalidInputException;
import com.bnpp.tictactoe.model.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class Game {

    private static final String INVALID_INPUT_RANGE_ERROR = "Input should be [1-9]";

    public GameBoard initBoard() {
        return new GameBoard();
    }

    public void validateInput(int input) throws InvalidInputException {
        if (input < 1 || input > 9) {
            throw new InvalidInputException(INVALID_INPUT_RANGE_ERROR);
        }
    }

    public void validateIfInputPosFilled(String[][] board, String input) {

    }
}