import java.util.Scanner;

public class BalloonsPopMain
{
	public static void main(String[] args) throws Exception {
		Scanner input = new Scanner(System.in);
		System.out.println(PrintWelcomeMessage());

		String difficulty = input.nextLine().toLowerCase();
		GameEngine game = new GameEngine(difficulty);

		System.out.println(game.getMatrixImage());

		System.out.println("Enter a row and column: ");
		String userInput = input.nextLine();
		while (true) {
			userInput = userInput.toUpperCase().trim();
			if (userInput.equals("EXIT")) {
				System.out.println("Game over! Have a nice day!");
				return;
			}
			try {
				game.processGame(userInput);
			}
			catch (RuntimeException ex) {
				System.out.println(ex.getMessage());
			}
			System.out.println("Enter a row and column: ");
			userInput = new Scanner(System.in).nextLine();
		}
	}

	public static String PrintWelcomeMessage() {
		StringBuilder welcomeMessage = new StringBuilder();

		welcomeMessage.append("********************************");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("* Welcome to Balloons Pop Game *");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("********************************");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("Please, insert \"TOP\" to see Top Five score board.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("Please, insert \"RESTART\" to restart the game.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("Please, insert \"EXIT\" to exit the game.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.append("Please, insert what difficulty do you want? - Easy, Medium, Hard");

		return welcomeMessage.toString();
	}
}