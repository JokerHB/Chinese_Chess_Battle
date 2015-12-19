package AIAlogithm;

public abstract class BaseNode {
	private String id = new String();
	private int[] nextPosition = new int[2];
	
	public BaseNode() {
		this.id = "";
		
		this.nextPosition[0] = -1;
		this.nextPosition[1] = -1;
	}
	
	public String getId(){
		return this.id;
	}
	
	public int[] getNextPosition() {
		return this.nextPosition;
	}
	
	public void setId(String Id) {
		this.id = Id;
	}
	
	public void setNextPosition(int[] pos) {
		this.nextPosition = pos;
	}
}
