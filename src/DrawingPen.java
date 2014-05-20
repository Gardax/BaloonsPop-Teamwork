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
	
	private Timer timer;
	private Ball currentBall;
	private ArrayList<Ball> availableBalls;
	private Ball[][] matrix;
	
	public DrawingPen(Ball[][] matrix) {
		this.matrix = matrix;
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
				BALL_HEIGHT * matrix.length, BALL_WIDTH * matrix[0].length));
	}
	
	public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D)g;
//        for (int i = 0; i < matrix.length; i++) {
//        	for (int j = 0; j < matrix[i].length; j++) {
//        		if (matrix[i][j] != null) {
//        			g2d.drawImage(matrix[i][j].getImage(),
//        					matrix[i][j].getX(),matrix[i][j].getY(), this);
//        		}
//			}
//				
//        }
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
		// checks for every ball if there is another one underneath
//		for (int i = 0; i < matrix.length - 1; i++) {
//			for (int j = 0; j < matrix[i].length; j++) {
//				if (matrix[i][j] != null && matrix[i + 1][j] != null ) {
//					Rectangle one = matrix[i][j].getBounds();
//					Rectangle two = matrix[i + 1][j].getBounds();
//					if (!one.intersects(two)) {
//						matrix[i][j].fall();
//					}
//				}
//			}
//		}
//		// same check but only for the last line
//		for (int r = matrix.length - 1, c = 0; c < matrix[r].length; c++) {
//			if (matrix[r][c] != null && matrix[r][c].getY() <= matrix.length * BALL_HEIGHT) {
//				matrix[r][c].fall();
//			}
//		}
		
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
		
//		@Override
//		public void mousePressed(MouseEvent e) {
//			for (int i = 0; i < matrix.length; i++) {
//				for (int j = 0; j < matrix[i].length; j++) {
//					if (isMouseOnBall(i, j)) {
//					
//						//checkField(i, j, matrix[i][j].getBallColor());
//						//moveColumnsDown();
//						
//						playSound("src/Sounds/POP.WAV");
//					}
//				}
//			}
		public void mousePressed(MouseEvent e) {
			for (int i = 0; i < availableBalls.size(); i++) {
				if (availableBalls.get(i).getBounds().contains(getMousePosition())) {
					availableBalls.remove(i);
					playSound("src/Sounds/POP.WAV");
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
		for (int col = 0; col < matrix[0].length; col++) {
			for (int row = matrix.length - 1; row > 0; row--) {
				matrix[row][col] = matrix[row - 1][col];
			}
		}
	}
	
	private void checkField(int row, int column, BallColor searchedColor) {
        //If index is out of the matrix stops recursion
        if (column >= matrix.length || row >= matrix[0].length
            || column < 0 || row < 0) {
            return;
        }

//        if (this.matrix[row][column] != null &&
//        		this.matrix[row][column].getBallColor() == searchedColor) {
//            this.matrix[row][column] = null;
//            checkNeighboringFields(row, column, searchedColor);
//        }
//        else
//        {
//            return;
//        }
    }

    public void checkNeighboringFields(int row, int column, BallColor searchedColor)
    {
        checkField(row, column + 1, searchedColor);
        checkField(row, column - 1, searchedColor);
        checkField(row + 1, column, searchedColor);
        checkField(row - 1, column, searchedColor);
    }
	
}
