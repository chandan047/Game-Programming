import java.awt.*;

class Ball {
	public float x,y,dx,dy;
	private Color color;
	private int size;
	int radius;
	
	public Ball (float x, float y, float dx, float dy, int size, Color color) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.color = color;
		this.radius = size>>1;
	}
	
	public void draw (Graphics g) {
		g.setColor(color);
		g.fillOval((int)x-radius,(int)y-radius,size,size);
	}
	
	public void move (Rectangle bounds) {
	
		// Add velocity values dx/dy to position to get balls new position
		x += dx;
		y += dy;
		
		// Check for collision with left edge
		if ( (x < bounds.x && dx < 0f) || ((x + size) > (bounds.x + bounds.width) && dx>0) )
			x += (dx = -dx);
		
	}
	
}