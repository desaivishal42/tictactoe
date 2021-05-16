package com.bnpp.tictactoe;

import com.bnpp.tictactoe.exception.InvalidInputException;
import com.bnpp.tictactoe.model.GameBoard;
import com.bnpp.tictactoe.service.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static com.bnpp.tictactoe.service.Game.PLAYER_O;
import static com.bnpp.tictactoe.service.Game.PLAYER_X;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GameTest {

    @Autowired
    Game game;

    @Test
    public void shouldReturnNewBoard() {
        GameBoard gameBoard = game.initBoard();
        Assertions.assertNotNull(gameBoard);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void shouldBeValidInputByPlayer(int input) {
        Assertions.assertDoesNotThrow(() -> game.validateInput(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    public void shouldThrowExceptionIncaseInputLessThanOne(int input) {
        Assertions.assertThrows(InvalidInputException.class, () -> game.validateInput(input));
    }

    @ParameterizedTest
    @ValueSource(ints = {10, 12})
    public void shouldThrowExceptionIncaseInputGreaterThanNine(int input) {
        Assertions.assertThrows(InvalidInputException.class, () -> game.validateInput(input));
    }

    @Test
    public void shouldThrowExceptionIncaseInputPositionAlreadyFilledInBoard() {
        var input = 1;
        GameBoard gameBoard = game.initBoard();
        String[][] board = gameBoard.getBoard();
        board[0][0] = "X";
        Assertions.assertThrows(InvalidInputException.class, () -> game.validateIfInputPosFilled(board, input));
    }

    @ParameterizedTest
    @MethodSource("createBoardForPlayerX")
    public void shouldDeclarePlayerOneWinnerIfTicTacToeConditionSatisfies(GameBoard gameBoard) {
        String[][] board = gameBoard.getBoard();
        String winner = game.searchWinner(board);
        Assertions.assertEquals(PLAYER_X, winner);
    }

    @ParameterizedTest
    @MethodSource("createBoardForPlayerY")
    public void shouldDeclarePlayerTwoWinnerIfTicTacToeConditionSatisfies(GameBoard gameBoard) {
        String[][] board = gameBoard.getBoard();
        String winner = game.searchWinner(board);
        Assertions.assertEquals(PLAYER_O, winner);
    }

    @ParameterizedTest
    @MethodSource("createIncompleteBoard")
    public void shouldReturnNullIfTicTacToeConditionDoesNotSatisfies(GameBoard gameBoard) {
        String[][] board = gameBoard.getBoard();
        String winner = game.searchWinner(board);
        Assertions.assertNull(winner);
    }

    static List<GameBoard> createBoardForPlayerX() {
        return createBoard("X");
    }

    static List<GameBoard> createBoardForPlayerY() {
        return createBoard("Y");
    }

    public static List<GameBoard> createBoard(String playerOne) {
        List<GameBoard> boards = new ArrayList<>();

        GameBoard boardOne = new GameBoard();
        setRow(boardOne.getBoard(), 0, playerOne);
        boards.add(boardOne);

        GameBoard boardTwo = new GameBoard();
        setRow(boardTwo.getBoard(), 1, playerOne);
        boards.add(boardTwo);

        GameBoard boardThree = new GameBoard();
        setRow(boardThree.getBoard(), 2, playerOne);
        boards.add(boardThree);

        GameBoard boardFour = new GameBoard();
        setCol(boardFour.getBoard(), 0, playerOne);
        boards.add(boardFour);

        GameBoard boardFive = new GameBoard();
        setCol(boardFive.getBoard(), 1, playerOne);
        boards.add(boardFive);

        GameBoard boardSix = new GameBoard();
        setCol(boardSix.getBoard(), 2, playerOne);
        boards.add(boardSix);

        GameBoard boardSeven = new GameBoard();
        setDiagonal(boardSeven.getBoard(), playerOne);
        boards.add(boardSeven);

        GameBoard boardEight = new GameBoard();
        setDiagonal2(boardEight.getBoard(), playerOne);
        boards.add(boardEight);

        return boards;
    }

    static String[][] setRow(String[][] board, int row, String input) {
        for (int i = 0; i < 3; i++) {
            board[row][i] = input;
        }
        return board;
    }

    static String[][] setCol(String[][] board, int col, String input) {
        for (int i = 0; i < 3; i++) {
            board[i][col] = input;
        }
        return board;
    }

    static String[][] setDiagonal(String[][] board, String input) {
        for (int i = 0, j = 0; i < 3 && j < 3; i++, j++) {
            board[i][j] = input;
        }
        return board;
    }

    static String[][] setDiagonal2(String[][] board, String input) {
        for (int i = 0, j = 2; i < 3 && j >= 0; i++, j--) {
            board[i][j] = input;
        }
        return board;
    }

    static List<GameBoard> createIncompleteBoard() {
        List<GameBoard> boards = new ArrayList<>();

        GameBoard boardOne = new GameBoard();
        String[][] board1 = boardOne.getBoard();
        board1[0][0] = "X";
        board1[0][1] = "O";
        board1[0][2] = "X";
        boards.add(boardOne);

        GameBoard boardTwo = new GameBoard();
        String[][] board2 = boardOne.getBoard();
        board2[0][1] = "X";
        board2[1][1] = "O";
        board2[2][1] = "X";
        boards.add(boardTwo);

        return boards;
    }
}