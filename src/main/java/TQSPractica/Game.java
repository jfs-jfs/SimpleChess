package TQSPractica;

import java.util.Scanner;

import TQSPractica.models.Board;
import TQSPractica.models.BoardImp;
import TQSPractica.models.Move;
import TQSPractica.views.Display;
import TQSPractica.views.TerminalDisplay;

public class Game {

	public static final int SURRENDER = -1;
	public static final int LEGAL = 0;
	public static final int ILLEGAL = 1;
	public static Scanner scanner = new Scanner(System.in);
	
	private Display display;
	private Board board;
	
	private boolean white_surrender = false;
	private boolean black_surrender = false;
	
	private boolean first_move = true;
	
	
	public Game(Display dis) throws Exception {
		if (dis == null) 
			throw new Exception("No display found");
		
		this.display = dis;
		this.board = new BoardImp();
		this.board.initBoard();
	}
	
	public boolean menuState() {
		return this.display.showMenu() == Display.Menu.OK;		
	}
	
	private int getMoveFromPlayer(Player p) throws Exception {
		
		// Display assures move is valid or null
		Move m = null;
		m = this.display.getMove(p);

		
		// null surrender
		if (m == null) {
			
			this.white_surrender = (p == Player.WHITE);
			this.black_surrender = (p == Player.BLACK);
			
			return SURRENDER;
		}
			
		
		// make move -- return if it can be made or not
		if (this.board.makeMove(m, p)) {
			this.display.showBoard(this.board.getBoard());
			return LEGAL;
		}
		return ILLEGAL; // valid move but with the actual board it is not possible to do
	}
	
	
	public int whiteState() throws Exception {
		if (this.first_move) {
			this.display.showBoard(this.board.getBoard());
			this.first_move = false;
		}
			
		return this.getMoveFromPlayer(Player.WHITE);
	}
	
	public int blackState() throws Exception {
		return this.getMoveFromPlayer(Player.BLACK);
	}
	
	public Player resState() throws Exception {
		
		Player winner;
		if (black_surrender)
			winner = Player.WHITE;
		
		else if (white_surrender)
			winner = Player.BLACK;
		
		else if (!this.board.isGameOver())
			return null;
		else
			winner = this.board.getPuntuation(Player.WHITE) == Integer.MAX_VALUE ? Player.WHITE : Player.BLACK;

		this.display.showWinner(winner);
		return winner;
	}

	public static void main(String[] args) throws Exception {
		
		Display display = new TerminalDisplay(Game.scanner);		
		boolean exit = false;
		
		while (!exit) {
			// Create a new game
			Game controller = new Game(display);
			boolean white_turn = true;
			
			// Show menu
			exit = !controller.menuState();
			
			if(exit) continue;
			// Start Game
			do {
				int valid = white_turn ? controller.whiteState() : controller.blackState();
				
				if (valid == SURRENDER) continue;
				
				while(valid != LEGAL) {
					
					System.out.println("\tThis move can't be placed or it is ambigous. Try again");
					
					valid = white_turn ? controller.whiteState() : controller.blackState();
				}
				white_turn = !white_turn;
			} while (controller.resState() == null);
		}
	}

	public Display getDisplay() {
		return this.display;
	}
	
	

}
