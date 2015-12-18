import Chess.Board;
import GameControl.GameControler;
import UIView.MainViewControler;

public class Main {
	private Board board;
	private GameControler controller;
	private MainViewControler view;

	public static void main(String[] args) throws InterruptedException {
		Main game = new Main();
		game.init();
		game.run();
	}

	public void init() {
		controller = new GameControler();
		board = controller.playChess();

		view = new MainViewControler(controller);
		view.init(board);
	}

	public void run() throws InterruptedException {
		while (controller.hasWin(board) == 'x') {
			view.showPlayer(board.currentPlayer);
			while (board.currentPlayer.hashCode() == "red".hashCode()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (controller.hasWin(board) != 'x'){
				view.showWinner("red");
			}
			
			// call the ai alogrithm to slove the next step
			// controller.responseMoveChess(board, view);
			
			view.showPlayer(board.currentPlayer);
			while (board.currentPlayer.hashCode() == "black".hashCode()) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			if (controller.hasWin(board) != 'x'){
				view.showWinner("black");
			}
		}
	}
}
