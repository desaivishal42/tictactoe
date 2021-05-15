package com.bnpp.tictactoe.service;

import com.bnpp.tictactoe.exception.InvalidInputException;
import com.bnpp.tictactoe.model.GameBoard;
import org.springframework.stereotype.Service;

@Service
public class Game {

    public GameBoard initBoard() {
        return new GameBoard();
    }

    public void validateInput(int input) throws InvalidInputException {
        throw new InvalidInputException();
    }
}