package org.snakesandladdersgame;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class ClientTest {

	@Test
	public void snakeModelTest() throws Exception {
		Snake snake = new Snake();
		assertTrue(snake.isSnake(31));
		assertTrue(snake.isSnake(78));
		assertFalse(snake.isSnake(47));
		assertEquals(snake.snakePositions(73), 53);
		assertEquals(snake.snakePositions(92), 35);
		assertFalse(snake.snakePositions(73) == 23);
	}

	@Test
	public void ladderModelTest() throws Exception {
		Ladder ladder = new Ladder();
		assertTrue(ladder.isLadder(5));
		assertTrue(ladder.isLadder(10));
		assertFalse(ladder.isLadder(20));
		assertEquals(ladder.ladderPositions(28), 55);
		assertEquals(ladder.ladderPositions(44), 95);
		assertFalse(ladder.ladderPositions(79) == 80);
	}

	@Test
	public void playerModelTest() throws Exception {
		Player player1 = new Player(10, "name1");
		Player player2 = new Player(0, "name2");
		assertEquals(player1.getName(), "name1");
		assertEquals(player2.getPosition(), 0);
		player2.setPosition(50);
		assertTrue(player2.getPosition() == 50);
		assertFalse(player1.getPosition() == 30);
	}

	@Test
	public void inventoryImpTest() throws Exception {
		Player player1 = new Player(10, "name1");
		Player player2 = new Player(40, "name2");
		Player player3 = new Player(100, "name3");
		Map<Integer, Player> playerList = new HashMap<Integer, Player>();
		playerList.put(1, player1);
		playerList.put(2, player2);
		playerList.put(3, player3);
		Inventory inventory = new InventoryImp();
		inventory.save(playerList);
		Player player4 = new Player(40, "name1");
		Player player5 = new Player(100, "name2");
		Player player6 = new Player(80, "name3");
		Player player7 = new Player(90, "name4");
		Map<Integer, Player> playerList2 = new HashMap<Integer, Player>();
		playerList2.put(1, player4);
		playerList2.put(2, player5);
		playerList2.put(3, player6);
		playerList2.put(4, player7);
		inventory.save(playerList2);
		assertEquals(inventory.allGameResults().get(0).getWinnerName(),
				"name3");
		String[] game1={"name1","name2","name3"};
		assertTrue(Arrays.asList(game1).contains(inventory.gameResultDetails("Game1").get(0).getName()));
		assertFalse(inventory.gameResultDetails("Game1").get(0).getName().equals("name5"));
		
		inventory.delete("Game2");
		assertEquals(inventory.allGameResults().size(), 1);
	}

	@Test
	public void gameResultsModelTest() throws Exception {
		CurrentDate date = new CurrentDate();
		GameResults gameresult1 = new GameResults("Game1", date.getDate(),
				"name1");
		GameResults gameresult2 = new GameResults("Game2", date.getDate(),
				"name2");
		assertEquals(gameresult1.getGameId(), "Game1");
		assertFalse(gameresult2.getWinnerName().equals("name1"));
	}

	@Test
	public void diceTest() throws Exception {
		Dice dice = new Dice();
		assertNotNull(dice.roll());
		assertNotNull(dice.roll());
		assertNotNull(dice.roll());
		assertNotNull(dice.roll());
		assertTrue(dice.roll() >= 1 && dice.roll() <= 6);
		assertTrue(dice.roll() >= 1 && dice.roll() <= 6);
		assertTrue(dice.roll() >= 1 && dice.roll() <= 6);
		assertTrue(dice.roll() >= 1 && dice.roll() <= 6);
	}

	@Test
	public void gameTest() throws Exception {
		Player player1 = new Player(0, "name1");
		Player player2 = new Player(0, "name2");
		Player player3 = new Player(0, "name3");
		Map<Integer, Player> playerList = new HashMap<Integer, Player>();
		playerList.put(0, player1);
		playerList.put(1, player2);
		playerList.put(2, player3);
		Game game = new Game();
		game.board(playerList);
		assertEquals(playerList.size(), 3);
		assertTrue(playerList.get(0).getPosition() == 100
				|| playerList.get(1).getPosition() == 100
				|| playerList.get(2).getPosition() == 100);
		assertFalse(playerList.get(0).getPosition() == 100
				&& playerList.get(1).getPosition() == 100
				&& playerList.get(2).getPosition() == 100);
		assertFalse(playerList.get(0).getPosition() == 100
				&& playerList.get(1).getPosition() == 100);
		assertFalse(playerList.get(0).getPosition() == 100
				&& playerList.get(2).getPosition() == 100);
		assertFalse(playerList.get(1).getPosition() == 100
				&& playerList.get(2).getPosition() == 100);

	}
	
	@Test
	public void dbInventoryImpTest() throws Exception {
		Player player1 = new Player(10, "name1");
		Player player2 = new Player(40, "name2");
		Player player3 = new Player(100, "name3");
		Map<Integer, Player> playerList = new HashMap<Integer, Player>();
		playerList.put(1, player1);
		playerList.put(2, player2);
		playerList.put(3, player3);
		Inventory inventory = new DBInventoryImp();
		inventory.save(playerList);
		Player player4 = new Player(40, "name1");
		Player player5 = new Player(100, "name2");
		Player player6 = new Player(80, "name3");
		Player player7 = new Player(90, "name4");
		Map<Integer, Player> playerList2 = new HashMap<Integer, Player>();
		playerList2.put(1, player4);
		playerList2.put(2, player5);
		playerList2.put(3, player6);
		playerList2.put(4, player7);
		inventory.save(playerList2);
		assertEquals(inventory.allGameResults().get(0).getWinnerName(),
				"name3");
		String[] game1={"name1","name2","name3"};
		assertTrue(Arrays.asList(game1).contains(inventory.gameResultDetails("Game1").get(0).getName()));
		assertFalse(inventory.gameResultDetails("Game1").get(0).getName().equals("name5"));
		
	}
}
