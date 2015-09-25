package org.snakesandladdersgame;

public class GameResults {

	private String gameId; // Game Id
	private String date; // Date on which game played
	private String winnerName; // Winner of the game.

	/**
	 *  Construct a new game result with given fields.
	 * @param gameId
	 * @param date
	 * @param winnerName
	 */
	public GameResults(String gameId, String date, String winnerName) {
		this.gameId = gameId;
		this.date = date;
		this.winnerName = winnerName;
	}

	/**
	 *  @return game id
	 */
	public String getGameId() {
		return gameId;
	}

	/**
	 * @return date on which game played
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @return winner of game.
	 */
	public String getWinnerName() {
		return winnerName;
	}
}
