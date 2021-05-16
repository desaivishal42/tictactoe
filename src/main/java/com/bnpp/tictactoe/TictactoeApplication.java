package com.bnpp.tictactoe;

import com.bnpp.tictactoe.service.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class TictactoeApplication {

    @Autowired
    Game game;

	public static void main(String[] args) {
		SpringApplication.run(TictactoeApplication.class, args);
	}

    @EventListener(ApplicationReadyEvent.class)
    public void startGame() {
        game.playGame();
    }
}
