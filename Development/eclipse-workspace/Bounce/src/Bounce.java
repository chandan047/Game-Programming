import java.applet.*;
import java.awt.*;

public class Bounce extends Applet implements Runnable {

	private Thread 	ticker;
	private boolean	running = false;
	
	public void run() {
		while(running) {
			repaint();
			try{
				ticker.sleep(1000 / 100);
			}
			catch(InterruptedException e) {
				
			}
		}
	}
	
	public synchronized void start() {
		if(ticker==null || ticker.isAlive()) {
			running = true;
			ticker = new Thread(this);
			ticker.setPriority(Thread.MIN_PRIORITY + 1);
			ticker.start();
		}
	}
	
	public synchronized void stop() {
		running = false;
	}
	
	private Rectangle bounds;
	private Ball ball;
	private int width, height;
	
	public void init() {
		width = size().width;
		height = size().height;
		bounds = new Rectangle(width,height);
		ball = new Ball(width/3f, height/4f, 1.5f, 2.3f, 12, Color.blue);
	}
	
	private Image offscreenImage;
	private Graphics offscr;	
	
	public void paint(Graphics g) {
		if(offscr == null){
			offscreenImage = createImage(width,height);
			offscr = offscreenImage.getGraphics();
		}
		
		int x2 = width>>1;
		int y2 = height>>1;
		offscr.setColor(Color.gray);
		offscr.fillRect(0, 0, x2, y2);
		offscr.fillRect(x2, y2, width - x2, height-y2);
		offscr.setColor(Color.white);
		offscr.fillRect(x2, 0, width - x2, height - y2);
		offscr.fillRect(0, y2, x2, y2);
		ball.move(bounds);
		ball.draw(offscr);
		g.drawImage(offscreenImage, 0, 0, null);
	}
	
	public void update(Graphics g) {
		paint(g);
	}
}

