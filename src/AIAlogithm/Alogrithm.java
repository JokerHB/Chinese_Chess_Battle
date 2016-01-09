/*
 * Alogrithm Interface
 * @author 洪博 靳
 */

package AIAlogithm;

import Chess.Board;

/**
 * Algorithm interface
 * @author Joker
 *
 */

public interface Alogrithm {
	/**
	 * 根据当前棋盘初始化算法
	 * @param board
	 */
	public void init(Board board);
	
	/**
	 * 根据之前得到的棋盘进行最佳决策，返回一个棋子与其移动方法 
	 * @return
	 */
	public BaseNode slove();
}
