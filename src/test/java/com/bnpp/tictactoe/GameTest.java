package com.bnpp.tictactoe;

import com.bnpp.tictactoe.model.GameBoard;
import com.bnpp.tictactoe.service.Game;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9})
    public void shouldBeValidInputByPlayer(int input) {
        Assertions.assertDoesNotThrow(() -> game.validateInput(input));
    }
}