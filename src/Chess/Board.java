/*
 * Board Class
 * @author 洪博 靳
 */

package Chess;

import java.util.Map;

public class Board {
	public static final int BOARD_WIDTH = 9;	// 棋盘宽度
	public static final int BOARD_HEIGHT = 10;	// 棋盘长度
	public Map<String, Piece> pieces;			// 棋盘上的棋子映射表
	public String currentPlayer = "red";		// 棋盘当前执子一方
	public int gameModel = -1;					// 游戏模式
	private Piece[][] cells = new Piece[BOARD_HEIGHT][BOARD_WIDTH];		// 棋子

	/**
	 * 判断position处的棋子是否在棋盘上
	 * @param position
	 * @return true if position has a piece
	 */
	public boolean isInside(int[] position) {
		return this.isInside(position[0], position[1]);
	}

	/**
	 * 判断(x,y)处的棋子是否在棋盘上
	 * @param x
	 * @param y
	 * @return true if position has a piece
	 */
	public boolean isInside(int x, int y) {
		return !(x < 0 || x >= BOARD_HEIGHT || y < 0 || y >= BOARD_WIDTH);
	}

	/**
	 * 判断棋盘上position处是否有棋子
	 * @param position
	 * @return
	 */
	public boolean isEmpty(int[] position) {
		return this.isEmpty(position[0], position[1]);
	}
	
	/**
	 * 判断棋盘上position处是否有棋子
	 * @param x
	 * @param y
	 * @return
	 */
	public boolean isEmpty(int x, int y) {
		return isInside(x, y) && cells[x][y] == null;
	}

	/**
	 * 更新当前棋盘状态
	 * @param piece
	 * @return
	 */
	public boolean update(Piece piece) {
		int[] pos = piece.currentPosition;
		this.cells[pos[0]][pos[1]] = piece;
		
		return true;
	}

	/**
	 * 更新棋子id的新位置，并改变当前执子方
	 * @param id
	 * @param newPos
	 * @return
	 */
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
	/**
	 * 棋盘回溯，用于模型评估
	 * @param id
	 * @return
	 */
	public boolean backPiece(String id) {
		int[] srcPos = pieces.get(id).currentPosition;
		cells[srcPos[0]][srcPos[1]] = pieces.get(id);
		
		return true;
	}

	/**
	 * 得到pos处的棋子
	 * @param pos
	 * @return
	 */
	public Piece getPiece(int[] pos) {
		return getPiece(pos[0], pos[1]);
	}

	/**
	 * 得到(x,y)处的棋子
	 * @param x
	 * @param y
	 * @return
	 */
	public Piece getPiece(int x, int y) {
		return cells[x][y];
	}
}
