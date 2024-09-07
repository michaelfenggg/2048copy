package backend;

import java.awt.Point;
import java.util.ArrayList;

public class Board {
	
	private Tile[][] board;
	private int score;
	private int[] scores = new int[12];
	
	public Board() {
		board = new Tile[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				board[i][j] = new Tile();
			}
		}
		score = 0;
		scores[1] = 0;
		scores[0] = 0;
		for (int i = 2; i < 12; i++) {
			scores[i] = (int) (Math.pow(2, i) + scores[i - 1]);
		}
	}
	
	public void makeBoard() {
		int x1 = (int) (Math.random() * 4);
		int y1 = (int) (Math.random() * 4);
		int x2 = x1, y2 = y1;
		while (x2 == x1 && y1 == y2) {
			x2 = (int) (Math.random() * 4);
			y2 = (int) (Math.random() * 4);
		}
		double temp = Math.random();
		if (temp > 0.9) {
			board[x1][y1].setValue(4);
			
		}
		else {
			board[x1][y1].setValue(2);
		}
		board[x1][y1].update();
		temp = Math.random();
		if (temp > 0.9) {
			board[x2][y2].setValue(4);
		}
		else {
			board[x2][y2].setValue(2);
		}
		board[x2][y2].update();
	}
	
	public int getVal(int row, int col) {
		return board[row][col].getValue();
	}
	
	public boolean hasNextMove() {
		Board temp = new Board();
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				temp.setVal(x, y, board[x][y].getValue());
				if (temp.getVal(x, y) == -1) return true;
			}
		}
		temp.moveDown();
		temp.moveUp();
		temp.moveLeft();
		temp.moveRight();
