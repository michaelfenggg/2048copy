package engine;
import java.util.List;
import java.util.ArrayList;

import javafx.scene.image.ImageView;


public abstract class Actor extends ImageView {
	/**
	 * This method called to give actors a chance to perform some action, typically
	 * once per frame.
	 * 
	 */
	public Actor () {
	}
	public abstract void act(long now);

	/**
	 * Returns the height of this actor in pixels.
	 */
	public double getHeight() {
		return getBoundsInParent().getHeight();
	}

	/**
	 * Returns a list of actors that intersect with this actor.
	 */
	public <A extends Actor> ArrayList<A> getIntersectingObjects(java.lang.Class<A> cls) {
    ArrayList<A> ans = new ArrayList<>();
    List<A> list = getWorld().getObjects(cls);
    for(int i=0;i<list.size();i++) {
    	A actor = list.get(i);
    	if(actor!=this && localToScene(getBoundsInLocal()).intersects(actor.localToScene(actor.getBoundsInLocal())))ans.add(actor);
    }
	return ans;
	}

	/**
	 * Returns the first actor that intersects with this actor.
	 */
	public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
		List<A> list = getWorld().getObjects(cls);
		for(int i=0;i<list.size();i++) {
			A actor = list.get(i);
	    	if(actor!=this && localToScene(getBoundsInLocal()).intersects(actor.localToScene(actor.getBoundsInLocal())))return actor;
	    }
		return null; 
	}

	/**
	 * Returns the width of this actor in pixels.
	 */
	public double getWidth() {
		return getBoundsInParent().getWidth();
	}

	/**
	 * Returns the world that this actor is in.
	 */
	public World getWorld() {
		return (World) getParent();
	}

	/**
	 * Moves this actor by a given distance in the current direction it is facing.
	 */
	public void move(double dx, double dy) {
		setX(getX() + dx);
		setY(getY() + dy);

	}

	public void addedToWorld() {
		this.getParent();
	}
}
