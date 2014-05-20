import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Game extends JFrame {
	
	private final int WINDOW_WIDTH = 455;
	private final int WINDOW_HEIGHT = 650;
	
	
	public Game() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("BalloonPop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		startGame("hard");
	}
	
	public void startGame(String difficulty) {
		GameEngine engine = new GameEngine(difficulty);
		Ball[][] matrix = engine.generateMatrix();
		add(new DrawingPen(matrix), BorderLayout.CENTER);
	}
}
