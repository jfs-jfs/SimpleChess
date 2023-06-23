package TQSPractica.models;

import static org.junit.Assert.*;

import org.junit.Test;

import TQSPractica.Player;

public class RookTest {
	
	Position e1 = new Position("e1");
	Position e2 = new Position("e2");
	Position e3 = new Position("e3");
	Position e4 = new Position("e4");
	Position e5 = new Position("e5");
	Position e6 = new Position("e6");
	Position e7 = new Position("e7");
	Position e8 = new Position("e8");
	Position a4 = new Position("a4");
	Position b4 = new Position("b4");
	Position c4 = new Position("c4");
	Position d4 = new Position("d4");
	Position f4 = new Position("f4");
	Position g4 = new Position("g4");
	Position h4 = new Position("h4");
	
	Position a1 = new Position("a1");
	Position a2 = new Position("a2");
	Position a3 = new Position("a3");
	Position a5 = new Position("a5");
	Position a6 = new Position("a6");
	Position a7 = new Position("a7");
	Position a8 = new Position("a8");
	
	Position b1 = new Position("b1");
	Position c1 = new Position("c1");
	Position d1 = new Position("d1");
	Position f1 = new Position("f1");
	Position g1 = new Position("g1");
	Position h1 = new Position("h1");
	
	Position b8 = new Position("b8");
	Position c8 = new Position("c8");
	Position d8 = new Position("d8");
	Position f8 = new Position("f8");
	Position g8 = new Position("g8");
	Position h8 = new Position("h8");

	Position h2 = new Position("h2");
	Position h3 = new Position("h3");
	Position h5 = new Position("h5");
	Position h6 = new Position("h6");
	Position h7 = new Position("h7");
	
	Position f5 = new Position("f5");
	Position f3 = new Position("f3");
	Position d5 = new Position("d5");
	Position d3 = new Position("d3");

