package TQSPractica.models;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import TQSPractica.Player;

public class PawnTest {
	
	static boolean comaprePositionsArray(Position[] a, Position[] b) {
		
		if (a.length != b.length) {
			return false;
		}
		
		int c = a.length;
		
		for (int i = 0; i < b.length; i++) {
			
			for (int j = 0; j < a.length; j++) {
				if (b[i].equals(a[j])) {
					c--;
				}
			}
		}
		return c == 0;
		
	}

	@Test
	public void testToString() throws Exception {
		System.out.println("[TESTING: Pawn->toString()] : String representation of the piece and it's position");
		// A good one
		Position pos = new Position("e4");
		Board b = new MockBoard();
		Pawn p = new Pawn(b, pos, Player.BLACK);
		assertEquals("e4", p.toString());
		
		
		// A bad one
		Position pos2 = new Position("e9");
		try {
			Pawn p2 = new Pawn(b, pos2, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
		
		Position pos3 = null;
		try {
			Pawn p2 = new Pawn(b, pos3, Player.BLACK);
			fail("Should throw exception a bad position.");
		} catch (Exception e) {
			// It worked
		}
	}

	@Test
	public void testMoveTo() throws Exception {
		System.out.println("[TESTING: Pawn->MoveTo()] : A valid next position given the Board");
		MockBoard board = new MockBoard();
		Piece blocker;						// Doesn't matter which
		Position e2 = new Position("e2");
		Position e3 = new Position("e3");
		Position e4 = new Position("e4");
		Position e5 = new Position("e5");
		Position e6 = new Position("e6");
		Position d6 = new Position("d6");
		Position d5 = new Position("d5");
		Position d4 = new Position("d4");
		Position f4 = new Position("f4");
		Position f5 = new Position("f5");
		Position f6 = new Position("f6");

		
		System.out.println("[TESTING: Pawn->MoveTo()] : Normal");
		// Should be able to move one tile forward
		Pawn pawn = new Pawn(board,e2, Player.WHITE);
		assertTrue(pawn.moveTo(e3));
		pawn = new Pawn(board,e4, Player.BLACK);
		assertTrue(pawn.moveTo(e3));
		
		
		System.out.println("[TESTING: Pawn->MoveTo()] : Normal blocked");
		// Shouldn't be any move
		// Shouldn't check end of board as it will get changed to another piece if it reaches.
		// The responsibility of checking this will be given to the Board class
		
		// Blocking other player
		blocker = new Pawn(board, e4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e4));
		board.clean();
		blocker = new Pawn(board, e2, Player.WHITE);
		pawn = new Pawn(board, e3, Player.BLACK);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e2));
		board.clean();
		
