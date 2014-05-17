
public class Player {
	
	private String name;
	private int score;
	
	public Player(String name, int moves) {
		this.name = name;
		this.score = moves;
	}
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Integer getScore() {
		return this.score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
}
