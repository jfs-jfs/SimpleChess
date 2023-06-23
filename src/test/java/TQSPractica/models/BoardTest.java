package TQSPractica.models;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import TQSPractica.Player;

public class BoardTest {
	
	Piece[][] initialBoard;
	Board b = new BoardImp();

	private void init() throws Exception {
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
		
		this.initialBoard = new Piece[][] {
			{r1b, n1b, b1b, qb, kb, b2b, n2b, r2b},
			{ab,  bb,  cb,  db, eb, fb,  gb,  hb },
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{aw,  bw,  cw,  dw, ew, fw,  gw,  hw },
			{r1w, n1w, b1w, qw, kw, b2w, n2w, r2w}
		};
	}

	@Test
	public void testGetPices() throws Exception {
		System.out.println("[TESTING: Board->getPieces(Player)] : Get the pieces of a player");
		Board b = new BoardImp();
		b.initBoard();
		
		// Move the piece a bit
		Piece aux = b.getPieceOn(new Position("e2"));
		b.move(aux, new Position("e2"), new Position("e3"));
		
		Boolean flag = false;
		List<Piece> white = b.getPices(Player.WHITE);
		for (Piece piece : white) {
			if (piece.getCurrentPosition().equals(new Position("e3"))) {
				flag = (piece.getOwner() == Player.WHITE && piece instanceof Pawn);
			}
		}
		
		assertTrue(flag);
		assertTrue(white.size() == 16);
		
		// Take a piece
		b.move(aux, new Position("e3"), new Position("e4"));
		b.move(aux, new Position("e4"), new Position("e5"));
		b.move(aux, new Position("e5"), new Position("e6"));
		aux = b.getPieceOn(new Position("f7"));
		b.move(aux, new Position("f7"), new Position("e6"));
		
		white = b.getPices(Player.WHITE);
		assertTrue(white.size() == 15);
		
		// Wrong player
		assertTrue(b.getPices(null) == null);
	}

	@Test
	public void testGetPieceOn() throws Exception {
		Board b = new BoardImp();
		b.initBoard();
		Piece pawn;
		Piece king;
		Piece null_;
		Piece rook;
		
		Position e2 = new Position("e2");
		Position e8 = new Position("e8");
		Position e4 = new Position("e4");
		Position bad = new Position("sdfjslkd");
		Position _null = null;
		
		
		System.out.println("[TESTING: Board->getPieceOn(Position)] : Get the piece in a tile");
		pawn = b.getPieceOn(e2);
		assertTrue(pawn instanceof Pawn && pawn.getOwner() == Player.WHITE && e2.equals(pawn.getCurrentPosition()));
		king = b.getPieceOn(e8);
		assertTrue(king instanceof King && king.getOwner() == Player.BLACK && e8.equals(king.getCurrentPosition()));
		null_ = b.getPieceOn(e4);
		assertTrue(null_ == null);
		null_ = b.getPieceOn(bad);
		assertTrue(null_ == null);
		null_ = b.getPieceOn(_null);
		assertTrue(null_ == null);
		
		System.out.println("[TESTING: Board->getPieceOn(x, y)] : Get the piece in a tile");
		pawn = b.getPieceOn(e2.getCoorPosition()[0],e2.getCoorPosition()[1]);
		assertTrue(pawn instanceof Pawn && pawn.getOwner() == Player.WHITE && e2.equals(pawn.getCurrentPosition()));
		king = b.getPieceOn(e8.getCoorPosition()[0],e8.getCoorPosition()[1]);
		assertTrue(king instanceof King && king.getOwner() == Player.BLACK && e8.equals(king.getCurrentPosition()));
		null_ = b.getPieceOn(e4.getCoorPosition()[0],e4.getCoorPosition()[1]);
		assertTrue(null_ == null);
		rook = b.getPieceOn(0,0);
		assertTrue(rook instanceof Rook);
		rook = b.getPieceOn(7,7);
		assertTrue(rook instanceof Rook);
		null_ = b.getPieceOn(-1,-1);
		assertTrue(null_ == null);
		pawn = b.getPieceOn(1,1);
		assertTrue(pawn instanceof Pawn);
		null_ = b.getPieceOn(8,8);
		assertTrue(null_ == null);
		pawn = b.getPieceOn(6,6);
		assertTrue(pawn instanceof Pawn);
		
		System.out.println("[TESTING: Board->getPieceOn([x,y])] : Get the piece in a tile");
		pawn = b.getPieceOn(e2.getCoorPosition());
		assertTrue(pawn instanceof Pawn && pawn.getOwner() == Player.WHITE && e2.equals(pawn.getCurrentPosition()));
		king = b.getPieceOn(e8.getCoorPosition());
		assertTrue(king instanceof King && king.getOwner() == Player.BLACK && e8.equals(king.getCurrentPosition()));
		null_ = b.getPieceOn(e4.getCoorPosition());
		assertTrue(null_ == null);
		rook = b.getPieceOn(new int[] {0,0});
		assertTrue(rook instanceof Rook);
		rook = b.getPieceOn(new int[] {7,7});
		assertTrue(rook instanceof Rook);
		null_ = b.getPieceOn(new int[] {-1,-1});
		assertTrue(null_ == null);
		pawn = b.getPieceOn(new int[] {1,1});
		assertTrue(pawn instanceof Pawn);
		null_ = b.getPieceOn(new int[] {8,8});
		assertTrue(null_ == null);
		pawn = b.getPieceOn(new int[] {6,6});
		assertTrue(pawn instanceof Pawn);
	}

