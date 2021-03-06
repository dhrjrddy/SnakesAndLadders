package org.snakesandladdersgame;


/**
 * Snakes and Ladders Game
 * @Description
 * The game has multiple players where players take turns to roll dice.
 * If a player's token lands on the bottom of a ladder, the token moves to the top of the ladder (always greater than the current position).
 * Conversely, if the player's token lands on snake's mouth, then token moves to the tail of the snake (always less that the current position).
 * Whoever gets to the last number or 100 wins.
 * 
 * To Start the game we need to call startGame method by passing no of players.
 * @param number of players
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class SnakeAndLadder {

	private static Map<Integer, Player> playerList; // Contains all players and
													// their positions.
	private static Repository gameResults;

	public SnakeAndLadder() {
		gameResults = new DbRepositoryImp();
	}

	/**
	 * @return list of game results
	 */
	public List<GameResults> allGamesResults() {
		return gameResults.allGameResults();

	}

	/**
	 * @param gameId
	 * @return list of input gameId details with player name and position.
	 */
	public List<Player> gameDetails(String gameId) {

		return gameResults.gameResultDetails(gameId);

	}

	/**
	 * This method auto generate the player names and initialize their position
	 * and store them in player list.
	 * 
	 * @param totalPlayers
	 */
	private void createMultiplePlayers(int totalPlayers) {
		playerList = new HashMap<Integer, Player>();
		int startPosition = 0;
		for (int playerNum = 0; playerNum < totalPlayers; playerNum++) {
			// initializing player position to 0.

			Player player = new Player(startPosition, "Player" + (playerNum + 1));
			playerList.put(playerNum, player);
		}
	}

	/**
	 * This method is responsible for starting the game and setting the total
	 * players list. It will also call the game board and send the final results
	 * to database.
	 * 
	 */
	public void startGame(int totalPlayers) {
		if (totalPlayers < 2) { // Check if total players is less than 2.
			System.out.println("Please enter more than 1 player.");
			return;
		}
		createMultiplePlayers(totalPlayers);
		Game game = new Game();
		game.board(playerList);
		gameResults.save(playerList);
	}

	public static void main(String args[]) {
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
		DisplayResults display = new DisplayResults();
		Scanner input = new Scanner(System.in);
		int userChoice;
		do {
			System.out.println("Select the option:");
			System.out.println("1. Play new game.");
			System.out.println("2. Display all game results.");
			System.out.println("3. Display game details.");
			userChoice = input.nextInt();
			switch (userChoice) {
			case 1:
				System.out.println("Enter player count...");
				int totalPlayers = input.nextInt();
				snakeAndLadder.startGame(totalPlayers);
				display.showWinner(playerList);
				break;
			case 2:
				display.showAllGamesResults(snakeAndLadder.allGamesResults());
				break;
			case 3:
				System.out.println("Enter game id...");
				display.showGameDetails(snakeAndLadder.gameDetails(input.next()));
				break;
			}
		} while (userChoice == 1 || userChoice == 2 || userChoice == 3);

	}

}
