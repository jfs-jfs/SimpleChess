package TQSPractica.models;

import static org.junit.Assert.*;

import org.junit.Test;

import TQSPractica.Player;

public class KnightTest {
	
	Position e1 = new Position("e1");
	Position e2 = new Position("e2");
	Position e3 = new Position("e3");
	Position e4 = new Position("e4");
	Position e5 = new Position("e5");
	Position e6 = new Position("e6");
	Position e7 = new Position("e7");
	Position e8 = new Position("e8");
	Position d6 = new Position("d6");
	Position d2 = new Position("d2");
	Position f6 = new Position("f6");
	Position f2 = new Position("f2");
	Position g3 = new Position("g3");
	Position g5 = new Position("g5");
	Position c3 = new Position("c3");
	Position c5 = new Position("c5");
	
	Position a1 = new Position("a1");
	Position b3 = new Position("b3");
	Position c2 = new Position("c2");
	
	Position a8 = new Position("a8");
	Position b6 = new Position("b6");
	Position c7 = new Position("c7");
	
	Position h8 = new Position("h8");
	Position g6 = new Position("g6");
	Position f7 = new Position("f7");
	
	
	Position h1 = new Position("h1");
	
	

	@Test
	public void testToString() throws Exception {
		System.out.println("[TESTING: Knight->toString()] : String representation of the piece and it's position");
		MockBoard b = new MockBoard();
		
		// Good one
		Knight n = new Knight(b, new Position("e4"), Player.BLACK);
		assertEquals("Ne4", n.toString());
		
		// Bad one -- position
		Position bad = new Position("lskdjlsk");
		try {
			Knight n2 = new Knight(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		bad = null;
		try {
			Knight n3 = new Knight(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		// A bad one (board)
		b = null;
		try {
			Knight n4 = new Knight(b, e4, Player.BLACK);
			fail("Should throw exception a bad board.");
		} catch (Exception e) {
			// It worked
		}
	}

	@Test
	public void testGetPossibleMoves() throws Exception {
		System.out.println("[TESTING: Knight->GetPossibleMoves()] : Where can a piece can move");
		MockBoard b = new MockBoard();
		Knight n;
		
		// Empty board
		// -- center
		n = new Knight(b, e4, Player.WHITE);
		Position[] exp = {
				d6, f6, c5,c3,
				d2, f2, g3, g5
		};
		Position[] res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		// -- Corners
		n = new Knight(b, a1, Player.WHITE); // Bottom left
		exp = new Position[] {
				b3,c2
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		n = new Knight(b, h1, Player.WHITE); // Bottom right
		exp = new Position[] {
				g3,f2
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		n = new Knight(b, a8, Player.WHITE); // Top left
		exp = new Position[] {
				b6,c7
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		n = new Knight(b, h8, Player.WHITE); // Top right
		exp = new Position[] {
				g6,f7
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
		// Blocked by same pieces
		n = new Knight(b, e4, Player.WHITE);
		b.setPiece(new Pawn(b, f6, Player.WHITE));
		b.setPiece(new Pawn(b, d6, Player.WHITE));
		b.setPiece(new Pawn(b, g3, Player.WHITE));
		exp = new Position[] {
				c5,c3,
				d2, f2, g5
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		
		// Taking pieces
		n = new Knight(b, e4, Player.WHITE);
		b.setPiece(new Pawn(b, c5, Player.BLACK));
		b.setPiece(new Pawn(b, c3, Player.BLACK));
		b.setPiece(new Pawn(b, f2, Player.BLACK));
		exp = new Position[] {
				d6, f6, c5,c3,
				d2, f2, g3, g5
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		
		// Mixed
		n = new Knight(b, e4, Player.WHITE);
		b.setPiece(new Pawn(b, c5, Player.BLACK));
		b.setPiece(new Pawn(b, c3, Player.BLACK));
		b.setPiece(new Pawn(b, f2, Player.BLACK));
		b.setPiece(new Pawn(b, f6, Player.WHITE));
		b.setPiece(new Pawn(b, d6, Player.WHITE));
		b.setPiece(new Pawn(b, g3, Player.WHITE));
		exp = new Position[] {
				c5,c3,
				d2, f2, g5
		};
		res = n.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
	}

	@Test
	public void testGetPieceValue() throws Exception {
		System.out.println("[TESTING: Knight->GetPieceValue()] : Value of the piece");
		Knight n = new Knight(new MockBoard(), e4, Player.BLACK);
		assertEquals(3, n.getPieceValue());
	}
	
	/**
	 * NOTE: There is no moveTo() test as I decided to move the functionality to the Piece class.
	 * As only the Pawn has a routine that differs from the other pieces.
	 * This shall be tested in the PieceTest.
	 */

}
