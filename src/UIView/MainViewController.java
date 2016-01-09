/*
 * MainViewController
 * @author 洪博 靳
 */

package UIView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;

import Chess.Board;
import Chess.Piece;
import GameController.GameController;

public class MainViewController {
	private static final int VIEW_WIDTH = 700;
	private static final int VIEW_HEIGHT = 712;
	private static final int PIECE_WIDTH = 67;
	private static final int PIECE_HEIGHT = 67;
	private static final int SY_COE = 68;
	private static final int SX_COE = 68;
	private static final int SX_OFFSET = 50;
	private static final int SY_OFFSET = 15;

	private Map<String, JLabel> pieceObjects = new HashMap<String, JLabel>();				// 棋子映射表
	private Map<String, JLabel> nextStepObjects = new HashMap<String, JLabel>();			// 可走点表
	private Board board;																	// 当前棋局
	private String selectedPieceID;															// 当前选中棋子
	private JFrame frame;
	private JLayeredPane gamePane;
	private GameController gameController;
	private JLabel lblPlayer;

	public MainViewController(GameController gc) {
		this.gameController = gc;
	}

	/**
	 * 初始化游戏UI
	 * @param bd
	 */
	public void init(final Board bd) {
		this.board = bd;

		this.frame = new JFrame("Chinese Chess Battle @HongBo.Jing, Jing.Chen, WenShan.Li");
		this.frame.setIconImage(new ImageIcon("res/img/icon.icon.png").getImage());
		this.frame.setSize(this.VIEW_WIDTH, this.VIEW_HEIGHT + 40);
		this.frame.setLocationRelativeTo(null);
		this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		this.gamePane = new JLayeredPane();
		this.frame.add(this.gamePane);
		
		JLabel backgroundBoard = new JLabel(new ImageIcon("res/img/board.png"));
		backgroundBoard.setLocation(0, 0);
		backgroundBoard.setSize(this.VIEW_WIDTH, this.VIEW_HEIGHT);
		backgroundBoard.addMouseListener(new BoardClickListener());
		this.gamePane.add(backgroundBoard, 1);

		this.lblPlayer = new JLabel(new ImageIcon("res/img/r.png"));
		this.lblPlayer.setLocation(10, 320);
		this.lblPlayer.setSize(this.PIECE_WIDTH, this.PIECE_HEIGHT);
		this.gamePane.add(this.lblPlayer, 0);

		for (int i = 0; i < Board.BOARD_HEIGHT; i++) {
			for (int j = 0; j < Board.BOARD_WIDTH; j++) {
				JLabel nextStep = new JLabel(new ImageIcon("res/img/ns.png"));
				String id = String.valueOf(i) + "," + String.valueOf(j);
				int[] setPosition = modelToViewConverter(new int[]{i,j});

				nextStep.setLocation(setPosition[0], setPosition[1]);				
				nextStep.setSize(this.PIECE_WIDTH, this.PIECE_HEIGHT);
				nextStep.setVisible(false);
				
				this.nextStepObjects.put(id, nextStep);
				this.gamePane.add(nextStep, 1);
			}
		}
		this.frame.setVisible(true);

		Map<String, Piece> pieces = board.pieces;
		for (Map.Entry<String, Piece> stringPieceEntry : pieces.entrySet()) {
			String id = stringPieceEntry.getKey();
			int[] currentPosition = stringPieceEntry.getValue().currentPosition;
			int[] setPosition = modelToViewConverter(currentPosition);
			JLabel lblPiece = new JLabel(new ImageIcon("res/img/" + id.substring(0, 2) + ".png"));

			lblPiece.setLocation(setPosition[0], setPosition[1]);
			lblPiece.setSize(this.PIECE_WIDTH, this.PIECE_HEIGHT);
			lblPiece.addMouseListener(new PieceOnClickListener(id));

			this.pieceObjects.put(stringPieceEntry.getKey(), lblPiece);
			this.gamePane.add(lblPiece, 0);
		}
		
		this.frame.setVisible(true);
	}

	/**
	 * 玩家：移动棋子到to位置
	 * @param pieceId
	 * @param to
	 */
	public void movePieceFromModel(String pieceId, int[] to) {
		JLabel pieceObject = this.pieceObjects.get(pieceId);
		int[] setPosition = this.modelToViewConverter(to);

		pieceObject.setLocation(setPosition[0], setPosition[1]);
		this.selectedPieceID = null;
	}
	
	/**
	 * AI：移动棋子到to位置
	 * @param pieceId
	 * @param to
	 */
	public void movePieceFromAI(String pieceId, int[] to) {
		Piece inNewPos = this.board.getPiece(to);

		if (null != inNewPos) {
			this.gamePane.remove(this.pieceObjects.get(inNewPos.id));
			this.pieceObjects.remove(inNewPos.id);
		}

		JLabel pieceObject = this.pieceObjects.get(pieceId);
		int[] setPosition = this.modelToViewConverter(to);

		pieceObject.setLocation(setPosition[0], setPosition[1]);
		this.selectedPieceID = null;
	}

