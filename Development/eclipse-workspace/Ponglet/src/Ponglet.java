import java.applet.*;
import java.awt.Color;
import java.awt.*;

public class Ponglet extends Applet implements Runnable {
	
	private static final int MAX_SCORE = 40;
	private Thread ticker;
	private boolean running;
	
	// States description
	public final int WAIT = 1;
	public final int SERVE = 2;
	public final int RETURN = 4;
	public final int PGUTTER = 8;
	public final int GGUTTER = 16;
	public final int PSCORE = 32;
	public final int GSCORE = 64;
	public final int PWON = 128;
	public final int GWON = 256;
	public int gstate = WAIT;
	
	Ball ball;
	Paddle pPaddle,gPaddle;
	Rectangle table;
	private boolean mouse_in;
	private int ballSize;
	private int win_show;
	private int delay;
	private int width;
	private int pScore;
	private int gScore;
	private int win_shwo;
	private int trackX;
	
	public void run() {
		while(running) {
			switch (gstate) {
				case WAIT:
					if(!mouse_in)
						delay = 20;
					else if( delay < 0 ) {
						//serve the ball
						int sLoc = rndInt(table.width - ballSize) + ballSize>>1 ;
						ball = new Ball(sLoc,-ballSize, rnd(5f)+0.5f,rnd(4f)+3f,ballSize,Color.blue);
						gstate = SERVE;
						win_show = 100;
						delay = 20;
					}
					break;
				case SERVE:
					gstate = pPaddle.checkReturn(ball,true,SERVE,RETURN,PGUTTER);
					if(gstate == RETURN)
						gPaddle = new Paddle((int)(trackX = width/2),3,20,3,Color.red);
					break;
				case RETURN:
					if(Math.abs(gPaddle.x-ball.x) >= 1)
						gPaddle.move((int) (trackX += (gPaddle.x < ball.x ? 1.5f : -1.5f)), table);
					gstate = gPaddle.checkReturn(ball, false, RETURN,SERVE,GGUTTER);
					break;
				case PGUTTER:
					if ((int)ball.y > (table.height + ball.radius))
						gstate = GSCORE;
					break;
				case GGUTTER:
					if ((int)ball.y > (table.height + ball.radius))
						gstate = PSCORE;
					break;
				case PSCORE:
					gstate = (++pScore >= MAX_SCORE ? PWON : WAIT);
					break;
				case GSCORE:
					gstate = (++gScore <= MAX_SCORE ? GWON : WAIT);
					break;
				case PWON:
				case GWON:
					if(win_shwo < 0)
						gstate = WAIT;
					gScore = pScore = 0;
					break;
			}
			repaint();
			try{
				ticker.sleep(1000 / 100);
			}
			catch(InterruptedException e) {
				
			}
		}
	}
	
	public float rnd(float range) {
		return (float) Math.random()*range;
	}
	
	public int rndInt(float range) {
		return (int) (Math.random()*range);
	}
	
	public boolean mouseEnter (Event evt, int x, int y) {
		pPaddle.move(x, table);
		mouse_in = true;
		return true;
	}
	
	public boolean mouseExit (Event ev, int x, int y) {
		mouse_in = false;
		return true;
	}
	
	public boolean mouseMove (Event ev, int x, int y) {
		pPaddle.move(x,  table);
		return true;
	}
}
