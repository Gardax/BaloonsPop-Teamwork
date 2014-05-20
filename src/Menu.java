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
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Menu extends JPanel {
	
	
	private final String DIFFICULTY_EASY = "EASY";
	private final String dIFFICULTY_MEDIUM = "MEDIUM";
	private final String dIFFICULTY_HARD = "HARD";
	
	public Menu() {
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
	
	
	
	
}
