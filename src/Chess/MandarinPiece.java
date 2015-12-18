package Chess;

public class MandarinPiece extends Piece {

	public MandarinPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		int[][] target = new int[][] { { -1, -1 }, { 1, 1 }, { -1, 1 }, { 1, -1 } };

		this.nextPosition.clear();
		
		for (int[] aTarget : target) {
			int[] e = new int[] { this.currentPosition[0] + aTarget[0], this.currentPosition[1] + aTarget[1] };
			
			if (!board.isInside(e) || ((e[0] > 2 || e[1] < 3 || e[1] > 5) && board.currentPlayer.hashCode() == "black".hashCode())
					|| ((e[0] < 7 || e[1] < 3 || e[1] > 5) && board.currentPlayer.hashCode() == "red".hashCode()))
				continue;
			if (board.isEmpty(e)) {
				this.nextPosition.add(e);
			} else if (board.getPiece(e).getCamp().hashCode() != board.currentPlayer.hashCode()) {
				this.nextPosition.add(e);
			}
		}
	}

}
