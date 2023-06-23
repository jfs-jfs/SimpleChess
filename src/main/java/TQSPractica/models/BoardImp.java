package TQSPractica.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import TQSPractica.Player;

@SuppressWarnings("deprecation")
public class BoardImp extends Observable implements Board{

	private int[] dim;
	private Piece[][] board;

	private HashMap<Player, List<Piece>> pieces_player;

	private boolean isPossibleMove(Position pos, Piece p) {
		
		Position[] possible_moves = p.getPossibleMoves();
		int i = 0;
		boolean match = false;
		while (!match && i < possible_moves.length) {
			
			if (pos.equals(possible_moves[i])) {
				match = true;
			}
			++i;
		}
		return match;
	}
	
	private void promote() throws Exception {
		
		// Check both top and bottom rows for pawns and exchange them for Queens
		// Also delete them from the piece lists
		
		Piece[][] rows2look = {
				this.board[0],
				this.board[7]
		};
		
		for (int i = 0; i < rows2look.length; i++) {
			for (int j = 0; j < rows2look[0].length; j++) {
				Piece candidate = rows2look[i][j];
				if(candidate instanceof Pawn) {
					Queen replacement = new Queen(this, candidate.getCurrentPosition(), candidate.getOwner());
					// Change from list
					this.pieces_player.get(candidate.getOwner()).remove(candidate);
					this.pieces_player.get(candidate.getOwner()).add(replacement);
					// Change from board
					this.board[i*7][j] = replacement;
				}
				
			}
		}
		
	}
	
	public BoardImp() {
		this.board = null;
		this.dim = null;
		this.pieces_player = null;
	}
	
	@Override
	public List<Piece> getPices(Player player) {
		if (player == null) {
			return null;
		}
		return this.pieces_player.get(player);
	}

	@Override
	public Piece getPieceOn(int x, int y) {
		if (x < this.dim[0] && x >= 0 && y < this.dim[1] && y >= 0) {
			return this.board[y][x];
		}
		return null;
	}

	@Override
	public Piece getPieceOn(int[] infront) {
		return this.getPieceOn(infront[0], infront[1]);
	}

	@Override
	public boolean move(Piece p, Position from, Position to) throws Exception {
		
		if (p == null || from == null || to == null || !from.equals(p.getCurrentPosition())) {
			return false;
		}
		
		if (from.getTileId() == Position.ERR_NON_EXISTENT_TILE || to.getTileId() == Position.ERR_NON_EXISTENT_TILE) {
			return false;
		}
		
		if (this.isPossibleMove(to, p)) {
			// make it
			int[] prev_coor = from.getCoorPosition();
			int[] new_coor = to.getCoorPosition();
			
			// Check if there is a piece there and it is taking
			Piece aux = this.getPieceOn(to);
			if (aux != null) {
				// If so delete it from the pieces of that player
				this.pieces_player.get(aux.getOwner()).remove(aux);
			}
			//Move it on the board and let the piece know it has been moved
			this.board[prev_coor[1]][prev_coor[0]] = null;
			this.board[new_coor[1]][new_coor[0]] = p;
			p.setCurrentPosition(to);
			this.setChanged();
			this.notifyObservers(this);
			this.promote();
			return true;
		}
		
		return false;
		
	}

	@Override
	public int[] getDimensions() {
		return this.dim;
	}

	@Override
	public Piece getPieceOn(Position pos) {
		if (pos == null || pos.getTileId() == Position.ERR_NON_EXISTENT_TILE) {
			return null;
		}
		return this.getPieceOn(pos.getCoorPosition());
	}

