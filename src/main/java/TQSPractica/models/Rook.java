package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;

import TQSPractica.Player;

public class Rook extends Piece {
	
	private static final int VALUE = 5;
	private static final char IDENTIFIER = 'R';
	
	public Rook(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, VALUE);
	}

	@Override
	protected void generatePossibleMoves() {
		List<Position> possible_moves = new ArrayList<>();
		
		int[][] dir_modifiers = {
				{0, 1}, {1, 0}, {0, -1}, {-1, 0}
		};
		
		boolean[] dir_continue = {
				true, true, true, true
		};
		
		boolean stop = false;
		
		int i = 1;
		
		while(!stop) {
			
			for (int j = 0; j < dir_continue.length; j++)
				if (dir_continue[j])
					dir_continue[j] = this.lookOnDirection(
								i, dir_modifiers[j][0], dir_modifiers[j][1], possible_moves
							);
			
			i++;
			for (int j = 0; j < dir_continue.length; j++)
				stop = stop || dir_continue[j];
			stop = !stop;
		}
		
		
		this.setPossibleMoves(possible_moves);
		
	}

	@Override
	public String toString() {
		return Rook.IDENTIFIER + this.getCurrentPosition().getChessPosition();
	}

}
