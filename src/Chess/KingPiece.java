package Chess;

public class KingPiece extends Piece {

	public KingPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		/* 3*3 block */
		int[][] target = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

		this.nextPosition.clear();
		
		for (int[] aTarget : target) {
			int[] e = new int[] { this.currentPosition[0] + aTarget[0], this.currentPosition[1] + aTarget[1] };

			if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && board.currentPlayer.hashCode() == "black".hashCode())
					|| ((e[0] < 7 || e[1] < 3 || e[1] > 5) && board.currentPlayer.hashCode() == "red".hashCode()))
				continue;
			if (board.isEmpty(e)) {
				this.nextPosition.add(e);
			} else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0)) {
				this.nextPosition.add(e);
			}
		}

		boolean flag = true;
		int[] oppoBoss = (board.currentPlayer.hashCode() == "red".hashCode()) ? board.pieces.get("bb0").currentPosition
				: board.pieces.get("rb0").currentPosition;

		if (oppoBoss[1] == this.currentPosition[1]) {
			for (int i = Math.min(oppoBoss[0], this.currentPosition[0]) + 1; i < Math.max(oppoBoss[0],
					this.currentPosition[0]); i++) {
				if (board.getPiece(i, this.currentPosition[1]) != null) {
					flag = false;
					break;
				}
			}
			if (flag)
				this.nextPosition.add(oppoBoss);
		}
	}

}
