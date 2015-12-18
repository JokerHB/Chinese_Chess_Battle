package Chess;

public class PawnsPiece extends Piece {

	public PawnsPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		int[][] targetU = new int[][] { { 0, 1 }, { 0, -1 }, { -1, 0 } };
		int[][] targetD = new int[][] { { 0, 1 }, { 0, -1 }, { 1, 0 } };
		
		this.nextPosition.clear();
		
		if (board.currentPlayer.hashCode() == "red".hashCode()) {
			if (this.currentPosition[0] > 4) {
				int[] e = new int[] { this.currentPosition[0] - 1, this.currentPosition[1] };
				if (board.isEmpty(e))
					this.nextPosition.add(e);
				else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0))
					this.nextPosition.add(e);
			} else {
				for (int[] aTarget : targetU) {
					int[] e = new int[] { this.currentPosition[0] + aTarget[0], this.currentPosition[1] + aTarget[1] };
					if (!board.isInside(e))
						continue;
					if (board.isEmpty(e))
						this.nextPosition.add(e);
					else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0))
						this.nextPosition.add(e);
				}
			}
		}
		if (board.currentPlayer.hashCode() == "black".hashCode()) {
			if (this.currentPosition[0] < 5) {
				int[] e = new int[] { this.currentPosition[0] + 1, this.currentPosition[1] };
				if (board.isEmpty(e))
					this.nextPosition.add(e);
				else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0))
					this.nextPosition.add(e);
			} else {
				for (int[] aTarget : targetD) {
					int[] e = new int[] { this.currentPosition[0] + aTarget[0], this.currentPosition[1] + aTarget[1] };
					if (!board.isInside(e))
						continue;
					if (board.isEmpty(e))
						this.nextPosition.add(e);
					else if (board.getPiece(e).getCamp().charAt(0) != board.currentPlayer.charAt(0))
						this.nextPosition.add(e);
				}
			}
		}
	}
}
