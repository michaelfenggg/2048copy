package ui;

import backend.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.image.Image;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Pos;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.StackPane;

public class Display extends Application {
    private static final int BOARD_SIZE = 4;
    private static final int TILE_SIZE = 150;
    Board b = new Board();

    ArrayList<Image> tileImages = new ArrayList<>();
    GridPane gridPane = new GridPane();
    Label scoreLabel = new Label("Score: 0");
    


    public Display() {
        for (int i = 1; i <= 12; i++) {
            Image image = new Image(getClass().getResource("images/" + i + ".png").toString());
            tileImages.add(image);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        
        b.makeBoard();
//        gridPane.setAlignment(Pos.CENTER);
        gridPane.setLayoutX(100);
        gridPane.setLayoutY(100);
        renderBoard();
        
        Pane pane = new Pane();
//        pane.setMinSize(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
//        pane.setMaxSize(BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
        pane.getChildren().addAll(gridPane);
        
     // Create rectangle
        Rectangle rectangle = new Rectangle(100, 50, Color.LIGHTGRAY);
        rectangle.setStroke(Color.BLACK);
        rectangle.setStrokeWidth(2.0);
        rectangle.setArcWidth(10.0); 
        rectangle.setArcHeight(10.0);  
        
        
        // Create label
        
        scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 20));
//        label.setLayoutX(110);
//        label.setLayoutY(65);
        StackPane stack = new StackPane();
        stack.getChildren().addAll(rectangle, scoreLabel);
        stack.setLayoutX(10);
        stack.setLayoutY(10);

        pane.getChildren().addAll(stack);
//        pane.setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, BOARD_SIZE * TILE_SIZE, BOARD_SIZE * TILE_SIZE);
        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.W || keyCode == KeyCode.UP) {
            	b.move(1);
                renderBoard();
            } else if (keyCode == KeyCode.A|| keyCode == KeyCode.LEFT) {
            	b.move(4);
                renderBoard();
            } else if (keyCode == KeyCode.S|| keyCode == KeyCode.DOWN) {
            	b.move(3);
                renderBoard();
            } else if (keyCode == KeyCode.D|| keyCode == KeyCode.RIGHT) {
            	b.move(2);
                renderBoard();
            }else if(keyCode == KeyCode.SLASH) {
//            	Platform.exit();
            	try {
					new Rules(true).start(new Stage());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        });
//        if(!b.hasNextMove()) {
//        	Platform.exit();
//        }
        stage.setResizable(false);
        stage.setMinWidth(800);
        stage.setMinHeight(800);
        stage.setScene(scene);
        stage.show();
    }
    
    public void renderBoard() {
    	gridPane.getChildren().clear();
    	// if(b.gameOver()){
    	// die 
    	// }
        Tile[][] tmp = b.getBoard();

        
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Tile t = tmp[i][j];
                int v = t.getValue();
//                System.out.println("V: " + v);
                int val = v == -1 ? 0 : (int) (Math.log10(v) / Math.log10(2));
//                System.out.println("Val at pos (" + (int)(i+1) + "," + (int)(j+1) +"): " + val);

                ImageView imageView = new ImageView(tileImages.get(val));
                imageView.setFitWidth(TILE_SIZE);
                imageView.setFitHeight(TILE_SIZE);
                gridPane.add(imageView, j, i);
            }
        }
        System.out.println(b.hasNextMove());
        //System.out.println();
        System.out.println(b.toString());
        if(!b.hasNextMove()) {
        	Platform.exit();
        }
        String tmpScore = ""+b.getScore();
        scoreLabel.setText("Score: "+ b.getScore());
        if(tmpScore.length()>3) scoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 22-tmpScore.length()));
    }
    public void gameOver() {
    	
    }
}
