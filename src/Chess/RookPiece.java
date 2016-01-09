/*
 * RookPiece Class
 * @author 洪博 靳
 */

package Chess;

public class RookPiece extends Piece {

	public RookPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		int[] yOffsets = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] xOffsets = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };

		this.nextPosition.clear();
		
		for (int offset : yOffsets) {
			int[] rMove = new int[] { currentPosition[0], currentPosition[1] + offset };
			if (board.isEmpty(rMove)) {
				nextPosition.add(rMove);
			} else if (board.isInside(rMove) && board.getPiece(rMove).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
				nextPosition.add(rMove);
				break;
			} else
				break;
		}
		for (int offset : yOffsets) {
			int[] lMove = new int[] { currentPosition[0], currentPosition[1] - offset };
			if (board.isEmpty(lMove)) {
				nextPosition.add(lMove);
			} else if (board.isInside(lMove) && board.getPiece(lMove).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
				nextPosition.add(lMove);
				break;
			} else
				break;
		}
		for (int offset : xOffsets) {
			int[] uMove = new int[] { currentPosition[0] - offset, currentPosition[1] };
			if (board.isEmpty(uMove)) {
				nextPosition.add(uMove);
			} else if (board.isInside(uMove) && board.getPiece(uMove).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
				nextPosition.add(uMove);
				break;
			} else
				break;
		}
		for (int offset : xOffsets) {
			int[] dMove = new int[] { currentPosition[0] + offset, currentPosition[1] };
			if (board.isEmpty(dMove)) {
				nextPosition.add(dMove);
			} else if (board.isInside(dMove) && board.getPiece(dMove).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
				nextPosition.add(dMove);
				break;
			} else
				break;
		}
	}

}
