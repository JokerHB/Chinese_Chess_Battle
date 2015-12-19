import Chess.Board;
import GameController.GameController;
import UIView.MainViewController;

public class Main {
	private Board board;
	private GameController controller;
	private MainViewController view;

	public static void main(String[] args) throws InterruptedException {
		Main game = new Main();
		game.init();
	}

	public void init() {
		controller = new GameController();
		board = controller.playChess();

		view = new MainViewController(controller);
		view.init(board);
		
		controller.dobuleModel(view, board);
	}
}
