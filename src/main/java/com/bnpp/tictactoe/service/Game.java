package com.bnpp.tictactoe.service;

import com.bnpp.tictactoe.exception.InvalidInputException;
import com.bnpp.tictactoe.model.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class Game {

    private static final String INVALID_INPUT_RANGE_ERROR = "Input should be [1-9]";
    private static final String INVALID_INPUT_ALREADY_FILLED_ERROR = "Input position already filled by counter player";
    private static final String X_INPUT = "X";
    private static final String O_INPUT = "O";
    public static final String PLAYER_X = "Player 1";
    public static final String PLAYER_O = "Player 2";

    public GameBoard initBoard() {
        return new GameBoard();
    }

    public void validateInput(int input) throws InvalidInputException {
        if (input < 1 || input > 9) {
            throw new InvalidInputException(INVALID_INPUT_RANGE_ERROR);
        }
    }

    public void validateIfInputPosFilled(String[][] board, int input) throws InvalidInputException {
        String value = getValueFromBoard(board, input);
        if (value.equals(X_INPUT) || value.equals(O_INPUT)) {
            throw new InvalidInputException(INVALID_INPUT_ALREADY_FILLED_ERROR);
        }
    }

    private String getValueFromBoard(String[][] board, int input) throws InvalidInputException {
        switch (input) {
            case 1:
                return board[0][0];
            case 2:
                return board[0][1];
            case 3:
                return board[0][2];
            case 4:
                return board[1][0];
            case 5:
                return board[1][1];
            case 6:
                return board[1][2];
            case 7:
                return board[2][0];
            case 8:
                return board[2][1];
            case 9:
                return board[2][2];
            default:
                throw new InvalidInputException(INVALID_INPUT_RANGE_ERROR);
        }
    }

    public String searchWinner(String[][] board) {
        return null;
    }
}