		// Blocking yourself
		blocker = new Pawn(board, e4, Player.WHITE);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e4));
		board.clean();
		
		
		System.out.println("[TESTING: Pawn->MoveTo()] : Taking");
		// Left or right one tile, with or without a blacking piece
		
		// Can take other player pieces left black and white
		blocker = new Pawn(board, d4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(blocker);
		assertTrue(pawn.moveTo(d4));
		board.clean();
		blocker = new Pawn(board, d4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(pawn);
		assertTrue(blocker.moveTo(e3));
		board.clean();
		
		// Can take other player pieces right
		blocker = new Pawn(board, f4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(blocker);
		assertTrue(pawn.moveTo(f4));
		board.clean();
		blocker = new Pawn(board, f4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(pawn);
		assertTrue(blocker.moveTo(e3));
		board.clean();
		
		// Can not take same team
		blocker = new Pawn(board, f4, Player.WHITE);
		pawn = new Pawn(board,e3, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(f4));
		board.clean();
		blocker = new Pawn(board, f4, Player.BLACK);
		pawn = new Pawn(board,e3, Player.BLACK);
		board.setPiece(pawn);
		assertFalse(blocker.moveTo(e3));
		board.clean();
		
		
		System.out.println("[TESTING: Pawn->MoveTo()] : First move");
		// Can move 2 spaces forward as an extra to normal
		
		// First move				---- WHITE
		pawn = new Pawn(board, e2, Player.WHITE);
		assertTrue(pawn.moveTo(e4));
		// Not frist move
		assertFalse(pawn.moveTo(e6));
		// First move				---- BLACK
		pawn = new Pawn(board, e6, Player.BLACK);
		assertTrue(pawn.moveTo(e4));
		// Not frist move
		assertFalse(pawn.moveTo(e2));
		
		System.out.println("[TESTING: Pawn->MoveTo()] : First move blocked");
		// Blocking the second square or the first one
		blocker = new Pawn(board, e4, Player.WHITE);
		pawn = new Pawn(board, e2, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e4));
		board.clean();
		blocker = new Pawn(board, e4, Player.BLACK);
		pawn = new Pawn(board, e2, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e4));
		board.clean();
		blocker = new Pawn(board, e2, Player.BLACK);
		pawn = new Pawn(board, e4, Player.BLACK);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e2));
		board.clean();
		blocker = new Pawn(board, e2, Player.WHITE);
		pawn = new Pawn(board, e4, Player.BLACK);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e2));
		board.clean();

		
		System.out.println("[TESTING: Pawn->MoveTo()] : Taking en passant");
		// Special case, can take a pawn that has made its first move and chose
		// to move 2 squares and I have it by side
		blocker = new Pawn(board, d6, Player.BLACK);
		pawn = new Pawn(board, e4, Player.WHITE);
		board.setPiece(blocker);
		blocker.moveTo(d4);
		assertTrue(pawn.moveTo(d5));
		board.clean();
		blocker = new Pawn(board, f6, Player.BLACK);
		pawn = new Pawn(board, e4, Player.WHITE);
		board.setPiece(blocker);
		blocker.moveTo(f4);
		assertTrue(pawn.moveTo(f5));
		board.clean();
		// -- now with BLACK
		blocker = new Pawn(board, e2, Player.WHITE);
		pawn = new Pawn(board, d4, Player.BLACK);
		board.setPiece(blocker);
		blocker.moveTo(e4);
		assertTrue(pawn.moveTo(e3));
		board.clean();
		blocker = new Pawn(board, e2, Player.WHITE);
		pawn = new Pawn(board, f4, Player.BLACK);
		board.setPiece(blocker);
		blocker.moveTo(e4);
		assertTrue(pawn.moveTo(e3));
		board.clean();
		// Another piece
		blocker = new King(board, d4, Player.BLACK);
		pawn = new Pawn(board, e4, Player.WHITE);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(d5));
		board.clean();
		blocker = new Queen(board, e4, Player.WHITE);
		pawn = new Pawn(board, f4, Player.BLACK);
		board.setPiece(blocker);
		assertFalse(pawn.moveTo(e3));
		board.clean();
		
		System.out.println("[TESTING: Pawn->MoveTo()] : wrong places");
		// Where it is, backwards, non existent positions
		pawn = new Pawn(board, f4, Player.BLACK);
		assertFalse(pawn.moveTo(f4));
		assertFalse(pawn.moveTo(f5));
		pawn = new Pawn(board, e4, Player.WHITE);
		assertFalse(pawn.moveTo(e3));
		assertFalse(pawn.moveTo(null));
		
		// Extra test for the bug found in commit #1f30371a994aab42b85aced0ca962b105b70688b
		pawn = new Pawn(board, e4, Player.WHITE);
		Pawn pawn3 = new Pawn(board, e5, Player.BLACK);
		board.setPiece(pawn3);
		board.setPiece(pawn);
		board.setPiece(new Pawn(board,d4, Player.WHITE));
		assertFalse(pawn.moveTo(e5));
		assertTrue(pawn3.moveTo(d4));
		assertTrue(pawn.moveTo(e5));
	}

	@Test
	public void testGetPossibleMoves() throws Exception {
		System.out.println("[TESTING: Pawn->GetPieceValue()] : Value of the piece");
		
		// Starting
		MockBoard b = new MockBoard();
		Position e2 = new Position("e2");
		Position e3 = new Position("e3");
		Position e4 = new Position("e4");
		Position e5 = new Position("e5");
		Position e6 = new Position("e6");
		Position d6 = new Position("d5");
		Position d5 = new Position("d6");
		Position d3 = new Position("d3");
		Position f3 = new Position("f3");
		Position f4 = new Position("f4");
		Position f5 = new Position("f5");
		Position f6 = new Position("f6");
		Pawn p = new Pawn(b, e2, Player.WHITE);
		
		Position[] res = p.getPossibleMoves();
		Position[] exp = {new Position("e3"), new Position("e4")};
		assertTrue(PawnTest.comaprePositionsArray(res, exp));
		
		// Taking and normal
		b.setPiece(new Pawn(b, d3, Player.WHITE));
		
		b.setPiece(new Pawn(b, f3, Player.WHITE));
		p = new Pawn(b, e4, Player.BLACK);
		res = p.getPossibleMoves();
		exp = new Position[] {d3, f3, e3, e2};
		assertTrue(PawnTest.comaprePositionsArray(res, exp));
		b.clean();

	}

	@Test
	public void testGetPieceValue() throws Exception {
		System.out.println("[TESTING: Pawn->GetPieceValue()] : Value of the piece");
		Pawn p = new Pawn(
					new MockBoard(),
					new Position("e3"),
					Player.WHITE
				);
		assertEquals(1, p.getPieceValue());
	}
	
	@Test
	public void testUpdate() throws Exception {
		System.out.println("[TESTING: Pawn->Update()] : En passant");
		Pawn p = new Pawn(
					new MockBoard(),
					new Position("e2"),
					Player.WHITE
				);
		assertFalse(p.getEnPassant());
		p.moveTo(new Position("e4"));
		assertTrue(p.getEnPassant());
		p.update(new MockBoard(), 1);
		assertFalse(p.getEnPassant());
		p.moveTo(new Position("e5"));
		assertFalse(p.getEnPassant());
		p.update(new MockBoard(), 1);
		assertFalse(p.getEnPassant());
		
		
	}

}
