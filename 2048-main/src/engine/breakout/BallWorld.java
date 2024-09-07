package engine.breakout;

import javafx.scene.image.Image;

import engine.World;

public class BallWorld extends World {
	
	public BallWorld() {
		this.setWidth(1080);
		this.setWidth(720);
	}

	@Override
	public void act(long n) {
	}

	@Override
	public void onDimensionsInitialized() {
		// TODO Auto-generated method stub
		Image img = new Image(getClass().getClassLoader().getResource("images/ball.png").toString());
		Ball b = new Ball(0, 0, img);
		

	}

}
