import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.Border;





import org.omg.CORBA.PUBLIC_MEMBER;

import sun.applet.Main;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sun.xml.internal.ws.api.pipe.Engine;

public class DrawingPen extends JPanel
		implements ActionListener {
	
	private final int BALL_WIDTH = 50;
	private final int BALL_HEIGHT = 50;
	
	private Timer timer;
	private ArrayList<Ball> availableBalls;
	private Ball[][] matrix;
	private Player player;
	private String difficulty;
	
	public DrawingPen(Ball[][] matrix, Player player, String difficulty) {
		this.matrix = matrix;
		this.player = player;
		this.difficulty = difficulty;
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
				BALL_WIDTH * matrix[0].length , BALL_HEIGHT * (matrix.length + 1) + 5));
	}
	
	// Draws the balls
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for (int i = 0; i < availableBalls.size(); i++) {
        	g2d.drawImage(availableBalls.get(i).getImage(),
        			availableBalls.get(i).getX(), availableBalls.get(i).getY(), this);
		}
        JLabel score = new JLabel();
        score.setText(String.format("Score: %d", player.getScore()));
        score.setBounds(0, 300, 20, 20);
        score.setVisible(true);
        add(score);
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
		
		if (availableBalls.size() == 0) {
			this.matrix = new GameEngine(difficulty).generateMatrix();
			initAvailableBalls();
			initBoard();
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
						checkField(r, c, matrix[r][c].getBallColor());
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
