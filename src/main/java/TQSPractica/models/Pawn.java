package TQSPractica.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import TQSPractica.Player;

@SuppressWarnings("deprecation")
public class Pawn extends Piece implements Observer {
	
	private static final int VALUE = 1;
	private boolean initial_move;
	private boolean enpassant_target;
	
	public Pawn(Board board,Position initial_position, Player owner) throws Exception {
		super(board, initial_position, owner, Pawn.VALUE);
		this.initial_move = true;
		this.enpassant_target = false;
	}

	@Override
	protected void generatePossibleMoves() {
		
		int[] infront;
		int[] twoforward;
		int[] taking_left;
		int[] taking_right;
		int[] coor = this.getCurrentPosition().getCoorPosition();
		int[] passant_left = {coor[0]-1, coor[1]};
		int[] passant_right = {coor[0]+1, coor[1]};
		Pawn aux;
		
		if (this.getOwner() == Player.WHITE) {
			infront = new int[] {coor[0], coor[1]-1};
			twoforward = new int[] {coor[0], coor[1]-2};
			taking_left = new int[] {coor[0]-1, coor[1]-1};
			taking_right = new int[] {coor[0]+1, coor[1]-1};
		} else {
			infront = new int[] {coor[0], coor[1]+1};
			twoforward = new int[] {coor[0], coor[1]+2};
			taking_left = new int[] {coor[0]-1, coor[1]+1};
			taking_right = new int[] {coor[0]+1, coor[1]+1};
		}
		
		
		Board b = this.getBoard();
		List<Position> possible_moves = new ArrayList<>();
		
		
		// Normal forward
		if(b.getPieceOn(infront) == null) {
			possible_moves.add(new Position(infront[0],infront[1]));
			
			// Double move on first move
			if(this.initial_move) {
				if(b.getPieceOn(twoforward) == null) {
					possible_moves.add(new Position(twoforward[0], twoforward[1]));
				}
			}
		}
		
		// Taking
		Piece right = b.getPieceOn(taking_right);
		Piece left = b.getPieceOn(taking_left);
		if(right != null && right.getOwner() != this.getOwner()) {
			possible_moves.add(right.getCurrentPosition());
		}
		if(left != null && left.getOwner() != this.getOwner()) {
			possible_moves.add(left.getCurrentPosition());
		}
		
		// En passant
		right = b.getPieceOn(passant_right);
		left = b.getPieceOn(passant_left);
		if (right != null && right instanceof Pawn && right.getOwner() != this.getOwner()) {
			aux = (Pawn) right;
			if (aux.enpassant_target) {
				possible_moves.add(new Position(taking_right[0], taking_right[1]));
			}
		} 
		if (left != null && left instanceof Pawn && left.getOwner() != this.getOwner()) {
			aux = (Pawn) left;
			if (aux.enpassant_target) {
				possible_moves.add(new Position(taking_left[0], taking_left[1]));
			}
		}
		
		this.setPossibleMoves(possible_moves);
	}

	@Override
	public boolean moveTo(Position pos) {
		
		if (pos == null) {
			return false;
		}
		
		for( Position p :this.getPossibleMoves()) {
			if(p.equals(pos)) {
				// Make the move and return True
				Position curr_pos = this.getCurrentPosition();
				this.setCurrentPosition(pos);
				this.initial_move = false;
				this.setPossibleMoves(null);
				if (Math.abs(p.getCoorPosition()[1] - curr_pos.getCoorPosition()[1]) == 2) {
					// set as an observer to the board to remove the en passant flag the next turn
					this.enpassant_target = true;
					Observable o = (Observable) this.getBoard();
					o.addObserver(this);
				}
				
				return true;
			}
		}
		
		return false;
	}

	@Override
	public String toString() {
		return this.getCurrentPosition().getChessPosition();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// The next move
		this.enpassant_target = false;
		arg0.deleteObserver(this);
		
	}


	// only testing
	public boolean getEnPassant() {
		return this.enpassant_target;
	}

}