	@Override
	public void initBoard() throws Exception {
		Board b = this;
		this.dim = new int[] {8,8};
		this.pieces_player = new HashMap<>();
		
		Pawn aw = new Pawn(b, new Position("a2"),Player.WHITE);
		Pawn bw = new Pawn(b, new Position("b2"),Player.WHITE);
		Pawn cw = new Pawn(b, new Position("c2"),Player.WHITE);
		Pawn dw = new Pawn(b, new Position("d2"),Player.WHITE);
		Pawn ew = new Pawn(b, new Position("e2"),Player.WHITE);
		Pawn fw = new Pawn(b, new Position("f2"),Player.WHITE);
		Pawn gw = new Pawn(b, new Position("g2"),Player.WHITE);
		Pawn hw = new Pawn(b, new Position("h2"),Player.WHITE);
		
		Rook r1w = new Rook(b, new Position("a1"), Player.WHITE);
		Rook r2w = new Rook(b, new Position("h1"), Player.WHITE);
		Knight n1w = new Knight(b, new Position("b1"), Player.WHITE);
		Knight n2w = new Knight(b, new Position("g1"), Player.WHITE);
		Bishop b1w = new Bishop(b, new Position("c1"), Player.WHITE);
		Bishop b2w = new Bishop(b, new Position("f1"), Player.WHITE);
		Queen qw = new Queen(b, new Position("d1"), Player.WHITE);
		King kw = new King(b, new Position("e1"), Player.WHITE);
		
		Pawn ab = new Pawn(b, new Position("a7"),Player.BLACK);
		Pawn bb = new Pawn(b, new Position("b7"),Player.BLACK);
		Pawn cb = new Pawn(b, new Position("c7"),Player.BLACK);
		Pawn db = new Pawn(b, new Position("d7"),Player.BLACK);
		Pawn eb = new Pawn(b, new Position("e7"),Player.BLACK);
		Pawn fb = new Pawn(b, new Position("f7"),Player.BLACK);
		Pawn gb = new Pawn(b, new Position("g7"),Player.BLACK);
		Pawn hb = new Pawn(b, new Position("h7"),Player.BLACK);
		
		Rook r1b = new Rook(b, new Position("a8"), Player.BLACK);
		Rook r2b = new Rook(b, new Position("h8"), Player.BLACK);
		Knight n1b = new Knight(b, new Position("b8"), Player.BLACK);
		Knight n2b = new Knight(b, new Position("g8"), Player.BLACK);
		Bishop b1b = new Bishop(b, new Position("c8"), Player.BLACK);
		Bishop b2b = new Bishop(b, new Position("f8"), Player.BLACK);
		Queen qb = new Queen(b, new Position("d8"), Player.BLACK);
		King kb = new King(b, new Position("e8"), Player.BLACK);
		
		this.board = new Piece[][] {
			{r1b, n1b, b1b, qb, kb, b2b, n2b, r2b},
			{ab,  bb,  cb,  db, eb, fb,  gb,  hb },
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{aw,  bw,  cw,  dw, ew, fw,  gw,  hw },
			{r1w, n1w, b1w, qw, kw, b2w, n2w, r2w}
		};
		
		List<Piece> white = new ArrayList<>();
		white.add(aw); white.add(bw); white.add(cw); 
		white.add(dw); white.add(ew); white.add(fw); 
		white.add(gw); white.add(hw); white.add(r1w); 
		white.add(r2w); white.add(n1w); white.add(n2w); 
		white.add(b1w); white.add(b2w); white.add(qw); 
		white.add(kw);
		List<Piece> black = new ArrayList<>();
		black.add(ab); black.add(bb); black.add(cb); 
		black.add(db); black.add(eb); black.add(fb); 
		black.add(gb); black.add(hb); black.add(r1b); 
		black.add(r2b); black.add(n1b); black.add(n2b); 
		black.add(b1b); black.add(b2b); black.add(qb); 
		black.add(kb);
		
		this.pieces_player.put(Player.BLACK, black);
		this.pieces_player.put(Player.WHITE, white);

		
	}

	@Override
	public boolean makeMove(Move m, Player p) throws Exception {
		
		if (m == null || p == null || !m.valid()) {
			return false;
		}
		
		// Destination is easy
		Position to = m.getDestination();
		// Piece a bit hard
		Piece pi = null;
		List<Piece> candidates = this.pieces_player.get(p);
		
		int i = 0;
		
		for (Piece piece : candidates) {
			if (m.getPieceType().isInstance(piece) && this.isPossibleMove(to, piece)) {
				++i;
				
				if (i>1) {
					int column = m.getColumn();
					if (column == Move.UNSET_COLUMN) return false;
					if(column == piece.getCurrentPosition().getCoorPosition()[0]) {
						pi = piece;
					}
				} else {
					pi = piece;
				}
				
			}
		}
		
		// No piece can do the move
		if (pi == null) return false;
		
		// From is easy too
		Position from = pi.getCurrentPosition();
		
		return this.move(pi, from, to);
	}

	@Override
	public int getPuntuation(Player p) {
		
		List<Piece> all = new ArrayList<>();
		all.addAll(this.pieces_player.get(Player.BLACK));
		all.addAll(this.pieces_player.get(Player.WHITE));
		
		
		long p_acc = 0;
		long other_acc = 0;
		
		for (Piece piece : all) {
			if (piece.getOwner() == p) {
				p_acc += piece.getPieceValue();
			} else {
				other_acc += piece.getPieceValue();
			}
		}
		
		long diff = p_acc - other_acc;
		
		if (diff > 100) {
			return Integer.MAX_VALUE;
		}
		if (diff < -100) {
			return Integer.MIN_VALUE;
		}
		return (int) diff;
	}

	@Override
	public int[] getPuntuation() {
		return new int[] {
				this.getPuntuation(Player.WHITE),
				this.getPuntuation(Player.BLACK)
		};
	}

	@Override
	public boolean isGameOver() {
		
		if (this.pieces_player == null)
			return false;
		
		List<Piece> all = new ArrayList<>();
		all.addAll(this.pieces_player.get(Player.BLACK));
		all.addAll(this.pieces_player.get(Player.WHITE));
		
		int king_count = 2;
		
		for (Piece piece : all) {
			if (piece instanceof King) {
				--king_count;
			}
		}
		
		return king_count != 0;
	}

	@Override
	public Piece[][] getBoard() {
		return this.board;
	}

}
