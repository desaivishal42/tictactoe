package com.bnpp.tictactoe.service;

import com.bnpp.tictactoe.exception.InvalidInputException;
import com.bnpp.tictactoe.model.GameBoard;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Scanner;

@Service
public class Game {

    private static final String INVALID_INPUT_RANGE_ERROR = "Input should be [1-9]";
    private static final String INVALID_INPUT_ALREADY_FILLED_ERROR = "Input position already filled by counter player";
    private static final String X_INPUT = "X";
    private static final String O_INPUT = "O";
    public static final String PLAYER_X = "Player 1";
    public static final String PLAYER_O = "Player 2";
    public static final String PLAYER_X_WINNER_PATTERN = "XXX";
    public static final String PLAYER_O_WINNER_PATTERN = "OOO";
    public static final String DRAW = "Draw";


    public void playGame() {
        String turn = PLAYER_X;
        String[][] board = initBoard().getBoard();
        Scanner in = new Scanner(System.in);

        welcomeMessage();

        while (null == searchWinner(board)) {
            displayBoard(board);
            System.out.println(turn + ": Select the position");
            int position = in.nextInt();
            try {
                validateInput(position);
                validateIfInputPosFilled(board, position);
            } catch (InvalidInputException e) {
                System.out.println(e.getMessage());
                continue;
            }

            String value = turn.equals(PLAYER_X) ? X_INPUT : O_INPUT;
            updateBoard(board, position, value);
            turn = turn.equals(PLAYER_X) ? PLAYER_O : PLAYER_X;
        }
        String winner = searchWinner(board);

        endGame(winner);
    }

    private void welcomeMessage() {
        System.out.println("**** Welcome to TicTacToe Game ****");
        System.out.println("Player X will go first");
    }

    private void endGame(String winner) {
        if (DRAW.equals(winner)) {
            System.out.println("Match is drawn");
        } else {
            System.out.println("**** " + winner + " wins the game ****");
        }

        System.out.println("*************************");
        System.out.println("Do you want to play it again Y/N??");

        Scanner in = new Scanner(System.in);
        String input = in.next();
        if (input.equals("Y")) {
            playGame();
        } else {
            System.out.println("Thanks for playing!!");
        }
    }

    private void updateBoard(String[][] board, int input, String value) {
        switch (input) {
            case 1:
                board[0][0] = value;
                break;
            case 2:
                board[0][1] = value;
                break;
            case 3:
                board[0][2] = value;
                break;
            case 4:
                board[1][0] = value;
                break;
            case 5:
                board[1][1] = value;
                break;
            case 6:
                board[1][2] = value;
                break;
            case 7:
                board[2][0] = value;
                break;
            case 8:
                board[2][1] = value;
                break;
            case 9:
                board[2][2] = value;
                break;
        }
    }

    private void displayBoard(String[][] board) {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0][0] + " | " + board[0][1] + " | " + board[0][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[1][0] + " | " + board[1][1] + " | " + board[1][2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[2][0] + " | " + board[2][1] + " | " + board[2][2] + " |");
        System.out.println("|---|---|---|");
    }

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
        String row1 = board[0][0] + board[0][1] + board[0][2];
        String row2 = board[1][0] + board[1][1] + board[1][2];
        String row3 = board[2][0] + board[2][1] + board[2][2];

        String col1 = board[0][0] + board[1][0] + board[2][0];
        String col2 = board[0][1] + board[1][1] + board[2][1];
        String col3 = board[0][2] + board[1][2] + board[2][2];

        String dia1 = board[0][0] + board[1][1] + board[2][2];
        String dia2 = board[0][2] + board[1][1] + board[2][0];

        if (PLAYER_X_WINNER_PATTERN.equals(row1)
                || PLAYER_X_WINNER_PATTERN.equals(row2)
                || PLAYER_X_WINNER_PATTERN.equals(row3)
                || PLAYER_X_WINNER_PATTERN.equals(col1)
                || PLAYER_X_WINNER_PATTERN.equals(col2)
                || PLAYER_X_WINNER_PATTERN.equals(col3)
                || PLAYER_X_WINNER_PATTERN.equals(dia1)
                || PLAYER_X_WINNER_PATTERN.equals(dia2)) {
            return PLAYER_X;
        } else if (PLAYER_O_WINNER_PATTERN.equals(row1)
                || PLAYER_O_WINNER_PATTERN.equals(row2)
                || PLAYER_O_WINNER_PATTERN.equals(row3)
                || PLAYER_O_WINNER_PATTERN.equals(col1)
                || PLAYER_O_WINNER_PATTERN.equals(col2)
                || PLAYER_O_WINNER_PATTERN.equals(col3)
                || PLAYER_O_WINNER_PATTERN.equals(dia1)
                || PLAYER_O_WINNER_PATTERN.equals(dia2)) {
            return PLAYER_O;
        } else if (isGameDraw(board)) {
            return DRAW;
        } else {
            return null;
        }
    }

    private boolean isGameDraw(String[][] board) {
        int count = (int) Arrays.stream(board)
                .flatMap(Arrays::stream)
                .filter(x -> x.equals("X") || x.equals("O"))
                .count();
        return count == 9;
    }
}