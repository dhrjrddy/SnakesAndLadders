package org.snakesandladdersgame;

import java.util.Map;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Inventory {

	/**
	 * This method saves the game results into a list and details of each player
	 * positions in hash map.
	 * 
	 * @param playerList
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void save(Map<Integer, Player> playerList) throws SQLException, IOException;

	/**
	 * This method takes gameId as input and delete the game details.
	 * 
	 * @param gameId
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public void delete(String gameId) throws SQLException, IOException;

	/**
	 * This method returns all the game Results. this returns gameId and date
	 * when the game is played and winner of game.
	 * 
	 * @return all games and their results.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public List<GameResults> allGameResults() throws SQLException, IOException;

	/**
	 * This method takes gameId as input and results the results of that game.
	 * 
	 * @param gameId
	 * @return gameId detailed results.
	 * @throws SQLException 
	 * @throws IOException 
	 */
	public List<Player> gameResultDetails(String gameId) throws SQLException, IOException;
}
