package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;

import TQSPractica.Player;

public class Bishop extends Piece {
	
	private static final int VALUE = 3;
	private static final char IDENTIFIER = 'B';
	
	public Bishop(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, Bishop.VALUE);
	}

	@Override
	protected void generatePossibleMoves() {
		List<Position> possible_moves = new ArrayList<>();
		int i = 1;
		
		
		boolean topl = this.lookOnDirection(i, +1, +1, possible_moves);		// Stop conditions for directions
		boolean topr = this.lookOnDirection(i, -1, +1, possible_moves);
		boolean botl = this.lookOnDirection(i, +1, -1, possible_moves);
		boolean botr = this.lookOnDirection(i, -1, -1, possible_moves);
		boolean stop = !(topl || topr || botr || botl);						// Stop condition for main loop
		
		++i;
		while (!stop) {
			
			if (botr)
				botr = this.lookOnDirection(i, -1, -1, possible_moves);			
			if (botl)
				botl = this.lookOnDirection(i, +1, -1, possible_moves);
			if (topr)
				topr = this.lookOnDirection(i, -1, +1, possible_moves);
			if (topl)
				topl = this.lookOnDirection(i, +1, +1, possible_moves);
			
			stop = !(topl || topr || botr || botl);
			i++;
		}
		
		this.setPossibleMoves(possible_moves);
	}


	@Override
	public String toString() {
		return Bishop.IDENTIFIER+this.getCurrentPosition().getChessPosition();
	}

}
