package Chess;

import java.util.ArrayList;

public abstract class Piece implements Cloneable {
	public String id;	// piece id = camp, type, no
 
	public int[] currentPosition = new int[2];	// current position in board
	public ArrayList<int[]> nextPosition = new ArrayList<int[]>();	// next position
	
	public abstract void rule(Board board);  
	
	public Piece(String pid, int[] cp) {
		this.id = pid;
		this.currentPosition = cp;
	}
	
	public String getCamp() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(0,1);
		} 
		
		return null;
	}
	
	public String getType() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(1,2);
		} 
		
		return null;
	}
	
	public String getNo() {
		if(this.id != null && this.id.length() == 3) {
			return this.id.substring(2,3);
		} 
		
		return null;
	}
}
