package game;

import backend.*;

import javafx.stage.Stage;
import ui.*;
import javafx.application.Application;
                                                              


public class Game extends Application {
	Board board = new Board();
	

	public static void main(String[] args) {
		launch(args);
	}


	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		 FinalGame game = new FinalGame();
		 game.start(stage);
//		 new Display().start(stage);
	}
	
}