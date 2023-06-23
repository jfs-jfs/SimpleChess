package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;

import TQSPractica.Player;

public class Knight extends Piece {
	
	private static final int VALUE = 3;
	private static final char IDENTIFIER = 'N';
	
	public Knight(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, Knight.VALUE);
	}

	@Override
	protected void generatePossibleMoves() {
		List<Position> possible_moves = new ArrayList<>();
		Board b = this.getBoard();
		
		int coor[] = this.getCurrentPosition().getCoorPosition();
		
		// To iterate over this and generate the 8 possible convinations of
		// this two groups
		int helper[][] = new int[][] {
			{-1, 1}, {-2, 2}
		};
		
		// Will be 4 iteration
		for (int i = 0; i < 2; i++) {
			
			for (int j = 0; j < 2; j++) {
				
				// The two positions each iteration gives
				Position[] candidates = {
						new Position(coor[0] + helper[0][i], coor[1] + helper[1][j]),
						new Position(coor[0] + helper[1][i], coor[1] + helper[0][j])
				};
				
				// If it is valid and not occupied or occupied by the other team is valid
				for (Position pos : candidates) {
					if (pos.getCoorPosition() != null) {
						
						Piece p = b.getPieceOn(pos);
						if (p == null || p.getOwner() != this.getOwner()) {
							possible_moves.add(pos);
						}
					}
				}
			}
		}
		
		this.setPossibleMoves(possible_moves);
	}

	@Override
	public String toString() {
		return Knight.IDENTIFIER + this.getCurrentPosition().getChessPosition();
	}

}
