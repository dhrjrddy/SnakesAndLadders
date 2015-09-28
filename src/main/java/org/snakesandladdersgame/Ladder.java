package org.snakesandladdersgame;

import java.util.HashMap;

public class Ladder {
	// This HashMap contains all the ladder positions.
	private HashMap<Integer, Integer> ladderPositions;

	/**
	 * Constructor initializes all the ladder start and end positions.
	 */
	public Ladder() {
		ladderPositions = new HashMap<Integer, Integer>();
		ladderPositions.put(5, 25);
		ladderPositions.put(10, 29);
		ladderPositions.put(22, 41);
		ladderPositions.put(28, 55);
		ladderPositions.put(44, 95);
		ladderPositions.put(70, 89);
		ladderPositions.put(79, 81);
	}

	/**
	 * Returns the end position of the ladder.
	 * 
	 * @param startingPosition
	 * @return ending position of ladder.
	 */
	public int ladderPositions(int startingPosition) {
		return ladderPositions.get(startingPosition);
	}

	/**
	 * Checks whether ladder is in that position and returns true is ladder is
	 * there.
	 */
	public boolean isLadder(int ladderPosition) {
		return ladderPositions.containsKey(ladderPosition);
	}
}
