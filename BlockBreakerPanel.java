import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;
//KeyListener is used to take record of what keys user has pressed
public class BlockBreakerPanel extends JPanel implements KeyListener {
	// you can create a arraylist of objects,we create one of blocks
	ArrayList<Block> blocks = new ArrayList<Block>();
	ArrayList<Block> ball = new ArrayList<Block>();
	ArrayList<Block> powerup = new ArrayList<Block>();
	Block paddle;
	Thread thread;
	Animate animate;
	int size = 25;
	//V this adds all the colored blocks to the GUI
	BlockBreakerPanel(){
		paddle = new Block(175, 480, 150, 25, "paddle.png"); //reads in blocks and stores in arraylist
		for(int i=0;i<8;i++){
			blocks.add(new Block(i*60+2,0,60,25, "blue.png"));
		}
		for(int i=0;i<8;i++){
			blocks.add(new Block(i*60+2,25,60,25, "green.png"));
		}
		for(int i=0;i<8;i++){
			blocks.add(new Block(i*60+2,50,60,25, "red.png"));
		}
		for(int i=0;i<8;i++){
			blocks.add(new Block(i*60+2,75,60,25, "yellow.png"));
		}
		Random random = new Random();
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		blocks.get(random.nextInt(32)).powerup = true;
		ball.add(new Block(237, 437, 25, 25, "ball.png"));
		addKeyListener(this);
		setFocusable(true); 
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);// this erases screen and re prints
		for(Block b: blocks)//goes through all the blocks created in arraylist
			b.draw(g, this);//creates blocks_see block class
		paddle.draw(g, this);// creates paddle_see block class
		
		for(Block b: ball)//goes through all the blocks created in arraylist
			b.draw(g, this);//creates blocks_see block class
		paddle.draw(g, this);// creates paddle_see block class
		
		for(Block p: powerup)//goes through all the blocks created in arraylist
			p.draw(g, this);//creates blocks_see block class
		paddle.draw(g, this);
	}
	
	public void update(){
		for(Block p: powerup)
			p.y+=1;
			
		
		for(Block ba: ball){
			ba.x+=ba.dx;//starts the ball to launch
			if(ba.x>(getWidth()-size) && ba.dx>0 || ba.x<0)//restricts x axis of ball
				ba.dx*=-1;
			if(ba.y<0 || ba.intersects(paddle))//restriction on y axis
				ba.dy*=-1;
			ba.y+=ba.dy;
			
			for(Block b: blocks){
				if((b.left.intersects(ba) || b.right.intersects(ba)) && !b.destroyed){
					ba.dx*=-1;
					b.destroyed= true;
					if(b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
					
				}
				if(ba.intersects(b) && !b.destroyed){ //if the block is not destoryed yet and it intersects with the ball
					b.destroyed = true;
					ba.dy*=-1;// inverts y axis & makes ball come backm down once it hits a block,thus creating randomized movemnts
					if(b.powerup)
						powerup.add(new Block(b.x, b.y, 25, 19, "extra.png"));
				}
			}
		}
		repaint();//updates graphic
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()== KeyEvent.VK_ENTER){//start allowing user to move paddle
			animate = new Animate(this);
			thread = new Thread(animate);
			thread.start();
			
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT && paddle.x >0){//if user moves left then x-axis will decrease,thus go left
			paddle.x-=50;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && paddle.x < (getWidth()-paddle.width)){//if user moves left then x-axis will increase,thus go right
			paddle.x+=50;
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
