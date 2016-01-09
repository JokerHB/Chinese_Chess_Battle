/*
 * AlphaBetaNode Class
 * @author 洪博 靳
 */

package AIAlogithm;

public class AlphaBetaNode extends BaseNode{
	private int[] fromPosition;
	private int value = 0;
	
	/**
	 * 初始化博弈树节点
	 * @param piece
	 * @param from
	 * @param to
	 */
	public AlphaBetaNode(String piece, int[] from, int[] to){
		this.setId(piece);
		this.setNextPosition(to);
		this.setFromPosition(from);
	}
	
	/**
	 * 得到棋子来源位置
	 * @return
	 */
	public int[] getFromPosition() {
		return this.fromPosition;
	}
	
	/**
	 * 得到棋子的评估值
	 * @return value-int
	 */
	public int getVlaue(){
		return this.value;
	}
	
	/**
	 * 设定棋子的评估值
	 * @param val
	 */
	public void setValue(int val) {
		this.value = val;
	}
	
	/**
	 * 设定棋子来源位置
	 * @param pos
	 */
	public void setFromPosition(int[] pos) {
		this.fromPosition = pos;
	}
}
