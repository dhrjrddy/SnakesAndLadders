package org.snakesandladdersgame;

import java.util.Map;

public class Game {

	private static Snake snakePosition;
	private static Ladder ladderPosition;
	private static final int TOTAL_BOARD_SIZE = 100; // Declaring total board size to
												// 100.

	public Game() {
		snakePosition = new Snake(); // Initializing Snake positions.
		ladderPosition = new Ladder(); // Initializing Ladder positions.
	}

	public void board(Map<Integer, Player> playerList) {

		int playerTurn = 0; // First player starts the game.
		Dice dice = new Dice();
		while (true) {
			int rollDieScore = dice.roll(); // gets dice score
			Player player = playerList.get(playerTurn);
			int totalScore = player.getPosition() + rollDieScore;
			if (totalScore == TOTAL_BOARD_SIZE) {
				// Checks if player reached maximum board size.

				player.setPosition(totalScore);
				break;
			} else if (totalScore > TOTAL_BOARD_SIZE) {
				// Checks if player's location if greater then a 100.
				// Subtract userRoll from the player position to get back old
				// position.

				player.setPosition(totalScore - rollDieScore);

			} else if (ladderPosition.isLadder(totalScore)) {
				// Checks for ladder position and sets new position.

				player.setPosition(ladderPosition.ladderPositions(totalScore));

			} else if (snakePosition.isSnake(totalScore)) {
				// Checks for Snake position and sets new position.

				player.setPosition(snakePosition.snakePositions(totalScore));

			} else {
				player.setPosition(totalScore);
			}

			playerTurn++; // Increments the players turn for all players to get
							// chance in order.
			if (playerTurn == playerList.size()) {
				// Checks whether all players got a chance and then sets the
				// turn to first player.

				playerTurn = 0;
			}
		}
	}
}
