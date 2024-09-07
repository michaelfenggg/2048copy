package ui;

import backend.Tile;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import engine.*;
import javafx.application.Platform;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import backend.*;
//import javafx.scene.image.ImageView;

class Ddisplay extends World {
	private static final int BOARD_SIZE = 4;
	private static final int TILE_SIZE = 150;
	Robot rob;
	Board b = new Board();
	Tile[][] rep = b.getBoard();
	int highScore;
	private Stage primaryStage;
	double n = -1;

	public Ddisplay(Stage primaryStage) {
		this.primaryStage = primaryStage;
		try {
			rob = new Robot();
		} catch (AWTException e1) {
//			exceptions.add(e1);
			e1.printStackTrace();
		}
	}

	@Override
	public void act(long n) {
		// TODO Auto-generated method stub
		if (this.n == -1) {
			System.out.print("HERE");
			rob.keyRelease(KeyEvent.VK_SLASH);
			this.n = n;
		}

		if (b.hasNextMove()) {
			Tile[][] tmp = b.getBoard();
			if (!checkBoardsEqual(tmp, rep)) {
				try {
					renderBoard(tmp);
				} catch (Throwable t) {
					System.out.println(t);
				}
				rep = tmp;
			}
			setOnKeyReleased(event -> {
				KeyCode keyCode = event.getCode();
				if (keyCode == KeyCode.W || keyCode == KeyCode.UP) {
					b.move(1);
					renderBoard(tmp);
				} else if (keyCode == KeyCode.A || keyCode == KeyCode.LEFT) {
					b.move(4);
					renderBoard(tmp);
				} else if (keyCode == KeyCode.S || keyCode == KeyCode.DOWN) {
					b.move(3);
					renderBoard(tmp);
				} else if (keyCode == KeyCode.D || keyCode == KeyCode.RIGHT) {
					b.move(2);
					renderBoard(tmp);
				} else if (keyCode == KeyCode.SLASH) {
//	            	Platform.exit();
					try {
						new Rules(true).start(new Stage());
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} else {
			try {
				if (b.getScore() > highScore) {
					FileWriter f = new FileWriter("./src/backend/highscore.txt");
					f.write(((Integer)b.getScore()).toString(),0,((Integer)b.getScore()+"").length());
					f.close();
				}
				this.stop();
				new GameOver(b.getScore(), this, primaryStage).start(new Stage());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void renderBoard(Tile[][] tmp) {
		int tileSize = 150; // Size of each tile/image
		int worldSize = 800; // Size of the world

		int xOffset = (worldSize - (tmp[0].length * tileSize)) / 2;
		int yOffset = (worldSize - (tmp.length * tileSize)) / 2;
		if (b.getScore() > highScore) {
			FinalGame.updateScoreLabel(2, "" + b.getScore());
		}
		for (int i = 0; i < BOARD_SIZE; i++) {
			for (int j = 0; j < BOARD_SIZE; j++) {
				Tile t = tmp[i][j];
				int v = t.getValue();
//                System.out.println("V: " + v);
				int val = v == -1 ? 0 : (int) (Math.log10(v) / Math.log10(2));
				VisualTile vt = new VisualTile(val);
				this.add(vt);
				vt.setX(xOffset + (tileSize * j));
				vt.setY(yOffset + (tileSize * i));
			}
		}
		FinalGame.updateScoreLabel(1, "" + b.getScore());
	}

	public boolean checkBoardsEqual(Tile[][] bOne, Tile[][] bTwo) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (bOne[i][j] != bTwo[i][j]) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void onDimensionsInitialized() {
		// TODO Auto-generated method stub
		String hstxt = "./src/backend/highscore.txt";
		File f;
		Scanner in;
		try {
			f = new File(hstxt);
			in = new Scanner(f);
			highScore = in.nextInt();
			FinalGame.updateScoreLabel(2, "" + highScore);
			
//			System.out.println("expert opinion: " + highScore);
			in.close();
		} catch (IOException e) {
			System.out.println("Brudda");
			try {
				try {
					f = new File(hstxt);
					if (f.createNewFile()) {
						System.out.println("File created: " + f.getName());
					} else {
						System.out.println("File already exists.");
					}
				} catch (IOException ea) {
					throw ea;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		b.makeBoard();
		renderBoard(rep);
	}

}