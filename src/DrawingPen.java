import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javafx.geometry.Orientation;

import javax.swing.JPanel;
import javax.swing.Timer;

public class DrawingPen extends JPanel
		implements ActionListener {
	
	private final int BOARD_WIDTH = 8;
	private final int BOARD_HEIGHT = 6;
	
	private Timer timer;
	private Ball currentBall;
	private Ball[][] field;
	
	private BallColor[] board;
	
	public DrawingPen(Ball[][] field) {
		this.field = field;
		initBoard();
		timer = new Timer(10, this);
		timer.start();
		
		// addKeyListener();
		//clearBoard();
	}

	private void initBoard() {
		setFocusable(true);
		setDoubleBuffered(true);
		setBackground(Color.WHITE);
	}
	
	public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D)g;
        for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				g2d.drawImage(field[i][j].getImage(), field[i][j].getX(), field[i][j].getY(), this);
			}
        }
        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

	private void clearBoard() {
		for (int i = 0; i < BOARD_HEIGHT * BOARD_WIDTH; ++i)
	        board[i] = BallColor.EMPTY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				field[i][j].fall();
			}
		}
		repaint();
		
	}
}
