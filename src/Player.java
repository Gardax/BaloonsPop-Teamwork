
public class Player {
	
	private String name;
	private int currentScore;
	private int bestScore;
	
	public Player(String name, int moves) {
		this.name = name;
		this.currentScore = moves;
	}
	
	public Player(String name) {
		this.name = name;
		this.currentScore = 0;
	}
	
	public String getName(){
		return this.name;
	}
	
	public Integer getScore() {
		return this.currentScore;
	}
	
	public void setScore(int score) {
		this.currentScore = score;
	}
	
	public int getBestScore() {
		return bestScore;
	}

	public void setBestScore(int bestScore) {
		this.bestScore = bestScore;
	}

}
