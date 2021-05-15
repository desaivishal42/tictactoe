package com.bnpp.tictactoe;

import com.bnpp.tictactoe.model.GameBoard;
import com.bnpp.tictactoe.service.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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

    @Test
    public void shouldBeValidInputByPlayer() {
        var input = 1;
        Assertions.assertDoesNotThrow(() -> game.validateInput(input));
    }
}