import java.awt.Image;
import java.awt.Rectangle;
import java.awt.color.ICC_ColorSpace;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Ball {
	
	private Image image;
	private BallColor ballColor;
	private int x;
	private int y;
	
	public Image getImage() {
		return this.image;
	}

	public void setImage(BallColor bColor) {
		this.image = getIcon(bColor);
	}
	
	public BallColor getBallColor() {
		return this.ballColor;
	}

	public void setBallColor(BallColor ballColor) {
		this.ballColor = ballColor;
		setImage(ballColor);
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
		setImage(ballColor);
		this.x = x;
		this.y = y;
	}
	
	private Image getIcon(BallColor ballColor) {
		String source;
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
		case YELLOW:
			source = "Images/YellowBall.png";
			break;
		case Brown:
			source = "Images/BrownBall.png";
			break;
		default:
			source = "Images/Empty.png";
			break;
		}
		try {
			Image img = ImageIO.read(new File(getClass().getResource(source).toURI()));
			return img;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
		
//		return new ImageIcon(getClass().getResource(source));
	}

	public void fall() {
		this.y++;
	}
	
	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, 50, 50);
	}
	
}
