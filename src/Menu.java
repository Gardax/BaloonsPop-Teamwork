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
	private final String dIFFICULTY_MEDIUM = "MEDIUM";
	private final String dIFFICULTY_HARD = "HARD";
	private final String MENU_IMAGE = "Images/Grass.png";
	
	public Menu(JFrame mainFrame) {
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(30, 200));
		
		JButton startGame=new JButton("Start");
		
		startGame.addActionListener(new ActionListener() {
			@Override
        	public void actionPerformed(ActionEvent e) {
				GameEngine engine = new GameEngine("hard");
				Ball[][] matrix = engine.generateMatrix();
				mainFrame.setContentPane(new DrawingPen(matrix));
				mainFrame.invalidate();
				mainFrame.validate();
        	}
    	});
		add(startGame);
		
		JButton topPlayers=new JButton("Top Player");
		topPlayers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			
        			
                }
    	});
		add(topPlayers);
		
		JButton exit=new JButton("Exit");
		exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			System.exit(0);
                }
    	});
		add(exit);
		
	}
	
	
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D)g;
	    ImageIcon icon = new ImageIcon(getClass().getResource(MENU_IMAGE));
		java.awt.Image img = icon.getImage();
		g2d.drawImage(img, 0, 0, null);
		
	}
	
}
