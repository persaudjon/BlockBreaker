import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
//this class reads in images and sets dimensions
public class Block extends Rectangle {
	Image pic;//creates picture object
	int dx = 3;
	int dy = -3;
	Rectangle left, right;
	Boolean powerup = false;
	boolean destroyed = false;
	Block(int a, int b, int w, int h, String s){//constructor
		x=a;
		y=b;
		width=w;
		height=h;
		left = new Rectangle(a-1, b, 1, h);
		right = new Rectangle(a+w, b, 1, h);
		
		pic = Toolkit.getDefaultToolkit().getImage(s);//maps to picture file
	}
	
	public void draw(Graphics g, Component c){//this actually draws the image onto the screen
		if(!destroyed)
		g.drawImage(pic,x,y,width,height,c);
	}
}
