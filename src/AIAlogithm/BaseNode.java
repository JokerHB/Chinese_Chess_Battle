/*
 * BaseNode Class
 * @author 洪博 靳
 */

package AIAlogithm;

public abstract class BaseNode {
	private String id = new String();
	private int[] nextPosition = new int[2];
	
	public BaseNode() {
		this.id = "";
		
		this.nextPosition[0] = -1;
		this.nextPosition[1] = -1;
	}
	
	/**
	 * 得到棋子的ID
	 * @return id-String
	 */
	public String getId(){
		return this.id;
	}
	
	/**
	 * 得到棋子下一步的位置
	 * @return
	 */
	public int[] getNextPosition() {
		return this.nextPosition;
	}
	
	/**
	 * 设定棋子的ID
	 * @param Id
	 */
	public void setId(String Id) {
		this.id = Id;
	}
	
	/**
	 * 设定棋子下一步的位置
	 * @param pos
	 */
	public void setNextPosition(int[] pos) {
		this.nextPosition = pos;
	}
}
