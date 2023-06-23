package TQSPractica.views;

import java.util.Scanner;

import TQSPractica.Player;
import TQSPractica.models.Bishop;
import TQSPractica.models.King;
import TQSPractica.models.Knight;
import TQSPractica.models.Move;
import TQSPractica.models.MoveImp;
import TQSPractica.models.Pawn;
import TQSPractica.models.Piece;
import TQSPractica.models.Queen;
import TQSPractica.models.Rook;

public class TerminalDisplay implements Display {
	
	private Scanner stdin;
	
	private static final String ROW_SEPARATOR = "  + - + - + - + - + - + - + - + - +";

	public TerminalDisplay(Scanner s) throws Exception {

		if (s == null) {
			throw new Exception("Need stdin to get input");
		}
		
		this.stdin = s;

	}

	@Override
	public Move getMove(Player p) throws Exception {

		if (p == null) {
			throw new Exception("PLayer cant be null");
		}
		
		String player = p == Player.WHITE ? "WHITE" : "BLACK";
		System.out.print("\t>["+player+"] next move:");
		String in = this.stdin.nextLine();
		Move m = new MoveImp(in);
		if (in.equals("X")) m = null;
		while(m != null && !m.valid()) {
			System.out.println("This move doesn't make any sense, please try again.");
			System.out.print("\t>["+player+"] next move(X to surrender):");
			in = this.stdin.nextLine();
			m = new MoveImp(in);
			if (in.equals("X")) m = null;
		}

		return m;
	}

	@Override
	public Menu showMenu() {

		String option = "99";
		boolean ok = false;
		
		System.out.println("Welcome to a ~chess game.");
		System.out.println("\t 1.) To play.");
		System.out.println("\t99.) To exit");
		
		while(!ok) {
			System.out.print("\n\nEnter number:");
			option = stdin.nextLine();
			ok = (option.equals("1") || option.equals("99"));
			
			if(!ok) System.out.println("Please enter a number from the selection.");
		}
		
		return option.equals("1") ? Menu.OK : Menu.EXIT;
	}

	@Override
	public void showWinner(Player p) throws Exception {
		if (p == null) {
			throw new Exception("Player cant be null");
		}
		
		String winner = p == Player.WHITE? "WHITE" : "BLACK";
		
		System.out.println("Congratulations on you victory "+winner+"!");
	}
	
	private char piece2identifier(Piece p) {
		
		String holder;
		if (p == null)
			holder = " ";
		else if (p.getClass() == King.class)
			holder = "K";
		else if (p.getClass() == Queen.class)
			holder = "Q";
		else if (p.getClass() == Knight.class)
			holder = "N";
		else if (p.getClass() == Rook.class)
			holder = "R";
		else if (p.getClass() == Bishop.class)
			holder = "B";
		else
			holder = "P";
		
		if (!holder.equals(" ") && p.getOwner() == Player.WHITE) {
			holder = holder.toLowerCase();
		}
		
		return holder.charAt(0);
		
	}

	@Override
	public void showBoard(Piece[][] board) throws Exception {
		
		if (board == null) {
			throw new Exception("Board cant be null");
		}
		if (board.length != 8 || board[0].length != 8) {
			throw new Exception("Board must be 8x8");
		}
		char[] rows = {'8','7','6','5','4','3','2','1'};
		
		// Clean a bit the screen
		for (int i = 0; i < 10; i++)
			System.out.println();
		System.out.println("    a   b   c   d   e   f   g   h ");
		System.out.println(ROW_SEPARATOR);
		for (int i = 0; i < board.length; i++) {
			System.out.print(rows[i]+" |");
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(" "+this.piece2identifier(board[i][j])+" |");
			}
			System.out.println();
			System.out.println(ROW_SEPARATOR);
		}
	}

}
