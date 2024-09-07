package ui;

import javafx.application.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.text.*;

public class Rules extends Application {
	private boolean helpSummoned = false;

	public Rules(boolean b) {
		helpSummoned = b;
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		System.out.println("rawr");
		Label l = new Label(
				"How To Play 2048:\nUse the \"wasd\" keys or the arrow keys to direct the tiles up (w), down (s), left (a), or right (d)\nThe goal of the game is to combine tiles until you reach the 2048 tile, in which you win the game.\n However, you can continue until you lose.\n"
						+ "You lose the game when you have no possible moves left.\n" + "Tips:\n"
						+ "Try to keep your largest tile in a corner and keep it there for the rest of the game. \n This will ensure that your board stays orderly and it's easier to combine tiles.\n"
						+ "Good luck!\n" + "Press enter to start");
		l.setTextAlignment(TextAlignment.CENTER);
		l.setFont(Font.font("ARIAL"));
		Scene scene = new Scene(l, 800, 800);
		scene.setOnKeyPressed(event -> {
			KeyCode keyCode = event.getCode();
			if (keyCode == KeyCode.ENTER) {
				if (!helpSummoned) {
//					new Display().start(stage);
					System.out.print("Moo");
					stage.close();
				} else {
//					Platform.exit();
					System.out.print("Moew");
					stage.close();
				}
			}
		});
		stage.setMinWidth(800);
		stage.setMinHeight(800);
		stage.setScene(scene);
		stage.show();

	}

}