package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;

import TQSPractica.Player;

public class King extends Piece {
	
	private static final int VALUE = Integer.MAX_VALUE;
	private static final char IDENTIFIER = 'K';

	public King(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, King.VALUE);
	}

	@Override
	protected void generatePossibleMoves() {
		
		List<Position> possible_moves = new ArrayList<>();
		Board b = this.getBoard();
		int[] coor = this.getCurrentPosition().getCoorPosition();
		
		int[][] helper = {
				{0,1,-1}, { 0,1,-1}
		};
		
		for (int i = 0; i < 3; i++) {
			
			for (int j = 0; j < 3; j++) {
				
				if (helper[0][i] == 0 && helper[1][j] == 0)
					continue;
				
				Position candidate = new Position(coor[0] + helper[0][i], coor[1] +helper[1][j]);
				
				if (candidate.getChessPosition() == null)
					continue;
				
				Piece p = b.getPieceOn(candidate);
				if (p == null || p.getOwner() != this.getOwner()) {
					possible_moves.add(candidate);
				}
			}
		}
		this.setPossibleMoves(possible_moves);
	}

	@Override
	public String toString() {
		return King.IDENTIFIER + this.getCurrentPosition().getChessPosition();
	}
}
