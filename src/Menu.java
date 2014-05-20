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
	
	private final String MENU_IMAGE = "Images/GrassScreen.png";
	private final String DIFFICULTY_EASY = "EASY";
	private final String dIFFICULTY_MEDIUM = "MEDIUM";
	private final String dIFFICULTY_HARD = "HARD";
	
	public Menu() {
		startGame();
	}
	
	public void startGame() {
		GameEngine engine = new GameEngine("medium");
		Ball[][] matrix = engine.generateMatrix();
		add(new DrawingPen(matrix), BorderLayout.WEST);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon icon = new ImageIcon(getClass().getResource(MENU_IMAGE));
		Image img = icon.getImage();
		g2d.drawImage(img, 0, 0, null);
		
	}
}
