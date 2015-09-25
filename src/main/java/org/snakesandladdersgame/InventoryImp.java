package org.snakesandladdersgame;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InventoryImp implements Inventory {
	// This list contains list of all the game results.
	private List<GameResults> gameResults;

	// This map contains all the players and there positions for all games.
	private Map<String, Map<Integer, Player>> gameResultDetails;

	public InventoryImp() {
		gameResults = new LinkedList<GameResults>();
		gameResultDetails = new HashMap<String, Map<Integer, Player>>();
	}

	public void save(Map<Integer, Player> playerList) {
		CurrentDate date = new CurrentDate();
		String gameId = "Game" + (gameResults.size() + 1);
		// Generating a gameId for every game.

		gameResultDetails.put(gameId, playerList);
		for (Integer key : playerList.keySet()) {
			if (playerList.get(key).getPosition() == 100) {
				// Check the winner and then register the game results.

				gameResults.add(new GameResults(gameId, date.getDate(),
						playerList.get(key).getName()));
			}
		}

	}

	public void delete(String gameId) {
		gameResultDetails.remove(gameId);
		for (Iterator<GameResults> game = gameResults.iterator(); game
				.hasNext();) {
			GameResults myGame = (GameResults) game.next();
			if (myGame.getGameId().equals(gameId)) {
				gameResults.remove(myGame);
				break;
			}
		}

	}

	public List<GameResults> allGameResults() {
		return gameResults;
	}

	public List<Player> gameResultDetails(String gameId) {
		List<Player> gameDetailsList=new LinkedList<Player>();  
		if (gameResultDetails.get(gameId) != null) {
			for (Player player : gameResultDetails.get(gameId)
					.values()) {
				gameDetailsList.add(player);
			}
		} else {
			System.out.println("No Game played of specied Id.");
		}
		return gameDetailsList;
	}

}
