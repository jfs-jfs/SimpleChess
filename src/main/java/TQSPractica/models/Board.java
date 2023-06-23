package TQSPractica.models;
import java.util.List;

import TQSPractica.Player;

public interface Board {
	
	public List<Piece> getPices(Player player);

	public Piece getPieceOn(int x, int y);

	public Piece getPieceOn(int[] infront);

	public boolean move(Piece p, Position from, Position to) throws Exception;

	public int[] getDimensions();

	public Piece getPieceOn(Position pos);
	
	public void initBoard() throws Exception;
	
	public boolean makeMove(Move m, Player p) throws Exception;
	
	public int getPuntuation(Player p);
	
	public int[] getPuntuation();
	
	public boolean isGameOver();
	
	public Piece[][] getBoard();

}
