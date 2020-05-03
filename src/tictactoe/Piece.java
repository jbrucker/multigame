package tictactoe;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * A piece that occupies a square on the board.
 * Piece can have an image, text, or whatever.
 * This class represents the view of a piece.
 * 
 * Piece extends StackPane so it can be placed on another pane.
 * 
 * @author jim
 *
 */
public class Piece extends StackPane {
	/** Special object to represent no piece. Used to avoid nulls on game board. */
	public static final Piece NONE = new Piece(Player.NONE, 10.0);
	/** text displayed on the piece */
	private Text text;
	/** Type type of the piece.  Its public final for efficiency in checking board state. */
	public final Player type;
	
	public Piece(Player type, double size) {
		this.type = type;
		this.text = new Text(type.text);
		text.setStroke(Color.BLACK);
		text.setStrokeWidth(0.04*size); // guess of appropriate thickness
		text.setFont(new Font(0.85*size)); // smaller than board cell size to avoid expanding the board cell
		text.setTextAlignment(TextAlignment.CENTER);
		super.getChildren().add(text);
	}
	
	/**
	 * @see javafx.scene.layout.StackPane#layoutChildren()
	 */
	@Override
	protected void layoutChildren() {
		super.layoutChildren();
		double w = super.getWidth();
		double h = super.getHeight();
		text.resize(w, h);
	}
	
	@Override
	public String toString() {
		return type.text;
	}
}
