import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Game extends JFrame {
	
	private final int WINDOW_WIDTH = 500;
	private final int WINDOW_HEIGHT = 400;
	
	public Game() {
		GameEngine engine = new GameEngine("medium");
		Ball[][] matrix = engine.generateMatrix();
		
		add(new DrawingPen(matrix), BorderLayout.WEST);
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("BalloonPop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
