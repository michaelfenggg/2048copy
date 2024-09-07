package backend;
import java.util.Scanner;

public class Test {
	public static void main(String args[]) {
		Board board = new Board();
		board.makeBoard();
		System.out.println(board.toString());
		String input = "";
		while(!input.equals("q")) {
			Scanner in = new Scanner(System.in);
			input = in.nextLine();
			if(input.equals("l")) {
				board.move(4);
			} else if(input.equals("d")) {
				board.move(3);
			}else if(input.equals("r")) {
				board.move(2);
			}else if(input.equals("u")) {
				board.move(1);
			} else {
				System.out.println("");
			}
			System.out.println(board.hasNextMove());
			//System.out.println();
			System.out.println(board.toString());
		}
	}
}
