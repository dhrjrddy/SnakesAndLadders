package org.snakesandladdersgame;

public class Player {
	int position; // Player position
	String name; // Player name

	// Construct a new player with given fields
	public Player(int position, String name) {
		this.position = position;
		this.name = name;
	}

	// Returns the name of the player
	public String getName() {
		return name;
	}

	// Returns the position of the player
	public int getPosition() {
		return position;
	}

	// Sets the position of the player
	public void setPosition(int position) {
		this.position = position;

	}
}