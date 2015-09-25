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

public class SnakeAndLadder {

	private Map<Integer, Player> playerList; // Contains all players and their
												// positions.
	private Inventory gameResults;

	public SnakeAndLadder() {
		gameResults = new DBInventoryImp();
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

			Player player = new Player(startPosition, "Player"
					+ (playerNum + 1));
			playerList.put(playerNum, player);
		}
	}

	/**
	 * This method is responsible for starting the game and setting the total
	 * players list. It will also call the game board and send the final results
	 * to database.
	 */
	public void startGame(int totalPlayers) {
		if (totalPlayers < 2) { // Stop game if total players is less than 2.
			System.out.println("Please enter more than 1 player.");
			System.exit(-1);
		}
		createMultiplePlayers(totalPlayers);
		Game game = new Game();
		game.board(playerList);
		gameResults.save(playerList);
	}

	public static void main(String args[]) {
		SnakeAndLadder snakeAndLadder = new SnakeAndLadder();
		DisplayResults display = new DisplayResults();
		int totalPlayers = 3;
		snakeAndLadder.startGame(totalPlayers);
		display.showAllGamesResults(snakeAndLadder.allGamesResults());
		display.showGameDetails(snakeAndLadder.gameDetails("Game1"));
		totalPlayers = 4;
		snakeAndLadder.startGame(totalPlayers);
		display.showAllGamesResults(snakeAndLadder.allGamesResults());
		display.showGameDetails(snakeAndLadder.gameDetails("Game1"));
		totalPlayers = 5;
		snakeAndLadder.startGame(totalPlayers);
		display.showAllGamesResults(snakeAndLadder.allGamesResults());
		display.showGameDetails(snakeAndLadder.gameDetails("Game1"));
		display.showGameDetails(snakeAndLadder.gameDetails("Game2"));
		display.showGameDetails(snakeAndLadder.gameDetails("Game3"));

	}

}
