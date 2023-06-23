package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;

import TQSPractica.Player;

public class Queen extends Piece {
	
	private static final int VALUE = 9;
	private static final char IDENTIFIER = 'Q';

	public Queen(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, Queen.VALUE);
	}

	@Override
	protected void generatePossibleMoves() {
		
		// Directions
		// 7 0 1
		// 6 Q 2
		// 5 4 3
		int[][] dir_modifiers = {
				{-1, +1}, { 0, +1}, {+1, +1},
				{-1,  0},           {+1,  0},
				{-1, -1}, { 0, -1}, {+1, -1},
		};
		List<Position> possible_moves = new ArrayList<>();
		boolean[] dir_stop_conditions = {
				false, false, false, false, false, false, false, false
		};
		boolean stop = false;
		int i = 1;
		while(!stop) {
			
			// Look all directions
			for (int j = 0; j < 8; j++) {
				if (!dir_stop_conditions[j])
					dir_stop_conditions[j] = !this.lookOnDirection(i, dir_modifiers[j][0], dir_modifiers[j][1], possible_moves);				
			}
			
			i++;
			// Checking if everything is true
			stop = dir_stop_conditions[0];
			for (int j = 1; j < dir_stop_conditions.length; j++)
				stop = stop && dir_stop_conditions[j];
			
		}
		this.setPossibleMoves(possible_moves);
	}

	@Override
	public String toString() {
		return Queen.IDENTIFIER+this.getCurrentPosition().getChessPosition();
	}
}
