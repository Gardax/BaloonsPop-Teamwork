import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
<<<<<<< HEAD
import javax.swing.JLabel;
=======
import javax.swing.JFrame;
>>>>>>> 6c6b9676fc8c6df35313eb01ecd6cffc6bbe0c2d
import javax.swing.JPanel;


public class Menu extends JPanel {
	
<<<<<<< HEAD
	private final String MENU_IMAGE = "Images/Grass.png";
	private final String LOGO_IMAGE = "Images/Logo.jpg";
=======
>>>>>>> 6c6b9676fc8c6df35313eb01ecd6cffc6bbe0c2d
	
	private final String DIFFICULTY_EASY = "EASY";
	private final String dIFFICULTY_MEDIUM = "MEDIUM";
	private final String dIFFICULTY_HARD = "HARD";
	
	public Menu() {
<<<<<<< HEAD
		Player player = new Player("Test");
		
		startGame(player, "hard");
	}
	
	public void startGame(Player player, String difficulty) {
		GameEngine engine = new GameEngine(difficulty);
		Ball[][] matrix = engine.generateMatrix();
		add(new DrawingPen(matrix, player, difficulty), BorderLayout.CENTER);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		ImageIcon bg = new ImageIcon(getClass().getResource(MENU_IMAGE));
		Image imgBG = bg.getImage();
		ImageIcon logo = new ImageIcon(getClass().getResource(LOGO_IMAGE));
		Image imgLogo = logo.getImage();
		g2d.drawImage(imgBG, 0, 0, null);
		g2d.drawImage(imgLogo, 0, 0, null);
		
	}
=======
		JFrame frame=new JFrame();
		frame.setVisible(true);
		frame.setSize(200,200);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new FlowLayout());
		JPanel panel=new JPanel();
		
		frame.add(panel);
		
		JButton startGame=new JButton("Start");
		startGame.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			new Game();
        			//startGame("hard");
        			frame.dispose();
                }
    	});
		panel.add(startGame);
		
		JButton topPlayers=new JButton("Top Player");
		topPlayers.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			
        			frame.dispose();
                }
    	});
		panel.add(topPlayers);
		
		JButton exit=new JButton("Exit");
		exit.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
        			
        			frame.dispose();
                }
    	});
		panel.add(exit);
		
	}
	
	
	
	
>>>>>>> 6c6b9676fc8c6df35313eb01ecd6cffc6bbe0c2d
}
