package org.snakesandladdersgame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DBInventoryImp implements Inventory {

	private static final String saveGameResults = "insert into game_results (GameId,Date,Winner) values(?,?,?)";
	private static final String saveGameDetails = "insert into game_details (GameId,PlayerName,Position) values(?,?,?)";
	private static final String deleteGame = "delete from game_results where GameId=?";
	private static final String gameCount = "select count(*) as rowcount from game_results";
	private static final String gameResultsTable = "CREATE  TABLE IF NOT EXISTS game_results (`GameId` VARCHAR(20) NOT NULL ,`Date` VARCHAR(20) NULL ,`Winner` VARCHAR(20) NULL ,PRIMARY KEY (`GameId`) )";
	private static final String gameDetailsTable = "CREATE  TABLE IF NOT EXISTS game_details ( `GameId` VARCHAR(20) NOT NULL ,`PlayerName` VARCHAR(20) NULL ,`Position` VARCHAR(20) NULL ,INDEX `GameId` (`GameId` ASC) , CONSTRAINT `GameId` FOREIGN KEY (`GameId` ) REFERENCES `snakesandladders`.`game_results` (`GameId` ) ON DELETE CASCADE ON UPDATE CASCADE);";
	private static final String gameResults = "select * from game_results";
	private static final String gameResultDetails = "select PlayerName,Position from game_details where GameId=?";
	private static final String rowCount = "rowcount";
	private static final String gameId = "GameId";
	private static final String date = "Date";
	private static final String winner = "Winner";
	private static final String playerName = "PlayerName";
	private static final String position = "Position";

	public DBInventoryImp() {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement gameResultsStatement = connection.prepareStatement(gameResultsTable);
			PreparedStatement gameDetailsStatement = connection.prepareStatement(gameDetailsTable);
			gameResultsStatement.execute();
			gameDetailsStatement.execute();
			gameResultsStatement.close();
			gameDetailsStatement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}
	}

	public void save(Map<Integer, Player> playerList) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			connection.setAutoCommit(false);
			PreparedStatement statement = connection.prepareStatement(gameCount);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			String gameId = "Game" + (resultSet.getInt(rowCount) + 1);
			// Generating a gameId for every game.

			PreparedStatement gameResultsStatement = connection.prepareStatement(saveGameResults);
			CurrentDate date = new CurrentDate();
			gameResultsStatement.setString(1, gameId);
			gameResultsStatement.setString(2, date.getDate());
			PreparedStatement gameDetailsStatement = connection.prepareStatement(saveGameDetails);
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
			statement.close();
			gameResultsStatement.execute(); // Saving results in game_results
											// table.
			gameDetailsStatement.executeBatch(); // Saving results in game
													// details table.
			gameResultsStatement.close();
			gameDetailsStatement.close();
			connection.commit();
			connection.setAutoCommit(true);
			connection.close();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}
	}

	public void delete(String gameId) {
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteGame);
			statement.setString(1, gameId);
			statement.execute();
			statement.close();
			connection.close();

		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}
	}

	public List<GameResults> allGameResults() {
		List<GameResults> gameResultsList = new LinkedList<GameResults>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(gameResults);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				gameResultsList.add(
						new GameResults(result.getString(gameId), result.getString(date), result.getString(winner)));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}
		return gameResultsList;
	}

	public List<Player> gameResultDetails(String gameId) {
		List<Player> gameDetailsList = new LinkedList<Player>();
		try {
			Connection connection = DBConnection.getInstance().getConnection();
			PreparedStatement statement = connection.prepareStatement(gameResultDetails);
			statement.setString(1, gameId);
			ResultSet result = statement.executeQuery();
			while (result.next()) {
				gameDetailsList.add(new Player(result.getInt(position), result.getString(playerName)));
			}
			statement.close();
			connection.close();
		} catch (SQLException e) {
			System.out.println("Database Connection Error");
		}
		return gameDetailsList;
	}

}
