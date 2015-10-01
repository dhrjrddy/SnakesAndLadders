package org.snakesandladdersgame;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class DbInventoryImp implements Inventory {

	private static final String SAVE_GAME_RESULTS = "insert into game_results (game_id,date,winner) values(?,?,?)";
	private static final String SAVE_GAME_DETAILS = "insert into game_details (game_id,player_name,position) values(?,?,?)";
	private static final String DELETE_GAME = "delete from game_results where game_id=?";
	private static final String GAME_COUNT = "select count(*) as row_count from game_results";
	private static final String GAME_RESULTS = "select * from game_results";
	private static final String GAME_RESULT_DETAILS = "select player_name,position from game_details where game_id=?";
	private static final String ROW_COUNT = "row_count";
	private static final String GAME_ID = "game_id";
	private static final String DATE = "date";
	private static final String WINNER = "winner";
	private static final String PLAYER_NAME = "player_name";
	private static final String POSITION = "position";
	private static Logger log = Logger.getLogger(DbInventoryImp.class);

	public void save(Map<Integer, Player> playerList) throws SQLException, IOException {
		Connection connection = null;
		String gameId = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(GAME_COUNT);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			gameId = "Game" + (resultSet.getInt(ROW_COUNT) + 1);
			// Generating a gameId for every game.

			PreparedStatement gameResultsStatement = connection.prepareStatement(SAVE_GAME_RESULTS);
			CurrentDate date = new CurrentDate();
			gameResultsStatement.setString(1, gameId);
			gameResultsStatement.setString(2, date.getDate());
			PreparedStatement gameDetailsStatement = connection.prepareStatement(SAVE_GAME_DETAILS);
			for (Integer key : playerList.keySet()) {
				if (playerList.get(key).getPosition() == 100) {
					// Check the winner and then register the game results.
					gameResultsStatement.setString(3, playerList.get(key).getName());
				}
				gameDetailsStatement.setString(1, gameId);
				gameDetailsStatement.setString(2, playerList.get(key).getName());
				gameDetailsStatement.setInt(3, playerList.get(key).getPosition());
				gameDetailsStatement.addBatch();
			}
			gameResultsStatement.execute(); // Saving results in game_results
											// table.
			gameDetailsStatement.executeBatch(); // Saving results in game
													// details table.
			connection.commit();
			connection.setAutoCommit(true);
			log.info("Saved " + gameId + " results Succeded");
		} catch (SQLException e) {
			log.error("Saving " + gameId + " results failed: " + e);
			throw new SQLException("Saving " + gameId + " results failed: " + e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public void delete(String gameId) throws SQLException, IOException {
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(DELETE_GAME);
			statement.setString(1, gameId);
			statement.execute();
			log.info("Deleted " + gameId + " results Successfully");
		} catch (SQLException e) {
			log.error("Deleting " + gameId + " failed: " + e);
			throw new SQLException("Deleting " + gameId + " failed: " + e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
	}

	public List<GameResults> allGameResults() throws SQLException, IOException {
		List<GameResults> gameResultsList = new LinkedList<GameResults>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GAME_RESULTS);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				gameResultsList.add(
						new GameResults(result.getString(GAME_ID), result.getString(DATE), result.getString(WINNER)));
			}
			log.info("Returned list of games played Successfully");
		} catch (SQLException e) {
			log.error("Returning list of games failed: " + e);
			throw new SQLException("Returning list of games failed: " + e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return gameResultsList;
	}

	public List<Player> gameResultDetails(String gameId) throws SQLException, IOException {
		List<Player> gameDetailsList = new LinkedList<Player>();
		Connection connection = null;
		try {
			connection = DbConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(GAME_RESULT_DETAILS);
			statement.setString(1, gameId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				gameDetailsList.add(new Player(result.getInt(POSITION), result.getString(PLAYER_NAME)));
			}
			log.info("Returned " + gameId + " results Successfully");
		} catch (SQLException e) {
			log.error("Returning " + gameId + " results failed: " + e);
			throw new SQLException("Returning " + gameId + " results failed: " + e);
		} finally {
			if (connection != null) {
				connection.close();
			}
		}
		return gameDetailsList;
	}

}
