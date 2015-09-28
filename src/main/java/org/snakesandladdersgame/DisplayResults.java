package org.snakesandladdersgame;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class DisplayResults {

	/**
	 * This method displays all the games and its winners.
	 * 
	 * @param gameResultsList
	 */
	public void showAllGamesResults(List<GameResults> gameResultsList) {
		for (Iterator<GameResults> game = gameResultsList.iterator(); game.hasNext();) {
			GameResults myGame = (GameResults) game.next();
			System.out.print("Game Name: " + myGame.getGameId());
			System.out.print("   Game Played on " + myGame.getDate());
			System.out.println("   Winner is: " + myGame.getWinnerName());
		}
	}

	/**
	 * This Method displays all the players and respective positions in a game.
	 * 
	 * @param gameDetailsList
	 */
	public void showGameDetails(List<Player> gameDetailsList) {
		for (Iterator<Player> players = gameDetailsList.iterator(); players.hasNext();) {
			Player player = (Player) players.next();
			System.out.print("Player Name = " + player.getName());
			System.out.println("   Player position = " + player.getPosition());
		}
	}

	/**
	 * This Method displays winner of the game.
	 * 
	 * @param playerList
	 */
	public void showWinner(Map<Integer, Player> playerList) {
		for (Integer key : playerList.keySet()) {
			if (playerList.get(key).getPosition() == 100) {
				System.out.println(playerList.get(key).getName() + " is the Winner.");
			}
		}
	}
}
