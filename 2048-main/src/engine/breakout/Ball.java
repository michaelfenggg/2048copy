package engine.breakout;

import engine.Actor;
import engine.World;
import javafx.scene.image.Image;

public class Ball extends Actor {
	
	private double dxSpeed;
	private double dySpeed;
	
	public Ball (double dx, double dy, Image temp) {
		this.setImage(temp);
		dxSpeed = dx;
		dySpeed = dy;
	}
	
	@Override
	public void act(long now) {
		World tempWorld = this.getWorld();
		double worldWidth = tempWorld.getWidth();
		double worldHeight = tempWorld.getHeight();
		this.move(dxSpeed, dySpeed);
		if (this.getX() == this.getWidth()) {
			dxSpeed *= -1;
		}
		else if (this.getX() == worldWidth - this.getWidth()) {
			dxSpeed *= -1;
		}
		if (this.getY() == 0.0) {
			dySpeed *= -1;
		}
		else if (this.getY() == worldHeight - this.getHeight()) {
			dySpeed *= -1;
		}
		
	}
	
}