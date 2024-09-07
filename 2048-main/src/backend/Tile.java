package backend;

import java.awt.Color;

public class Tile {
	
	private int value;
	private Color color;
	private boolean isEmpty;
	
	public Tile() {
		isEmpty = true;
		value = -1;
	}
	//changes color, isempty
	public void update() {
		if (value == -1) color = Color.gray;
		else {
			//need to change the colors for 
			isEmpty = false;
			if (value == 2) color = new Color(223,221,209);
			else if (value == 4) color = new Color(233,217,189);
			else if (value == 8) color = new Color(237,162,102);
			else if (value == 16) color = new Color(241,130,80);
			else if (value == 32) color = new Color(241,102,77);
			else if (value == 64) color = new Color(240,70,45);
			else if (value == 128) color = new Color(232,198,95);
			else if (value == 256) color = new Color(231,195,80);
			else if (value == 512) color = new Color(232,190,64);
			else if (value == 1024) color = new Color(231,186,49);
			else if (value == 2048) color = new Color(231,182,36);
			//else { color = new Color(46,44,39) };
		}
	}
	
	public boolean isEmpty() {
		return isEmpty;
	}
	
	public void setIsEmpty(boolean b) {
		isEmpty = b;
	}
	
	public void setValue(int i) {
		value = i;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setColor(Color c) {
		color = c;
	}
}
