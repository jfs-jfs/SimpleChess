package TQSPractica;

import TQSPractica.models.Move;
import TQSPractica.models.MoveImp;
import TQSPractica.models.Piece;
import TQSPractica.views.Display;

public class MockDisplay implements Display {
	
	private Display.Menu menu_op = Display.Menu.OK;
	private Move m_white = new MoveImp("e3");
	private Move m_balck = new MoveImp("e6");

	public void pickExit() {
		menu_op = Display.Menu.EXIT;
		
	}

	public void whiteSurrender() {
		m_white = null;
		
	}

	public void blackSurrender() {
		m_balck = null;
		
	}

	public void blackwins(Game g) throws Exception {
		
		Move[] moves = new MoveImp[] {
				new MoveImp("e5"),
				new MoveImp("Bc5"),
				new MoveImp("Bf2"),
				new MoveImp("Be1"),
		};
		
		for (int i = 0; i < moves.length; i++) {
			m_balck = moves[i];
			g.blackState();
		}
		
		
	}

	public void whitewins(Game g) throws Exception {
		Move[] moves = new MoveImp[] {
				new MoveImp("e4"),
				new MoveImp("Bc4"),
				new MoveImp("Bf7"),
				new MoveImp("Be8"),
		};
		
		for (int i = 0; i < moves.length; i++) {
			m_white = moves[i];
			g.whiteState();
		}
		
		
	}
	
	public boolean equals(Display d) {
		return this == d;
	}

	@Override
	public Display.Menu showMenu() {
		return menu_op;
	}

	public void badMove() {
		m_white = new MoveImp("e1");
		m_balck = new MoveImp("e1");
	}

	@Override
	public Move getMove(Player p) {
		return p == Player.WHITE ? this.m_white : this.m_balck;
	}

	@Override
	public void showWinner(Player p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showBoard(Piece[][] board) {
		// TODO Auto-generated method stub
		
	}

}