	@Test
	public void testMove() throws Exception {
		System.out.println("[TESTING: Board->move(piece, from, to)] : Make a move, lower level");
		Board b = new BoardImp();
		b.initBoard();
		Piece aux = b.getPieceOn(new Position("e2"));
		// Wrong stuff -- invalid parameters
		assertFalse(b.move(null, new Position("e2"), new Position("e3")));
		assertFalse(b.move(aux, new Position("e2"), null));
		assertFalse(b.move(aux, null, new Position("e3")));
		// Wrong stuff -- invalid movements
		assertFalse(b.move(aux, new Position("e7"), new Position("e3")));
		assertFalse(b.move(aux, new Position("e2"), new Position("e5")));
		// Wrong stuff -- bad positions
		assertFalse(b.move(aux, new Position("skajd"), new Position("e3")));
		assertFalse(b.move(aux, new Position("e2"), new Position("askjd")));
		assertFalse(b.move(aux, new Position("asdj"), new Position("askjd")));
		
		// Good ones
		assertTrue(b.move(aux, new Position("e2"), new Position("e4")));
	}

	@Test
	public void testGetDimensions() throws Exception {
		System.out.println("[TESTING: Board->getDimensions()] : Get dimensions of the board");
		Board b = new BoardImp();
		b.initBoard();
		int[] dim = b.getDimensions();
		
		assertTrue(dim[0] == 8 && dim[1] == 8);
	}

	@Test
	public void testInitBoard() throws Exception {
		System.out.println("[TESTING: Board->initBoard()] : Prepare for a new game");
		Board b = new BoardImp();
		this.init();
		b.initBoard();
		Piece[][] res = b.getBoard();
		
		assertTrue(res.length == initialBoard.length);
		assertTrue(res[0].length == initialBoard[0].length);
		assertTrue(res[1].length == initialBoard[1].length);
		assertTrue(res[2].length == initialBoard[2].length);
		assertTrue(res[3].length == initialBoard[3].length);
		assertTrue(res[4].length == initialBoard[4].length);
		assertTrue(res[5].length == initialBoard[5].length);
		assertTrue(res[6].length == initialBoard[6].length);
		assertTrue(res[7].length == initialBoard[7].length);
		
		for (int i = 0; i < res.length; i++) {
			
			for (int j = 0; j < res[i].length; j++) {
				
				if (res[i][j] == null) {
					assertTrue(initialBoard[i][j] == null);
				} else {
					assertTrue(res[i][j].getClass().equals(initialBoard[i][j].getClass()));
					assertTrue(res[i][j].getOwner() == initialBoard[i][j].getOwner());	
				}
			}
		}
		
	}

