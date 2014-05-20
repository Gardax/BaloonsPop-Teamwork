import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private String name;
	private int score;
	private List<Integer> bestScores;
	
	public Player(String name) {
		this.name = name;
		this.score = 0;
		this.bestScores = new ArrayList<Integer>();
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
