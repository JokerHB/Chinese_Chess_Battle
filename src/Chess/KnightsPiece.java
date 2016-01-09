/*
 * KnightsPiece Class
 * @author 洪博 靳
 */

package Chess;

public class KnightsPiece extends Piece {

	public KnightsPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		int[][] target = new int[][] { { 1, -2 }, { 2, -1 }, { 2, 1 }, { 1, 2 }, { -1, 2 }, { -2, 1 }, { -2, -1 },
				{ -1, -2 } };
		int[][] obstacle = new int[][] { { 0, -1 }, { 1, 0 }, { 1, 0 }, { 0, 1 }, { 0, 1 }, { -1, 0 }, { -1, 0 },
				{ 0, -1 } };

		this.nextPosition.clear();
				
		for (int i = 0; i < target.length; i++) {
			int[] e = new int[] { this.currentPosition[0] + target[i][0], this.currentPosition[1] + target[i][1] };
			int[] f = new int[] { this.currentPosition[0] + obstacle[i][0], this.currentPosition[1] + obstacle[i][1] };

			if (!board.isInside(e))
				continue;
			if (board.isEmpty(f)) {
				if (board.isEmpty(e)) {
					this.nextPosition.add(e);
				} else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
					this.nextPosition.add(e);
				}
			}
		}
	}
}