	@Test
	public void testMakeMove() throws Exception {
		System.out.println("[TESTING: Board->makeMove(Move, Player)] : Make a move, higer level");
		Board b = new BoardImp();
		b.initBoard();
		// Wrong stuff -- invalid parameters
		assertFalse(b.makeMove(null, Player.WHITE));
		assertFalse(b.makeMove(new MockMove("Nc3"), null));
		// Wrong stuff -- invalid movements
		assertFalse(b.makeMove(new MockMove("Nc4"), Player.WHITE));
		assertFalse(b.makeMove(new MockMove("Nc3"), Player.BLACK));
		// Good ones
		assertTrue(b.makeMove(new MockMove("Nc3"), Player.WHITE));
		assertTrue(b.makeMove(new MockMove("Nc6"), Player.BLACK));
		
		// Bad one -- ambigous
		b = new BoardImp();
		b.initBoard();
		// set up
		assertTrue(b.makeMove(new MockMove("e3"), Player.WHITE));
		assertTrue(b.makeMove(new MockMove("Ne2"), Player.WHITE));
		assertFalse(b.makeMove(new MockMove("Nc3"), Player.WHITE));
		// Good one -- ambigous
		assertTrue(b.makeMove(new MockMove("Nbc3"), Player.WHITE));
		b = new BoardImp();
		b.initBoard();
		// set up
		assertTrue(b.makeMove(new MockMove("e3"), Player.WHITE));
		assertTrue(b.makeMove(new MockMove("Ne2"), Player.WHITE));
		assertFalse(b.makeMove(new MockMove("Nc3"), Player.WHITE));
		// Good one -- ambigous
		MockMove m = new MockMove("Nec3");
		m.setColumn(4);
		assertTrue(b.makeMove(m, Player.WHITE));
		
		
	}

	@Test
	public void testGetPuntuationPlayer() throws Exception {
		System.out.println("[TESTING: Board->getPuntuation(Player)] : Get diff of puntuation");
		Board b = new BoardImp();
		b.initBoard();
		assertTrue(b.getPuntuation(Player.WHITE) == 0);
		assertTrue(b.getPuntuation(Player.BLACK) == 0);
		
		Piece q = b.getPieceOn(new Position("d1"));
		Piece p = b.getPieceOn(new Position("e2"));
		
		b.move(p, p.getCurrentPosition(), new Position("e4"));
		b.move(q, q.getCurrentPosition(), new Position("f3"));
		b.move(q, q.getCurrentPosition(), new Position("f7")); // takes pawn
		assertTrue(b.getPuntuation(Player.WHITE) == 1);
		assertTrue(b.getPuntuation(Player.BLACK) == -1);
		b.move(q, q.getCurrentPosition(), new Position("g7")); // takes pawn
		assertTrue(b.getPuntuation(Player.WHITE) == 2);
		assertTrue(b.getPuntuation(Player.BLACK) == -2);
		b.move(q, q.getCurrentPosition(), new Position("h8")); // takes rook
		assertTrue(b.getPuntuation(Player.WHITE) == 7);
		assertTrue(b.getPuntuation(Player.BLACK) == -7);
		b.move(q, q.getCurrentPosition(), new Position("g8")); // takes knight
		assertTrue(b.getPuntuation(Player.WHITE) == 10);
		assertTrue(b.getPuntuation(Player.BLACK) == -10);
		b.move(q, q.getCurrentPosition(), new Position("f8")); // takes bishop
		assertTrue(b.getPuntuation(Player.WHITE) == 13);
		assertTrue(b.getPuntuation(Player.BLACK) == -13);
		b.move(q, q.getCurrentPosition(), new Position("f7"));
		b.move(q, q.getCurrentPosition(), new Position("e6"));
		b.move(q, q.getCurrentPosition(), new Position("d7")); // Takes pawn
		assertTrue(b.getPuntuation(Player.WHITE) == 14);
		assertTrue(b.getPuntuation(Player.BLACK) == -14);
		b.move(q, q.getCurrentPosition(), new Position("d8")); // Takes queen
		assertTrue(b.getPuntuation(Player.WHITE) == 23);
		assertTrue(b.getPuntuation(Player.BLACK) == -23);
		b.move(q, q.getCurrentPosition(), new Position("e8")); // Takes king
		assertTrue(b.getPuntuation(Player.WHITE) == Integer.MAX_VALUE);
		assertTrue(b.getPuntuation(Player.BLACK) == Integer.MIN_VALUE);
	}

