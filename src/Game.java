
public class Game {
	
	private String difficulty;
	private Player player;
	private Ball[][] matrix;
	
	public Game(String difficulty, Player player) {
		this.difficulty = difficulty;
		this.player = player;
		this.matrix = MatrixDispatcher.generateMatrix(difficulty);
	}
	
	public String getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Ball[][] getMatrix() {
		return matrix;
	}

	public void setMatrix(Ball[][] matrix) {
		this.matrix = matrix;
	}
}
