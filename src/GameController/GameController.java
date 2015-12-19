package GameController;

import java.util.HashMap;
import java.util.Map;

import AIAlogithm.Alogrithm;
import AIAlogithm.BaseNode;
import AIAlogithm.SearchModel;
import Chess.Board;
import Chess.CannonsPiece;
import Chess.ElephantPiece;
import Chess.KingPiece;
import Chess.KnightsPiece;
import Chess.MandarinPiece;
import Chess.PawnsPiece;
import Chess.Piece;
import Chess.RookPiece;
import UIView.MainViewController;

public class GameController {
	private Board initBoard() {
		Board board = new Board();
		board.pieces = initPieces();
		for (Map.Entry<String, Piece> stringPieceEntry : initPieces().entrySet())
			board.update(stringPieceEntry.getValue());

		return board;
	}

	public Board playChess() {
		initPieces();

		return this.initBoard();
	}

	public void moveChess(String id, int[] position, Board board) {
		board.updatePiece(id, position);
	}

	public void responseMoveChess(Board board, MainViewController view) {
		// AI alogithm
		// AI alogithm should return the best result
		// result should include piece's id, and target position
		// update UI and board
		// view.movePieceFromAI(result.piece, result.to);
		// board.updatePiece(result.piece, result.to);

		// eg
		Alogrithm alogrithm = (Alogrithm) new SearchModel();
		alogrithm.init(board);
		BaseNode result = alogrithm.slove();

		view.movePieceFromAI(result.getId(), result.getNextPosition());
		board.updatePiece(result.getId(), result.getNextPosition());
	}

	public char hasWin(Board board) {
		boolean isRedWin = board.pieces.get("bb0") == null;
		boolean isBlackWin = board.pieces.get("rb0") == null;

		if (isRedWin)
			return 'r';
		else if (isBlackWin)
			return 'b';
		else
			return 'x';
	}

	public void dobuleModel(MainViewController view, Board board) {
		while (this.hasWin(board) == 'x') {
			view.showPlayer(board.currentPlayer);

			if (this.hasWin(board) != 'x') {
				view.showWinner("red");
			}

			view.showPlayer(board.currentPlayer);

			if (this.hasWin(board) != 'x') {
				view.showWinner("black");
			}
		}
	}

	public void singleModel(MainViewController view, Board board) {
		while (this.hasWin(board) == 'x') {
			view.showPlayer(board.currentPlayer);
			while (board.currentPlayer.hashCode() == "red".hashCode()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			if (this.hasWin(board) != 'x') {
				view.showWinner("red");
			}

			view.showPlayer(board.currentPlayer);
			// call the ai alogrithm to slove the next step
			this.responseMoveChess(board, view);

			if (this.hasWin(board) != 'x') {
				view.showWinner("black");
			}
		}
	}

	public void watchModel(MainViewController view, Board board) {
		while (this.hasWin(board) == 'x') {
			view.showPlayer(board.currentPlayer);
			// call the ai alogrithm to slove the next step
			this.responseMoveChess(board, view);

			if (this.hasWin(board) != 'x') {
				view.showWinner("black");
			}

			view.showPlayer(board.currentPlayer);
			// call the ai alogrithm to slove the next step
			this.responseMoveChess(board, view);

			if (this.hasWin(board) != 'x') {
				view.showWinner("black");
			}
		}
	}

	public void runModel(MainViewController view, Board board) {
		switch (board.gameModel) {
		case 1:
			this.singleModel(view, board);
			break;
		case 2:
			this.dobuleModel(view, board);
			break;
		case 3:
			this.watchModel(view, board);
			break;
		}
	}

	private Map<String, Piece> initPieces() {
		Map<String, Piece> pieces = new HashMap<String, Piece>();
		pieces.put("bj0", new RookPiece("bj0", new int[] { 0, 0 }));
		pieces.put("bm0", new KnightsPiece("bm0", new int[] { 0, 1 }));
		pieces.put("bx0", new ElephantPiece("bx0", new int[] { 0, 2 }));
		pieces.put("bs0", new MandarinPiece("bs0", new int[] { 0, 3 }));
		pieces.put("bb0", new KingPiece("bb0", new int[] { 0, 4 }));
		pieces.put("bs1", new MandarinPiece("bs1", new int[] { 0, 5 }));
		pieces.put("bx1", new ElephantPiece("bx1", new int[] { 0, 6 }));
		pieces.put("bm1", new KnightsPiece("bm1", new int[] { 0, 7 }));
		pieces.put("bj1", new RookPiece("bj1", new int[] { 0, 8 }));
		pieces.put("bp0", new CannonsPiece("bp0", new int[] { 2, 1 }));
		pieces.put("bp1", new CannonsPiece("bp1", new int[] { 2, 7 }));
		pieces.put("bz0", new PawnsPiece("bz0", new int[] { 3, 0 }));
		pieces.put("bz1", new PawnsPiece("bz1", new int[] { 3, 2 }));
		pieces.put("bz2", new PawnsPiece("bz2", new int[] { 3, 4 }));
		pieces.put("bz3", new PawnsPiece("bz3", new int[] { 3, 6 }));
		pieces.put("bz4", new PawnsPiece("bz4", new int[] { 3, 8 }));

		pieces.put("rj0", new RookPiece("rj0", new int[] { 9, 0 }));
		pieces.put("rm0", new KnightsPiece("rm0", new int[] { 9, 1 }));
		pieces.put("rx0", new ElephantPiece("rx0", new int[] { 9, 2 }));
		pieces.put("rs0", new MandarinPiece("rs0", new int[] { 9, 3 }));
		pieces.put("rb0", new KingPiece("rb0", new int[] { 9, 4 }));
		pieces.put("rs1", new MandarinPiece("rs1", new int[] { 9, 5 }));
		pieces.put("rx1", new ElephantPiece("rx1", new int[] { 9, 6 }));
		pieces.put("rm1", new KnightsPiece("rm1", new int[] { 9, 7 }));
		pieces.put("rj1", new RookPiece("rj1", new int[] { 9, 8 }));
		pieces.put("rp0", new CannonsPiece("rp0", new int[] { 7, 1 }));
		pieces.put("rp1", new CannonsPiece("rp1", new int[] { 7, 7 }));
		pieces.put("rz0", new PawnsPiece("rz0", new int[] { 6, 0 }));
		pieces.put("rz1", new PawnsPiece("rz1", new int[] { 6, 2 }));
		pieces.put("rz2", new PawnsPiece("rz2", new int[] { 6, 4 }));
		pieces.put("rz3", new PawnsPiece("rz3", new int[] { 6, 6 }));
		pieces.put("rz4", new PawnsPiece("rz4", new int[] { 6, 8 }));

		return pieces;
	}
}
