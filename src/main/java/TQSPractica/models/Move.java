package TQSPractica.models;

import TQSPractica.Player;

public interface Move {
	
	int UNSET_COLUMN = -1;

	Class<?> getPieceType();

	int getColumn();

	Position getDestination();

	boolean valid();

}