	/**
	 * 将逻辑坐标转为UI界面坐标
	 * @param currentPosition
	 * @return UI position
	 */
	private int[] modelToViewConverter(int[] currentPosition) {
		int sx = currentPosition[1] * this.SX_COE + this.SX_OFFSET;
		int sy = currentPosition[0] * this.SY_COE + this.SY_OFFSET;

		return new int[] { sx, sy };
	}
	
	/**
	 * 将UI界面坐标转化为逻辑坐标
	 * @param setPosition
	 * @return logical position
	 */
	private int[] viewToModelConverter(int[] setPosition) {
		int ADDITIONAL_SY_OFFSET = 25;
		int y = (setPosition[0] - this.SX_OFFSET) / this.SX_COE;
		int x = (setPosition[1] - this.SY_OFFSET - ADDITIONAL_SY_OFFSET) / this.SY_COE;

		return new int[] { x, y };
	}

	/**
	 * 显示当前执子方
	 * @param player
	 */
	public void showPlayer(String player) {
		this.lblPlayer.setIcon(new ImageIcon("res/img/" + player.substring(0, 1) + ".png"));
		this.frame.setVisible(true);
	}

	/**
	 * 显式当前赢家并结束程序
	 * @param player
	 */
	public void showWinner(String player) {
		JOptionPane.showMessageDialog(null, player + " has won!");
		System.exit(0);
	}

	/**
	 * 得到选中棋子的ID
	 * @return id-String
	 */
	public String getSelectPieceId() {
		return this.selectedPieceID;
	}

	/**
	 * 添加棋子点击监视器
	 * @author 洪博 靳
	 *
	 */
	class PieceOnClickListener extends MouseAdapter {
		private String id;

		PieceOnClickListener(String Id) {
			this.id = Id;
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (selectedPieceID != null && id.charAt(0) != board.currentPlayer.charAt(0)) {
				int[] pos = board.pieces.get(id).currentPosition;
				board.pieces.get(selectedPieceID).rule(board);
				ArrayList<int[]> nextPosition = board.pieces.get(selectedPieceID).nextPosition;

				for (int[] each : nextPosition) {
					if (Arrays.equals(each, pos)) {
						gamePane.remove(pieceObjects.get(id));
						pieceObjects.remove(id);
						gameController.moveChess(selectedPieceID, pos, board);
						movePieceFromModel(selectedPieceID, pos);
						// 消除可走点
						for (Map.Entry<String, JLabel> stringPieceEntry : nextStepObjects.entrySet()) {
							stringPieceEntry.getValue().setVisible(false);
						}
						
						break;
					}
				}
			} else if (id.charAt(0) == board.currentPlayer.charAt(0)) {
				if (selectedPieceID != null && id.hashCode() != selectedPieceID.hashCode()) {
					// 消除可走点
					for (Map.Entry<String, JLabel> stringPieceEntry : nextStepObjects.entrySet()) {
						stringPieceEntry.getValue().setVisible(false);
					}
				}

				selectedPieceID = id;

				board.pieces.get(selectedPieceID).rule(board);
				ArrayList<int[]> nextPosition = board.pieces.get(selectedPieceID).nextPosition;

				// 显示可走点
				for (int[] each : nextPosition) {
					String id = String.valueOf(each[0]) + "," + String.valueOf(each[1]);
					nextStepObjects.get(id).setVisible(true);
				}
				frame.setVisible(true);
			}
		}
	}

	/**
	 * 添加棋盘点击监视器
	 * @author 洪博 靳
	 *
	 */
	class BoardClickListener extends MouseAdapter {
		@Override
		public void mousePressed(MouseEvent e) {
			if (selectedPieceID != null) {
				int[] sPos = new int[] { e.getXOnScreen() - frame.getX(), e.getYOnScreen() - frame.getY() };
				int[] pos = viewToModelConverter(sPos);
				board.pieces.get(selectedPieceID).rule(board);
				ArrayList<int[]> nextPosition = board.pieces.get(selectedPieceID).nextPosition;

				for (int[] each : nextPosition) {
					if (Arrays.equals(each, pos)) {
						gameController.moveChess(selectedPieceID, pos, board);
						movePieceFromModel(selectedPieceID, pos);

						for (Map.Entry<String, JLabel> stringPieceEntry : nextStepObjects.entrySet()) {
							stringPieceEntry.getValue().setVisible(false);
						}
						
						break;
					}
				}
			}
		}
	}
}
