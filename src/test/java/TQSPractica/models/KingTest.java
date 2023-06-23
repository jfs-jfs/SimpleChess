package TQSPractica.models;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.net.httpserver.Authenticator.Success;

import TQSPractica.Player;

public class KingTest {

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
		System.out.println("[TESTING: King->toString()] : String representation of the piece and it's position");
		MockBoard b = new MockBoard();
		
		King n = new King(b, new Position("e4"), Player.BLACK);
		assertEquals("Ke4", n.toString());
	}

	@Test
	public void testKing() {
		System.out.println("[TESTING: King->King()] : Constructor");
		// Bad one -- position
		MockBoard b = new MockBoard();
		Position bad = new Position("lskdjlsk");
		try {
			King n2 = new King(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		bad = null;
		try {
			King n3 = new King(b, bad, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		// A bad one (board)
		b = null;
		try {
			King n4 = new King(b, e4, Player.BLACK);
			fail("Should throw exception a bad board.");
		} catch (Exception e) {
			// It worked
		}
	}

	@Test
	public void testGetPossibleMoves() throws Exception {
		System.out.println("[TESTING: King->GetPossibleMoves()] : Where can a piece can move");
		King k;
		MockBoard b = new MockBoard();
		
		// Empty board -- center
		k = new King(b, e4, Player.BLACK);
		Position[] exp = {
				e3, f3, d3, d4,
				f4, f5, e5, d5
		};
		Position[] res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
		// Empty board -- corners
		k = new King(b, a1, Player.WHITE); 			// -- bottom left
		exp = new Position[] {
				a2,b2,b1
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		k = new King(b, h1, Player.BLACK); 			// -- bottom right
		exp = new Position[] {
				h2,g2,g1
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		k = new King(b, a8, Player.WHITE); 			// -- top left
		exp = new Position[] {
				a7, b7, b8
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		k = new King(b, h8, Player.BLACK); 			// -- top right
		exp = new Position[] {
				h7, g8, g7
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		
		// Blocked same team
		b.setPiece(new Pawn(b, f3, Player.BLACK));
		b.setPiece(new Pawn(b, d3, Player.BLACK));
		b.setPiece(new Pawn(b, d5, Player.BLACK));
		b.setPiece(new Pawn(b, f5, Player.BLACK));
		k = new King(b, e4, Player.BLACK);
		exp = new Position[] {
				e3, d4,
				f4, e5
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		
		// Blocked opposite team
		b.setPiece(new Pawn(b, f3, Player.BLACK));
		b.setPiece(new Pawn(b, d3, Player.BLACK));
		b.setPiece(new Pawn(b, d5, Player.BLACK));
		b.setPiece(new Pawn(b, f5, Player.BLACK));
		k = new King(b, e4, Player.WHITE);
		exp = new Position[] {
				e3, f3, d3, d4,
				f4, f5, e5, d5
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();
		// Blocked mixed
		b.setPiece(new Pawn(b, f3, Player.BLACK));
		b.setPiece(new Pawn(b, d3, Player.WHITE));
		b.setPiece(new Pawn(b, d5, Player.BLACK));
		b.setPiece(new Pawn(b, f5, Player.WHITE));
		k = new King(b, e4, Player.WHITE);
		exp = new Position[] {
				e3, f3, d4,
				f4, e5, d5
		};
		res = k.getPossibleMoves();
		assertTrue(PawnTest.comaprePositionsArray(exp, res));
		b.clean();

	}

	@Test
	public void testGetPieceValue() throws Exception {
		System.out.println("[TESTING: King->GetPieceValue()] : Value of the piece");
		King n = new King(new MockBoard(), e4, Player.BLACK);
		assertEquals(Integer.MAX_VALUE, n.getPieceValue());
	}

}
