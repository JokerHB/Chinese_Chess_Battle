/*
 * Chess Class
 * @author 洪博 靳
 */

import Chess.Board;
import GameController.GameController;
import UIView.FunctionViewController;
import UIView.MainViewController;

public class Main {
	private Board board;
	private GameController controller;
	private MainViewController view;
	private FunctionViewController selectView;

	public static void main(String[] args) throws InterruptedException {
		Main game = new Main();
		game.init();
	}

	public void init() {
		selectView = new FunctionViewController();
		controller = new GameController();
		board = controller.playChess();

		selectView.init();

		// 判断玩家选择的游戏模式
		while(selectView.gameModel == -1) {
			// do not delete this line
			System.out.println(-1);
		}
		
		if(selectView.gameModel == 1) {
			while(selectView.gameFirst == -1) {
				System.out.println(-1);
			}
			
			if(selectView.gameFirst == 1) {
				board.currentPlayer = "red";
			} else {
				board.currentPlayer = "black";
			}	
		}
		
		if(selectView.gameModel == 3) {
			board.currentPlayer = "black";
		}
		
		board.gameModel = selectView.gameModel;
		selectView.gameModel = -1;
		selectView.gameFirst = -1;

		// 开始游戏
		view = new MainViewController(controller);
		view.init(board);
		controller.runModel(view, board);
	}
}
