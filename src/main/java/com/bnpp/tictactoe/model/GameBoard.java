package com.bnpp.tictactoe.model;

public class GameBoard {

    private String[][] board;

    public GameBoard() {
        this.board = new String[3][3];
        setDefaultBoard();
    }

    public String[][] getBoard() {
        return board;
    }

    public void setBoard(String[][] board) {
        this.board = board;
    }

    private void setDefaultBoard() {
        board[0][0] = "1";
        board[0][1] = "2";
        board[0][2] = "3";
        board[1][0] = "4";
        board[1][1] = "5";
        board[1][2] = "6";
        board[2][0] = "7";
        board[2][1] = "8";
        board[2][2] = "9";
    }
}