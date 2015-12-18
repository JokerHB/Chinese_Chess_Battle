package Chess;

public class CannonsPiece extends Piece {

	public CannonsPiece(String pid, int[] cp) {
		super(pid, cp);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void rule(Board board) {
		// TODO Auto-generated method stub
		int[] yOffsets = new int[] { 1, 2, 3, 4, 5, 6, 7, 8 };
		int[] xOffsets = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
		boolean rr = false, ll = false, uu = false, dd = false;

		this.nextPosition.clear();
		
		for (int offset : yOffsets) {
			int[] rMove = new int[] { this.currentPosition[0], this.currentPosition[1] + offset };

			if (!board.isInside(rMove))
				break;
			boolean e = board.isEmpty(rMove);
			if (!rr) {
				if (e)
					this.nextPosition.add(rMove);
				else
					rr = true;
			} else if (!e) {
				if (board.getPiece(rMove).getCamp().hashCode() != board.currentPlayer.hashCode())
					this.nextPosition.add(rMove);
				break;
			}
		}
		for (int offset : yOffsets) {
			int[] lMove = new int[] { this.currentPosition[0], this.currentPosition[1] - offset };
			
			if (!board.isInside(lMove))
				break;
			boolean e = board.isEmpty(lMove);
			if (!ll) {
				if (e)
					this.nextPosition.add(lMove);
				else
					ll = true;
			} else if (!e) {
				if (board.getPiece(lMove).getCamp().hashCode() != board.currentPlayer.hashCode()) {
					this.nextPosition.add(lMove);
				}
				break;
			}
		}
		for (int offset : xOffsets) {
			int[] uMove = new int[] { this.currentPosition[0] - offset, this.currentPosition[1] };
			
			if (!board.isInside(uMove))
				break;
			boolean e = board.isEmpty(uMove);
			if (!uu) {
				if (e)
					this.nextPosition.add(uMove);
				else
					uu = true;
			} else if (!e) {
				if (board.getPiece(uMove).getCamp().hashCode() != board.currentPlayer.hashCode())
					this.nextPosition.add(uMove);
				break;
			}
		}
		for (int offset : xOffsets) {
			int[] dMove = new int[] { this.currentPosition[0] + offset, this.currentPosition[1] };
			
			if (!board.isInside(dMove))
				break;
			boolean e = board.isEmpty(dMove);
			if (!dd) {
				if (e)
					this.nextPosition.add(dMove);
				else
					dd = true;
			} else if (!e) {
				if (board.getPiece(dMove).getCamp().hashCode() != board.currentPlayer.hashCode())
					this.nextPosition.add(dMove);
				break;
			}
		}
	}
}
