import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.geometry.Orientation;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;


public class DrawingPen extends JPanel
		implements ActionListener {
	
	private Timer timer;
	private Ball currentBall;
	private ArrayList<Ball> availableBalls;
	private Ball[][] matrix;
	
	public DrawingPen(Ball[][] matrix) {
		this.matrix = matrix;
		initAvailableBalls();
		initBoard();
		
		addMouseListener(new MAdapter());
		timer = new Timer(10, this);
		timer.start();
	}

	private void initAvailableBalls() {
		this.availableBalls = new ArrayList<Ball>();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				availableBalls.add(matrix[i][j]);
			}
		}
	}

	private void initBoard() {
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Color.WHITE);
		setPreferredSize(new Dimension(50 * matrix.length, 50 * matrix[0].length));
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        for (int i = 0; i < availableBalls.size(); i++) {
				g2d.drawImage(availableBalls.get(i).getImage(),
						availableBalls.get(i).getX(), availableBalls.get(i).getY(), this);
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < availableBalls.size(); i++) {
			if (availableBalls.get(i).getY() <= matrix.length * 50) {
				availableBalls.get(i).fall();
			}
		}
		repaint();
		
	}
	
	public class MAdapter extends MouseAdapter {
		
		@Override
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < availableBalls.size(); i++) {
					if (availableBalls.get(i).getBounds().contains(getMousePosition())) {
						availableBalls.remove(i);
						playSound("src/Sounds/POP.WAV");
					}
				
			}
		}
	}

	public synchronized void playSound(final String url) {
		new Thread(
	            new Runnable() {
	                public void run() {
	                    try {
	                    	Clip clip = AudioSystem.getClip();
	                        clip.open(AudioSystem.getAudioInputStream(new File(url)));
	                        clip.start();
	                    } catch (Exception e) {
	                        e.printStackTrace();
	                    }
	                }
	            }).start();
		}
}
