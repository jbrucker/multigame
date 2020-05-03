package tictactoe;

import javafx.geometry.Bounds;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

/**
 * A TicTacToe board.
 * This class provides a view of the board.
 */
public class Board extends GridPane  {
	/** Size of squares on the board. */
	public static final int TILESIZE = 100;
	/** gap between rows and cols. May be zero. */
	public static final double GAP = 2;
	/** The actual squares on the board. */
	private BoardSquare[][] board;

	/**
	 * Create an empty tic tac toe board.
	 * @return empty board object
	 */
	public Board(int rows, int cols) {
		// is this necessary? We can get the squares directly from GridPane.
		board = new BoardSquare[rows][cols];
		// for tic-tac-toe the squares are all same color
		Color squareColor = Color.LIGHTGRAY; // nice boring color
		// draw border around each square
		super.setGridLinesVisible(true);
		super.setHgap(GAP);
		super.setVgap(GAP);
		
		for (int row = 0; row < board.length; row++) {
			int y = row * TILESIZE;
			for (int col = 0; col < board[row].length; col++) {
				int x = col * TILESIZE;
				BoardSquare cell = new BoardSquare(row, col, TILESIZE);
				cell.setFill( squareColor );
				board[row][col] = cell;
				super.add( cell, col, row );
				Bounds b = cell.getBoundsInParent();
//				System.out.printf("Cell [%d,%d] has bounds x=[%.1f,%.1f] y=[%.1f,%.1f]\n", 
//						row, col,
//						b.getMinX(), b.getMaxX(), b.getMinY(), b.getMaxY() );
			}
		}
		// make the game board fit the cells. Don't forget hgap and vgap.
		this.setPrefSize(cols * TILESIZE + (cols-1)*GAP, rows * TILESIZE + (rows-1)*GAP);
		this.setMinSize(cols * TILESIZE + (cols-1)*GAP, rows * TILESIZE + (rows-1)*GAP);
	}	
}
