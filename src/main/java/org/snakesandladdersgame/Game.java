package org.snakesandladdersgame;

import java.util.Map;

public class Game {

	private Snake snakePosition;
	private Ladder ladderPosition;
	private static final int totalPos = 100; // Declaring total board size to
												// 100.

	public Game() {
		snakePosition = new Snake(); // Initializing Snake positions.
		ladderPosition = new Ladder(); // Initializing Ladder positions.
	}

	public void board(Map<Integer, Player> playerList) {

		int playerturn = 0; // First player starts the game.
		Dice dice = new Dice();
		while (true) {
			int rollDieScore = dice.roll(); // gets dice score
			Player player = playerList.get(playerturn);
			int totalScore = player.getPosition() + rollDieScore;
			if (totalScore == totalPos) {
				// Checks if player reached maximum board size.

				System.out.println("User : " + player.getName() + " got "
						+ rollDieScore + ".WINNER!!");
				player.setPosition(totalScore);
				break;
			} else if (totalScore > totalPos) {
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

			playerturn++; // Increments the players turn for all players to get
							// chance in order.
			if (playerturn == playerList.size()) {
				// Checks whether all players go chance and then sets the turn
				// to first player.

				playerturn = 0;
			}
		}
	}
}
