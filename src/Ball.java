import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Ball {
	
	private Image image;
	private BallColor ballColor;
	private int x;
	private int y;
	
	public Image getImage() {
		return image;
	}

	public void setImage(BallColor bColor) {
		this.image = getIcon(bColor).getImage();
	}
	
	public BallColor getBallColor() {
		return ballColor;
	}

	public void setBallColor(BallColor ballColor) {
		this.ballColor = ballColor;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Ball(int x, int y, BallColor ballColor) {
		this.ballColor = ballColor;
		ImageIcon ic = getIcon(ballColor); 
		this.image = ic.getImage();
		this.x = x;
		this.y = y;
	}
	
	private ImageIcon getIcon(BallColor ballColor) {
		String source = "";
		switch (ballColor) {
		case RED:
			source = "Images/RedBall.png";
			break;
		case BLUE:
			source = "Images/BlueBall.png";
			break;
		case GREEN:
			source = "Images/GreenBall.png";
			break;
		default:
			source = "Images/Empty.png";
			break;
		}
		return new ImageIcon(this.getClass().getResource(source));
	}

	public void fall() {
		this.y++;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, 50, 50);
	}
}
