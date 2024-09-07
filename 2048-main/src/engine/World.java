package engine;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

public abstract class World extends Pane {
    private boolean isTimerRunning ;
	private ArrayList<KeyCode> keys = new ArrayList<KeyCode> ();
	private boolean isHeightSet ;
	private boolean isWidthSet ;
	private boolean dimensionsCall = true;
	private AnimationTimer timer ;
	
	public World() {
		
		isTimerRunning = false;
        
		setOnKeyPressed(event -> keys.add(event.getCode()));
        setOnKeyReleased(event -> keys.remove(event.getCode()));
        
        WidthListener w = new WidthListener();
        HeightListener h = new HeightListener();
        
        widthProperty().addListener(w);
        heightProperty().addListener(h);
        sceneProperty().addListener(new SceneListener());
        
        World world = this;
        timer = new AnimationTimer() {
    		@Override
    		public void handle(long arg0) {
    			act(arg0);
                for (Node actor : getObjects(Actor.class)) {
                    if (((Actor) actor).getWorld() != null) ((Actor)(actor)).act(arg0);
                }
    		}
    	};
	}
	
	public abstract void act(long n) ;
	
	public abstract void onDimensionsInitialized() ;
    
	public <A extends Actor> List<A> getObjects(java.lang.Class<A> cls) {
        ArrayList<A> contents = new ArrayList<A> ();
        for(int i  = 0; i < getChildren().size(); i++) {
             Node child = getChildren().get(i);
             if (cls.isInstance(child)) {
                    contents.add(cls.cast(child));
             }
        }
        return contents;
    }
    
	
    public <A extends Actor> List<A> getObjectsAt(double x, double y, java.lang.Class<A> cls) {
    	ArrayList<A> arr = new ArrayList<A> ();
        for(int i = 0; i < getChildren().size(); i++) {
        	Node child = getChildren().get(i);
        	if(getChildren().get(i).getBoundsInParent().contains(x, y) && cls.isInstance(child)) {
        		arr.add(cls.cast(child));
        	}
        }
        
    	return arr;
    }
    
    public boolean isKeyPressed(KeyCode code) {
    	return keys.contains(code);	
    }

    
    public boolean isStopped() {
    	return !isTimerRunning;
    }
    
    public void start() {
    	isTimerRunning = true;
        timer.start();
    }
    
    public void stop() {
    	isTimerRunning = false;
    	timer.stop();
    }
    
    public void add(Actor actor) {
    	getChildren().add(actor);
    	actor.addedToWorld();
    }
    
    public void remove(Actor actor) {
    	getChildren().remove(actor);
    }
    
    private class WidthListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			
			if(oldValue.doubleValue() == 0 && newValue.doubleValue() > 0) {
				isWidthSet = true;
			} 
			if(isWidthSet && isHeightSet && dimensionsCall){
				onDimensionsInitialized();
				dimensionsCall = false;
			}
		}
    	
    }
    
    private class HeightListener implements ChangeListener<Number> {
		@Override
		public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
			if (oldValue.doubleValue() == 0 && newValue.doubleValue() > 0) {
				isHeightSet = true;
			} 
			if(isWidthSet && isHeightSet && dimensionsCall){
				onDimensionsInitialized();
				dimensionsCall = false;
			}
		}
    	
    }
    
    private class SceneListener implements ChangeListener<Scene> {
		@Override
		public void changed(ObservableValue<? extends Scene> arg0, Scene arg1, Scene arg2) {
			if(arg2 != null && arg1 == null) {
				setFocusTraversable(true); 
			}
			
		}
    	
    }
}
