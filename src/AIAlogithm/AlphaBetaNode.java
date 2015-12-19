package AIAlogithm;

public class AlphaBetaNode extends BaseNode{
	private int[] fromPosition;
	private int value = 0;
	
	public AlphaBetaNode(String piece, int[] from, int[] to){
		this.setId(piece);
		this.setNextPosition(to);
		this.setFromPosition(from);
	}
	
	public int[] getFromPosition() {
		return this.fromPosition;
	}
	
	public int getVlaue(){
		return this.value;
	}
	
	public void setValue(int val) {
		this.value = val;
	}
	
	public void setFromPosition(int[] pos) {
		this.fromPosition = pos;
	}
}
