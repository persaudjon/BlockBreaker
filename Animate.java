//animates objects on the screen
public class Animate implements Runnable {
	BlockBreakerPanel bp;
	
	 Animate(BlockBreakerPanel b){ //default constructor
		bp = b;
	}
	public void run(){
		while(true){
			bp.update();//the loop will run continous and the bbpanel will update
			try {
				Thread.sleep(10);// Sleeps this so the animation isnt running to fast
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		}
	}
}
