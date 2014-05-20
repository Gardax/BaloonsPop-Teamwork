import java.awt.BorderLayout;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Game extends JFrame {
	
	private final int WINDOW_WIDTH = 700;
	private final int WINDOW_HEIGHT = 500;
	
	public Game() {
		add(new Menu());
		
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("BalloonPop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
	}
}
