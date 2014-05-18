import java.util.Scanner;

public class BalloonsPopMain
{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		System.out.println(PrintWelcomeMessage());

		String difficulty = input.nextLine().toLowerCase();
		GameEngine game = new GameEngine(difficulty);

		System.out.println(game.GetMatrixImage());

		System.out.println("Enter a row and column: ");
		String userInput = input.nextLine();
		while (true) {
			userInput = userInput.toUpperCase().trim();
			if (userInput.equals("EXIT")) {
				System.out.println("Game over! Have a nice day!");
				return;
			}
			try {
				game.ProcessGame(userInput);
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

		welcomeMessage.AppendLine("********************************");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("* Welcome to Balloons Pop Game *");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("********************************");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("Please, insert \"TOP\" to see Top Five score board.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("Please, insert \"RESTART\" to exit the game.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("Please, insert \"EXIT\" to exit the game.");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("");
		welcomeMessage.append(System.getProperty("line.separator"));
		welcomeMessage.AppendLine("Please, insert what difficulty do you want? - Easy, Medium, Hard");

		return welcomeMessage.toString();
	}
}