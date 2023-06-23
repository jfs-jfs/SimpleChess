package TQSPractica.models;

public class MockMove implements Move {

	private String str;
	private int col;
	
	public MockMove(String string) {
		// TODO Auto-generated constructor stub
		str = string;
		col = 1;
	}

	@Override
	public Class<?> getPieceType() {
		if (str.length() == 2) {
			return Pawn.class;
		}
		return Knight.class;
	}

	@Override
	public int getColumn() {
		if (str.length() == 3) {
			return Move.UNSET_COLUMN;
		} 
		return col;
	}

	@Override
	public Position getDestination() {
		// TODO Auto-generated method stub
		if (str.length() == 2) {
			return new Position(str);
		}
		return new Position(str.substring(str.length()-2));
	}

	@Override
	public boolean valid() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public void setColumn(int c) {
		this.col = c;
	}

}
