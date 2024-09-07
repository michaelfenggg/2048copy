package ui;

import java.awt.Color;
import java.util.ArrayList;

import engine.Actor;
import javafx.scene.image.Image;

public class VisualTile extends Actor {
	private int mainVal; 

	
//	ArrayList<Image> tileImages = new ArrayList<>();
	
	public VisualTile(int a) {
		mainVal = a;
//		for (int i = 1; i <= 12; i++) {
//            Image image = new Image(getClass().getResource("images/" + i + ".png").toString());
//            tileImages.add(image);
//        }
		a+=1;
		this.setImage(new Image(getClass().getResource("images/" + a + ".png").toString()));
	}

	public int getMainVal() {
		return mainVal;
	}

	public void setMainVal(int mainVal) {
		this.mainVal = mainVal;
	}

	@Override
	public void act(long now) {
		// TODO Auto-generated method stub
		
	}
	
	
}