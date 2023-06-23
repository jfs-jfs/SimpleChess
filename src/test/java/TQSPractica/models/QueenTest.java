package TQSPractica.models;

import static org.junit.Assert.*;

import org.junit.Test;

import TQSPractica.Player;

public class QueenTest {
	
	Position a4 = new Position("a4");
	Position b4 = new Position("b4");
	Position c4 = new Position("c4");
	Position g4 = new Position("g4");
	Position h4 = new Position("h4");
	
	Position a3 = new Position("a3");
	Position a5 = new Position("a5");
	Position a6 = new Position("a6");
	
	Position c1 = new Position("c1");
	Position d1 = new Position("d1");
	Position f1 = new Position("f1");
	
	Position c8 = new Position("c8");
	Position d8 = new Position("d8");
	Position f8 = new Position("f8");

	Position h3 = new Position("h3");
	Position h5 = new Position("h5");
	Position h6 = new Position("h6");
	
	Position e1 = new Position("e1");
	Position e2 = new Position("e2");
	Position e3 = new Position("e3");
	Position e4 = new Position("e4");
	Position e5 = new Position("e5");
	Position e6 = new Position("e6");
	Position e7 = new Position("e7");
	Position e8 = new Position("e8");
	Position f3 = new Position("f3");
	Position f5 = new Position("f5");
	Position f4 = new Position("f4");
	Position d3 = new Position("d3");
	Position d5 = new Position("d5");
	Position d4 = new Position("d4");
	
	Position c6 = new Position("c6");
	Position g6 = new Position("g6");
	Position c2 = new Position("c2");
	Position f6 = new Position("f6");
	Position c3 = new Position("c3");
	
	Position a1 = new Position("a1");
	Position a2 = new Position("a2");
	Position b2 = new Position("b2");
	Position b1 = new Position("b1");
	
	Position a8 = new Position("a8");
	Position a7 = new Position("a7");
	Position b7 = new Position("b7");
	Position b8 = new Position("b8");
	
	Position h1 = new Position("h1");
	Position h2 = new Position("h2");
	Position g2 = new Position("g2");
	Position g1 = new Position("g1");
	
	Position h8 = new Position("h8");
	Position h7 = new Position("h7");
	Position g7 = new Position("g7");
	Position g8 = new Position("g8");

	@Test
	public void testToString() throws Exception {
		System.out.println("[TESTING: Queen->toString()] : String representation of the piece and it's position");
		MockBoard b = new MockBoard();
		
		Queen n = new Queen(b, new Position("e4"), Player.BLACK);
		assertEquals("Qe4", n.toString());
	}

	@Test
	public void testQueen() {
		System.out.println("[TESTING: Queen->Queen()] : Testing bad parameters in constructor");
		// Bad one -- position
		MockBoard b = new MockBoard();
		Position bad = new Position("lskdjlsk");
		try {
			Queen n2 = new Queen(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		bad = null;
		try {
			Queen n3 = new Queen(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		// A bad one (board)
		b = null;
		try {
			Queen n4 = new Queen(b, e4, Player.BLACK);
			fail("Should throw exception a bad board.");
		} catch (Exception e) {
			// It worked
		}
	}

	@Test
	public void testGetPossibleMoves() throws Exception {
		System.out.println("[TESTING: Queen->GetPossibleMoves()] : Where can the piece can move");
		Queen q;
		MockBoard b = new MockBoard();
		Position[] exp;
		Position[] res;
		
		// Empty board
		q = new Queen(b, e4, Player.BLACK);   // Center
		exp = new Position[] {
				d5, c6, b7, a8,
				f5, g6, h7,
				d3, c2, b1,
				f3, g2, h1,
				e5, e6,e7,e8,
				e3, e2, e1,
				a4, b4,c4,d4,
				f4, g4, h4
		};
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		q = new Queen(b, a8, Player.BLACK);   // Corner -top left
		exp = new Position[] {
				d5, c6, b7, e4,
				f3, g2, h1,
				a7,a6,a5,a4,a3,a2,a1,
				b8,c8,d8,e8,f8,g8,h8
		};
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		q = new Queen(b, h1, Player.BLACK);   // Corner -bottom right
		exp = new Position[] {
				d5, c6, b7, a8,
				f3, g2, e4,
				h2,h3,h4,h5,h6,h7,h8,
				a1,b1,c1,d1,e1,f1,g1
		};
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		q = new Queen(b, h8, Player.BLACK);   // Corner -top left
		exp = new Position[] {
				g7, f6, e5, d4,
				c3, b2, a1,
				h1,h2,h3,h4,h5,h6,h7,
				a8,b8,c8,d8,e8,f8,g8
		};
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		q = new Queen(b, a1, Player.BLACK);   // Corner -bottom left
		exp = new Position[] {
				g7, f6, e5, d4,
				c3, b2, h8,
				a2,a3,a4,a5,a6,a7,a8,
				b1,c1,d1,e1,f1,g1,h1
		};
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
		// Filled board -- same team
		q = new Queen(b, e4, Player.BLACK);
		exp = new Position[] {
				f5, g6, h7,
				d3, c2,
				f3,
				e5, e6,e7,e8,
				e3, e2, e1,
				a4, b4,c4,d4
		};
		b.setPiece(new Pawn(b, d5, Player.BLACK));
		b.setPiece(new Queen(b, g2, Player.BLACK));
		b.setPiece(new King(b, b1, Player.BLACK));
		b.setPiece(new Pawn(b, f4, Player.BLACK));
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		// Filled board -- Opposite team
		q = new Queen(b, e4, Player.WHITE);
		exp = new Position[] {
				f5, g6, h7, d5,
				d3, c2, g2, b1,
				f3, f4,
				e5, e6,e7,e8,
				e3, e2, e1,
				a4, b4,c4,d4
		};
		b.setPiece(new Pawn(b, d5, Player.BLACK));
		b.setPiece(new Queen(b, g2, Player.BLACK));
		b.setPiece(new King(b, b1, Player.BLACK));
		b.setPiece(new Pawn(b, f4, Player.BLACK));
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		// Filled board -- Mixed
		q = new Queen(b, e4, Player.WHITE);
		exp = new Position[] {
				d5,
				f5, g6,
				d3, c2, b1,
				f3,
				e5, e6,e7,e8,
				e3, e2, e1,
				a4, b4,c4,d4,
		};
		b.setPiece(new Queen(b, d5, Player.BLACK));
		b.setPiece(new Queen(b, g2, Player.WHITE));
		b.setPiece(new Queen(b, b1, Player.BLACK));
		b.setPiece(new Queen(b, h7, Player.WHITE));
		b.setPiece(new Pawn(b, f4, Player.WHITE));
		res = q.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
	}

	@Test
	public void testGetPieceValue() throws Exception {
		System.out.println("[TESTING: Queen->GetPieceValue()] : Value of the piece");
		Queen n = new Queen(new MockBoard(), e4, Player.BLACK);
		assertEquals(9, n.getPieceValue());
	}

}
