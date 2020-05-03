package tictactoe;

/**
 * Types of pieces in the game.
 */
public enum Player {
	X("X", 1),
	O("O", -1),
	NONE("", 0);
	
	public final String text;
	public final int value;
	
	private Player(String text, int value) {
		this.text = text;
		this.value = value;
	}
}
