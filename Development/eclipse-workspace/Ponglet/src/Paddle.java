import java.awt.*;
import java.applet.*;

public class Paddle {
	public int x,y,width,height;
	private Color color;
	
	Paddle (int x, int y, int width, int height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void move(int x, Rectangle bd) {
		if(x>(width>>1) && x<(bd.width-(width>>1) ) )
			this.x = x;
	}
	
	public int checkReturn(Ball ball, boolean plyr, int r1, int r2, int r3) {
		if( (plyr && ball.y > (y-ball.radius) ) ||
				(!plyr && ball.y < (y+ball.radius) ) )
			if( (int)Math.abs(ball.x-x) < width/2 + ball.radius ){
				ball.dy = ball.dy;
				ball.dx += (int) (ball.dx*Math.abs(ball.x-x)/(width/2) );
				return r2;
			}
			else
				return r3;
		return r1;
	}
}
