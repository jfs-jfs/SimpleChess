package TQSPractica.models;

public class Position {
	
	private String chessNotation;
	private int[] coordinates;
	
	public static final int ERR_NON_EXISTENT_TILE = -1;
	
	public static String coord2Chess(int x, int y) {
		if (x < 0 || x > 7 || y < 0 || y > 7) {
			return null;
		}
		
		char[] columns = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
		
		return columns[x] + Integer.toString(8 - y);

	}
	public static int[] chess2Coord(String chess) {
		if (chess == null || chess.length() != 2) {
			return null;
		}
		
		char[] elements = chess.toCharArray();
		char column = elements[0];
		char row = elements[1];
		
		if (column < 97 || column > 104 || row < 49 || row > 56) {
			return null;
		}
		
		return new int[] { column - 97, 7 - ( row - 49 ) };		
	}
	
	private void init() {
		this.chessNotation = null;
		this.coordinates = null;
	}
	
	public Position(String chessNotation) {
		this.init();
		this.coordinates = Position.chess2Coord(chessNotation);
		if (this.coordinates != null) {
			this.chessNotation = chessNotation;
		}
	}
	public Position(int x, int y) {
		this.init();
		this.chessNotation = Position.coord2Chess(x, y);
		if (this.chessNotation != null) {
			this.coordinates = new int[] {x, y};
		}
	}
	
	public String getChessPosition() {
		return this.chessNotation;
	}
	
	public int[] getCoorPosition() {
		return this.coordinates;
	}
	
	public int getTileId() {
		if (this.coordinates == null) {
			return Position.ERR_NON_EXISTENT_TILE;
		}
		
		return (8 * this.coordinates[1]) + this.coordinates[0];
	}
	
	public boolean setPosition(String chessNotation) {
		int[] holder = Position.chess2Coord(chessNotation);
		if (holder == null) {
			return false;
		}
		this.coordinates = holder;
		this.chessNotation = chessNotation;
		return true;
	}
	public boolean setPosition(int x, int y) {
		String holder = Position.coord2Chess(x, y);
		if(holder == null) {
			return false;
		}
		this.chessNotation = holder;
		this.coordinates = new int[] {x,y};
		return true;
	}
	
	public boolean equals(Position p) {
		if(p == null) return false;
		return p.getTileId() == this.getTileId();
	}

}