//		System.out.println(temp);
		if (temp.isSame(board)) return false;
		return true;
	}
	
	public void setVal(int row, int col, int val) {
		board[row][col].setValue(val);
	}
	
	public Tile[][] getBoard() {
		return board;
	}
	
	public int getScore() {
		return score;
	}
	
	public void updateScore(int v) {
		score += v;
	}
	
	public boolean gameWon() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (board[i][j].getValue() == 2048) return true;
			}
		}
		return false;
	}
	
	/* i = 1: up
	 * i = 2: right
	 * i = 3: down
	 * i = 4: left
	 */
	public void move(int i) {
		Tile[][] temp = new Tile[4][4];
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				temp[x][y] = new Tile();
				temp[x][y].setValue(board[x][y].getValue());
			}
		}
		switch (i) {
			case (1):
				moveUp();
				if (! isSame(temp)) {addRandomTile();}
				break;
			case (2):
				moveRight();
				if (! isSame(temp)) {addRandomTile();}
				break;
			case (3):
				moveDown();
				if (! isSame(temp)) {addRandomTile();}
				break;
			case (4):
				moveLeft();
				if (! isSame(temp)) {addRandomTile();}
				break;
		}
		
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				s += board[i][j].getValue() + " ";
			}
			s += "\n";
		}
		return s;
	}
	
	public boolean isSame(Tile[][] arr) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (arr[i][j].getValue() != board[i][j].getValue()) return false;
			}
		}
		return true;
	}
	
	public void moveUp() {
		for (int i = 0; i < 4; i++) {
			moveUpColumn(i);
		}
	}
	
	public void moveDown() {
		for (int i = 0; i < 4; i++) {
			moveDownColumn(i);
		}
	}
	
	public void moveRight() {
		for (int i = 0; i < 4; i++) {
			moveRightRow(i);
		}
	}
	
	public void moveLeft() {
		for (int i = 0; i < 4; i++) {
			moveLeftRow(i);
		}
	}
	
	public void canMoveUp() {
		
	}
	
	public void moveUpColumn(int col) {
		int row = 0;
		while (row != 4) {
			//move up if it's empty
			if (board[row][col].getValue() == -1) {
				for (int i = row + 1; i < 4; i++) {
					if (board[i][col].getValue() != -1) {
						board[row][col].setValue(board[i][col].getValue());
						board[row][col].update();
						board[i][col].setValue(-1);
						board[i][col].update();
						break;
					}
				}
			}
			//combine if possible
			for (int i = row + 1; i < 4; i++) {
				if (board[i][col].getValue() != -1) {
					if (board[i][col].getValue() != board[row][col].getValue()) break;
					else {
						board[row][col].setValue(board[i][col].getValue() * 2);
						board[row][col].update();
						board[i][col].setValue(-1);
						board[i][col].update();
						updateScore(board[row][col].getValue());
						break;
					}
				}
			}
			row++;
		}
	}
	
	public void moveDownColumn(int col) {
		int row = 3;
		while (row != -1) {
			//move down if it's empty
			if (board[row][col].getValue() == -1) {
				for (int i = row - 1; i > -1; i--) {
					if (board[i][col].getValue() != -1) {
						board[row][col].setValue(board[i][col].getValue());
						board[row][col].update();
						board[i][col].setValue(-1);
						board[i][col].update();
						break;
					}
				}
			}
			//combine if possible
			for (int i = row - 1; i > -1; i--) {
				if (board[i][col].getValue() != -1) {
					if (board[i][col].getValue() != board[row][col].getValue()) break;
					else {
						board[row][col].setValue(board[i][col].getValue() * 2);
						board[row][col].update();
						board[i][col].setValue(-1);
						board[i][col].update();
						updateScore(board[row][col].getValue());
						break;
					}
				}
			}
			row--;
		}
	}
	
	public void moveRightRow(int row) {
		int col = 3;
		while (col != -1) {
			//move right if it's empty
			if (board[row][col].getValue() == -1) {
				for (int i = col - 1; i > -1; i--) {
					if (board[row][i].getValue() != -1) {
						board[row][col].setValue(board[row][i].getValue());
						board[row][col].update();
						board[row][i].setValue(-1);
						board[row][i].update();
						break;
					}
				}
			}
			//combine if possible
			for (int i = col - 1; i > -1; i--) {
				if (board[row][i].getValue() != -1) {
					if (board[row][i].getValue() != board[row][col].getValue()) break;
					else {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[row][col].update();
						board[row][i].setValue(-1);
						board[row][i].update();
						updateScore(board[row][col].getValue());
						break;
					}
				}
			}
			col--;
		}
	}
	
	public void moveLeftRow(int row) {
		int col = 0;
		while (col != 4) {
			//move right if it's empty
			if (board[row][col].getValue() == -1) {
				for (int i = col + 1; i < 4; i++) {
					if (board[row][i].getValue() != -1) {
						board[row][col].setValue(board[row][i].getValue());
						board[row][col].update();
						board[row][i].setValue(-1);
						board[row][i].update();
						break;
					}
				}
			}
			//combine if possible
			for (int i = col + 1; i < 4; i++) {
				if (board[row][i].getValue() != -1) {
					if (board[row][i].getValue() != board[row][col].getValue()) break;
					else {
						board[row][col].setValue(board[row][col].getValue() * 2);
						board[row][col].update();
						board[row][i].setValue(-1);
						board[row][i].update();
						updateScore(board[row][col].getValue());
						break;
					}
				}
			}
			col++;
		}
	}
	
	public void addRandomTile() {
		ArrayList <Point> p = new ArrayList <Point> ();
		for(int i = 0; i < board.length; i++) {
			for(int g = 0; g < board[0].length; g++) {
				if(board[i][g].getValue() == -1) {
					p.add(new Point(i,g));
				}
			}
		}
		int x = (int)(Math.random() * p.size());
		double temp = Math.random();
        if (temp > 0.9) {
        	board[(int)(p.get(x).getX())][(int)(p.get(x).getY())].setValue(4);

        }
        else {
        	board[(int)(p.get(x).getX())][(int)(p.get(x).getY())].setValue(2);
        }
		
	}
	
}
