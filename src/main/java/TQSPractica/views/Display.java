package TQSPractica.views;

import TQSPractica.Player;
import TQSPractica.models.Move;
import TQSPractica.models.Piece;

public interface Display {

	enum Menu {
		OK,
		EXIT
	}

	Menu showMenu();
	
	void showWinner(Player p) throws Exception;
	
	void showBoard(Piece[][] board) throws Exception;

	Move getMove(Player p) throws Exception;

}
