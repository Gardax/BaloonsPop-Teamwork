import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Stack;

public class GameEngine {
	public Score scoreBoard;
	private byte[][] matrix;
	private int userMoves;
	private byte matrixRows;
	private byte matrixCols;
	private String difficulty;

	public GameEngine(String difficulty) {

		this.scoreBoard = new Score();
		this.difficulty = difficulty;
		this.matrix = generateMatrix();
		this.userMoves = 0;
	}

	public String getMatrixImage() {
		StringBuilder output = new StringBuilder();

		output.append("    ");
		for (byte column = 0; column < this.matrixCols; column++) {
			output.append(column + " ");
		}
		output.append(System.getProperty("line.separator") + "   ");
		for (byte column = 0; column < this.matrixCols * 2 + 1; column++) {
			output.append("-");
		}
		output.append(System.getProperty("line.separator"));

		for (byte i = 0; i < this.matrixRows; i++) {
			output.append(i + " | ");
			for (byte j = 0; j < this.matrixCols; j++) {
				if (this.matrix[i][j] == 0) {
					output.append("  ");
				} else {
					output.append(this.matrix[i][j] + " ");
				}
			}
			output.append("| ");
			output.append(System.getProperty("line.separator"));
		}

		output.append("   ");
		for (byte column = 0; column < this.matrixCols * 2 + 1; column++) {
			output.append("-");
		}
		output.append(System.getProperty("line.separator"));

		return output.toString();
	}

	private byte[][] generateMatrix() {
		if (this.difficulty.equals("easy")) {
			this.matrixRows = 4;
			this.matrixCols = 5;
		} else if (this.difficulty.equals("medium")) {
			this.matrixRows = 6;
			this.matrixCols = 8;
		} else if (this.difficulty.equals("hard")) {
			this.matrixRows = 9;
			this.matrixCols = 9;
		}

		byte[][] matrix = new byte[this.matrixRows][this.matrixCols];
		Random randNumber = new Random();

		for (byte row = 0; row < this.matrixRows; row++) {
			for (byte column = 0; column < this.matrixCols; column++) {
				byte tempByte = (byte) randNumber.nextInt(5);
				matrix[row][column] = (byte) (tempByte + 1);
			}
		}
		return matrix;
	}

	private void checkField(int row, int column, int searchedItem) {
		// If index is out of the matrix stops recursion
		if (column >= this.matrixCols || row >= this.matrixRows || column < 0
				|| row < 0) {
			return;
		}

		if (this.matrix[row][column] == searchedItem) {
			this.matrix[row][column] = 0;
			checkNeighboringFields(row, column, searchedItem);
		} else {
			return;
		}
	}

	private void checkNeighboringFields(int row, int column, int searchedItem) {
		checkField(row, column + 1, searchedItem);
		checkField(row, column - 1, searchedItem);
		checkField(row + 1, column, searchedItem);
		checkField(row - 1, column, searchedItem);
	}

	private boolean isPopped(int rowAtm, int columnAtm) throws Exception {
		if (this.matrix[rowAtm][columnAtm] == 0) {
			throw new Exception("This baloon is popped!");
		} else {
			return false;
		}
	}

	private boolean isFinished() {
		boolean isWinner = true;
		for (int j = 0; j < this.matrixCols; j++) {
			for (int i = 0; i < this.matrixRows; i++) {
				if (this.matrix[i][j] != 0) {
					isWinner = false;
				}
			}
		}
		return isWinner;
	}

	private void dropDownMatrix() {
		Stack<Byte> stack = new Stack<Byte>();
		for (int j = 0; j < this.matrixCols; j++) {
			for (int i = 0; i < this.matrixRows; i++) {
				if (this.matrix[i][j] != 0) {
					stack.push(this.matrix[i][j]);
				}
			}
			for (int k = this.matrixRows - 1; k >= 0; k--) {
				if (stack.size() != 0) {
					this.matrix[k][j] = stack.pop();
				} else {
					this.matrix[k][j] = 0;
				}
			}
		}
	}

	public void processGame(String input) throws Exception {
		if (input.equals("RESTART")) {
			this.matrix = generateMatrix();
			System.out.println(getMatrixImage());
			this.userMoves = 0;
		} else if (input.equals("TOP")) {
			scoreBoard.PrintScoreBoard();
		} else {
			if (isInputValid(input)) {
				int userRow = Integer.parseInt(Character.toString(input
						.charAt(0)));
				if (userRow >= matrixRows) {
					throw new Exception("There is no such field!");
				}

				int userColumn = Integer.parseInt(Character.toString(input
						.charAt(2)));
				if (!(isPopped(userRow, userColumn))) {
					byte searchedTarget = this.matrix[userRow][userColumn];
					this.matrix[userRow][userColumn] = 0;
					checkNeighboringFields(userRow, userColumn, searchedTarget);
					dropDownMatrix();
				}

				this.userMoves++;

				if (isFinished()) {
					System.out.println("Great! You completed it in "
							+ this.userMoves + " moves.");
					if (scoreBoard.IsGoodEnough(this.userMoves)) {
						System.out.println("Enter your name: ");
						BufferedReader bufferRead = new BufferedReader(
								new InputStreamReader(System.in));
						String playerName = bufferRead.readLine();
						scoreBoard.AddPlayer(playerName, this.userMoves);
						scoreBoard.Sort();
						scoreBoard.PrintScoreBoard();
					} else {
						System.out
								.println("I'm sorry, you are not skillful enough for Top Five chart!");
					}

					this.matrix = generateMatrix();
					this.userMoves = 0;
				}

				System.out.println(getMatrixImage());

			} else {
				throw new Exception("There is no such field! Try again!");
			}
		}
	}

	private boolean isInputValid(String input) {
		if ((input.length() == 3)
				&& (input.charAt(0) >= '0' && input.charAt(0) <= '9')
				&& (input.charAt(2) >= '0' && input.charAt(2) <= '9')
				&& (input.charAt(1) == ' ' || input.charAt(1) == '.' || input
						.charAt(1) == ',')) {
			return true;
		} else {
			return false;
		}
	}
}
