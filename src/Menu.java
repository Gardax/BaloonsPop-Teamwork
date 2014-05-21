import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

import sun.tools.jar.Main;


public class Menu extends JPanel {
	
	
	private final String DIFFICULTY_EASY = "EASY";
	private final String DIFFICULTY_MEDIUM = "MEDIUM";
	private final String DIFFICULTY_HARD = "HARD";
	private final String MENU_IMAGE = "Images/Grass.png";
	
	public Menu(JFrame mainFrame) {
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Color.WHITE);
		
		JButton startEasyGame = new JButton(DIFFICULTY_EASY);
		
		startEasyGame.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				startGame(mainFrame, "easy");
        	}
		});
		add(startEasyGame);
		
		JButton startMediumGame = new JButton(DIFFICULTY_MEDIUM);
		startMediumGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			startGame(mainFrame, "medium");
                }
    	});
		add(startMediumGame);
		
		JButton startHardGame = new JButton(DIFFICULTY_HARD);
		startHardGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			startGame(mainFrame, "hard");
                }
    	});
		add(startHardGame);
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D)g;
	    ImageIcon icon = new ImageIcon(getClass().getResource(MENU_IMAGE));
		java.awt.Image img = icon.getImage();
		g2d.drawImage(img, 0, 0, null);
		
	}
	
	public void startGame(JFrame mainFrame, String difficulty) {
		Ball[][] matrix = MatrixDispatcher.generateMatrix(difficulty);
		mainFrame.setContentPane(new Engine(new Game(difficulty, new Player("Unufri"))));
		mainFrame.setLayout(new BorderLayout());
		mainFrame.invalidate();
		mainFrame.validate();
	}
	
}
