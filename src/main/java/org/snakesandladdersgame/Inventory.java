package org.snakesandladdersgame;

import java.util.Map;
import java.util.List;

public interface Inventory {

	/**
	 * This method saves the game results into a list and details of each player
	 * positions in hash map.
	 * 
	 * @param playerList
	 */
	public void save(Map<Integer, Player> playerList);

	/**
	 * This method takes gameId as input and delete the game details.
	 * 
	 * @param gameId
	 */
	public void delete(String gameId);

	/**
	 * This method returns all the game Results. this returns gameId and date
	 * when the game is played and winner of game.
	 * 
	 * @return all games and their results.
	 */
	public List<GameResults> allGameResults();

	/**
	 * This method takes gameId as input and results the results of that game.
	 * 
	 * @param gameId
	 * @return gameId detailed results.
	 */
	public List<Player> gameResultDetails(String gameId);
}