	@Test
	public void testGetPuntuation() throws Exception {
		System.out.println("[TESTING: Board->getPuntuation()] : Get diff of puntuation for both players");
		Board b = new BoardImp();
		b.initBoard();
		assertTrue(b.getPuntuation().length == 2);
		assertTrue(b.getPuntuation()[0] == 0 && b.getPuntuation()[1] == 0);
		
		Piece q = b.getPieceOn(new Position("d1"));
		Piece p = b.getPieceOn(new Position("e2"));
		
		b.move(p, p.getCurrentPosition(), new Position("e4"));
		b.move(q, q.getCurrentPosition(), new Position("f3"));
		b.move(q, q.getCurrentPosition(), new Position("f7")); // takes pawn
		assertTrue(b.getPuntuation()[0] == 1 && b.getPuntuation()[1] == -1);
		b.move(q, q.getCurrentPosition(), new Position("g7")); // takes pawn
		assertTrue(b.getPuntuation()[0] == 2 && b.getPuntuation()[1] == -2);
		b.move(q, q.getCurrentPosition(), new Position("h8")); // takes rook
		assertTrue(b.getPuntuation()[0] == 7 && b.getPuntuation()[1] == -7);
		b.move(q, q.getCurrentPosition(), new Position("g8")); // takes knight
		assertTrue(b.getPuntuation()[0] == 10 && b.getPuntuation()[1] == -10);
		b.move(q, q.getCurrentPosition(), new Position("f8")); // takes bishop
		assertTrue(b.getPuntuation()[0] == 13 && b.getPuntuation()[1] == -13);
		b.move(q, q.getCurrentPosition(), new Position("f7"));
		b.move(q, q.getCurrentPosition(), new Position("e6"));
		b.move(q, q.getCurrentPosition(), new Position("d7")); // Takes pawn
		assertTrue(b.getPuntuation()[0] == 14 && b.getPuntuation()[1] == -14);
		b.move(q, q.getCurrentPosition(), new Position("d8")); // Takes queen
		assertTrue(b.getPuntuation()[0] == 23 && b.getPuntuation()[1] == -23);
		b.move(q, q.getCurrentPosition(), new Position("e8")); // Takes king
		assertTrue(b.getPuntuation()[0] == Integer.MAX_VALUE && b.getPuntuation()[1] == Integer.MIN_VALUE);
	}

	@Test
	public void testIsGameOver() throws Exception {
		System.out.println("[TESTING: Board->isGameOver()] : Did the game end?");
		
		Board b = new BoardImp();
		assertFalse(b.isGameOver());
		b.initBoard();
		assertFalse(b.isGameOver());
		
		
		Piece q = b.getPieceOn(new Position("d1"));
		Piece bi = b.getPieceOn(new Position("f1"));
		Piece p = b.getPieceOn(new Position("e2"));
		
		
		b.move(p, p.getCurrentPosition(), new Position("e4"));
		assertFalse(b.isGameOver());
		b.move(bi, bi.getCurrentPosition(), new Position("c4"));
		assertFalse(b.isGameOver());
		b.move(q, q.getCurrentPosition(), new Position("f3"));
		assertFalse(b.isGameOver());
		b.move(q, q.getCurrentPosition(), new Position("f7"));
		assertFalse(b.isGameOver());
		b.move(q, q.getCurrentPosition(), new Position("e8"));
		assertTrue(b.isGameOver());
	}
	
	@Test
	public void testPromote() throws Exception {
		System.out.println("[TESTING: Board->promote()] : any pawn got to the other side? shall I change it for a queen?");
		Board b = new BoardImp();
		b.initBoard();
		
		// White
		Piece p = b.getPieceOn(new Position("e2"));
		b.move(p, p.getCurrentPosition(), new Position("e4"));
		b.move(p, p.getCurrentPosition(), new Position("e5"));
		b.move(p, p.getCurrentPosition(), new Position("e6"));
		b.move(p, p.getCurrentPosition(), new Position("f7"));
		b.move(p, p.getCurrentPosition(), new Position("g8"));
		assertTrue(b.getPices(Player.WHITE).size() == 16 && b.getPieceOn(new Position("g8")) instanceof Queen);
		
		// Black
		b = new BoardImp();
		b.initBoard();
		p = b.getPieceOn(new Position("e7"));
		b.move(p, p.getCurrentPosition(), new Position("e5"));
		b.move(p, p.getCurrentPosition(), new Position("e4"));
		b.move(p, p.getCurrentPosition(), new Position("e3"));
		b.move(p, p.getCurrentPosition(), new Position("f2"));
		b.move(p, p.getCurrentPosition(), new Position("g1"));
		assertTrue(b.getPices(Player.BLACK).size() == 16 && b.getPieceOn(new Position("g1")) instanceof Queen);
		
		
	}

}
