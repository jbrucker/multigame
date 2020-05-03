package guessinggame;

import java.util.Random;

/**
 * A model class for a guessing game.
 * Change this to be suitable for your game.
 * You need to modify the GameController to match.
 * 
 * @author Jim
 */
public class GuessingGame {
	// the secret number to guess
	private int secret;
	// the upper bound for secret. This determines how hard the game is.
	private int upperBound;
	// message for the player
	private String message = "";
	// count the guesses
	private int count;
	
	/**
	 * Since this is a general model, we should have a default contructor.
	 */
	public GuessingGame() {
		this(100);
	}
	
	/**
	 * Initialize a guessing game with given upper limit on the secret number.
	 * @param upperBound  the upper limit for the secret number. Must be positive.
	 */
	public GuessingGame(int upperBound) {
		this.upperBound = upperBound;
		this.count = 0; 
		final long seed = System.currentTimeMillis();
		Random rand = new Random(seed);
		this.secret = rand.nextInt(upperBound) + 1;
		this.message = "I'm thinking of a number between 1 and "+upperBound;
	}

	public String getTitle() {
		return "Guessing Game";
	}
	
	public String getMessage() {
		return message;
	}
	
	/**
	 * Evaluate a user's input.
	 * Since this is a guessing game, I called it "guess".
	 * After evaluating the user's guess, prepare a message
	 * to display (call getMessage to get it).
	 * 
	 * @param value the user's input
	 * @return true if user guesses the secret number, false otherwise
	 */
	public boolean guess(String value) {
		int guess = 0;
		try {
			guess = Integer.parseInt(value);
		} catch (NumberFormatException nfe) {
			message = String.format("\"%s\" is not a valid guess",value);
			return false;
		}
		count++; // always count the guess
		message = makeHint(guess);
		return guess == secret;
	}

	/**
	 * Make a hint based on the most recent guess.
	 * @param guess is the most recent guess
	 * @return a hint
	 */
	protected String makeHint(int guess) {
		if (guess < 1 || guess > upperBound) {
			return "Impossible! The secret is 1 - "+upperBound;
		}
		if (guess < secret) {
			return guess + " is too small.";
		}
		if (guess > secret) {
			return guess + " is too large.";
		}
		// only remaining case is correct
		return "Right! You guessed it.";
	}
	
	/**
	 * Get the upper bound for the secret.
	 * @return the upperbound on secret number
	 */
	public int getUpperBound() {
		return upperBound;
	}

	/** Return the number of guesses so far. */
	public int getCount() {
		return this.count;
	}
}
