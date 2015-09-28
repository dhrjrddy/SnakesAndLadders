package org.snakesandladdersgame;

import java.util.HashMap;

public class Snake {
	// This HashMap contains all the snake positions.
	private HashMap<Integer, Integer> snakePositions;

	/**
	 * Constructor initializes all the snake start and end positions.
	 */
	public Snake() {
		snakePositions = new HashMap<Integer, Integer>();
		snakePositions.put(31, 14);
		snakePositions.put(37, 17);
		snakePositions.put(73, 53);
		snakePositions.put(78, 39);
		snakePositions.put(92, 35);
		snakePositions.put(99, 7);
	}

	/**
	 * Returns the end position of the snake.
	 * 
	 * @param startingPosition
	 * @return ending position of snake.
	 */
	public int snakePositions(int startingPosition) {
		return snakePositions.get(startingPosition);
	}

	/**
	 * Checks whether snake is in that position and returns true is snake is
	 * there.
	 */
	public boolean isSnake(int snakePosition) {
		return snakePositions.containsKey(snakePosition);
	}

}
