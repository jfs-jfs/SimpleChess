package TQSPractica.models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MoveImp implements Move {
	
	private Position destination;
	private int column = Move.UNSET_COLUMN;
	private Class<?> piece;
	private boolean valid;

	private void setUnvalid() {
		this.valid = false;
		this.destination = null;
		this.column = Move.UNSET_COLUMN;
		this.piece = null;
	}
	
	
	private void setPiece(char piece) {
		
		switch(piece) {
		
		case 'K':
			this.piece = King.class;
			break;
		case 'Q':
			this.piece = Queen.class;
			break;
		case 'R':
			this.piece = Rook.class;
			break;
		case 'B':
			this.piece = Bishop.class;
			break;
		case 'N':
			this.piece = Knight.class;
			break;
		default:
			this.piece = Pawn.class;
			break;
		}
		
	}
	
	
	public MoveImp(String string) {
		
		Pattern non_ambiguous = Pattern.compile("^[KNBQR]{0,1}[abcdefgh][12345678]$");
		Pattern ambiguous = Pattern.compile("^[NBQR]{0,1}[abcdefgh]{2}[12345678]$");
		
		Matcher matcher_non = non_ambiguous.matcher(string);
		Matcher matcher_ambi = ambiguous.matcher(string);
		
		if (matcher_non.find()) {
			// Non ambiguous position
			this.setPiece(string.charAt(0));
			this.setDestination(string);
			this.valid = true;
			
			
		} else if (matcher_ambi.find()) {
			// Ambiguous position
			this.setPiece(string.charAt(0));
			this.setDestination(string);
			this.setColumn(string);
			this.valid = true;
			
		} else {
			this.setUnvalid();
		}
		
	}

	private void setColumn(String string) {
		
		char[] cols = {'a','b','c','d','e','f','g','h'};
		int index_of_char = this.piece == Pawn.class ? 0 : 1;
		
		boolean match = false;
		int i = 0;
		while(!match) {
			if (string.charAt(index_of_char) == cols[i]) {
				this.column = i;
				match = true;
			}
			++i;
		}		
	}


	private void setDestination(String string) {
		String dest_desc = string.length() > 2 ? string.substring(string.length()-2) : string;
		this.destination = new Position(dest_desc);
	}


	@Override
	public Class<?> getPieceType() {
		return this.piece;
	}

	@Override
	public int getColumn() {
		return this.column;
	}

	@Override
	public Position getDestination() {
		return this.destination;
	}

	@Override
	public boolean valid() {
		return valid;
	}

}
