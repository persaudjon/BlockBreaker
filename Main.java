import javax.swing.JFrame;
//This creates the window in a JFrame
public class Main {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Block Breaker");
		BlockBreakerPanel panel = new BlockBreakerPanel();//this is where our game will be stored
		
		frame.getContentPane().add(panel);//adds panel to JFrame window
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//programs ends when user hits X
		frame.setVisible(true);
		frame.setSize(500, 600);
		
		frame.setResizable(false);

	}

}