	@Test
	public void testToString() throws Exception {
		System.out.println("[TESTING: Rook->toString()] : String representation of the piece and it's position");
		
		// A good one
		Position e4 = new Position("e4");
		Board b = new MockBoard();
		Rook r = new Rook(b, e4, Player.BLACK);
		assertEquals("Re4", r.toString());
		
		// A bad one (position)
		Position bad = new Position("lskdjlsk");
		try {
			Rook r2 = new Rook(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		bad = null;
		try {
			Rook r3 = new Rook(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		// A bad one (board)
		b = null;
		try {
			Rook r4 = new Rook(b, e4, Player.BLACK);
			fail("Should throw exception a bad board.");
		} catch (Exception e) {
			// It worked
		}
	}

	@Test
	public void testMoveTo() throws Exception {
		System.out.println("[TESTING: Rook->MoveTo()] : Moving to a square");
		Rook r;
		MockBoard b = new MockBoard();
		Piece blocker;
		// Empty board -- Good moves
		r = new Rook(b, e4, Player.WHITE);
		assertTrue(r.moveTo(h4));
		assertTrue(r.moveTo(h1));
		assertTrue(r.moveTo(a1));
		assertTrue(r.moveTo(a8));
		assertTrue(r.moveTo(h8));
		// Empty board -- bad moves
		r = new Rook(b, e4, Player.WHITE);
		assertFalse(r.moveTo(d5));
		assertFalse(r.moveTo(d3));
		assertFalse(r.moveTo(f5));
		assertFalse(r.moveTo(f3));
		
		// Populated board -- Same team
		b.setPiece(new Queen(b, e6, Player.WHITE));
		r = new Rook(b, e4, Player.WHITE);
		assertFalse(r.moveTo(e8));
		assertFalse(r.moveTo(e6));
		assertTrue(r.moveTo(e5));
		b.clean();
		
		// Populated board -- Opposite team
		b.setPiece(new Queen(b, e6, Player.WHITE));
		r = new Rook(b, e4, Player.BLACK);
		assertFalse(r.moveTo(e8));
		assertTrue(r.moveTo(e6));
		assertTrue(r.moveTo(e5));
		b.clean();
		
		// Populated board -- Mixed
		b.setPiece(new Queen(b, e6, Player.BLACK));
		b.setPiece(new Queen(b, e3, Player.WHITE));
		r = new Rook(b, e4, Player.BLACK);
		assertFalse(r.moveTo(e8));
		assertFalse(r.moveTo(e6));
		assertTrue(r.moveTo(e5));
		assertTrue(r.moveTo(e3));
		b.clean();
		
		// Non sense
		r = new Rook(b, e4, Player.BLACK);
		assertFalse(r.moveTo(null));
		assertFalse(r.moveTo(e4));
	}

	@Test
	public void testGetPossibleMoves() throws Exception {
		System.out.println("[TESTING: Rook->GetPossibleMoves()] : Future moves a piece can do");		
		Rook r;
		MockBoard b = new MockBoard();
		Piece blocker;
		
		
		// Empty board --- Center
		r = new Rook(b, e4, Player.WHITE);
		Position[] exp = {
				e1,e2,e3,e5,e6,e7,e8,
				a4,b4,c4,d4,f4,g4,h4
		};
		Position[] res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
		// Empty board --- Corners
		// Bottom left
		r = new Rook(b, a1, Player.BLACK);
		exp = new Position[] {
				a2,a3,a4,a5,a6,a7,a8,
				b1,c1,d1,e1,f1,g1,h1
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Bottom right
		r = new Rook(b, h1, Player.WHITE);
		exp = new Position[] {
				h2,h3,h4,h5,h6,h7,h8,
				b1,c1,d1,e1,f1,g1,a1
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Top left
		r = new Rook(b, a8, Player.BLACK);
		exp = new Position[] {
				a2,a3,a4,a5,a6,a7,a1,
				b8,c8,d8,e8,f8,g8,h8
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Top right
		r = new Rook(b, h8, Player.WHITE);
		exp = new Position[] {
				h2,h3,h4,h5,h6,h7,h1,
				b8,c8,d8,e8,f8,g8,a8
		};
		
		
		// Populated board --- Same team
		// One side
		blocker = new Pawn(b, f8, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, a8, Player.BLACK);
		exp = new Position[] {
				a2,a3,a4,a5,a6,a7,a1,
				b8,c8,d8,e8
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Two sides
		blocker = new Queen(b, d8, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, e8, Player.BLACK);
		exp = new Position[] {
				e1,e2,e3,e4,e5,e6,e7
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Three sides
		blocker = new King(b, e7, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, e8, Player.BLACK);
		exp = new Position[] {};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		// Every side
		b.setPiece(new Pawn(b, e5, Player.BLACK));
		b.setPiece(new Pawn(b, e3, Player.BLACK));
		b.setPiece(new Pawn(b, d4, Player.BLACK));
		b.setPiece(new Pawn(b, f4, Player.BLACK));
		r = new Rook(b, e4, Player.BLACK);
		exp = new Position[] {};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		
		// Populated board --- Opposite team
		// One side
		blocker = new Pawn(b, f8, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, a8, Player.WHITE);
		exp = new Position[] {
				a2,a3,a4,a5,a6,a7,a1,
				b8,c8,d8,e8,f8
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Two sides
		blocker = new Queen(b, d8, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, e8, Player.WHITE);
		exp = new Position[] {
				e1,e2,e3,e4,e5,e6,e7,
				d8,f8
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// Three sides
		blocker = new King(b, e7, Player.BLACK);
		b.setPiece(blocker);
		r = new Rook(b, e8, Player.WHITE);
		exp = new Position[] {
				e7,d8,f8
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		// Every side
		b.setPiece(new Pawn(b, e5, Player.BLACK));
		b.setPiece(new Pawn(b, e3, Player.WHITE));
		b.setPiece(new Pawn(b, d4, Player.BLACK));
		b.setPiece(new Pawn(b, f4, Player.WHITE));
		r = new Rook(b, e4, Player.BLACK);
		exp = new Position[] {
				e3, f4
		};
		res = r.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
	}

	@Test
	public void testGetPieceValue() throws Exception {
		System.out.println("[TESTING: Rook->GetPieceValue()] : Value of the piece");
		Rook r = new Rook(
					new MockBoard(),
					new Position("e3"),
					Player.WHITE
				);
		assertEquals(5, r.getPieceValue());
	}

}
