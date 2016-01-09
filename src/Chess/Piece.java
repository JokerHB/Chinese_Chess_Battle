/*
 * Piece Class
 * @author 洪博 靳
 */

package Chess;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {
	public String id;	// piece id = camp, type, no
 
	public int[] currentPosition = new int[2];	// current position in board
	public ArrayList<int[]> nextPosition = new ArrayList<int[]>();	// next position
	
	public abstract void rule(Board board);  	// each piece has its own method to move, subclass must rewrite this method
	
	/**
	 * init the class
	 * @param pid
	 * @param cp
	 */
	public Piece(String pid, int[] cp) {
		this.id = pid;
		this.currentPosition = cp;
	}
	
	/**
	 * get the camp of piece
	 * @return
	 */
	public String getCamp() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(0,1);
		} 
		
		return null;
	}
	
	/**
	 * get the type of piece
	 * @return
	 */
	public String getType() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(1,2);
		} 
		
		return null;
	}
	
	/**
	 * get the id(no) of the piece
	 * @return
	 */
	public String getNo() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(2,3);
		} 
		
		return null;
	}
}
