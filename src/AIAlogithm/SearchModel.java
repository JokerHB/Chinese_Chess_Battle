/*
 * SearchModel Class
 * @author 洪博 靳
 */

package AIAlogithm;

import java.util.ArrayList;
import java.util.Map;

import Chess.Board;
import Chess.Piece;
import GameController.GameController;

public class SearchModel implements Alogrithm {
	private static int DEPTH = 2;								// 默认搜索深度
	private Board board;										// 当前棋局
	private char currentPlayer;									// 当前执子方
	private GameController controller = new GameController();

	/**
	 * AlphaBeta 剪枝搜索， 根据当前棋局的棋子数设置不同的搜索深度以平衡搜索时间
	 * @param board
	 * @return 返回一个最佳决策
	 */
	public AlphaBetaNode search(Board board) {
		this.board = board;
		if (board.pieces.size() < 28)
			DEPTH = 2;
		if (board.pieces.size() < 16)
			DEPTH = 4;
		if (board.pieces.size() < 6)
			DEPTH = 6;
		if (board.pieces.size() < 4)
			DEPTH = 8;

		long startTime = System.currentTimeMillis();
		AlphaBetaNode best = null;
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(true);

		for (AlphaBetaNode n : moves) {
			/* Move */
			Piece eaten = board.updatePiece(n.getId(), n.getNextPosition());
			n.setValue(alphaBeta(DEPTH, Integer.MIN_VALUE, Integer.MAX_VALUE, false));
			/* Select a best move during searching to save time */
			if (best == null || n.getVlaue() >= best.getVlaue())
				best = n;
			/* Back move */
			board.updatePiece(n.getId(), n.getFromPosition());
			if (eaten != null) {
				board.pieces.put(eaten.id, eaten);
				board.backPiece(eaten.id);
			}
		}

		long finishTime = System.currentTimeMillis();
		System.out.println(finishTime - startTime);

		return best;
	}

	/**
	 * alphaBeta 剪枝搜索， 返回一个移动后的最大收益
	 * @param depth
	 * @param alpha
	 * @param beta
	 * @param isMax
	 * @return
	 */
	private int alphaBeta(int depth, int alpha, int beta, boolean isMax) {
		/* Return evaluation if reaching leaf node or any side won. */
		if (depth == 0 || controller.hasWin(board) != 'x')
			return new EvalModel().eval(board, currentPlayer);
		ArrayList<AlphaBetaNode> moves = generateMovesForAll(isMax);

		synchronized (this) {
			for (final AlphaBetaNode n : moves) {
				Piece eaten = board.updatePiece(n.getId(), n.getNextPosition());
				/* Is maximizing player? */
				final int finalBeta = beta;
				final int finalAlpha = alpha;
				final int finalDepth = depth;
				final int[] temp = new int[1];
				/*
				 * Only adopt multi threading strategy in depth 2. To avoid
				 * conjunction.
				 */
				if (depth == 2) {
					if (isMax) {
						new Thread(new Runnable() {
							@Override
							public void run() {
								temp[0] = Math.max(finalAlpha, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, false));
							}
						}).run();
						alpha = temp[0];
					} else {
						new Thread(new Runnable() {
							@Override
							public void run() {
								temp[0] = Math.min(finalBeta, alphaBeta(finalDepth - 1, finalAlpha, finalBeta, true));
							}
						}).run();
						beta = temp[0];
					}
				} else {
					if (isMax)
						alpha = Math.max(alpha, alphaBeta(depth - 1, alpha, beta, false));
					else
						beta = Math.min(beta, alphaBeta(depth - 1, alpha, beta, true));
				}
				board.updatePiece(n.getId(), n.getFromPosition());
				if (eaten != null) {
					board.pieces.put(eaten.id, eaten);
					board.backPiece(eaten.id);
				}
				/* Cut-off */
				if (beta <= alpha)
					break;
			}
		}
		return isMax ? alpha : beta;
	}

	/**
	 * 得到可行走法集
	 * @param isMax
	 * @return ArrayList<AlphaBetaNode>
	 */
	private ArrayList<AlphaBetaNode> generateMovesForAll(boolean isMax) {
		ArrayList<AlphaBetaNode> moves = new ArrayList<AlphaBetaNode>();
		for (Map.Entry<String, Piece> stringPieceEntry : board.pieces.entrySet()) {
			Piece piece = stringPieceEntry.getValue();
			if (currentPlayer == 'b') {
				if (isMax && piece.getCamp().charAt(0) == 'r')
					continue;
				if (!isMax && piece.getCamp().charAt(0) == 'b')
					continue;
			} else {
				if (!isMax && piece.getCamp().charAt(0) == 'r')
					continue;
				if (isMax && piece.getCamp().charAt(0) == 'b')
					continue;
			}
			piece.rule(board);
			ArrayList<int[]> nextPosition = piece.nextPosition;
			for (int[] nxt : nextPosition)
				moves.add(new AlphaBetaNode(piece.id, piece.currentPosition, nxt));
		}
		return moves;
	}

	@Override
	public void init(Board board) {
		// TODO Auto-generated method stub
		this.board = board;
		this.currentPlayer = board.currentPlayer.charAt(0);
	}

	@Override
	public BaseNode slove() {
		// TODO Auto-generated method stub
		return this.search(board);

	}

}