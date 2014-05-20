import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class Menu extends JPanel {
	
	private final String MENU_IMAGE = "Images/Grass.png";
	private final String DIFFICULTY_EASY = "EASY";
	private final String dIFFICULTY_MEDIUM = "MEDIUM";
	private final String dIFFICULTY_HARD = "HARD";
	
	public Menu() {
		 startGame("hard");
	}
	
	public void startGame(String difficulty) {
		GameEngine engine = new GameEngine(difficulty);
		Ball[][] matrix = engine.generateMatrix();
		add(new DrawingPen(matrix), BorderLayout.CENTER);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon icon = new ImageIcon(getClass().getResource(MENU_IMAGE));
		Image img = icon.getImage();
		g2d.drawImage(img, 0, 0, null);
		
	}
}
