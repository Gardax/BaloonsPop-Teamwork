import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Rectangle;
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
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;

import org.omg.CORBA.PUBLIC_MEMBER;

import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class DrawingPen extends JPanel
		implements ActionListener {
	
	private final int BALL_WIDTH = 50;
	private final int BALL_HEIGHT = 50;
	private final String MENU_IMAGE = "Images/Grass.png";
	private final String LOGO_IMAGE = "Images/Logo.jpg";
	
	private Timer timer;
	private ArrayList<Ball> availableBalls;
	private JLabel score;
	private Ball[][] matrix;
	private Player player;
	
	public DrawingPen(Ball[][] matrix, Player player) {
		this.player = player;
		this.matrix = matrix;
		score = new JLabel(player.getScore().toString());
		initAvailableBalls();
		initBoard();
		
		addMouseListener(new MAdapter());
		timer = new Timer(4, this);
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
		setPreferredSize(new Dimension(
				BALL_WIDTH * matrix[0].length , BALL_HEIGHT * (matrix.length + 1)));
		
	}
	
	// Draws the balls
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
        
        ImageIcon bgIcon = new ImageIcon(getClass().getResource(MENU_IMAGE));
		Image bg = bgIcon.getImage();
		ImageIcon logoIcon = new ImageIcon(getClass().getResource(LOGO_IMAGE));
		Image logo = logoIcon.getImage();
		g2d.drawImage(bg, 0, 0, null);
		g2d.drawImage(logo, 0, 0, null);
		g2d.drawString(player.getScore().toString(), 0, 200);
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
			if (!intersectsAnyUnder(i) &&
					availableBalls.get(i).getY() <= matrix.length * BALL_HEIGHT) {
				availableBalls.get(i).fall();
			}
		}
		repaint();
	}
		
	private boolean intersectsAnyUnder(int index) {
		Rectangle currBall = availableBalls.get(index).getBounds();
		Rectangle otherBall;
		for (int i = 0; i < index; i++) {
			otherBall = availableBalls.get(i).getBounds();
			if (currBall.intersects(otherBall)
					&& currBall.y < otherBall.y) {
				return true;
			}
		}
		for (int i = index + 1; i < availableBalls.size(); i++) {
			otherBall = availableBalls.get(i).getBounds();
			if (currBall.intersects(otherBall)
					&& currBall.y < otherBall.y) {
				return true;
			}
		}
		return false;
	}

	public class MAdapter extends MouseAdapter {
		
		public void mousePressed(MouseEvent e) {
			
			for (int r = 0; r < matrix.length; r++) {
				for (int c = 0; c < matrix[r].length; c++) {
					if (isMouseOnBall(r, c)) {
						int ballCountNow = availableBalls.size();
						checkField(r, c, matrix[r][c].getBallColor());
						int ballCountLater = availableBalls.size();
						int balls = ballCountNow - ballCountLater;
						player.setScore(player.getScore() + calculateScore(balls));
						moveColumnsDown();
						playSound("src/Sounds/POP.WAV");
					}
				}
			}
		}
	
		public boolean isMouseOnBall(int i, int j) {
			return matrix[i][j] != null && 
					matrix[i][j].getBounds().contains(getMousePosition());
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

	public Integer calculateScore(int balls) {
		return (balls + 2) * balls * balls;
	}

	public void moveColumnsDown() {
		for (int cycle = 0; cycle < matrix.length; cycle++) {
			for (int col = 0; col < matrix[0].length; col++) {
				for (int row = matrix.length - 1; row > 0; row--) {
					if (matrix[row][col] == null) {
						matrix[row][col] = matrix[row - 1][col];
						matrix[row - 1][col] = null; 
					}
				}
			}
		}
	}
	
	private void checkField(int row, int column, BallColor searchedColor) {
        //If index is out of the matrix stops recursion
        if (column >= matrix[0].length || row >= matrix.length
            || column < 0 || row < 0) {
            return;
        }

        if (this.matrix[row][column] != null &&
        		this.matrix[row][column].getBallColor() == searchedColor) {
        	this.availableBalls.remove(matrix[row][column]);
            this.matrix[row][column] = null;
            checkNeighboringFields(row, column, searchedColor);
        }
    }

    public void checkNeighboringFields(int row, int column, BallColor searchedColor)
    {
        checkField(row, column + 1, searchedColor);
        checkField(row, column - 1, searchedColor);
        checkField(row + 1, column, searchedColor);
        checkField(row - 1, column, searchedColor);
    }
	
}
