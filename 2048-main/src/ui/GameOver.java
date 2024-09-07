package ui;

import engine.World;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class GameOver extends Application {
	private int finalScoreVal = 0;
    private World w = null;
    private Stage previousStage = null;

    public GameOver(int b, World w, Stage previousStage) {
        finalScoreVal = b;
        this.w = w;
        this.previousStage = previousStage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Label l = new Label("Try Again next time.\nYou scored: " + finalScoreVal +"\nStart new game?");
        l.setTextAlignment(TextAlignment.CENTER);
        l.setFont(Font.font("Arial", FontWeight.NORMAL, 16));
        StackPane.setAlignment(l, Pos.CENTER);

        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> {
            Platform.exit();
        });

        Button screamButton = new Button("Start new game");
        screamButton.setOnAction(event -> {
            w.stop(); // Stop the current game
            previousStage.close(); // Close the previous stage/window
            stage.close(); // Close the GameOver stage/window

            Stage newStage = new Stage();
            FinalGame newGame = new FinalGame();
            try {
                newGame.start(newStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });



        HBox buttonContainer = new HBox(10);
        buttonContainer.getChildren().addAll(screamButton, exitButton);
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setAlignment(Pos.BASELINE_RIGHT);

        BorderPane root = new BorderPane();
        root.setCenter(new StackPane(l));
        root.setBottom(buttonContainer);

        Scene scene = new Scene(new StackPane(root), 800, 800);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.ENTER) {
                w.stop(); // Stop the current game
                stage.close();
            }
        });

        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
    }

}
