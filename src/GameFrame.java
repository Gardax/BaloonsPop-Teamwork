import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GameFrame extends JFrame {
	
	private final int WINDOW_WIDTH = 800;
	private final int WINDOW_HEIGHT = 650;
	
	
	public GameFrame() {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setTitle("BalloonPop");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		add(new Menu(this), BorderLayout.CENTER);
	}
}
