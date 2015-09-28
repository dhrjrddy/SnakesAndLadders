package org.snakesandladdersgame;

public class Dice {
	private static final int MIN = 1;
	private static final int MAX = 6;

	/**
	 * This method gets random method from random method and
	 * 
	 * @return random dice score
	 */
	public int roll() {
		return this.random(MIN, MAX);
	}

	/**
	 * This method generates random number between 1 and 6. Next it returns the
	 * value to roll method.
	 * 
	 * @param min
	 *            dice.
	 * @param mac
	 *            dice.
	 */
	private int random(int min, int max) {
		int result = (int) (min + Math.floor((max - min) * Math.random()));
		return result;
	}
}
