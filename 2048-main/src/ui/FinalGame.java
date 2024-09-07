package ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class FinalGame extends Application {
	public static Label scoreLabel = new Label("Score: 0");
	public static Label highScoreLabel = new Label("Highscore: 0");

	public FinalGame() {
		// Constructor logic here
	}

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Game");
		stage.setResizable(false);
		BorderPane root = new BorderPane();

		Ddisplay world = new Ddisplay(stage);
		world.setPrefSize(800, 800);

		// Set scoreLabel as the top node in the BorderPane
		root.setLeft(scoreLabel);
		scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		root.setRight(highScoreLabel);
		highScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		root.setCenter(world);

		Scene scene = new Scene(root);
		stage.setScene(scene);

		world.start();

		stage.show();
		new Rules(false).start(new Stage());
	}

	public static void updateScoreLabel(int a, String newScore) {
		if (a == 1) {
			scoreLabel.setText("Score: " + newScore);
		} else if (a==2) {
			highScoreLabel.setText("Highscore: " + newScore);
		}
	}

}