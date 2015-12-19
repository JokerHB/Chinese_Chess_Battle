package Chess;

import java.util.Map;

public class Board {
	public static final int BOARD_WIDTH = 9;
	public static final int BOARD_HEIGHT = 10;
	public Map<String, Piece> pieces;
	public String currentPlayer = "red";
	public int gameModel = -1;
	private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];

	public boolean isInside(int[] position) {
		return this.isInside(position[0], position[1]);
	}

	public boolean isInside(int x, int y) {
		return !(x < 0 || x >= BOARD_HEIGHT || y < 0 || y >= BOARD_WIDTH);
	}

	public boolean isEmpty(int[] position) {
		return this.isEmpty(position[0], position[1]);
	}

	public boolean isEmpty(int x, int y) {
		return isInside(x, y) && cells[x][y] == null;
	}

	public boolean update(Piece piece) {
		int[] pos = piece.currentPosition;
		this.cells[pos[0]][pos[1]] = piece;
		
		return true;
	}

	public Piece updatePiece(String id, int[] newPos) {
		Piece srcPiece = this.pieces.get(id);
		Piece newPiece = this.getPiece(newPos);
		
		if (newPiece != null){
			pieces.remove(newPiece.id);
		}
		
		int[] srcPos = srcPiece.currentPosition;
		cells[srcPos[0]][srcPos[1]] = null;
		cells[newPos[0]][newPos[1]] = srcPiece;
		srcPiece.currentPosition = newPos;
		currentPlayer = (currentPlayer.hashCode() == "red".hashCode()) ? "black" : "red";

		return newPiece;
	}

	public boolean backPiece(String id) {
		int[] srcPos = pieces.get(id).currentPosition;
		cells[srcPos[0]][srcPos[1]] = pieces.get(id);
		
		return true;
	}

	public Piece getPiece(int[] pos) {
		return getPiece(pos[0], pos[1]);
	}

	public Piece getPiece(int x, int y) {
		return cells[x][y];
	}
}
