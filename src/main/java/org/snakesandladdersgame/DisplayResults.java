package org.snakesandladdersgame;

import java.util.Iterator;
import java.util.List;

public class DisplayResults {
	
	/**
	 * This method displays all the games and its winners.
	 * @param gameResultsList
	 */
	public void showAllGamesResults(List<GameResults> gameResultsList) {
		for (Iterator<GameResults> game = gameResultsList.iterator(); game.hasNext();) {
			GameResults myGame = (GameResults) game.next();
			System.out.println("Game Name: " + myGame.getGameId());
			System.out.println("Game Played on " + myGame.getDate());
			System.out.println("Winner is: " + myGame.getWinnerName());
		}
	}
		
	/**
	 * This Method displays all the players and positions od a game.
	 * @param gameDetailsList
	 */
	public void showGameDetails(List<Player> gameDetailsList) {
		for (Iterator<Player> players = gameDetailsList.iterator(); players.hasNext();) {
			Player player=(Player)players.next();
				System.out.println("Player Name = " + player.getName());
				System.out.println("Player position = " + player.getPosition());
			}
	}
}
