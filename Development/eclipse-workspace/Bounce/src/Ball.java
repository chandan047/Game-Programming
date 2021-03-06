import java.awt.*;

class Ball {
	public float x,y,dx,dy;
	private Color color;
	private int size;
	
	public Ball (float x, float y, float dx, float dy, int size, Color color) {
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
		this.size = size;
		this.color = color;
	}
	
	public void draw (Graphics g) {
		g.setColor(color);
		g.fillOval((int)x,(int)y,size,size);
	}
	
	public void move (Rectangle bounds) {
	
		// Add velocity values dx/dy to position to get balls new position
		x += dx;
		y += dy;
		
		// Check for collision with left edge
		if (x < bounds.x && dx < 0) {
			dx = -dx;
			x -= 2 * (x - bounds.x);
		}
		
		// Check for collision with right edge
		else if ((x + size) > (bounds.x + bounds.width) && dx>0 ) {
			dx = -dx;
			x -= 2 * ((x + size) - (bounds.x + bounds.width));
		}
		
		// Check for collision with top edge
		if (y < bounds.y && dy < 0) {
			dy = -dy;
			y -= 2 * (y - bounds.y);
		}
		
		// Check for collision with bottom edge
		else if ((y + size) > (bounds.y + bounds.height) && dy>0 ) {
			dy = -dy;
			y -= 2 * ((y + size) - (bounds.y + bounds.height));
		}
	}
	
}